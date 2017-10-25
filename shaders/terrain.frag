#version 400 core

in vec2 pass_textureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;
in float visibility;

out vec4 out_Colour;

uniform sampler2D backgroundTexture;
uniform sampler2D rTexture;
uniform sampler2D gTexture;
uniform sampler2D bTexture;
uniform sampler2D blendMap;



uniform float shineDamper;
uniform float reflectivity;
uniform vec3 lightColour;
uniform vec3 skyColour;

void main(void)
{
	if (visibility <= 0.01){
		discard;
	}
	
	vec4 blendMapColour = texture(blendMap, pass_textureCoords);
	
	float backTextureAmount = 1 - (blendMapColour.r + blendMapColour.g + blendMapColour.b);
	vec2 tiledCoords = pass_textureCoords * 40;
	
	vec4 backgroundTextureColour = texture(backgroundTexture, tiledCoords) * backTextureAmount;
	vec4 rTextureColour = texture(rTexture, tiledCoords) * blendMapColour.r;
	vec4 gTextureColour = texture(gTexture, tiledCoords) * blendMapColour.g;
	vec4 bTextureColour = texture(bTexture, tiledCoords) * blendMapColour.b;
	
	vec4 totalColour = backgroundTextureColour + rTextureColour + gTextureColour + bTextureColour;
	
	vec3 n_surfaceNormal = normalize(surfaceNormal);
	vec3 n_toLightVector = normalize(toLightVector);
	
	float n_Dot = dot(n_toLightVector, n_surfaceNormal);
	
	float brightness = max(n_Dot, 0.35);
	brightness = min(brightness, 1.0);
	vec3 diffuse = brightness * lightColour;
	
	vec3 n_toCameraVector = normalize(toCameraVector);
	vec3 lightDirection = -n_toLightVector;
	vec3 reflectedLightDirection = reflect(lightDirection,n_surfaceNormal);
	
	float specularFactor = dot(reflectedLightDirection,n_toCameraVector);
	specularFactor = max(specularFactor,0.0);
	float dampedFactor = pow(specularFactor, shineDamper);
	vec3 finalSpecular = dampedFactor * reflectivity * lightColour;
	
	out_Colour = vec4(diffuse,1.0) * totalColour + vec4(finalSpecular,1.0);
	out_Colour = mix(vec4(skyColour,1.0),out_Colour,visibility);
	
}