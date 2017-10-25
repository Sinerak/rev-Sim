package shaders;

import entities.Camera;
import entities.Light;
import enums.ShaderPath;
import org.lwjgl.util.vector.Matrix4f;
import utils.MatrixCreator;
import utils.WorldFactors;

public class StaticShader extends ShaderProgram {

	private static final String VERTEX_FILE = ShaderPath.ENTITY_VERTEX.getPath();
	private static final String FRAGMENT_FILE = ShaderPath.ENTITY_FRAGMENT.getPath();

	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_numberOfRows;
	private int location_offset;
	
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
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");

		location_numberOfRows = super.getUniformLocation("numberOfRows");
		location_offset = super.getUniformLocation("offset");
	}
	

	
	public void loadTransformationMatrix(Matrix4f transformation){
		super.loadMatrix(location_transformationMatrix, transformation);
	}
	
	public void loadProjectionMatrix(Matrix4f projection){
		super.loadMatrix(location_projectionMatrix, projection);
	}
	
	public void loadViewMatrix(Camera camera){
		super.loadMatrix(location_viewMatrix, MatrixCreator.createViewMatrix(camera));
	}


	public void loadNumberOfRows(int numberOfRows){
		super.loadFloat(location_numberOfRows, numberOfRows);
		
	}

	public void loadOffset(float xOffset, float yOffset){
		super.load2DVector(location_offset, xOffset, yOffset);
	}
	
}
