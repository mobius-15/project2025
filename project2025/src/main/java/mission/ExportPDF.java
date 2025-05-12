package mission;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import aircrafts.FA_18F;
import aircrafts.Loadout;
import flightplan.Waypoint;
import weapons.Weapons;

@WebServlet("/ExportPDF")
public class ExportPDF extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String action = request.getParameter("action");
    	if (action != null && !action.isEmpty()) {
    	    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action for ExportPDF.");
    	    return;
    	}

      try {  MissionContext ctx = (MissionContext) request.getSession().getAttribute("ctx");
        if (ctx == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mission data not found.");
            return;
        }
        
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=mission_report.pdf");

        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph("Created At: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
        document.add(new Paragraph("Mission ID: " + ctx.getFlightPlan().getId()));
        

        document.add(new Paragraph("Mission Report").setBold().setFontSize(16));
        document.add(new Paragraph("Mission Type: " + ctx.getMissionType()));
        document.add(new Paragraph("Aircraft: " + ctx.getAircraft().getName()));
        document.add(new Paragraph("Carrier: " + (ctx.getCarrier() != null ? ctx.getCarrier().getName() : "N/A")));
        document.add(new Paragraph(String.format("Total Flight Time: %.1f min", ctx.getTotalFlightTime() / 60)));
        document.add(new Paragraph(" "));
        
   

        Table table = new Table(UnitValue.createPercentArray(7)).useAllAvailableWidth();
        table.addHeaderCell("WP");
        table.addHeaderCell("Alt (ft)");
        table.addHeaderCell("TAS (kt)");
        table.addHeaderCell("Mach");
        table.addHeaderCell("Dist (nm)");
        table.addHeaderCell("Time (min)");
        table.addHeaderCell("Fuel (lb)");
        
        List<Waypoint> wps = ctx.getFlightPlan().getWaypoints();
        List<Double> fuels = ctx.getSegmentFuelList();

        for (int i = 0; i < wps.size(); i++) {
            Waypoint wp = wps.get(i);
            table.addCell(String.valueOf(i + 1));
            table.addCell(String.valueOf(wp.getAltitude()));
            table.addCell(String.valueOf(wp.getSpeed()));
            table.addCell(String.format("%.2f", wp.getMach()));
            table.addCell(String.valueOf(wp.getDistance()));
            table.addCell(String.format("%.1f", wp.getSegmentTime()));
            table.addCell(String.format("%.1f", fuels.get(i)));
        }

        document.add(table);
        document.add(new Paragraph("Mission Targets: "));
        document.add(new Paragraph("Targets").setBold().setFontSize(14));
        
    if (ctx.getTargetPoints() != null) {


        Table targetTable = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
        targetTable.addHeaderCell("Name");
        targetTable.addHeaderCell("Latitude");
        targetTable.addHeaderCell("Longitude");
//        targetTable.addHeaderCell("Radius (nm)");

        for (Target tgt : ctx.getTargetPoints()) {
            targetTable.addCell(tgt.getName()!= null ? tgt.getName() : "Unknown");
            targetTable.addCell(String.format("%.4f", tgt.getLat()));
            targetTable.addCell(String.format("%.4f", tgt.getLon()));
            targetTable.addCell(String.format("%.1f", tgt.getRadius()));
        }

        document.add(targetTable);
    }
    
    document.add(new Paragraph("Loadout Summary").setBold().setFontSize(14));

    FA_18F fa18f = (FA_18F) ctx.getAircraft();
    Map<Integer, Loadout> stations = fa18f.getStations();

    Table loadoutTable = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
    loadoutTable.addHeaderCell("Station");
    loadoutTable.addHeaderCell("Weapon");
    loadoutTable.addHeaderCell("Weight (lb)");
    loadoutTable.addHeaderCell("Category");

    for (Loadout l : stations.values()) {
        Weapons w = l.getWeapons();
        loadoutTable.addCell(String.valueOf(l.getStationNumber()));
        if (w != null) {
            loadoutTable.addCell(w.getName());
            loadoutTable.addCell(String.valueOf(w.getWeightlb()));
            loadoutTable.addCell(w.getCategory());
        } else {
            loadoutTable.addCell("NONE");
            loadoutTable.addCell("-");
            loadoutTable.addCell("-");
        }
    }

    document.add(loadoutTable);   
  
    document.close();
  }catch(Exception e) {
	  e.printStackTrace();
	  	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to generate PDF.");
  		}
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}