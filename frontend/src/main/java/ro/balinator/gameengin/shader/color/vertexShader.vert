in vec3 position;
in vec3 colour;

out vec3 new_colour;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main(void){
	gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position,1.0);
	new_colour = colour;
}