package shaders;

import enums.ShaderPath;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Kelvin McClean on 25/10/2017.
 */
public class BackgroundShader extends ShaderProgram {

    private static final String VERTEX_FILE = ShaderPath.BG_VERTEX.getPath();
    private static final String FRAGMENT_FILE = ShaderPath.BG_FRAGMENT.getPath();

    private int location_transformationMatrix;
    private int location_Colour;
    public BackgroundShader() {
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
        location_Colour = super.getUniformLocation("fragColour");

    }



    public void loadTransformationMatrix(Matrix4f transformation){
        super.loadMatrix(location_transformationMatrix, transformation);
    }

    public void loadColour(Vector3f colour){
        super.load3DVector(location_Colour, colour);
    }

}
