package org.wolf.chestnut.graphics;

import org.wolf.chestnut.comp.Renderer;
import org.wolf.chestnut.graphics.Buffer;
import org.wolf.chestnut.graphics.Shader;

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
}