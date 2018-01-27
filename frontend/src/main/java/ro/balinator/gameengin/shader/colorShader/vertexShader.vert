in vec3 position;
in vec3 colour;

out vec3 new_colour;

void main(void){
	gl_Position = vec4(position,1.0);
	new_colour = colour;
}