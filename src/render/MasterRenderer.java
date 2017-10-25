package render;

import entities.Entity;
import models.TexturedModel;
import org.lwjgl.opengl.GL11;
import shaders.StaticShader;
import utils.MatrixCreator;
import utils.WorldFactors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {
	
	private StaticShader entityShader = new StaticShader();
	private EntityRenderer entityRenderer;
	

	private Map<TexturedModel,List<Entity>> entities = new HashMap<TexturedModel,List<Entity>>();

	public MasterRenderer(){
		
		enableBackfaceCulling();
		
		MatrixCreator.createProjectionMatrix();
		
		entityRenderer = new EntityRenderer(entityShader);
	}
	
	
	public void render(){
		prepare();
		entityShader.start();
		entityRenderer.render(entities);
		entityShader.stop();
		entities.clear();
}
	
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT| GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(WorldFactors.SKY_COLOUR_R, WorldFactors.SKY_COLOUR_G, WorldFactors.SKY_COLOUR_B, 1.0f);
	}

	public void processEntity(Entity entity){
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		if (batch != null){
			batch.add(entity);
		}
		else{
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}
	
	public static void enableBackfaceCulling(){
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	public static void disableBackfaceCulling(){
		GL11.glDisable(GL11.GL_CULL_FACE);
	}


	public void resize(){
		entityRenderer.resize();

	}

	
	public void cleanUp(){
		entityShader.cleanUp();

	}


	
}
