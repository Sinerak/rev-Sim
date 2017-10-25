package render;

import entities.Entity;
import models.TexturedModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import shaders.StaticShader;
import textures.ModelTexture;
import utils.MatrixCreator;

import java.util.List;
import java.util.Map;

public class EntityRenderer {
	
	private StaticShader shader;
	
	
	public EntityRenderer(StaticShader shader){
		this.shader = shader;
		this.shader.start();
		this.shader.loadProjectionMatrix(MatrixCreator.getProjectionMatrix());
		this.shader.stop();
	}
	
	


	public void render(Map<TexturedModel,List<Entity>> map){
		for (TexturedModel model : map.keySet()){
			prepareTexturedModel(model);
			List<Entity> batch = map.get(model);
			for(Entity entity: batch){
				prepareInstance(entity);
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
		shader.loadNumberOfRows(texture.getNumberOfRows());
		if (texture.hasTransparency()){
			MasterRenderer.disableBackfaceCulling();
		}
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,texture.getID());
	}
	
	
	private void unbindTexturedModel(){
		MasterRenderer.enableBackfaceCulling();
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
	
	private void prepareInstance(Entity entity){
		Matrix4f transformationMatrix = MatrixCreator.createTransformationMatrix(entity.getPosition(), entity.getRotationX(),
				entity.getRotationY(), entity.getRotationZ(), entity.getScale());
		
		this.shader.loadTransformationMatrix(transformationMatrix);
		this.shader.loadOffset(entity.getTextureXOffset(), entity.getTextureYOffset());
	}

	public void resize(){
		shader.start();
		shader.loadProjectionMatrix(MatrixCreator.getProjectionMatrix());
		shader.stop();
	}
	
}
