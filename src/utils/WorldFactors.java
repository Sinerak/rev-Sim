package utils;

import org.lwjgl.util.vector.Vector3f;

public class WorldFactors {

	public static final float SKY_COLOUR_R = 0.88f;
	public static final float SKY_COLOUR_G = 0.88f;
	public static final float SKY_COLOUR_B = 0.9f;

	public static final Vector3f SKY_COLOUR_VEC = new Vector3f(SKY_COLOUR_R,SKY_COLOUR_G,SKY_COLOUR_B);

	public static int numTilesX = -1;
	public static int numTilesY = -1;

	public static boolean gameRunning = true;

}
