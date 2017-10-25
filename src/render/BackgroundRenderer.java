package render;

import com.sun.prism.ps.Shader;
import entities.BgSquare;
import entities.Entity;
import models.TexturedModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import shaders.BackgroundShader;
import shaders.ShaderProgram;
import shaders.StaticShader;
import textures.ModelTexture;
import utils.MatrixCreator;

import java.util.List;
import java.util.Map;

public class BackgroundRenderer {

    private BackgroundShader shader;


    public BackgroundRenderer(BackgroundShader shader){
        this.shader = shader;
        this.shader.start();
        this.shader.stop();
    }



    public void run(Map<TexturedModel, List<BgSquare>> map){
        shader.start();

        render(map);
        shader.stop();
    }
    public void render(Map<TexturedModel,List<BgSquare>> map){
        for (TexturedModel model : map.keySet()){
            prepareTexturedModel(model);
            List<BgSquare> batch = map.get(model);
            for(BgSquare square: batch){
                prepareInstance(square.getEntity(), square.getColour());
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

            }
            unbindTexturedModel();
        }
    }

    private void prepareTexturedModel(TexturedModel model){


        GL30.glBindVertexArray(model.getRawModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        ModelTexture texture = model.getTexture();
        if (texture.hasTransparency()){
            MasterRenderer.disableBackfaceCulling();
        }

    }


    private void unbindTexturedModel(){
        MasterRenderer.enableBackfaceCulling();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareInstance(Entity entity, Vector3f colour){
        Matrix4f transformationMatrix = MatrixCreator.createTransformationMatrix(entity.getPosition(), entity.getScale());

        this.shader.loadTransformationMatrix(transformationMatrix);
        this.shader.loadColour(colour);
    }

    public void resize(){
        shader.start();
        shader.stop();
    }

}
