package org.wolf.chestnut.comp;

import org.wolf.chestnut.vector.Vec2;
import org.wolf.chestnut.vector.Vec3;
import org.wolf.chestnut.vector.Vec4;
import org.wolf.chestnut.vector.IVec2;
import org.wolf.chestnut.vector.IVec3;
import org.wolf.chestnut.vector.IVec4;
import org.wolf.chestnut.matrix.Mat2;
import org.wolf.chestnut.matrix.Mat3;
import org.wolf.chestnut.matrix.Mat4;
import org.wolf.chestnut.graphics.Buffer;
import org.wolf.chestnut.graphics.Mesh;
import org.wolf.chestnut.util.Logger;
import org.wolf.chestnut.graphics.Shader;
import org.wolf.chestnut.graphics.Texture;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL46;
import org.lwjgl.system.MemoryUtil;

import java.util.ArrayList;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;

public class Renderer {
    ArrayList<Buffer> buffers = new ArrayList<Buffer>();
    ArrayList<Mesh> meshes = new ArrayList<Mesh>();
    ArrayList<Shader> shaders = new ArrayList<Shader>();
    ArrayList<Texture> textures = new ArrayList<Texture>();

    Vec4 color = new Vec4(0f, 0f, 0f, 1f);

    public Renderer() {
        GL46.glEnable(GL46.GL_DEPTH_TEST);
        GL46.glEnable(GL46.GL_BLEND);

        GL46.glBlendFunc(GL46.GL_SRC_ALPHA, GL46.GL_ONE_MINUS_SRC_ALPHA);
        
        Logger.log("Renderer was created.");
    }

    public Vec4 getColor() {
        return color;
    }

    public void setColor(Vec4 _color) {
        color = _color;
    }

    public void update() {
        GL46.glClearColor(color.getX(), color.getY(), color.getZ(), color.getW());
        GL46.glClear(GL46.GL_COLOR_BUFFER_BIT | GL46.GL_DEPTH_BUFFER_BIT);
    }

    public void destroy() {
        for(int i = 0; i < buffers.size(); i++) {
            GL46.glDeleteBuffers(buffers.get(i).getBuffer());
        }
        
        for(int i = 0; i < meshes.size(); i++) {
            GL46.glDeleteVertexArrays(meshes.get(i).getVertexArray());
        }

        for(int i = 0; i < shaders.size(); i++) {
            GL46.glDetachShader(shaders.get(i).getProgram(), shaders.get(i).getVertexShader());
            GL46.glDetachShader(shaders.get(i).getProgram(), shaders.get(i).getFragmentShader());
            GL46.glDeleteProgram(shaders.get(i).getProgram());

            GL46.glDeleteShader(shaders.get(i).getVertexShader());
            GL46.glDeleteShader(shaders.get(i).getFragmentShader());
        }

        for(int i = 0; i < textures.size(); i++) {
            GL46.glDeleteTextures(textures.get(i).getTexture());
        }

        Logger.log("Renderer was destroyed.");
    }

    public void destroy(Buffer buffer) {
        buffers.remove(buffer);
        GL46.glDeleteBuffers(buffer.getBuffer());
    }

    public void destroy(Mesh mesh) {
        meshes.remove(mesh);
        GL46.glDeleteVertexArrays(mesh.getVertexArray());
    }

    public void destroy(Shader shader) {
        shaders.remove(shader);

        GL46.glDetachShader(shader.getProgram(), shader.getVertexShader());
        GL46.glDetachShader(shader.getProgram(), shader.getFragmentShader());
        GL46.glDeleteProgram(shader.getProgram());

        GL46.glDeleteShader(shader.getVertexShader());
        GL46.glDeleteShader(shader.getFragmentShader());
    }

    public void destroy(Texture texture) {
        textures.remove(texture);
        GL46.glDeleteTextures(texture.getTexture());
    }

    public void render(Mesh mesh, Shader shader) {
        GL46.glUseProgram(shader.getProgram());
        GL46.glBindVertexArray(mesh.getVertexArray());
        for(int i = 0; i < mesh.getBuffers().length; i++) {
            GL46.glEnableVertexAttribArray(i);
        }
        GL46.glDrawElements(GL46.GL_TRIANGLES, mesh.getIndexBuffer().getCount(), GL46.GL_UNSIGNED_INT, 0);
        for(int i = 0; i < mesh.getBuffers().length; i++) {
            GL46.glDisableVertexAttribArray(i);
        }
        GL46.glBindVertexArray(0);
        GL46.glUseProgram(0);
    }

    public void setUniform(Shader shader, String name, float value) {
        GL46.glUseProgram(shader.getProgram());
        int location = GL46.glGetUniformLocation(shader.getProgram(), name);
        GL46.glUniform1f(location, value);
        GL46.glUseProgram(0);
    }

    public void setUniform(Shader shader, String name, int value) {
        GL46.glUseProgram(shader.getProgram());
        int location = GL46.glGetUniformLocation(shader.getProgram(), name);
        GL46.glUniform1i(location, value);
        GL46.glUseProgram(0);
    }

    public void setUniform(Shader shader, String name, Vec2 value) {
        GL46.glUseProgram(shader.getProgram());
        int location = GL46.glGetUniformLocation(shader.getProgram(), name);
        GL46.glUniform2f(location, value.getX(), value.getY());
        GL46.glUseProgram(0);
    }

    public void setUniform(Shader shader, String name, IVec2 value) {
        GL46.glUseProgram(shader.getProgram());
        int location = GL46.glGetUniformLocation(shader.getProgram(), name);
        GL46.glUniform2i(location, value.getX(), value.getY());
        GL46.glUseProgram(0);
    }

    public void setUniform(Shader shader, String name, Vec3 value) {
        GL46.glUseProgram(shader.getProgram());
        int location = GL46.glGetUniformLocation(shader.getProgram(), name);
        GL46.glUniform3f(location, value.getX(), value.getY(), value.getZ());
        GL46.glUseProgram(0);
    }

    public void setUniform(Shader shader, String name, IVec3 value) {
        GL46.glUseProgram(shader.getProgram());
        int location = GL46.glGetUniformLocation(shader.getProgram(), name);
        GL46.glUniform3i(location, value.getX(), value.getY(), value.getZ());
        GL46.glUseProgram(0);
    }

    public void setUniform(Shader shader, String name, Vec4 value) {
        GL46.glUseProgram(shader.getProgram());
        int location = GL46.glGetUniformLocation(shader.getProgram(), name);
        GL46.glUniform4f(location, value.getX(), value.getY(), value.getZ(), value.getW());
        GL46.glUseProgram(0);
    }

    public void setUniform(Shader shader, String name, IVec4 value) {
        GL46.glUseProgram(shader.getProgram());
        int location = GL46.glGetUniformLocation(shader.getProgram(), name);
        GL46.glUniform4i(location, value.getX(), value.getY(), value.getZ(), value.getW());
        GL46.glUseProgram(0);
    }

    public void setUniform(Shader shader, String name, Mat2 value) {
        FloatBuffer data = MemoryUtil.memAllocFloat(4);
        data.put(new float[] {
            value.getX().getX(), value.getY().getX(),
            value.getX().getY(), value.getY().getY()
        }).flip();

        GL46.glUseProgram(shader.getProgram());
        int location = GL46.glGetUniformLocation(shader.getProgram(), name);
        GL46.glUniformMatrix2fv(location, false, data);
        GL46.glUseProgram(0);
    }

    public void setUniform(Shader shader, String name, Mat3 value) {
        FloatBuffer data = MemoryUtil.memAllocFloat(9);
        data.put(new float[] {
            value.getX().getX(), value.getY().getX(), value.getZ().getX(),
            value.getX().getY(), value.getY().getY(), value.getZ().getY(),
            value.getX().getZ(), value.getY().getZ(), value.getZ().getZ()
        }).flip();

        GL46.glUseProgram(shader.getProgram());
        int location = GL46.glGetUniformLocation(shader.getProgram(), name);
        GL46.glUniformMatrix3fv(location, false, data);
        GL46.glUseProgram(0);
    }

    public void setUniform(Shader shader, String name, Mat4 value) {
        FloatBuffer data = MemoryUtil.memAllocFloat(16);
        data.put(new float[] {
            value.getX().getX(), value.getY().getX(), value.getZ().getX(), value.getW().getX(),
            value.getX().getY(), value.getY().getY(), value.getZ().getY(), value.getW().getY(),
            value.getX().getZ(), value.getY().getZ(), value.getZ().getZ(), value.getW().getZ(),
            value.getX().getW(), value.getY().getW(), value.getZ().getW(), value.getW().getW()
        }).flip();

        GL46.glUseProgram(shader.getProgram());
        int location = GL46.glGetUniformLocation(shader.getProgram(), name);
        GL46.glUniformMatrix4fv(location, false, data);
        GL46.glUseProgram(0);
    }

    public void setUniform(Shader shader, String name, Texture value, int number) {
        GL46.glUseProgram(shader.getProgram());
        int location = GL46.glGetUniformLocation(shader.getProgram(), name);
        GL46.glUniform1i(location, number);
        GL46.glActiveTexture(GL46.GL_TEXTURE0 + number);
        GL46.glBindTexture(GL46.GL_TEXTURE_2D, value.getTexture());
        GL46.glUseProgram(0);
    }

    public Shader createShader(String vertexSource, String fragmentSource) {
        // create vertex shader
        int vertexShader = GL46.glCreateShader(GL46.GL_VERTEX_SHADER);

        if(vertexShader == 0) {
            Logger.log("Vertex shader failed to be created.");
            System.exit(-1);
        }

        // compile vertex shader
        GL46.glShaderSource(vertexShader, vertexSource);
        GL46.glCompileShader(vertexShader);

        if(GL46.glGetShaderi(vertexShader, GL46.GL_COMPILE_STATUS) == GL46.GL_FALSE) {
            Logger.log("Vertex shader failed to be compiled. Info Log: " + GL46.glGetShaderInfoLog(vertexShader, 1024));
            System.exit(-1);
            
        }

        // create fragment shader
        int fragmentShader = GL46.glCreateShader(GL46.GL_FRAGMENT_SHADER);

        if(fragmentShader == 0) {
            Logger.log("Fragment shader failed to be created.");
            System.exit(-1);
        }

        // compile fragment shader
        GL46.glShaderSource(fragmentShader, fragmentSource);
        GL46.glCompileShader(fragmentShader);

        if(GL46.glGetShaderi(fragmentShader, GL46.GL_COMPILE_STATUS) == GL46.GL_FALSE) {
            Logger.log("Fragment shader failed to be compiled. Info Log: " + GL46.glGetShaderInfoLog(fragmentShader, 1024));
            System.exit(-1);
        }

        // create shader program
        int program = GL46.glCreateProgram();

        // attach vertex and fragment shader
        GL46.glAttachShader(program, vertexShader);
        GL46.glAttachShader(program, fragmentShader);

        // link shader program
        GL46.glLinkProgram(program);

        if(GL46.glGetProgrami(program, GL46.GL_LINK_STATUS) == GL46.GL_FALSE) {
            Logger.log("Shader program failed to be linked. Info Log: " + GL46.glGetProgramInfoLog(program, 1024));
            System.exit(-1);
        }

        // return shader
        Shader shader = new Shader(vertexShader, fragmentShader, program);
        shaders.add(shader);
        return new Shader(vertexShader, fragmentShader, program);
    }

    public Mesh createMesh(Buffer[] buffers, Buffer indexBuffer) {
        // create and bind vertex array
        int vertexArray = GL46.glGenVertexArrays();
        GL46.glBindVertexArray(vertexArray);

        // bind index buffer
        GL46.glBindBuffer(GL46.GL_ELEMENT_ARRAY_BUFFER, indexBuffer.getBuffer());

        // setup buffers
        for(int i = 0; i < buffers.length; i++) {
            GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, buffers[i].getBuffer());
            GL46.glVertexAttribPointer(i, buffers[i].getSize(), buffers[i].getType(), false, 0, 0);
            GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, 0);
        }

        // unbind and return vertex array as mesh
        GL46.glBindVertexArray(0);
        Mesh mesh = new Mesh(vertexArray, buffers, indexBuffer);
        meshes.add(mesh);
        return mesh;
    }

    public Buffer createBuffer(float[] data) {
        // create data buffer
        FloatBuffer dataBuffer = MemoryUtil.memAllocFloat(data.length);
        dataBuffer.put(data).flip();

        // create buffer
        int buffer = GL46.glGenBuffers();

        // store data in buffer
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, buffer);
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, dataBuffer, GL46.GL_STATIC_DRAW);
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, 0);

        // return buffer
        Buffer buffer2 = new Buffer(buffer, 1, data.length, GL46.GL_FLOAT);
        buffers.add(buffer2);
        return buffer2;
    }

    public Buffer createBuffer(int[] data) {
        // create data buffer
        IntBuffer dataBuffer = MemoryUtil.memAllocInt(data.length);
        dataBuffer.put(data).flip();

        // create buffer
        int buffer = GL46.glGenBuffers();

        // store data in buffer
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, buffer);
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, dataBuffer, GL46.GL_STATIC_DRAW);
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, 0);

        // return buffer
        Buffer buffer2 = new Buffer(buffer, 1, data.length, GL46.GL_UNSIGNED_INT);
        buffers.add(buffer2);
        return buffer2;
    }

    public Buffer createBuffer(Vec2[] data) {
        // create new array
        float[] data2 = new float[data.length * 2];

        // store data in array
        for(int i = 0; i < data.length; i++) {
            data2[i * 2] = data[i].getX();
            data2[i * 2 + 1] = data[i].getY();
        }

        // create data buffer
        FloatBuffer dataBuffer = MemoryUtil.memAllocFloat(data2.length);
        dataBuffer.put(data2).flip();

        // create buffer
        int buffer = GL46.glGenBuffers();

        // store data in buffer
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, buffer);
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, dataBuffer, GL46.GL_STATIC_DRAW);
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, 0);

        // return buffer
        Buffer buffer2 = new Buffer(buffer, 2, data.length, GL46.GL_FLOAT);
        buffers.add(buffer2);
        return buffer2;
    }

    public Buffer createBuffer(IVec2[] data) {
        // create new array
        int[] data2 = new int[data.length * 2];

        // store data in array
        for(int i = 0; i < data.length; i++) {
            data2[i * 2] = data[i].getX();
            data2[i * 2 + 1] = data[i].getY();
        }

        // create data buffer
        IntBuffer dataBuffer = MemoryUtil.memAllocInt(data2.length);
        dataBuffer.put(data2).flip();

        // create buffer
        int buffer = GL46.glGenBuffers();

        // store data in buffer
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, buffer);
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, dataBuffer, GL46.GL_STATIC_DRAW);
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, 0);

        // return buffer
        Buffer buffer2 = new Buffer(buffer, 2, data.length, GL46.GL_UNSIGNED_INT);
        buffers.add(buffer2);
        return buffer2;
    }

    public Buffer createBuffer(Vec3[] data) {
        // create new array
        float[] data2 = new float[data.length * 3];

        // store data in array
        for(int i = 0; i < data.length; i++) {
            data2[i * 3] = data[i].getX();
            data2[i * 3 + 1] = data[i].getY();
            data2[i * 3 + 2] = data[i].getZ();
        }

        // create data buffer
        FloatBuffer dataBuffer = MemoryUtil.memAllocFloat(data2.length);
        dataBuffer.put(data2).flip();

        // create buffer
        int buffer = GL46.glGenBuffers();

        // store data in buffer
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, buffer);
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, dataBuffer, GL46.GL_STATIC_DRAW);
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, 0);

        // return buffer
        Buffer buffer2 = new Buffer(buffer, 3, data.length, GL46.GL_FLOAT);
        buffers.add(buffer2);
        return buffer2;
    }

    public Buffer createBuffer(IVec3[] data) {
        // create new array
        int[] data2 = new int[data.length * 3];

        // store data in array
        for(int i = 0; i < data.length; i++) {
            data2[i * 3] = data[i].getX();
            data2[i * 3 + 1] = data[i].getY();
            data2[i * 3 + 2] = data[i].getZ();
        }

        // create data buffer
        IntBuffer dataBuffer = MemoryUtil.memAllocInt(data2.length);
        dataBuffer.put(data2).flip();

        // create buffer
        int buffer = GL46.glGenBuffers();

        // store data in buffer
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, buffer);
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, dataBuffer, GL46.GL_STATIC_DRAW);
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, 0);

        // return buffer
        Buffer buffer2 = new Buffer(buffer, 3, data.length, GL46.GL_UNSIGNED_INT);
        buffers.add(buffer2);
        return buffer2;
    }

    public Buffer createBuffer(Vec4[] data) {
        // create new array
        float[] data2 = new float[data.length * 4];

        // store data in array
        for(int i = 0; i < data.length; i++) {
            data2[i * 4] = data[i].getX();
            data2[i * 4 + 1] = data[i].getY();
            data2[i * 4 + 2] = data[i].getZ();
            data2[i * 4 + 3] = data[i].getW();
        }

        // create data buffer
        FloatBuffer dataBuffer = MemoryUtil.memAllocFloat(data2.length);
        dataBuffer.put(data2).flip();

        // create buffer
        int buffer = GL46.glGenBuffers();

        // store data in buffer
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, buffer);
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, dataBuffer, GL46.GL_STATIC_DRAW);
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, 0);

        // return buffer
        Buffer buffer2 = new Buffer(buffer, 4, data.length, GL46.GL_FLOAT);
        buffers.add(buffer2);
        return buffer2;
    }
    
    public Buffer createBuffer(IVec4[] data) {
        // create new array
        int[] data2 = new int[data.length * 4];

        // store data in array
        for(int i = 0; i < data.length; i++) {
            data2[i * 4] = data[i].getX();
            data2[i * 4 + 1] = data[i].getY();
            data2[i * 4 + 2] = data[i].getZ();
            data2[i * 4 + 3] = data[i].getW();
        }

        // create data buffer
        IntBuffer dataBuffer = MemoryUtil.memAllocInt(data2.length);
        dataBuffer.put(data2).flip();

        // create buffer
        int buffer = GL46.glGenBuffers();

        // store data in buffer
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, buffer);
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, dataBuffer, GL46.GL_STATIC_DRAW);
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, 0);

        // return buffer
        Buffer buffer2 = new Buffer(buffer, 4, data.length, GL46.GL_UNSIGNED_INT);
        buffers.add(buffer2);
        return buffer2;
    }

    public Texture createTexture(ByteBuffer buffer, IVec2 resolution) {
        int texture = GL46.glGenTextures();
        GL46.glBindTexture(GL46.GL_TEXTURE_2D, texture);

        GL46.glPixelStorei(GL46.GL_UNPACK_ALIGNMENT, 1);

        GL46.glTexParameteri(GL46.GL_TEXTURE_2D, GL46.GL_TEXTURE_MIN_FILTER, GL46.GL_NEAREST);
        GL46.glTexParameteri(GL46.GL_TEXTURE_2D, GL46.GL_TEXTURE_MAG_FILTER, GL46.GL_NEAREST);
        GL46.glTexImage2D(
            GL46.GL_TEXTURE_2D, 
            0, 
            GL46.GL_RGBA, 
            resolution.getX(), 
            resolution.getY(), 
            0, 
            GL46.GL_RGBA, 
            GL46.GL_UNSIGNED_BYTE,
            buffer
        );

        GL46.glGenerateMipmap(GL46.GL_TEXTURE_2D);

        //GL46.glBindTexture(GL46.GL_TEXTURE_2D, 0);

        Texture texture2 = new Texture(texture);
        textures.add(texture2);
        return texture2;
    }
}