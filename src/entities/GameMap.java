package entities;

import enums.ModelPath;
import enums.TexturePath;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import utils.factories.EntityFactory;

import java.util.ArrayList;

/**
 * Created by Sinerak on 25/06/2017.
 */
public class GameMap {

    private Entity map;
    private final Vector3f position = new Vector3f(0,0,0);
    private final Vector3f rotation = new Vector3f(-90,0,0);
    private final float scale = 1f;
    private ArrayList<Entity> provinces;
    public GameMap(){
        map = EntityFactory.createEntity(ModelPath.MAP,TexturePath.MAP,position,rotation,scale);
        provinces = new ArrayList<Entity>();
        process();
    }

    public Entity getEntity() {
        return map;
    }

    public void process(){
        float noOfColours;
    }
}
