package ro.balinator.gameengine.entity;

import lombok.Getter;

/**
 * Created by Balinator on 2018. 01. 27..
 */
@Getter
public class TexturedModel {
    private RawModel rawModel;
    private ModelTexture modelTexture;

    public TexturedModel(RawModel rawModel, ModelTexture modelTexture) {
        this.rawModel = rawModel;
        this.modelTexture = modelTexture;
    }


}
