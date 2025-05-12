package controller;

import java.io.BufferedReader;
import java.io.IOException;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        MissionContext ctx = (MissionContext) session.getAttribute("ctx");

        if (ctx == null || ctx.getCarrier() == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Context or Carrier not found\"}");
            return;
        }
        if (ctx.getTargetPoints() != null) {
            session.setAttribute("targetPoints", ctx.getTargetPoints());
        }

        // JSON から読み込み
        BufferedReader reader = request.getReader();
        String json = reader.lines().collect(Collectors.joining());
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Double> coords = mapper.readValue(json, new TypeReference<>() {});

        double lat = coords.getOrDefault("lat", 0.0);
        double lon = coords.getOrDefault("lon", 0.0);

        // キャリア座標更新
        ctx.getCarrier().setLatitude(lat);
        ctx.getCarrier().setLongitude(lon);
        session.setAttribute("ctx", ctx);
        System.out.println("Session updated with new Carrier coordinates.");

        // 必要なら DB 反映もここで呼び出し可能
        // new MissionDataDAO().updateCarrierPosition(ctx.getFlightPlan().getId(), lat, lon);

        response.setContentType("application/json");
        response.getWriter().write("{\"status\":\"ok\"}");
    }
}