#version 460

uniform mat4 projection;
uniform mat4 view;
uniform mat4 model;

in vec3 in_position;
in vec4 in_color;
in vec3 in_normal;
out vec4 color;
out vec3 normal; 

void main() {
    color = in_color;
    normal = in_normal;
    
    vec4 position = view * model * vec4(in_position, 1);
    position.z = -position.z;
    gl_Position = projection * position;
}