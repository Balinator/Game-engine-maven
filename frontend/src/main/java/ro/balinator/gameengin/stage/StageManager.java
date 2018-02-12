package ro.balinator.gameengin.stage;


import ro.balinator.gameengine.exception.StageException;

import java.util.EnumMap;

/**
 * Created by Balinator on 2018. 02. 06..
 */
public class StageManager {
    public static final StageManager INSTANCE = new StageManager();

    private static EnumMap<StageEnum,Stage> stages = new EnumMap<>(StageEnum.class);

    private Stage primaryStage;

    private StageManager() {}

    public void frame(){
        if(this.primaryStage == null){
            throw new StageException("Primary stage is null!");
        }
        this.primaryStage.prepare();
        this.primaryStage.logic();
        this.primaryStage.render();
    }

    public void cleanUp(){
        for(Stage stage: stages.values()){
            stage.cleanUp();
        }
    }

    public void setStage(StageEnum stage) {
        if(stage == null) {
            throw new StageException("Can not set null stage!");
        }

        if(this.primaryStage != null) {
            this.primaryStage.timeStop();
        }

        this.primaryStage = stages.get(stage);
        if(this.primaryStage == null){
            try {
                stages.put(stage,(Stage)stage.getType().newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new StageException("Can't create stage!");
            }
            this.primaryStage = stages.get(stage);
        }

        this.primaryStage.timeStart();
    }

    public long getTime(){
        return primaryStage.getStageTime();
    }
}
