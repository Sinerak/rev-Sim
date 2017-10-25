package entities;

import enums.ModelPath;
import enums.TexturePath;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import utils.factories.EntityFactory;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Kelvin McClean on 25/06/2017.
 */
public class BgSquare implements Serializable{


    private Entity square;
    private Vector3f colour = new Vector3f(0,1,0);

    public BgSquare(Vector2f position, Vector3f colour){

        square = EntityFactory.createEntity(ModelPath.SQUARE,TexturePath.WHITE,position);
        this.colour = colour;

    }
    public Vector3f getColour() { return colour; }
    public Entity getEntity() {
        return square;
    }

}
