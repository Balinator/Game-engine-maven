package ro.balinator.gameengin.renderer;

import lombok.Getter;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import ro.balinator.gameengin.managers.DisplayManager;
import ro.balinator.gameengin.managers.InputManager;

/**
 * Created by Balinator on 2018. 02. 12..
 */
@Getter
public class Camera {

    private Vector3f position;
    private Vector3f rotation;

    public Camera() {
        this.position = new Vector3f();
        this.rotation = new Vector3f();
    }
}
