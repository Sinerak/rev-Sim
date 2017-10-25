package entities;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;

public class Entity {

	private TexturedModel model;
	private Vector2f position;
	private Vector2f scale;

	private float rotationX;
	private float rotationY;
	private float rotationZ;

	private int textureIndex = 0;

	public Entity(TexturedModel model, Vector2f position) {
		
		this.model = model;
		this.position = position;
		this.rotationX = 0;
		this.rotationY = 0;
		this.rotationZ = 0;
		scale = new Vector2f(1,1);

	}
	
	public Entity(TexturedModel model, Vector2f position, float rotationX, float rotationY, float rotationZ,
			float scale, int index) {
		
		this.model = model;
		this.position = position;
		this.rotationX = rotationX;
		this.rotationY = rotationY;
		this.rotationZ = rotationZ;
		this.scale = new Vector2f(scale,scale);
		this.textureIndex = index;
	}
	
	public float getTextureXOffset(){
		int column = textureIndex % model.getTexture().getNumberOfRows();
		return (float) column/ (float) model.getTexture().getNumberOfRows();
	}
	
	public float getTextureYOffset(){
		int row = textureIndex / model.getTexture().getNumberOfRows();
		return (float) row / (float) model.getTexture().getNumberOfRows();
		
	}
	

	public void increasePosition(float dx, float dy){
		this.position.x += dx;
		this.position.y += dy;
	}
	
	public void increaseRotation(float dx, float dy,float dz){
		this.rotationX += dx;
		this.rotationY += dy;
		this.rotationZ += dz;
	}
	
	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public float getRotationX() {
		return rotationX;
	}

	public void setRotationX(float rotationX) {
		this.rotationX = rotationX;
	}

	public float getRotationY() {
		return rotationY;
	}

	public void setRotationY(float rotationY) {
		this.rotationY = rotationY;
	}

	public float getRotationZ() {
		return rotationZ;
	}

	public void setRotationZ(float rotationZ) {
		this.rotationZ = rotationZ;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}

}
