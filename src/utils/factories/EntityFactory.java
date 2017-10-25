package utils.factories;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import enums.ModelPath;
import enums.TexturePath;
import models.RawModel;
import models.TexturedModel;
import textures.ModelTexture;
import utils.fileLoader.Loader;
import utils.fileLoader.ModelData;
import utils.fileLoader.OBJFileLoader;

public class EntityFactory {

	private static Map<ModelPath, Map<TexturePath, TexturedModel>> map = new HashMap<>();

	public static Map<ModelPath, Map<TexturePath, TexturedModel>> getMap() {
		return map;
	}

	public static Entity createEntity(ModelPath model, TexturePath texture, Vector3f pos) {
		TexturedModel tModel;
		if (map.containsKey(model)) {
			if (map.get(model).containsKey(texture)) {
				tModel = map.get(model).get(texture);
			} else {
				RawModel raw = ((TexturedModel) map.get(model).values().toArray()[0]).getRawModel();
				tModel = new TexturedModel(raw, new ModelTexture(TextureFactory.createTexture(texture)));
				tModel.getTexture().setHasTransparency(texture.getHasTransparency());
				tModel.getTexture().setUseFakeLighting(texture.getFakeLighting());
				map.get(model).put(texture, tModel);
			}
		} else {
			ModelData data = OBJFileLoader.loadOBJ(model.getPath());
			RawModel raw = Loader.loadToVao(data);
			tModel = new TexturedModel(raw, new ModelTexture(TextureFactory.createTexture(texture)));
			tModel.getTexture().setHasTransparency(texture.getHasTransparency());
			tModel.getTexture().setUseFakeLighting(texture.getFakeLighting());
			map.put(model, new HashMap<>());
			map.get(model).put(texture, tModel);

		}

		return new Entity(tModel, pos);

	}

	public static Entity createEntity(ModelPath model, TexturePath texture, Vector3f pos, Vector3f rotation,
			float scale, int textureIndex) {
		TexturedModel tModel;
		if (map.containsKey(model)) {
			if (map.get(model).containsKey(texture)) {
				tModel = map.get(model).get(texture);
			} else {
				RawModel raw = ((TexturedModel) map.get(model).values().toArray()[0]).getRawModel();
				tModel = new TexturedModel(raw, new ModelTexture(TextureFactory.createTexture(texture)));
				tModel.getTexture().setHasTransparency(texture.getHasTransparency());
				tModel.getTexture().setUseFakeLighting(texture.getFakeLighting());
				map.get(model).put(texture, tModel);
			}
		} else {
			ModelData data = OBJFileLoader.loadOBJ(model.getPath());
			RawModel raw = Loader.loadToVao(data);
			tModel = new TexturedModel(raw, new ModelTexture(TextureFactory.createTexture(texture)));
			tModel.getTexture().setHasTransparency(texture.getHasTransparency());
			tModel.getTexture().setUseFakeLighting(texture.getFakeLighting());
			map.put(model, new HashMap<>());
			map.get(model).put(texture, tModel);

		}

		return new Entity(tModel, pos, rotation.x, rotation.y, rotation.z, scale, textureIndex);

	}
	
	public static void setTextureNumberOfRows(ModelPath model, TexturePath texture, int numberOfRows) {
		if (map.containsKey(model)) {
			if (map.get(model).containsKey(texture)) {
				map.get(model).get(texture).getTexture().setNumberOfRows(numberOfRows);
			}
		}
	}

	public static void setModelProperties(ModelPath model, TexturePath texture, float reflectivity, float shineDamper) {
		if (map.containsKey(model)) {
			if (map.get(model).containsKey(texture)) {
				map.get(model).get(texture).getTexture().setReflectivity(reflectivity);
				map.get(model).get(texture).getTexture().setShineDamper(shineDamper);
			}

		}
	}

}
