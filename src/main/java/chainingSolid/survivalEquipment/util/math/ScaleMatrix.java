package chainingSolid.survivalEquipment.util.math;

public class ScaleMatrix extends Matrix4f {
	
	public ScaleMatrix(float x, float y, float z) {
		data[0][0] = x;
		data[1][1] = y;
		data[2][2] = z;
		data[3][3] = 1;
	}
	
	
	
}
