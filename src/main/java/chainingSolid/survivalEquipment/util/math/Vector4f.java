package chainingSolid.survivalEquipment.util.math;

public class Vector4f {
	
	public static final Vector4f WORLD_UP = new Vector4f(0, 1, 0, 0);
	
	public float x,y,z,w = 1;
	
	public Vector4f(float x, float y, float z, float w) {
		setData(x,y,z,w);
	}
	
	public Vector4f() {
		
	}
	
	public void setData(float x, float y, float z, float w){
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		
	}
	
	@Override
	public String toString(){
		return "X:"+x+" Y:"+y+" Z:"+z+"W:"+w;
	}
	
	public String xyzToString(){
		return "X:"+x+" Y:"+y+" Z:"+z;
	}
	
	public void normilize(){
		float lenght = (float) Math.sqrt((x*x)+(y*y)+(z*z));
		x = (x)/lenght;
		y = (y)/lenght;
		z = (z)/lenght;
		
		this.setData(x, y, z, w);
	}
	
	public Vector4f copy() {
		return new Vector4f(x,y,z,w);
	}
	
	public Vector4f crossProduct(Vector4f otherVector){
		float newVectorX = y * otherVector.z - z * otherVector.y;
		float newVectorY = z * otherVector.x - x * otherVector.z;
		float newVectorZ = x * otherVector.y - y * otherVector.x;
		return new Vector4f(newVectorX, newVectorY, newVectorZ, 0);
	}
	
	public Vector4f invert(){
		return new Vector4f(-x, -y, -z, w);
	}
	
	public Vector4f add(Vector4f otherVector){
		return new Vector4f(x + otherVector.x, y + otherVector.y, z + otherVector.z, 1);
	}
	
	public TranslationMatrix getVectorAsTranslationMatrix(){
		return new TranslationMatrix(x, y, z);
	}
	
}
