package storm.myStorm.v2;

import java.util.concurrent.BlockingQueue;

/**
 * Describe:
 * Author:   maoxiangyi
 * Domain:   www.maoxiangyi.cn
 * Data:     2016/1/6.
 */
public interface MyBolt extends Runnable {

    public void prepare(BlockingQueue sentenceQueue, BlockingQueue collector);
    public void execute(String message, BlockingQueue collector);
}
