package ro.balinator.gameengin.renderer.loader;

import org.joml.Vector2f;
import org.joml.Vector3f;
import ro.balinator.gameengine.entity.model.ObjModel;
import ro.balinator.gameengine.exception.FileException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Balinator on 2018. 02. 17..
 */
public class ObjLoader {
    private ObjLoader() {}

    public static ObjModel loadObjFile(String name) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(new File(name));
        } catch (FileNotFoundException e) {
            throw new FileException("Could not load the file!", e);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<Vector3f> vertices = new ArrayList<>();
        ArrayList<Vector2f> textures = new ArrayList<>();
        ArrayList<Vector3f> normals = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        float[] texturesArray = null;
        float[] normalsArray = null;
        float[] verticesArray;
        int[] indicesArray;

        try {
            for (String line : bufferedReader.lines().toArray(String[]::new)) {
                String[] elements = line.split(" ");
                switch (elements[0]) {
                    case "v":
                        vertices.add(new Vector3f(Float.parseFloat(elements[1]), Float.parseFloat(elements[2]), Float.parseFloat(elements[3])));
                        break;
                    case "vt":
                        textures.add(new Vector2f(Float.parseFloat(elements[1]), Float.parseFloat(elements[2])));
                        break;
                    case "vn":
                        normals.add(new Vector3f(Float.parseFloat(elements[1]), Float.parseFloat(elements[2]), Float.parseFloat(elements[3])));
                        break;
                    case "f":
                        if (texturesArray == null) {
                            texturesArray = new float[vertices.size() * 2];
                            normalsArray = new float[vertices.size() * 3];
                        }
                        String[] vertex1 = elements[1].split("/");
                        String[] vertex2 = elements[2].split("/");
                        String[] vertex3 = elements[3].split("/");
                        for (String[] vertex : new String[][]{vertex1, vertex2, vertex3}) {
                            int pointer = Integer.parseInt(vertex[0]) - 1;
                            indices.add(pointer);
                            Vector2f texture = textures.get(Integer.parseInt(vertex[1]) - 1);
                            texturesArray[pointer * 2] = texture.x;
                            texturesArray[pointer * 2 + 1] = texture.y;
                            Vector3f normal = normals.get(Integer.parseInt(vertex[2]) - 1);
                            normalsArray[pointer * 3] = normal.x;
                            normalsArray[pointer * 3 + 1] = normal.y;
                            normalsArray[pointer * 3 + 2] = normal.z;
                        }
                        break;
                }
            }
        } catch (Exception e) {
            throw new FileException("Wrong file format!", e);
        }

        verticesArray = new float[vertices.size() * 3];
        int count = -1;
        for (Vector3f vertex : vertices) {
            verticesArray[++count] = vertex.x;
            verticesArray[++count] = vertex.y;
            verticesArray[++count] = vertex.z;
        }
        indicesArray = indices.stream().mapToInt(Integer::intValue).toArray();
        return new ObjModel(indicesArray, verticesArray, normalsArray, texturesArray);
    }
}
