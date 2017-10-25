package main;

import entities.Camera;
import entities.Entity;
import entities.GameMap;
import enums.ModelPath;
import enums.TexturePath;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import org.lwjgl.util.vector.Vector3f;
import render.DisplayManager;
import render.MasterRenderer;
import utils.MatrixCreator;
import utils.WorldFactors;
import utils.factories.EntityFactory;
import utils.fileLoader.Loader;

import java.security.Key;

public class GameLoop {

    private static Camera camera;
    private static MasterRenderer masterRenderer;
    private static GameMap gameMap;

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
        gameMap = new GameMap();
        camera = new Camera();
    }

    private static void update() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        camera.update();
        masterRenderer.processEntity(gameMap.getEntity());
        pollKeyboard();
        pollMouse();
        pollEvents();
    }

    private static void draw() {
        masterRenderer.render(camera);
        DisplayManager.updateDisplay();

    }


    private static void close() {
        masterRenderer.cleanUp();
        Loader.cleanUp();
        DisplayManager.closeDisplay();
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
            MatrixCreator.createProjectionMatrix();
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

        }
    }
}
