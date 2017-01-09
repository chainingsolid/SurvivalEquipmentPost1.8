package chainingSolid.survivalEquipment.util.entity;

public class QuadraticFlightPath {
	
	private final double GRAVITY = 10;
	
	public double velocity,angle;
	
	public QuadraticFlightPath(double velocity, double angle){
		this.velocity = velocity;
		this.angle = angle;
	}
	
	public double getHieghtAtDistance(double distance){
		double height = 0;
		height = distance*Math.tan(angle) - ((GRAVITY*Math.pow(distance,2))/(2*(Math.pow(velocity*Math.cos(angle),2))));
		return height;
	}
	
	
	
}
