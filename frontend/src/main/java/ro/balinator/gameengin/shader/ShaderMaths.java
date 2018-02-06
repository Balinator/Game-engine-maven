package ro.balinator.gameengin.shader;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Created by Balinator on 2018. 01. 27..
 */
public class ShaderMaths {

    private final static Vector3f angleX = new Vector3f(1,0,0);
    private final static Vector3f angleY = new Vector3f(0,1,0);
    private final static Vector3f angleZ = new Vector3f(0,0,1);

    private ShaderMaths(){}

    public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, Vector3f scale){
        return new Matrix4f()
                .identity()
                .translate(translation)
                .rotate(rotation.x,angleX)
                .rotate(rotation.y,angleY)
                .rotate(rotation.z,angleZ)
                .scale(scale);
    }

    public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, float scale){
        return new Matrix4f()
                .identity()
                .translate(translation)
                .rotate(rotation.x,angleX)
                .rotate(rotation.y,angleY)
                .rotate(rotation.z,angleZ)
                .scale(scale);
    }
}
