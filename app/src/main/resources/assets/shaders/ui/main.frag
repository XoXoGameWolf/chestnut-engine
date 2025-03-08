#version 460

uniform sampler2D texture0;

in vec2 uv;
out vec4 out_color;

void main() {
    vec4 color = texture(texture0, uv);

    if(color.w == 0) {
        discard;
    }

    out_color = color;
}