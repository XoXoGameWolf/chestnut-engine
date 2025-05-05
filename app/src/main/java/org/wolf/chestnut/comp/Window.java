package org.wolf.chestnut.comp;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL46;

import org.wolf.chestnut.util.Logger;
import org.wolf.chestnut.vector.IVec2;
import org.wolf.chestnut.vector.Vec2;
import org.wolf.chestnut.util.Keys;

public class Window {
    private long window;

    private String title;
    private IVec2 resolution;
    private Vec2 scale;

    private boolean[] keys, lastKeys;
    private boolean[] mouseButtons, lastMouseButtons;
    private Vec2 cursorPos;
    private Vec2 lastCursorPos;

    private boolean mouseGrab;
    private boolean fullscreen;

    private GLFWWindowSizeCallback windowSizeCallback;

    private GLFWKeyCallback keyCallback;
    private GLFWMouseButtonCallback mouseButtonCallback;
    private GLFWCursorPosCallback cursorPosCallback;

    public Window(String _title, IVec2 _resolution) {
        if(!GLFW.glfwInit()) {
            Logger.log("GLFW failed to be initialized.");
            System.exit(1);
        }

        title = _title;
        resolution = _resolution;

        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);

        window = GLFW.glfwCreateWindow(resolution.getX(), resolution.getY(), title, 0, 0);

        if(window == 0) {
            Logger.log("Window failed to be created.");
            System.exit(1);
        }

        Logger.log("Window was created.");

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window, (videoMode.WIDTH - resolution.getX()) / 2, (videoMode.HEIGHT - resolution.getY()) / 2);

        windowSizeCallback = new GLFWWindowSizeCallback() {
            public void invoke(long __window, int __width, int __height) {
                resolution = new IVec2(__width, __height);
                GL46.glViewport(0, 0, (int)((float)resolution.getX() * scale.getX()), (int)((float)resolution.getY() * scale.getY()));
            }
        };

        GLFW.glfwSetWindowSizeCallback(window, windowSizeCallback);

        keyCallback = new GLFWKeyCallback() {
            public void invoke(long _window, int _key, int _scancode, int _action, int _mods) {
                if(_action == GLFW.GLFW_PRESS) {
                    keys[_key] = true;
                } else if(_action == GLFW.GLFW_RELEASE) {
                    keys[_key] = false;
                }
            }
        };

        mouseButtonCallback = new GLFWMouseButtonCallback() {
            public void invoke(long _window, int _button, int _action, int _mods) {
                if(_action == GLFW.GLFW_PRESS) {
                    mouseButtons[_button] = true;
                } else if(_action == GLFW.GLFW_RELEASE) {
                    mouseButtons[_button] = false;
                }
            }
        };

        cursorPosCallback = new GLFWCursorPosCallback() {
            public void invoke(long _window, double _x, double _y) {
                cursorPos = new Vec2(
                    (float)_x / (float)resolution.getX() * 2f - 1f, 
                    (float)(_y / (float)resolution.getY()) * 2f - 1f
                );
            }
        };

        GLFW.glfwSetKeyCallback(window, keyCallback);
        GLFW.glfwSetMouseButtonCallback(window, mouseButtonCallback);
        GLFW.glfwSetCursorPosCallback(window, cursorPosCallback);

        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwShowWindow(window);

        keys = new boolean[GLFW.GLFW_KEY_LAST];
        mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

        lastKeys = new boolean[GLFW.GLFW_KEY_LAST];
        lastMouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

        cursorPos = new Vec2(0, 0);

        float[] scaleX = new float[1];
        float[] scaleY = new float[1];
        GLFW.glfwGetWindowContentScale(window, scaleX, scaleY);
        scale = new Vec2(scaleX[0], scaleY[0]);

        GL.createCapabilities();
        GL46.glViewport(0, 0, (int)((float)resolution.getX() * scale.getX()), (int)((float)resolution.getY() * scale.getY()));

        fullscreen = false;
    }

    public void update() {
        for(int i = 0; i < GLFW.GLFW_KEY_LAST; i++) {
            lastKeys[i] = keys[i];
        }
        for(int i = 0; i < GLFW.GLFW_MOUSE_BUTTON_LAST; i++) {
            lastMouseButtons[i] = mouseButtons[i];
        }

        lastCursorPos = cursorPos;

        GLFW.glfwPollEvents();
        GLFW.glfwSwapBuffers(window);

        if(getKeyDown(Keys.F11)) {
            fullscreen = !fullscreen;

            if(fullscreen) {
                GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, resolution.getX(), resolution.getY(), 60);
            } else {
                GLFW.glfwSetWindowMonitor(window, 0, 0, 0, resolution.getX(), resolution.getY(), 60);
            }
        }
    }

    public void destroy() {
        GLFW.glfwDestroyWindow(window);

        Logger.log("Window was destroyed.");
    }

    public boolean getFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(boolean _fullscreen) {
        fullscreen = _fullscreen;

        if(fullscreen) {
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, resolution.getX(), resolution.getY(), 60);
        } else {
            GLFW.glfwSetWindowMonitor(window, 0, 0, 0, resolution.getX(), resolution.getY(), 60);
        }
    }

    public boolean isOpen() {return !GLFW.glfwWindowShouldClose(window);}

    public long getWindow() {return window;}

    public String getTitle() {return title;}
    public IVec2 getResolution() {return resolution;}
    public Vec2 getCursorPos() {return cursorPos;}
    public Vec2 getCursorRel() {return new Vec2(cursorPos.getX() - lastCursorPos.getX(), -cursorPos.getY() + lastCursorPos.getY());}

    public boolean getKeyState(int key) {return keys[key];}
    public boolean getMouseButtonState(int button) {return mouseButtons[button];}

    public boolean getKeyDown(int key) {return keys[key] && !lastKeys[key];}
    public boolean getMouseButtonDown(int button) {return mouseButtons[button] && !lastMouseButtons[button];}

    public boolean getKeyUp(int key) {return !keys[key] && lastKeys[key];}
    public boolean getMouseButtonUp(int button) {return !mouseButtons[button] && lastMouseButtons[button];}

    public boolean getMouseGrab() {return mouseGrab;}
    public void setMouseGrab(boolean _mouseGrab) {
        mouseGrab = _mouseGrab;
        if(mouseGrab) {
            GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
        } else {
            GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
        }
    }
}