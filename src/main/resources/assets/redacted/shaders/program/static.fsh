#version 150

uniform sampler2D DiffuseSampler;
uniform vec2 uTextureSize;
uniform float uTime;
uniform float uValue;
uniform float uCloudy;

in vec2 texCoord;
out vec4 fragColor;

const float PHI = 1.61803398874989484820459;
const float PIXEL_SIZE = 4.0;

float gold_noise(in vec2 xy, in float seed) {
    return fract(tan(distance(xy * PHI, xy) * seed) * xy.x);
}

void main() {
    vec4 base = texture(DiffuseSampler, texCoord);

    vec2 centered = texCoord - 0.5;
    float dist = length(centered) * 2.0;

    if (uCloudy >= 1.0) {
        // Photosensitive Mode
        float vignette = smoothstep(0.5, 1.0, dist) * uValue;
        vec3 darkened = mix(base.rgb, vec3(0.0), vignette);
        fragColor = vec4(darkened, base.a);
    } else {
        float seed = fract(uTime * 0.5);
        vec2 scaled = texCoord * uTextureSize;
        scaled = floor(scaled / PIXEL_SIZE) * PIXEL_SIZE;
        float noise = gold_noise(scaled, seed + 0.1);
        float vignette = smoothstep(1.0 - uValue, 1.0 + uValue, dist);
        float staticOpacity = vignette * uValue;
        vec3 mixed = mix(base.rgb, vec3(noise), staticOpacity);
        fragColor = vec4(mixed, base.a);
    }
}
