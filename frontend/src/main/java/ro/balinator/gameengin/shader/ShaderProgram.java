package ro.balinator.gameengin.shader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.EnumMap;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import ro.balinator.gameengine.exception.ShaderException;

/**
 * Created by Balinator on 2018. 01. 24..
 */
public abstract class ShaderProgram {

    private static FloatBuffer buffer = BufferUtils.createFloatBuffer(16);

    private EnumMap<UniformEnum,Integer> uniformIds = new EnumMap<>(UniformEnum.class);

    private int programId;
    private int vertexShaderId;
    private int fragmentShaderId;

    public ShaderProgram(String vertexFile, String fragmentFile) {
        vertexShaderId = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
        fragmentShaderId = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
        programId = GL20.glCreateProgram();
        GL20.glAttachShader(programId, vertexShaderId);
        GL20.glAttachShader(programId, fragmentShaderId);
        bindAttributes();
        GL20.glLinkProgram(programId);
        GL20.glValidateProgram(programId);
        getAllUniformLocations();
    }

    public void start() {
        GL20.glUseProgram(programId);
    }

    public void stop() {
        GL20.glUseProgram(0);
    }

    public void cleanUp() {
        stop();
        GL20.glDetachShader(programId, vertexShaderId);
        GL20.glDetachShader(programId, fragmentShaderId);
        GL20.glDeleteShader(vertexShaderId);
        GL20.glDeleteShader(fragmentShaderId);
        GL20.glDeleteProgram(programId);
    }

    protected abstract void bindAttributes();

    protected void bindAttribute(int attribute, String variableName) {
        GL20.glBindAttribLocation(programId, attribute, variableName);
    }

    private static int loadShader(String file, int type) {
        StringBuilder shaderSource = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        } catch (IOException e) {
            final String typeEx = type == GL20.GL_VERTEX_SHADER ? "Vertex" : type == GL20.GL_FRAGMENT_SHADER ? "Fragment" : "else";
            throw new ShaderException(
                    "Shader at given path not existing! Path: " + file + " Type: " +
                            typeEx,
                    e);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            throw new ShaderException("Could not compile shader!" + System.lineSeparator() +
                    GL20.glGetShaderInfoLog(shaderID, 500));
        }
        return shaderID;
    }

    protected abstract void getAllUniformLocations();

    protected void putUniformId(UniformEnum key){
        uniformIds.put(key,getUniformLocation(key.getName()));
    }

    protected int getUniformId(UniformEnum key){
        return uniformIds.get(key);
    }

    protected int getUniformLocation(String uniformName) {
        return GL20.glGetUniformLocation(programId, uniformName);
    }

    protected void loadFloat(int location, float value) {
        GL20.glUniform1f(location, value);
    }

    protected void loadVector3f(int location, Vector3f value) {
        GL20.glUniform3f(location, value.x, value.y, value.z);
    }

    protected void loadBoolean(int location, boolean value) {
        GL20.glUniform1f(location, value ? 1 : 0);
    }

    protected void loadMatrix4f(int location, Matrix4f value) {
        value.get(buffer);
        GL20.glUniformMatrix4fv(location, false, buffer);
    }

}
