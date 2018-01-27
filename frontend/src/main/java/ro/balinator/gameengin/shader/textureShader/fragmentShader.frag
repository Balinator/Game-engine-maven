in vec2 new_textureCoordinates;

out vec4 out_Color;

uniform sampler2D textureSampler;

void main(void){
	out_Color = texture(textureSampler,new_textureCoordinates);
}