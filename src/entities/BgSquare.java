package entities;

import enums.ModelPath;
import enums.TexturePath;
import org.lwjgl.util.vector.Vector3f;
import utils.factories.EntityFactory;

import java.util.ArrayList;

/**
 * Created by Sinerak on 25/06/2017.
 */
public class BgSquare {


    private Entity square;
    private final float scale = 1f;
    private Vector3f colour = new Vector3f(1,1,1);
    private ArrayList<Entity> provinces;

    public BgSquare(Vector3f position, Vector3f colour){

        square = EntityFactory.createEntity(ModelPath.SQUARE,TexturePath.WHITE,position);

    }

    public Entity getEntity() {
        return square;
    }

}
