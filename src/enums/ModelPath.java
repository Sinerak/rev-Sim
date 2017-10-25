package enums;

public enum ModelPath {

	SQUARE("square"),

	PLAYER("person");
	
	private final String path;

	public String getPath(){
		return path;
	}
	
	ModelPath(String path){
		this.path = path;
	}
}


