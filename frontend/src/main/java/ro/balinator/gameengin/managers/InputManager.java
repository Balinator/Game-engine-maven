package ro.balinator.gameengin.managers;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWScrollCallbackI;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;

/**
 * Created by Balinator on 2018. 02. 12..
 */
public class InputManager {
    public static final InputManager INSANCE = new InputManager();

    private ArrayList<GLFWScrollCallbackI> callbackGLFWScrollCallback = new ArrayList<>();
    private ArrayList<GLFWKeyCallbackI> callbackGLFWKeyCallback = new ArrayList<>();

    private InputManager() {
    }

    public void registerInput(GLFWScrollCallbackI function) {
        this.callbackGLFWScrollCallback.add(function);
        GLFW.glfwSetScrollCallback(DisplayManager.INSTANCE.getWindow(), (l, v, v1) -> {
            for (GLFWScrollCallbackI callback : InputManager.this.callbackGLFWScrollCallback) {
                callback.invoke(l, v, v1);
            }
        });
    }

    public void registerInput(GLFWKeyCallbackI function) {
        this.callbackGLFWKeyCallback.add(function);
        GLFW.glfwSetKeyCallback(DisplayManager.INSTANCE.getWindow(), (l, i, i1, i2, i3) -> {
            for (GLFWKeyCallbackI callback : InputManager.this.callbackGLFWKeyCallback) {
                callback.invoke(l, i, i1, i2, i3);
            }
        });
    }

    public void initImput() {
        GL.createCapabilities();
    }
}
