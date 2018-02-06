package ro.balinator.gameengine.entity;

import lombok.Getter;
import ro.balinator.gameengine.entity.interfaces.Model;
import ro.balinator.gameengine.entity.model.ModelTexture;
import ro.balinator.gameengine.entity.model.RawModel;

/**
 * Created by Balinator on 2018. 01. 27..
 */
@Getter
public class TexturedModel implements Model{
    private RawModel rawModel;
    private ModelTexture modelTexture;

    public TexturedModel(RawModel rawModel, ModelTexture modelTexture) {
        this.rawModel = rawModel;
        this.modelTexture = modelTexture;
    }


}
