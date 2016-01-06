package storm.myStorm.v2;

import java.util.concurrent.BlockingQueue;

/**
 * Describe:
 * Author:   maoxiangyi
 * Domain:   www.maoxiangyi.cn
 * Data:     2016/1/6.
 */
public interface MySpout extends Runnable {

    public void open(BlockingQueue collector);
    public void nextTuple();
}
