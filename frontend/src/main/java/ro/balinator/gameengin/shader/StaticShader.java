package ro.balinator.gameengin.shader;

/**
 * Created by Balinator on 2018. 01. 24..
 */
public class StaticShader extends ShaderProgram{
    private static final String VERTEX_FILE = "frontend/src/main/java/ro/balinator/gameengin/shader/vertexShader.vert";
    private static final String FRAGMENT_FILE = "frontend/src/main/java/ro/balinator/gameengin/shader/fragmentShader.frag";

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
    }



}
