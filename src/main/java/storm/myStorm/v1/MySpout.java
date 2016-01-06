package storm.myStorm.v1;

/**
 * Describe:
 * Author:   maoxiangyi
 * Domain:   www.maoxiangyi.cn
 * Data:     2016/1/6.
 */
public class MySpout implements Runnable {

    private MyStormProcessMain stormProcess;

    public MySpout(MyStormProcessMain stormProcess) {
        this.stormProcess = stormProcess;
    }

    public void run() {
        //storm框架在循环调用spout的netxTuple方法
        while (true) {
            stormProcess.nextTuple();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
