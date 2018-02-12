package ro.balinator.gameengin.stage;

import ro.balinator.gameengin.stage.instances.TestStage;

/**
 * Created by Balinator on 2018. 02. 06..
 */
public enum  StageEnum {
    TEST_STAGE(TestStage.class),

    MENU_STAGE(TestStage.class);

    StageEnum(Class<? extends Stage> type){
        this.type = type;
    }

    private Class type;

    public Class getType() {
        return type;
    }
}
