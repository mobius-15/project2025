package controller;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import mission.MissionContext;

/**
 * Servlet implementation class MapServlet
 */
@WebServlet("/MapServlet")
public class MapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		HttpSession session = request.getSession();
		MissionContext ctx = (MissionContext) session.getAttribute("ctx");

		if (ctx == null || ctx.getCarrier() == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("{\"status\":\"error\",\"message\":\"Context or Carrier not found\"}");
			return;
		}

		BufferedReader reader = request.getReader();
		String json = reader.lines().collect(Collectors.joining());
		ObjectMapper mapper = new ObjectMapper();
		if (json.contains("\"image\"")) {
			try {
				Map<String, String> data = mapper.readValue(json, new TypeReference<>() {
				});
				String base64Image = data.get("image");
				if (base64Image == null || !base64Image.startsWith("data:image/png;base64,")) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.getWriter().write("{\"status\":\"error\",\"message\":\"Invalid image data\"}");
					return;
				}
				base64Image = base64Image.replace("data:image/png;base64,", "");
				byte[] imageBytes = Base64.getDecoder().decode(base64Image);

				String imagePath = getServletContext().getRealPath("/map.png");

				try (FileOutputStream fos = new FileOutputStream(imagePath)) {
					fos.write(imageBytes);
				}

				// パスをセッションに保存（ExportPDF用）
				session.setAttribute("mapImagePath", imagePath);
				System.out.println("Map image saved to: " + imagePath);

				response.getWriter().write("{\"status\":\"ok\",\"message\":\"Map image saved\"}");
				return;

			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().write("{\"status\":\"error\",\"message\":\"Image save failed\"}");
				return;
			}
		} else {
			// --- 2B. 座標データ（Carrier）更新の場合 ---
			try {
				Map<String, Double> coords = mapper.readValue(json, new TypeReference<>() {
				});
				double lat = coords.getOrDefault("lat", 0.0);
				double lon = coords.getOrDefault("lon", 0.0);

				ctx.getCarrier().setLatitude(lat);
				ctx.getCarrier().setLongitude(lon);

				if (ctx.getTargetPoints() != null) {
					session.setAttribute("targetPoints", ctx.getTargetPoints());
				}

				session.setAttribute("ctx", ctx);
				System.out.println("Session updated with new Carrier coordinates.");

				response.getWriter().write("{\"status\":\"ok\"}");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("{\"status\":\"error\",\"message\":\"Coordinate update failed\"}");
				return;
			}

		}
	}
}