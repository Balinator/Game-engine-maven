in vec3 position;
in vec2 textureCoordinates;

out vec2 new_textureCoordinates;

void main(void){
	gl_Position = vec4(position,1.0);
	//colour = vec3(position.x+0.5,0.0,position.y+0.5);
	new_textureCoordinates = textureCoordinates;
}