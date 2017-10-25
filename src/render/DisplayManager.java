package render;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {

    public static float width = 1920f;
    public static float height = 1080f;
    private static final int FPS_CAP = 120;
    private static long lastFrameTime;
    private static float delta;


    public static void createDisplay() {

        ContextAttribs attribs = new ContextAttribs(3, 2)
                .withForwardCompatible(true)
                .withProfileCore(true);

        try {
            //Display.setDisplayMode(new DisplayMode((int) width, (int) height));
            //Display.setFullscreen(true);
            //Display.setTitle("basicEngine");
            //Display.setResizable(false);
            setDisplayMode((int)width,(int)height,true);
            Display.create();
            Display.setVSyncEnabled(true);
            Mouse.create();
            //Mouse.setGrabbed(true);
        } catch (LWJGLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        GL11.glViewport(0, 0, (int) width, (int) height);
        lastFrameTime = getCurrentTime();
    }

    public static void updateDisplay() {
        Display.sync(FPS_CAP);
        Display.update();
        long currentFrameTime = getCurrentTime();
        delta = (currentFrameTime - lastFrameTime) / 1000f;
        lastFrameTime = currentFrameTime;

    }

    public static float getDelta() {
        return delta;
    }

    public static void resize() {
        width = Display.getWidth();
        height = Display.getHeight();
        GL11.glViewport(0, 0, (int) width, (int) height);

    }

    public static void closeDisplay() {
        Display.destroy();
    }

    private static long getCurrentTime() {
        return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }


    /**
      * Set the display mode to be used 
      * 
      * @param width The width of the display required
      * @param height The height of the display required
      * @param fullscreen True if we want fullscreen mode
      */
    public static void setDisplayMode(int width, int height, boolean fullscreen) {
 
    // return if requested DisplayMode is already set
    if ((Display.getDisplayMode().getWidth() == width) && 
        (Display.getDisplayMode().getHeight() == height) && 
    (Display.isFullscreen() == fullscreen)) {
        return;
    }
 
    try {
        DisplayMode targetDisplayMode = null;
         
    if (fullscreen) {
        DisplayMode[] modes = Display.getAvailableDisplayModes();
        int freq = 0;
                 
        for (int i=0;i<modes.length;i++) {
            DisplayMode current = modes[i];
                     
        if ((current.getWidth() == width) && (current.getHeight() == height)) {
            if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
                if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
                targetDisplayMode = current;
                freq = targetDisplayMode.getFrequency();
                        }
                    }
 
            // if we've found a match for bpp and frequence against the 
            // original display mode then it's probably best to go for this one
            // since it's most likely compatible with the monitor
            if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
                        (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
                            targetDisplayMode = current;
                            break;
                    }
                }
            }
        } else {
            targetDisplayMode = new DisplayMode(width,height);
        }
 
        if (targetDisplayMode == null) {
            System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
            return;
        }
 
        Display.setDisplayMode(targetDisplayMode);
        Display.setFullscreen(fullscreen);
             
    } catch (LWJGLException e) {
        System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);
    }
    }

}
