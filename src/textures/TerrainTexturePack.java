package textures;

public class TerrainTexturePack {

	private TerrainTexture background;
	private TerrainTexture rTexture;
	private TerrainTexture gTexture;
	private TerrainTexture bTexture;
	
	
	public TerrainTexturePack(TerrainTexture background, TerrainTexture rTexture, TerrainTexture gTexture,
			TerrainTexture bTexture) {
		this.background = background;
		this.rTexture = rTexture;
		this.gTexture = gTexture;
		this.bTexture = bTexture;
	}


	public TerrainTexture getBackground() {
		return background;
	}


	public TerrainTexture getRTexture() {
		return rTexture;
	}


	public TerrainTexture getGTexture() {
		return gTexture;
	}


	public TerrainTexture getBTexture() {
		return bTexture;
	}
	
}
