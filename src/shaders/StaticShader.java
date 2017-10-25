package shaders;

import enums.ShaderPath;
import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram {

	private static final String VERTEX_FILE = ShaderPath.ENTITY_VERTEX.getPath();
	private static final String FRAGMENT_FILE = ShaderPath.ENTITY_FRAGMENT.getPath();

	private int location_transformationMatrix;

	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {

		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");

	}
	

	
	public void loadTransformationMatrix(Matrix4f transformation){
		super.loadMatrix(location_transformationMatrix, transformation);
	}

}
