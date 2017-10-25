#version 140

in

out vec4 out_Color;

uniform vec3 fragColour;

void main(void){

    vec4 colour = (fragColour,1);
	out_Color = colour;

}