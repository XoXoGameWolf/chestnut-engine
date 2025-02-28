package org.wolf.chestnut.graphics;

import org.wolf.chestnut.comp.Renderer;
import org.wolf.chestnut.vector.Vec2;
import org.wolf.chestnut.vector.IVec2;
import org.wolf.chestnut.vector.Vec3;
import org.wolf.chestnut.vector.IVec3;
import org.wolf.chestnut.vector.Vec4;
import org.wolf.chestnut.vector.IVec4;

public class Buffer {
    private int buffer;
    private int size;
    private int count;
    private int type;

    public Buffer(int _buffer, int _size, int _count, int _type) {
        buffer = _buffer;
        size = _size;
        count = _count;
        type = _type;
    }

    public Buffer(Renderer _renderer, float[] _data) {
        Buffer _buffer = _renderer.createBuffer(_data);

        buffer = _buffer.getBuffer();
        size = _buffer.getSize();
        count = _buffer.getCount();
        type = _buffer.getType();
    }

    public Buffer(Renderer _renderer, int[] _data) {
        Buffer _buffer = _renderer.createBuffer(_data);
    
        buffer = _buffer.getBuffer();
        size = _buffer.getSize();
        count = _buffer.getCount();
        type = _buffer.getType();
    }

    public Buffer(Renderer _renderer, Vec2[] _data) {
        Buffer _buffer = _renderer.createBuffer(_data);

        buffer = _buffer.getBuffer();
        size = _buffer.getSize();
        count = _buffer.getCount();
        type = _buffer.getType();
    }
    
    public Buffer(Renderer _renderer, IVec2[] _data) {
        Buffer _buffer = _renderer.createBuffer(_data);
   
        buffer = _buffer.getBuffer();
        size = _buffer.getSize();
        count = _buffer.getCount();
        type = _buffer.getType();
    }
    
    public Buffer(Renderer _renderer, Vec3[] _data) {
        Buffer _buffer = _renderer.createBuffer(_data);
    
        buffer = _buffer.getBuffer();
        size = _buffer.getSize();
        count = _buffer.getCount();
        type = _buffer.getType();
    }
    
    public Buffer(Renderer _renderer, IVec3[] _data) {
        Buffer _buffer = _renderer.createBuffer(_data);
    
        buffer = _buffer.getBuffer();
        size = _buffer.getSize();
        count = _buffer.getCount();
        type = _buffer.getType();
    }
    
    public Buffer(Renderer _renderer, Vec4[] _data) {
        Buffer _buffer = _renderer.createBuffer(_data);
   
        buffer = _buffer.getBuffer();
        size = _buffer.getSize();
        count = _buffer.getCount();
        type = _buffer.getType();
    }
    
    public Buffer(Renderer _renderer, IVec4[] _data) {
        Buffer _buffer = _renderer.createBuffer(_data);
   
        buffer = _buffer.getBuffer();
        size = _buffer.getSize();
        count = _buffer.getCount();
        type = _buffer.getType();
    }

    public void destroy(Renderer renderer) {
        renderer.destroy(this);
    }

    public int getBuffer() {
        return buffer;
    }
    public int getSize() {
        return size;
    }
    public int getCount() {
        return count;
    }
    public int getType() {
        return type;
    }
}