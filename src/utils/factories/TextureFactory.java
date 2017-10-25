package utils.factories;

import java.util.HashMap;
import java.util.Map;

import enums.TexturePath;
import textures.ModelTexture;
import utils.fileLoader.Loader;

public class TextureFactory {

	
	private static Map<TexturePath,Integer> map = new HashMap<TexturePath, Integer>();
	
	public static Integer createTexture(TexturePath path){
		
		if (map.containsKey(path)){

			return map.get(path);
		}
		else{
			map.put(path, Loader.loadTexture(path.getPath(), path.getFileType()));

			return map.get(path);
		}
		
	}
}
