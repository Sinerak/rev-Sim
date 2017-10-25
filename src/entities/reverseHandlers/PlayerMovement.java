package entities.reverseHandlers;

import entities.P_Entity;
import org.lwjgl.util.vector.Vector2f;
import utils.WorldFactors;

/**
 * Created by Kelvin McClean on 25/10/2017.
 */
public class PlayerMovement {
    public static void moveUpForward(P_Entity player) {
        Vector2f currentTile = player.getCurrentTile();
        if (currentTile.y > 0){
            currentTile.y--;
        }
        player.setCurrentTile(currentTile);
    }

    public static void moveUpBackwards(P_Entity player) {
        Vector2f currentTile = player.getCurrentTile();
        if (currentTile.y < WorldFactors.numTilesY-1){
            currentTile.y++;
        }
        player.setCurrentTile(currentTile);

    }

    public static void moveDownForward(P_Entity player) {
        Vector2f currentTile = player.getCurrentTile();
        if (currentTile.y < WorldFactors.numTilesY-1){
            currentTile.y++;
        }
        player.setCurrentTile(currentTile);
    }

    public static void moveDownBackwards(P_Entity player) {
        Vector2f currentTile = player.getCurrentTile();
        if (currentTile.y > 0){
            currentTile.y--;
        }
        player.setCurrentTile(currentTile);
    }
}
