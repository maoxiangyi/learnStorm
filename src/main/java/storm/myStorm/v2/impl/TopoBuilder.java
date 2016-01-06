package storm.myStorm.v2.impl;

import storm.myStorm.v2.MyBolt;
import storm.myStorm.v2.MySpout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Describe:
 * Author:   maoxiangyi
 * Domain:   www.maoxiangyi.cn
 * Data:     2016/1/6.
 */
public class TopoBuilder {

    List<MyBolt> boltList = new ArrayList<MyBolt>();
    List<MySpout> spoutList = new ArrayList<MySpout>();

    public void setSpout(MySpout mySpout){
        spoutList.add(mySpout);
    }

    public void setBolt(MyBolt myBolt){
        boltList.add(myBolt);
    }

    public void submitTopology(){
         BlockingQueue sentenceQueue = new ArrayBlockingQueue(500);
         BlockingQueue wordQueue = new ArrayBlockingQueue(500);
        //线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //发射句子到sentenceQuequ
        MySpout mySpout = spoutList.get(0);
        mySpout.open(sentenceQueue);
        executorService.submit(mySpout);

        //接受一个句子，并将句子切割
        MyBolt splitBolt = boltList.get(0);
        splitBolt.prepare(sentenceQueue,wordQueue);
        executorService.submit(splitBolt);


        //接受一个单词，并进行据算
        MyBolt wordCountBolt = boltList.get(1);
        splitBolt.prepare(sentenceQueue,wordQueue);
        executorService.submit(wordCountBolt);

    }



}
