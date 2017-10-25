package state;

import entities.BgSquare;
import entities.P_Entity;
import main.GameLoop;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Kelvin McClean on 25/10/2017.
 */
public class State implements Serializable{

    public P_Entity player;
    public ArrayList<BgSquare> squares;
    private static State state;

    private State(){};

    public static State getInstance(){
        if (state == null){
            state = new State();
        }
        return state;
    }

    public void writeState(){
        this.player = GameLoop.getPlayer();
        this.squares = GameLoop.getBgSquares();
        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try{
            fos = new FileOutputStream("test.state");
            out = new ObjectOutputStream(fos);
            out.writeObject(this);
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void readState(){
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream("test.state");
            in = new ObjectInputStream(fis);
            state = (State) in.readObject();
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
