package org.wolf.chestnut.graphics;

import org.wolf.chestnut.comp.Renderer;
import org.wolf.chestnut.vector.IVec2;

import java.nio.ByteBuffer;

public class Texture {
    int texture;

    public Texture(int _texture) {
        texture = _texture;
    }

    public Texture(Renderer renderer, ByteBuffer buffer, IVec2 resolution) {
        Texture _texture = renderer.createTexture(buffer, resolution);

        texture = _texture.getTexture();
    }

    public int getTexture() {
        return texture;
    }
}