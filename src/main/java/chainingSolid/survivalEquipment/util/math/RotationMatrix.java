package chainingSolid.survivalEquipment.util.math;

public class RotationMatrix extends Matrix4f {
	
	
	
	public RotationMatrix(EnumRotationAxis axis, float radians) {
		if(axis == EnumRotationAxis.X){
			loadXAxisRotationMatrix(radians);
		}else if(axis == EnumRotationAxis.Y){
			loadYAxisRotationMatrix(radians);
		}else if(axis == EnumRotationAxis.Z){
			loadZAxisRotationMatrix(radians);
		}
	}
	
	private void loadXAxisRotationMatrix(float radians){
		data[0][0] = 1;
		data[1][1] = (float) Math.cos(radians);
		data[2][1] = (float) Math.sin(radians);
		data[1][2] = -((float) Math.sin(radians));
		data[2][2] = (float) Math.cos(radians);
		data[3][3] = 1;
	}
	
	private void loadYAxisRotationMatrix(float radians){
		data[0][0] = (float) Math.cos(radians);
		data[1][1] = 1;
		data[2][2] = (float) Math.cos(radians);
		data[3][3] = 1;
		data[2][0] = (float) Math.sin(radians);
		data[0][2] = -((float) Math.sin(radians));
	}
	
	private void loadZAxisRotationMatrix(float radians){
		data[0][0] = (float) Math.cos(radians);
		data[1][1] = (float) Math.cos(radians);
		data[0][1] = -((float) Math.sin(radians));
		data[1][0] = (float) Math.sin(radians);
		data[2][2] = 1;
		data[3][3] = 1;
	}
	
	
	public enum EnumRotationAxis{
		X,
		Y,
		Z
	}
	
	public static class RotationMatrixGroup{
		
		public RotationMatrix x,y,z;
		
		public RotationMatrixGroup(){
			x = new RotationMatrix(EnumRotationAxis.X, 0);
			y = new RotationMatrix(EnumRotationAxis.Y, 0);
			z = new RotationMatrix(EnumRotationAxis.Z, 0);
		}
		
		public Matrix4f combineRotations(){
			return x.multiply(y).multiply(z);
		}
		
	}
	
}
