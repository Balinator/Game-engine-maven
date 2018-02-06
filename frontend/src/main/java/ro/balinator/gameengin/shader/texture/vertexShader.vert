in vec3 position;
in vec2 textureCoordinates;

out vec2 new_textureCoordinates;

uniform mat4 transformationMatrix;

void main(void){
	gl_Position = transformationMatrix * vec4(position,1.0);
	new_textureCoordinates = textureCoordinates;
}