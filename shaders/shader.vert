#version 400 core

in vec2 position;
in vec2 textureCoords;
in vec3 normal;

out vec2 pass_textureCoords;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

uniform float numberOfRows;
uniform vec2 offset;

void main(void)
{
	vec4 worldPosition = transformationMatrix * vec4(position,0,1.0);

	gl_Position =  worldPosition;
	pass_textureCoords = textureCoords;
	

	
}