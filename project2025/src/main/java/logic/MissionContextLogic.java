package logic;
import java.util.ArrayList;
import java.util.List;

import aircrafts.Aircrafts;
import aircrafts.FA_18;
import carrierOps.Carrier;
import flightplan.FlightPlan2;
import flightplan.Waypoint;
import mission.MissionContext;
import mission.Target;
public class MissionContextLogic {

    private final MissionContext context;

    public MissionContextLogic(MissionContext context) {
        this.context = context;
    }
	public void initializeFromServlet(Carrier carrier, List<Aircrafts> wingmen) {
	    setCarrier(carrier);
	    setWingmen(wingmen);
	}

    // Carrier 設定
    public void setCarrier(Carrier carrier) {
        context.setCarrier(carrier);
        updateStartPointWithCarrierLocation();
    }

    // 航空機設定
    public void setAircraft(Aircrafts aircraft) {
        context.setAircraft(aircraft);
    }

    // フライトプラン設定
    public void setFlightPlan(FlightPlan2 plan) {
        context.setFlightPlan(plan);
    }

    public void addCAPAtWaypoint(int wpIndex, int durationMinutes) {
        if (context.getAircraft() instanceof FA_18 && context.getFlightPlan() != null) {        
            FA_18 fa18= (FA_18)context.getAircraft();
            AircraftsLogic aLogic=new AircraftsLogic();
            List<Waypoint>wps=context.getFlightPlan().getWaypoints();
            if(wpIndex >=0 && wpIndex<wps.size()) {
            double extraFuel=aLogic.cap(wps, wpIndex, durationMinutes,fa18);
            context.addExtraFuelToSegment(wpIndex, extraFuel);
            context.setTotalFlightTime(durationMinutes * 60);
            context.setCapIndex(wpIndex);
            context.setCapDuration(durationMinutes);
            }
        }
    }

    // 僚機の追加
    public void setWingmen(List<Aircrafts> wingmen) {
        context.setWingmen(wingmen);
    }

    // 出発点にCarrier位置を反映
    private void updateStartPointWithCarrierLocation() {
        FlightPlan2 plan = context.getFlightPlan();
        Carrier carrier = context.getCarrier();
        if (plan != null && carrier != null && !plan.getWaypoints().isEmpty()) {
            Waypoint wp0 = plan.getWaypoints().get(0);
            Waypoint updated = Waypoint.createStartPoint(carrier.getLatitude(), carrier.getLongitude(), wp0.getAltitude(), wp0.getSpeed());
            plan.getWaypoints().set(0, updated);
        }
     }
        public void addTarget(Target target) {
            if (context.getTargetPoints() == null) {
                context.setTargetPoints(new ArrayList<>());
            }
            context.getTargetPoints().add(target);
        }
        public void removeTargetByName(String name) {
            if (context.getTargetPoints() != null) {
                context.getTargetPoints().removeIf(t -> t.getName().equalsIgnoreCase(name));
            }
        }

        public List<Target> getTargets() {
            return context.getTargetPoints() != null ? context.getTargetPoints() : new ArrayList<>();
        }
        public double calculateLandingWeight() {
            Aircrafts aircraft = context.getAircraft();
            if (aircraft == null) return 0;

            double startWeight = aircraft.getFullWeight();
            double fuelConsumed = 0.0;
            
            if (context.getSegmentFuelList() != null) {
                for (Double fuel : context.getSegmentFuelList()) {
                    fuelConsumed += fuel;
                }
            }
            return startWeight - fuelConsumed;
        }
        public int calculateAdjustedCruiseSpeed() {
            if (context == null || context.getAircraft() == null) return 0;
            Aircrafts aircraft = context.getAircraft();
            double factor = (aircraft.getIntFuel() + aircraft.getLoadoutWeight()) / aircraft.getFullWeight();
            return (int) (aircraft.getCruiseSpeed() * (1 - 0.1 * factor));
        }
    }
