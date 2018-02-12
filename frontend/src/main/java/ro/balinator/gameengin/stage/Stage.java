package ro.balinator.gameengin.stage;

/**
 * Created by Balinator on 2018. 02. 06..
 */
public abstract class Stage {

    private long startTime;
    private long plusTime = 0;

    protected Stage(){
        init();
    }

    public static long getTime(){
        return System.currentTimeMillis();
    }
    public long getStageTime(){
        return System.currentTimeMillis() - startTime + plusTime;
    }

    void timeStart(){
        startTime = System.currentTimeMillis();
    }

    void timeStop(){
        plusTime += startTime;
    }

    protected abstract void init();
    public abstract void prepare();
    public abstract void logic();
    public abstract void render();
    public abstract void cleanUp();
}
