package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Display;
import org.lwjgl.util.vector.Vector3f;
import render.DisplayManager;
import utils.WorldFactors;

import java.awt.*;


public class Camera {

	private static final float MAX_HEIGHT = 2200;
	private static final float MIN_HEIGHT = 100;
	private static final float ZOOM_SPEED = 350f;

	private static final float MAX_SPEED = 40f;
	private static final float SPEED_INCREASE = 2f;
	private static final float MAP_TOP = 0f;
	private static final float MAP_BOTTOM = 2048f;
	private static final float MAX_PITCH = 90f;
	private static final float MIN_PITCH = 27f;

	private float vectorZoom = 0;
	private float vectorHorz = 0;
	private float vectorVert = 0;
	boolean isMovingVert = false;
	boolean isMovingHorz = false;
	float positionalMultiplier = 0;

	private Vector3f position = new Vector3f(2048, 2400, 1024);
	private float focusPosition = position.z;
	private float pitch = 90;
	private float yaw;
	private float roll;
	private float targetPitch;



	public void update(){
		positionalMultiplier = (3000f-this.getPosition().y) *.0001f;


		move();
		zoom();
		pitch();
		focus();
	}




	private void checkMovement() {



		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			isMovingHorz = true;
			vectorHorz = vectorHorz <= -MAX_SPEED ? -MAX_SPEED : vectorHorz - SPEED_INCREASE;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			isMovingHorz = true;
			vectorHorz = vectorHorz >= MAX_SPEED ? MAX_SPEED : vectorHorz + SPEED_INCREASE;
		}
		else{
			isMovingHorz = false;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)){

			isMovingVert = true;
			vectorVert = vectorVert <= -MAX_SPEED ? -MAX_SPEED : vectorVert - SPEED_INCREASE;

		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			isMovingVert = true;
			vectorVert = vectorVert >= MAX_SPEED ? MAX_SPEED : vectorVert + SPEED_INCREASE;

		}
		else{
			isMovingVert = false;
		}

		checkBounds();
	}

	private void checkBounds() {
		if (vectorVert > 0 && focusPosition > MAP_BOTTOM){
			vectorVert = 0;
			focusPosition= MAP_BOTTOM;
		}
		else if (vectorVert < 0 && focusPosition < MAP_TOP){
			vectorVert = 0;
			focusPosition= MAP_TOP;
		}

	}


	private void move(){
		checkMovement();
		if (!isMovingHorz && vectorHorz!=0){
			vectorHorz = vectorHorz < 0 ?
					(vectorHorz > -SPEED_INCREASE ? 0 : vectorHorz/1.05f) :
					(vectorHorz < SPEED_INCREASE ? 0 : vectorHorz/1.05f);
		}

		if (!isMovingVert && vectorVert!=0){
			vectorVert = vectorVert < 0 ?
					(vectorVert > -SPEED_INCREASE ? 0 : vectorVert/1.1f) :
					(vectorVert < SPEED_INCREASE ? 0 : vectorVert/1.1f);
		}
		if (vectorHorz!=0){
			position.x+=vectorHorz;
		}
		if (vectorVert!=0){
			focusPosition+=vectorVert;
		}
	}

	private void zoom() {
		//System.out.println(positionalMultiplier);
		float zoomLevel = -Math.max(-1, Math.min(Mouse.getDWheel(), 1)) * (ZOOM_SPEED / positionalMultiplier) * DisplayManager.getDelta();
		calcZoom(zoomLevel);
		if (vectorZoom!=0){
			float movement = vectorZoom * DisplayManager.getDelta();
			this.position.y+= movement;

			vectorZoom-=movement;
			if (Math.abs(vectorZoom) < 0.1f) {
				vectorZoom = 0;
			}
		}

		//System.out.println(position.y);
		if (this.position.y >MAX_HEIGHT){
			this.position.y=MAX_HEIGHT;
			vectorZoom = 0;
		}
		if (this.position.y <MIN_HEIGHT){
			this.position.y = MIN_HEIGHT;
			vectorZoom = 0;
		}

		
	}

	private void calcZoom(float zoom) {
		//System.out.println(vectorZoom);

		vectorZoom += zoom;
		vectorZoom = vectorZoom < 0 ?
				(vectorZoom >-1 ? 0 : vectorZoom) :
				(vectorZoom < 1 ? 0 : vectorZoom);



	}


	private void pitch() {
		float min_pitch_height = MIN_HEIGHT;
		float max_pitch_height = MAX_HEIGHT - 400f;

		float cappedPosition = Math.min(Math.max(position.y,min_pitch_height),max_pitch_height);
		targetPitch = (((cappedPosition - min_pitch_height) / (max_pitch_height - min_pitch_height)) * (MAX_PITCH - MIN_PITCH) ) + MIN_PITCH;
		pitch = Math.abs(pitch-targetPitch) < 0.01 ? targetPitch : pitch + ((targetPitch - pitch) * DisplayManager.getDelta()*2);
		//System.out.println(targetPitch);
	}


	private void focus() {

		position.z = focusPosition -((pitch - MAX_PITCH) * 1f);
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}



}
