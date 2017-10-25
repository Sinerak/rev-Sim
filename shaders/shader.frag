#version 400 core

in vec2 pass_textureCoords;

out vec4 out_Colour;

uniform sampler2D textureSampler;

void main(void)
{


    struct Test
    {
        vec4 colour;
        vec4 ownerColour;
    };


    float borderSize = 0.0001f;
	vec4 colour = texture(textureSampler, pass_textureCoords);

	vec4 colourUp = texture(textureSampler,vec2(pass_textureCoords.x,pass_textureCoords.y+borderSize));
    vec4 colourDown = texture(textureSampler,vec2(pass_textureCoords.x,pass_textureCoords.y-borderSize));
	vec4 colourRight = texture(textureSampler,vec2(pass_textureCoords.x+borderSize,pass_textureCoords.y));
	vec4 colourLeft = texture(textureSampler,vec2(pass_textureCoords.x-borderSize,pass_textureCoords.y));

	if (colourUp!=colour || colourDown!=colour || colourLeft!=colour || colourRight!=colour){
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
