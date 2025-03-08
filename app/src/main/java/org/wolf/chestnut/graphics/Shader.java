package org.wolf.chestnut.graphics;

import org.wolf.chestnut.comp.Renderer;
import org.wolf.chestnut.vector.Vec2;
import org.wolf.chestnut.vector.Vec3;
import org.wolf.chestnut.vector.Vec4;
import org.wolf.chestnut.vector.IVec2;
import org.wolf.chestnut.vector.IVec3;
import org.wolf.chestnut.vector.IVec4;
import org.wolf.chestnut.matrix.Mat2;
import org.wolf.chestnut.matrix.Mat3;
import org.wolf.chestnut.matrix.Mat4;
import org.wolf.chestnut.graphics.Texture;

public class Shader {
    private int vertexShader, fragmentShader, program;

    public Shader(int _vertexShader, int _fragmentShader, int _program) {
        vertexShader = _vertexShader;
        fragmentShader = _fragmentShader;
        program = _program;
    }

    public Shader(Renderer _renderer, String _vertexSource, String _fragmentSource) {
        Shader shader = _renderer.createShader(_vertexSource, _fragmentSource);
        vertexShader = shader.getVertexShader();
        fragmentShader = shader.getFragmentShader();
        program = shader.getProgram();
    }

    public void destroy(Renderer renderer) {
        renderer.destroy(this);
    }

    public void setUniform(Renderer renderer, String name, float value) {
        renderer.setUniform(this, name, value);
    }
    public void setUniform(Renderer renderer, String name, int value) {
        renderer.setUniform(this, name, value);
    }
    public void setUniform(Renderer renderer, String name, Vec2 value) {
        renderer.setUniform(this, name, value);
    }
    public void setUniform(Renderer renderer, String name, IVec2 value) {
        renderer.setUniform(this, name, value);
    }
    public void setUniform(Renderer renderer, String name, Vec3 value) {
        renderer.setUniform(this, name, value);
    }
    public void setUniform(Renderer renderer, String name, IVec3 value) {
        renderer.setUniform(this, name, value);
    }
    public void setUniform(Renderer renderer, String name, Vec4 value) {
        renderer.setUniform(this, name, value);
    }
    public void setUniform(Renderer renderer, String name, IVec4 value) {
        renderer.setUniform(this, name, value);
    }
    public void setUniform(Renderer renderer, String name, Mat2 value) {
        renderer.setUniform(this, name, value);
    }
    public void setUniform(Renderer renderer, String name, Mat3 value) {
        renderer.setUniform(this, name, value);
    }
    public void setUniform(Renderer renderer, String name, Mat4 value) {
        renderer.setUniform(this, name, value);
    }
    public void setUniform(Renderer renderer, String name, Texture value, int number) {
        renderer.setUniform(this, name, value, number);
    }

    public int getVertexShader() {return vertexShader;}
    public int getFragmentShader() {return fragmentShader;}
    public int getProgram() {return program;}
}