package main;

import entities.BgSquare;
import entities.P_Entity;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import org.lwjgl.util.vector.Vector2f;
import render.DisplayManager;
import render.MasterRenderer;
import state.State;
import utils.MatrixCreator;
import utils.WorldFactors;
import utils.fileLoader.BackgroundLoader;
import utils.fileLoader.Loader;

import java.util.ArrayList;
import java.util.Random;

public class GameLoop {

    private static MasterRenderer masterRenderer;
    private static ArrayList<BgSquare> squares;
    private static P_Entity player;
    private static Random random = new Random();

    public static void main(String[] args) {
        setup();
        while (WorldFactors.gameRunning) {
            update();
            draw();
        }
        close();
    }


    private static void setup() {
        DisplayManager.createDisplay();
        GL11.glClearColor(WorldFactors.SKY_COLOUR_R, WorldFactors.SKY_COLOUR_G, WorldFactors.SKY_COLOUR_B, 1.0f);
        masterRenderer = new MasterRenderer();
        squares = BackgroundLoader.loadMap("test");
        for (BgSquare square : squares){
            masterRenderer.processBg(square);
        }

        player = new P_Entity(new Vector2f(0.0f,5.0f));
    }

    private static void update() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        if (random.nextInt(100) >= 99) {
            if (random.nextInt(100) > 50){
                player.moveUp(true);
            }
            else{
                player.moveDown(true);
            }
        }


        masterRenderer.processEntity(player.getEntity());
        pollKeyboard();
        pollMouse();
        pollEvents();
    }

    private static void draw() {
        masterRenderer.render();
        DisplayManager.updateDisplay();

    }


    private static void close() {
        masterRenderer.cleanUp();
        Loader.cleanUp();
        DisplayManager.closeDisplay();
    }

    public static P_Entity getPlayer(){
        return player;
    }

    public static ArrayList<BgSquare> getBgSquares(){
        return squares;
    }

    private static void pollMouse() {

        while (Mouse.next()) {
            if (Mouse.isButtonDown(0) && Mouse.getEventButtonState()) {
                //Do nothing for now

            }

        }

    }

    private static void pollEvents() {
        if (Display.isCloseRequested()) {
            WorldFactors.gameRunning = false;
        }
        if (Display.getWidth() != DisplayManager.width || Display.getHeight() != DisplayManager.height) {
            try{
                DisplayManager.resize();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            masterRenderer.resize();
        }
    }

    private static void pollKeyboard() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_TAB) {
                if (Keyboard.getEventKeyState()) {
                    //Do nothing for now
                    Mouse.setGrabbed(!Mouse.isGrabbed());
                }

            } else if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
                WorldFactors.gameRunning = false;

            }
            else if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT){
                State.getInstance().writeState();
            }
            else if (Keyboard.getEventKey() == Keyboard.KEY_LEFT){
                State.readState();
                player = State.getInstance().player;
                squares = State.getInstance().squares;
            }

        }
    }
}
