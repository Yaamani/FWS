varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;

uniform float u_percentage;

void main() {
    vec4 color = texture2D(u_texture, v_texCoords);
    float avg = (color.r + color.g + color.b)/3.0;

    vec3 distance = vec3(avg - color.r, avg - color.g, avg - color.b);

    gl_FragColor = v_color * vec4(color.r + u_percentage * distance.r,
                                    color.g + u_percentage * distance.g,
                                    color.b + u_percentage * distance.b,
                                    color.a);
}
