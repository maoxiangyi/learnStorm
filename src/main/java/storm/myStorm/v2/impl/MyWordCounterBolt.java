package storm.myStorm.v2.impl;


import storm.myStorm.v2.MyBolt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * Describe:
 * Author:   maoxiangyi
 * Domain:   www.maoxiangyi.cn
 * Data:     2016/1/6.
 */
public class MyWordCounterBolt implements MyBolt {
    private BlockingQueue collector;
    // 用来保存最后计算的结果key=单词，value=单词个数
    Map<String, Integer> counters = new HashMap<String, Integer>();

    public void prepare(BlockingQueue sentenceQueue,BlockingQueue collector) {
        this.collector = collector;
    }

    public void execute(String word, BlockingQueue collector) {
        if (!counters.containsKey(word)) {
            counters.put(word, 1);
        } else {
            Integer c = counters.get(word) + 1;
            counters.put(word, c);
        }
        System.out.println("print map:" + counters);
    }

    public void run() {
        while (true) {
            try {
                String word = (String) collector.take();
                execute(word, collector);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
