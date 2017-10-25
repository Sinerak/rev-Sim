package entities;

import enums.ModelPath;
import enums.TexturePath;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import utils.factories.EntityFactory;

/**
 * Created by Kelvin McClean on 25/10/2017.
 */
public class P_Entity {

    private Entity entity;

    public P_Entity(Vector2f position){

        entity = EntityFactory.createEntity(ModelPath.PLAYER, TexturePath.PLAYER,position);

    }

    public Entity getEntity(){
        return entity;
    }
}
