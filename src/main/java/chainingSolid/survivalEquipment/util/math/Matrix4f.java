package chainingSolid.survivalEquipment.util.math;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

public class Matrix4f {
	
	public float[][] data;
	
	public Matrix4f() {
		data = new float[4][4];
	}
	
	/*TODO fix 
	 * public Vector4f multiply(Vector4f vector){
		Vector4f result = new Vector4f();
		for(int row = 0;row < 4;row++){
			result.data[row] = 
					(data[row][0]*vector.data[0])
					+(data[row][1]*vector.data[1])
					+(data[row][2]*vector.data[2])
					+(data[row][3]*vector.data[3]);
		}
		float x = result.data[0];
		float y = result.data[1];
		float z = result.data[2];
		float w = result.data[3];
		result.setData(x, y, z, w);
		return result;
	}*/
	
	public Matrix4f multiply(Matrix4f m){
		Matrix4f result = new Matrix4f();
		for(int row = 0; row < 4; row++){
			for(int column = 0; column < 4;column++){
				result.data[row][column] = 
						data[row][0] * m.data[0][column]+
						data[row][1] * m.data[1][column]+
						data[row][2] * m.data[2][column]+
						data[row][3] * m.data[3][column];
			}
		}
		return result;
	}
	
	public ByteBuffer translateToOpenGL(){
		ByteBuffer buffer = BufferUtils.createByteBuffer(4*4*8);
		for (int width = 0; width < 4; width++) {
			for(int hieght = 0; hieght < 4; hieght++){
				buffer.putFloat(data[width][hieght]);
			}
		}
		buffer.flip();
		return buffer;
	}
	
	@Override
	public String toString(){
		String s = "";
		for(int column = 0; column < 4;column++){
			for(int row = 0; row < 4; row++){
				s = s + " , " +data[row][column];
			}
			s = s + "\n";
		}
		return s;
	}
	
}
