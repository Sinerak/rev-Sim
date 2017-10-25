package utils.fileLoader;

import entities.BgSquare;
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
        try{
            while(true){
                line = reader.readLine();
                if (line==null){
                    break;
                }

                for (int i =0; i<line.length();i++){
                    Vector3f colour = null;
                    switch(line.charAt(i)){
                        case '.': colour = new Vector3f(0,1,0);
                    }
                    squares.add(new BgSquare(new Vector3f(i,lineID,0),colour));
                }
                lineID++;
            }
        }
        catch( IOException e){
            System.err.println("Error reading the file: "+objFileName);
        }
        WorldFactors.numTilesX = line.length();
        WorldFactors.numTilesY = lineID - 1;
        return squares;
    }
}
