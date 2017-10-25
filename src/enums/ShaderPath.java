package enums;

public enum ShaderPath {

	ENTITY_VERTEX("shaders/shader.vert"), 
	ENTITY_FRAGMENT("shaders/shader.frag"),
	BG_VERTEX("shaders/terrain.vert"),
	BG_FRAGMENT("shaders/terrain.frag"),
	GUI_VERTEX("shaders/gui.vert"),
	GUI_FRAGMENT("shaders/gui.frag");

	private String path;

	private ShaderPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;

	}
}
