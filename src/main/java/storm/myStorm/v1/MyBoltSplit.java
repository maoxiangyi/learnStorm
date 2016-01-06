package storm.myStorm.v1;

/**
 * Describe:
 * Author:   maoxiangyi
 * Domain:   www.maoxiangyi.cn
 * Data:     2016/1/6.
 */
public class MyBoltSplit extends Thread {

    private MyStormProcessMain stormProcess;

    public void run() {
        while (true) {
            try {
                String sentence = (String) stormProcess.getSentenceQueue().take();
                stormProcess.split(sentence);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public MyBoltSplit(MyStormProcessMain stormProcess) {
        this.stormProcess = stormProcess;
    }
}
