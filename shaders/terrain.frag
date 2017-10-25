#version 140

in vec2 pass_textureCoords;

out vec4 out_Color;

uniform vec3 fragColour;
uniform sampler2D guiTexture;

void main(void){

    vec4 colour = vec4(fragColour,1);
    if (pass_textureCoords.x > 0.99f || pass_textureCoords.x < 0.01f || pass_textureCoords.y > 0.99f || pass_textureCoords.y < 0.01f){
        colour = vec4(0,0,0,1);

    }
	out_Color = colour;

}