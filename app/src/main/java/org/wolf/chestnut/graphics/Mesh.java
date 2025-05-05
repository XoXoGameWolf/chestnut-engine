package org.wolf.chestnut.graphics;

import org.wolf.chestnut.comp.Renderer;

import org.wolf.chestnut.graphics.Buffer;
import org.wolf.chestnut.graphics.Shader;

import org.wolf.chestnut.vector.Vec2;
import org.wolf.chestnut.vector.Vec3;

public class Mesh {
    private int vertexArray;
    private Buffer[] buffers;
    private Buffer indexBuffer;

    public Mesh(int _vertexArray, Buffer[] _buffers, Buffer _indexBuffer) {
        vertexArray = _vertexArray;
        buffers = _buffers;
        indexBuffer = _indexBuffer;
    }

    public Mesh(Renderer _renderer, Buffer[] _buffers, Buffer _indexBuffer) {
        Mesh mesh = _renderer.createMesh(_buffers, _indexBuffer);

        vertexArray = mesh.getVertexArray();
        buffers = mesh.getBuffers();
        indexBuffer = mesh.getIndexBuffer();
    }

    public void destroy(Renderer renderer) {
        renderer.destroy(this);
    }

    public void render(Renderer renderer, Shader shader) {
        renderer.render(this, shader);
    }

    public int getVertexArray() {
        return vertexArray;
    }

    public Buffer[] getBuffers() {
        return buffers;
    }

    public Buffer getIndexBuffer() {
        return indexBuffer;
    }

    public static Mesh getQuad(Renderer renderer) {
        return new Mesh(renderer, new Buffer[] {new Buffer(renderer, new Vec3[] {
            new Vec3(0f, 0f, 0f),
            new Vec3(1f, 0f, 0f),
            new Vec3(0f, 1f, 0f),
            new Vec3(1f, 1f, 0f)
        }), new Buffer(renderer, new Vec2[] {
            new Vec2(0f, 0f),
            new Vec2(1f, 0f),
            new Vec2(0f, 1f),
            new Vec2(1f, 1f)
        })}, new Buffer(renderer, new int[] {
            1, 3, 0,
            0, 3, 2
        }));
    }
}