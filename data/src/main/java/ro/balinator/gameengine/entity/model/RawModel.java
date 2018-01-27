package ro.balinator.gameengine.entity.model;

import lombok.Getter;

/**
 * Created by Balinator on 2018. 01. 24..
 */
@Getter
public class RawModel {
    private int vaoId;
    private int vertexCount;

    public RawModel(int vaoID, int vertexCount) {
        this.vaoId = vaoID;
        this.vertexCount = vertexCount;
    }
}
