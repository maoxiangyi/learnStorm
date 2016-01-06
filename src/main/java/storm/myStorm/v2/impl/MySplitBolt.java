package storm.myStorm.v2.impl;


import storm.myStorm.v2.MyBolt;

import java.util.concurrent.BlockingQueue;

/**
 * Describe:
 * Author:   maoxiangyi
 * Domain:   www.maoxiangyi.cn
 * Data:     2016/1/6.
 */
public class MySplitBolt implements MyBolt {

    private BlockingQueue collector;
    private BlockingQueue sentenceQueue;

    public void prepare(BlockingQueue sentenceQueue,BlockingQueue collector) {
        this.collector = collector;
        this.sentenceQueue = sentenceQueue;
    }

    public void execute(String message, BlockingQueue collector) {
        System.out.println("resv sentence" + message);
        String[] words = message.split(" ");
        for (String word : words) {
            word = word.trim();
            if (!word.isEmpty()) {
                word = word.toLowerCase();
                collector.add(word);
                System.out.println("split word:" + word);
            }
        }
    }

    public void run() {
        while (true) {
            try {
                execute((String) sentenceQueue.take(), collector);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
