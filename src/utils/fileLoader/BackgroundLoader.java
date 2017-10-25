package utils.fileLoader;

import entities.BgSquare;
import entities.Entity;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import utils.WorldFactors;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by 40111604 on 25/10/2017.
 */
public class BackgroundLoader {

    private static final String RES_LOC = "res/maps/";

    public static ArrayList<BgSquare> loadMap(String objFileName){
        ArrayList<BgSquare> squares = new ArrayList<>();



        FileReader isr = null;
        File objFile = new File(RES_LOC + objFileName);
        try {
            isr = new FileReader(objFile);
        } catch (FileNotFoundException e) {
            System.err.println("File \""+ objFileName + "\" not found in res; don't use any extention");
        }

        BufferedReader reader = new BufferedReader(isr);

        String line = null;
        int lineID= 0;
        int length=0;
        try{
            while(true){

                line = reader.readLine();
                if (line==null){
                    break;
                }

                for (int i =0; i<line.length();i++){
                    Vector3f colour = null;
                    switch(line.charAt(i)){
                        case '.': colour = new Vector3f(0.5f,0.79f,0.2f);break;
                        case '<': colour = new Vector3f(0.79f,0.2f,0.2f);break;
                        case '>': colour = new Vector3f(0.2f,0.2f,0.79f);break;
                    }
                    squares.add(new BgSquare(new Vector2f(i,lineID),colour));
                }
                lineID++;
                length = line.length();
            }
        }
        catch( IOException e){
            System.err.println("Error reading the file: "+objFileName);
        }
        WorldFactors.numTilesX = length;
        WorldFactors.numTilesY = lineID;

        for (BgSquare square : squares){
            Entity entity = square.getEntity();
            float offsetY = 1.0f/WorldFactors.numTilesY;
            entity.setScale(new Vector2f(1.0f/WorldFactors.numTilesX,1.0f/WorldFactors.numTilesY));
            entity.setPosition(new Vector2f(-1.0f+(entity.getPosition().x*2/ WorldFactors.numTilesX),
                    1-2*offsetY - (entity.getPosition().y*2/ WorldFactors.numTilesY)));
        }

        return squares;
    }
}
