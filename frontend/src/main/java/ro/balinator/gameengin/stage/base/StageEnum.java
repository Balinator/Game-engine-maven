package ro.balinator.gameengin.stage.base;

import ro.balinator.gameengin.stage.ColoredStage;
import ro.balinator.gameengin.stage.TexturedStage;

/**
 * Created by Balinator on 2018. 02. 06..
 */
public enum  StageEnum {
    TEXTURED_STAGE(TexturedStage.class),

    COLORED_STAGE(ColoredStage.class);

    StageEnum(Class<? extends Stage> type){
        this.type = type;
    }

    private Class type;

    public Class getType() {
        return type;
    }
}
