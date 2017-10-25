package entities;

import entities.reverseHandlers.PlayerMovement;
import enums.ModelPath;
import enums.TexturePath;
import org.lwjgl.Sys;
import org.lwjgl.util.vector.Vector2f;
import utils.WorldFactors;
import utils.factories.EntityFactory;

import java.io.Serializable;

/**
 * Created by Kelvin McClean on 25/10/2017.
 */
public class P_Entity implements Serializable{

    private Entity entity;
    private static Vector2f positionOffset;
    private Vector2f currentTile;
    private Vector2f oldTile;


    public P_Entity(Vector2f tileSet){

        currentTile = tileSet;

        positionOffset = new Vector2f((2.0f/WorldFactors.numTilesX)/2.0f,(2.0f/WorldFactors.numTilesY)/1.1f);

        Vector2f position = calculatePositionFromTileSet(tileSet);
        //Vector2f position = new Vector2f(tileSet.x,tileSet.y);
        entity = EntityFactory.createEntity(ModelPath.PLAYER, TexturePath.PLAYER, position);
        entity.setScale(new Vector2f(0.15f/WorldFactors.numTilesX, 0.15f / WorldFactors.numTilesY));

    }

    private Vector2f calculatePositionFromTileSet(Vector2f tileSet) {
        this.oldTile = tileSet;
        if (tileSet.x >= WorldFactors.numTilesX){
            tileSet.x = WorldFactors.numTilesX-1;
        }
        if (tileSet.y >= WorldFactors.numTilesY){
            tileSet.y = WorldFactors.numTilesY-1;
        }

        float stepX = 2.0f/ WorldFactors.numTilesX;
        float stepY = 2.0f/ WorldFactors.numTilesY;

        Vector2f position = new Vector2f();
        position.x = (-1+(tileSet.x * stepX)) + positionOffset.x;
        position.y = (1-(tileSet.y * stepY)) - positionOffset.y;


        return position;

    }

    public Entity getEntity(){
        return entity;
    }

    public void setCurrentTile(Vector2f tile){
        this.currentTile = tile;
    }

    public Vector2f getCurrentTile(){
        return currentTile;
    }



    public void moveUp(boolean forward){
        oldTile = currentTile;

        if (forward){
            PlayerMovement.moveUpForward(this);
        }
        else{
            PlayerMovement.moveUpBackwards(this);
        }

        entity.setPosition((calculatePositionFromTileSet(this.currentTile)));

    }

    public void moveDown(boolean forward){
        oldTile = currentTile;
        if (forward){
            PlayerMovement.moveDownForward(this);
        }
        else{
            PlayerMovement.moveDownBackwards(this);
        }

        entity.setPosition((calculatePositionFromTileSet(this.currentTile)));

    }
}
