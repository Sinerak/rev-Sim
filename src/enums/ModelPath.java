package enums;

public enum ModelPath {

	MAP("map"),
	STALL("stall"),
	FERN("fern"),
	LOW_POLY_TREE("lowPolyTree"),
	TREE("tree"),
	PLAYER("person");
	
	private final String path;

	public String getPath(){
		return path;
	}
	
	private ModelPath(String path){
		this.path = path;
	}
}


