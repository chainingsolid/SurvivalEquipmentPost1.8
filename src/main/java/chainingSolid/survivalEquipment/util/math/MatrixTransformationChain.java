package chainingSolid.survivalEquipment.util.math;

import chainingSolid.survivalEquipment.util.math.RotationMatrix.RotationMatrixGroup;

public class MatrixTransformationChain {
	
	public TranslationMatrix translation = new TranslationMatrix(0,0,0);
	public ScaleMatrix scale = new ScaleMatrix(1,1,1);
	public RotationMatrixGroup rotation = new RotationMatrixGroup();
	
	
	public Matrix4f generateTransformatinMatrix(){
		Matrix4f transformation;
		
		transformation = translation;
		transformation = transformation.multiply(rotation.combineRotations());
		transformation = transformation.multiply(scale);
		return transformation;
	}
	
	
	
}
