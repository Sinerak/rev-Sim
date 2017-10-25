package enums;

public enum TexturePath {

	MAP("map", FileType.BMP),
	STALL("stallTexture"),
	FERN("fernTA", true),
	LOW_POLY_TREE("lowPolyTree"),
	TEXTURE("texture"),
	TREE("tree"),
	GRASS_TILE("grass"),
	GRASSY_TILE("grassy"),
	GRASSY2_TILE("grassy2"),
	DIRT_TILE("dirt"),
	GRASS_FLOWERS_TILE("grassFlowers"),
	MUD_TILE("mud"),
	PATH_TILE("path"),
	PINK_FLOWERS_TILE("pinkFlowers"),
	BLEND_MAP("blendMap"),
	PLAYER("playerTexture"),
	HEIGHTMAP("heightmap"),
	HEALTH("health");
	
	private final String path;
	private boolean hasTransparency;
	private boolean useFakeLighting;
	private FileType fileType = FileType.PNG;
	
	private TexturePath(String path){
		this.path = path;
		this.hasTransparency = false;
		this.useFakeLighting = false;
	}

	private TexturePath(String path, FileType fileType){
		this.path = path;
		this.fileType = fileType;
		this.hasTransparency = false;
		this.useFakeLighting = false;
	}

	private TexturePath(String path, boolean transparentAndFakeLight){
		this.path = path;
		this.hasTransparency = transparentAndFakeLight;
		this.useFakeLighting = transparentAndFakeLight;
	}



	private TexturePath(String path, boolean hasTransparency, boolean useFakeLighting){
		this.path = path;
		this.hasTransparency = hasTransparency;
		this.useFakeLighting = useFakeLighting;
	}
	
	public boolean getHasTransparency(){
		return hasTransparency;
	}
	public boolean getFakeLighting(){
		return useFakeLighting;
	}
	
	public String getPath(){
		return path;
	}

	public FileType getFileType() { return fileType; }
}
