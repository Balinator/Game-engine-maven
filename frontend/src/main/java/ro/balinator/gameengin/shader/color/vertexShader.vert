in vec3 position;
in vec3 colour;

out vec3 new_colour;

uniform mat4 transformationMatrix;

void main(void){
	gl_Position = transformationMatrix * vec4(position,1.0);
	new_colour = colour;
}