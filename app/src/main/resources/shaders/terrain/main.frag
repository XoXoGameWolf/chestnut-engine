#version 460

in vec4 color;
in vec3 normal;
out vec4 out_color;

void main() {
    vec3 light_dir = normalize(vec3(-0.5, 1, 0));
    float diffuse = clamp(dot(normal, light_dir), 0.2, 1);

    vec4 col = color;
    col.xyz *= diffuse;

    out_color = col;
}