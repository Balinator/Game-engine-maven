package ro.balinator.gameengin.shader.color;

import ro.balinator.gameengin.shader.ShaderProgram;

/**
 * Created by Balinator on 2018. 01. 24..
 */
public class ColourShader extends ShaderProgram{
    private static final String VERTEX_FILE = "frontend/src/main/java/ro/balinator/gameengin/shader/color/vertexShader.vert";
    private static final String FRAGMENT_FILE = "frontend/src/main/java/ro/balinator/gameengin/shader/color/fragmentShader.frag";

    public ColourShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "colour");
    }



}
