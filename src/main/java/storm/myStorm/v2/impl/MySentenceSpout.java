package storm.myStorm.v2.impl;


import storm.myStorm.v2.MySpout;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Describe:
 * Author:   maoxiangyi
 * Domain:   www.maoxiangyi.cn
 * Data:     2016/1/6.
 */
public class MySentenceSpout implements MySpout {

    private BlockingQueue collector;
    private Random random;

    public void open(BlockingQueue collector) {
        this.collector = collector;
        random = new Random();
    }

    public void nextTuple() {
        String[] sentences = new String[]{"the cow jumped over the moon",
                "an apple a day keeps the doctor away",
                "four score and seven years ago",
                "snow white and the seven dwarfs", "i am at two with nature"};
        String sentence = sentences[random.nextInt(sentences.length)];
        try {
            collector.put(sentence);
            System.out.println("send sentence:" + sentence);
//            this.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true){
            nextTuple();
        }
    }
}
