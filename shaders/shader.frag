#version 400 core

in vec2 pass_textureCoords;

out vec4 out_Colour;

uniform sampler2D textureSampler;

void main(void)
{

    float borderSize = 0.0001f;
	vec4 colour = texture(textureSampler, pass_textureCoords);
    if (colour!= texture(textureSampler,vec2(pass_textureCoords.x+10,pass_textureCoords.y))){
        colour = vec4(0);
    }

/*	else if (colour.g > colour.b){
	    colour.r = 0;
	    colour.b = 0;
	    colour.g = 1;
	}
	else{
	    colour.g=1;
	    colour.r=1;
	    colour.b=1;
	}*/


    out_Colour = colour;
	
}
