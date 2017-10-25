package models;

import textures.ModelTexture;

import java.io.Serializable;

public class TexturedModel implements Serializable{

	private RawModel rawModel;
	private ModelTexture texture;
	
	public TexturedModel(RawModel rawModel, ModelTexture texture){
		this.rawModel = rawModel;
		this.texture = texture;
	}

	public RawModel getRawModel() {
		return rawModel;
	}

	public void setRawModel(RawModel rawModel) {
		this.rawModel = rawModel;
	}

	public ModelTexture getTexture() {
		return texture;
	}

	public void setTexture(ModelTexture texture) {
		this.texture = texture;
	}
}
