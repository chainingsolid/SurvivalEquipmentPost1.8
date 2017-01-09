package chainingSolid.survivalEquipment.util.math;

public class TranslationMatrix extends Matrix4f {
	
	
	
	public TranslationMatrix(float x, float y, float z) {
		super();
		data[0][0] = 1;
		data[1][1] = 1;
		data[2][2] = 1;
		data[3][3] = 1;
		
		data[0][3] = x;
		data[1][3] = y;
		data[2][3] = z;
	}
	
}
