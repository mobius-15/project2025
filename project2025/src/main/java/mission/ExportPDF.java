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

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
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
    	// Allow requests without action parameter or with empty action for PDF export
    	if (action != null && !action.isEmpty() && !action.equals("export")) {
    	    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action for ExportPDF.");
    	    return;
    	}

      try {  
        System.out.println("DEBUG: Starting PDF export...");
        MissionContext ctx = (MissionContext) request.getSession().getAttribute("ctx");
        if (ctx == null) {
            System.out.println("ERROR: Mission context is null");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mission data not found.");
            return;
        }
        System.out.println("DEBUG: Mission context found");
        
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=mission_report.pdf");

        System.out.println("DEBUG: Creating PDF document...");
        Document document = null;
        try {
            PdfWriter writer = new PdfWriter(response.getOutputStream());
            PdfDocument pdfDoc = new PdfDocument(writer);
            document = new Document(pdfDoc);
            System.out.println("DEBUG: PDF Document initialized");
        } catch (Exception e) {
            System.out.println("ERROR: Failed to initialize PDF document: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to initialize PDF");
            return;
        }
        
        System.out.println("DEBUG: Adding basic information...");
        System.out.println("DEBUG: Target list size: " + (ctx.getTargetPoints() != null ? ctx.getTargetPoints().size() : "null"));
        
        try {
            document.add(new Paragraph("Created At: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
            System.out.println("DEBUG: Created At - OK");
        } catch (Exception ex) {
            System.out.println("ERROR: PDF export failed");
            ex.printStackTrace();
            response.reset();                       // まだコミット前なら
            response.sendError(500,"PDF export failed"); // エラーページ
        }
        
        try {
            LocalDateTime now = LocalDateTime.now();
            String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            System.out.println("DEBUG: Formatted date = " + formatted);
            document.add(new Paragraph("Created At: " + formatted));
            System.out.println("DEBUG: Created At - OK");
        } catch (Exception e) {
            System.out.println("ERROR: Created At failed: " + e.getMessage());
            e.printStackTrace(); // 🔍 追加
            document.add(new Paragraph("Created At: Error"));
        }
        
        try {
            document.add(new Paragraph("Mission Report").setBold().setFontSize(16));
            System.out.println("DEBUG: Mission Report title - OK");
        } catch (Exception e) {
            System.out.println("ERROR: Mission Report title failed: " + e.getMessage());
        }
        
        try {
            String missionType = (ctx.getMissionType() != null) ? ctx.getMissionType() : "Unknown";
            document.add(new Paragraph("Mission Type: " + missionType));
            System.out.println("DEBUG: Mission Type - OK: " + missionType);
        } catch (Exception e) {
            System.out.println("ERROR: Mission Type failed: " + e.getMessage());
            document.add(new Paragraph("Mission Type: Error"));
        }
        
        try {
            String aircraftName = (ctx.getAircraft() != null && ctx.getAircraft().getName() != null) ? 
                                  ctx.getAircraft().getName() : "Unknown";
            document.add(new Paragraph("Aircraft: " + aircraftName));
            System.out.println("DEBUG: Aircraft - OK: " + aircraftName);
        } catch (Exception e) {
            System.out.println("ERROR: Aircraft failed: " + e.getMessage());
            document.add(new Paragraph("Aircraft: Error"));
        }
        
        try {
            String carrierName = (ctx.getCarrier() != null && ctx.getCarrier().getName() != null) ? 
                                ctx.getCarrier().getName() : "N/A";
            document.add(new Paragraph("Carrier: " + carrierName));
            System.out.println("DEBUG: Carrier - OK: " + carrierName);
        } catch (Exception e) {
            System.out.println("ERROR: Carrier failed: " + e.getMessage());
            document.add(new Paragraph("Carrier: Error"));
        }
        
        try {
            double totalTime = ctx.getTotalFlightTime();
            document.add(new Paragraph(String.format("Total Flight Time: %.1f hour", totalTime / 60)));
            System.out.println("DEBUG: Total Flight Time - OK: " + totalTime);
        } catch (Exception e) {
            System.out.println("ERROR: Total Flight Time failed: " + e.getMessage());
            document.add(new Paragraph("Total Flight Time: Error"));
        }
        
        try {
            document.add(new Paragraph(" "));
            System.out.println("DEBUG: All basic information added successfully");
        } catch (Exception e) {
            System.out.println("ERROR: Adding blank paragraph failed: " + e.getMessage());
        }
        
        System.out.println("DEBUG: Creating flight plan table...");
        
        // Check flight plan data
        if (ctx.getFlightPlan() == null) {
            System.out.println("ERROR: Flight plan is null");
            document.add(new Paragraph("Flight plan data not available."));
        } else {
            List<Waypoint> wps = ctx.getFlightPlan().getWaypoints();
            List<Double> fuels = ctx.getSegmentFuelList();
            
            System.out.println("DEBUG: Waypoints count: " + (wps != null ? wps.size() : "null"));
            System.out.println("DEBUG: Fuels count: " + (fuels != null ? fuels.size() : "null"));
            
            if (wps == null || wps.isEmpty()) {
                System.out.println("ERROR: No waypoints found");
                document.add(new Paragraph("No waypoints found in flight plan."));
            } else if (fuels == null || fuels.isEmpty()) {
                System.out.println("ERROR: No fuel data found");
                document.add(new Paragraph("No fuel data found for flight plan."));
            } else if (wps.size() != fuels.size()) {
                System.out.println("ERROR: Waypoints and fuel data size mismatch: " + wps.size() + " vs " + fuels.size());
                document.add(new Paragraph("Flight plan data inconsistency detected."));
            } else {
                // Flight Plan Table
                Table table = new Table(UnitValue.createPercentArray(7)).useAllAvailableWidth();
                table.addHeaderCell("WP");
                table.addHeaderCell("Alt (ft)");
                table.addHeaderCell("TAS (kt)");
                table.addHeaderCell("Mach");
                table.addHeaderCell("Dist (nm)");
                table.addHeaderCell("Time (min)");
                table.addHeaderCell("Fuel (lb)");
                
                try {
                    for (int i = 0; i < wps.size(); i++) {
                        Waypoint wp = wps.get(i);
                        if (wp == null) {
                            System.out.println("ERROR: Waypoint " + i + " is null");
                            continue;
                        }
                        
                        table.addCell(String.valueOf(i + 1));
                        table.addCell(String.valueOf(wp.getAltitude()));
                        table.addCell(String.valueOf(wp.getSpeed()));
                        table.addCell(String.format("%.2f", wp.getMach()));
                        table.addCell(String.valueOf(wp.getDistance()));
                        table.addCell(String.format("%.1f", wp.getSegmentTime()));
                        table.addCell(String.format("%.1f", fuels.get(i)));
                    }
                    
                    document.add(table);
                    System.out.println("DEBUG: Flight plan table created successfully");
                } catch (Exception e) {
                    System.out.println("ERROR: Exception in flight plan table creation: " + e.getMessage());
                    e.printStackTrace();
                    document.add(new Paragraph("Error creating flight plan table: " + e.getMessage()));
                }
            }
        }
        System.out.println("DEBUG: Flight plan table added");
        
        // Targets Section
        document.add(new Paragraph("Targets").setBold().setFontSize(14));
        System.out.println("DEBUG: Processing targets...");
        
        if (ctx.getTargetPoints() != null && !ctx.getTargetPoints().isEmpty()) {
            // Fix: Create table with 3 columns to match headers
            Table targetTable = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
            targetTable.addHeaderCell("Name");
            targetTable.addHeaderCell("Latitude");
            targetTable.addHeaderCell("Longitude");

            for (Target tgt : ctx.getTargetPoints()) {
                targetTable.addCell(tgt.getName() != null ? tgt.getName() : "Unknown");
                targetTable.addCell(String.format("%.4f", tgt.getLat()));
                targetTable.addCell(String.format("%.4f", tgt.getLon()));
            }

            document.add(targetTable);
            System.out.println("DEBUG: Target table added");
        } else {
            document.add(new Paragraph("No targets assigned."));
            System.out.println("DEBUG: No targets found");
        }
    
        // Loadout Section
        System.out.println("DEBUG: Processing loadout...");
        document.add(new Paragraph("Loadout Summary").setBold().setFontSize(14));

        FA_18F fa18f = (FA_18F) ctx.getAircraft();
        Map<Integer, Loadout> stations = fa18f.getStations();

        Table loadoutTable = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
        loadoutTable.addHeaderCell("Station");
        loadoutTable.addHeaderCell("Weapon");
        loadoutTable.addHeaderCell("Weight (lb)");
        loadoutTable.addHeaderCell("Category");

        for (Loadout l : stations.values()) {
            // Try both getWeapon() and getWeapons() to see which one works
            Weapons w = null;
            try {
                w = l.getWeapon();
            } catch (Exception e1) {
                try {
                    w = l.getWeapons();
                } catch (Exception e2) {
                    System.out.println("ERROR: Both getWeapon() and getWeapons() failed for station " + l.getStationNumber());
                }
            }
            
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
        System.out.println("DEBUG: Loadout table added");
        
        String imagePath = (String) request.getSession().getAttribute("mapImagePath");
        if (imagePath != null) {
            try {
                ImageData imgData = ImageDataFactory.create(imagePath);
                Image mapImage = new Image(imgData).scaleToFit(400, 300);
                document.add(new Paragraph("Mission Map"));
                document.add(mapImage);
            } catch (Exception e) {
                System.out.println("WARNING: Failed to embed map image: " + e.getMessage());
                document.add(new Paragraph("Map image could not be loaded."));
            }
        }
        
  
        document.close();
        System.out.println("DEBUG: PDF generation completed successfully");
        
        
      } catch(Exception e) {
          e.printStackTrace();
          response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to generate PDF: " + e.getMessage());
      }
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}