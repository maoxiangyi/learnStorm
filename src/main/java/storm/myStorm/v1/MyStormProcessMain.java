package storm.myStorm.v1;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
public class MyStormProcessMain {
    private Random random = new Random();

    private BlockingQueue sentenceQueue = new ArrayBlockingQueue(50000);
    private BlockingQueue wordQueue = new ArrayBlockingQueue(50000);


    // 用来保存最后计算的结果key=单词，value=单词个数
    Map<String, Integer> counters = new HashMap<String, Integer>();

    //用来发送句子
    public void nextTuple() {
        String[] sentences = new String[]{"the cow jumped over the moon",
                "an apple a day keeps the doctor away",
                "four score and seven years ago",
                "snow white and the seven dwarfs", "i am at two with nature"};
        String sentence = sentences[random.nextInt(sentences.length)];
        try {
            sentenceQueue.put(sentence);
            System.out.println("send sentence:" + sentence);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //用来切割句子
    public void split(String sentence) {
        System.out.println("resv sentence" + sentence);
        String[] words = sentence.split(" ");
        for (String word : words) {
            word = word.trim();
            if (!word.isEmpty()) {
                word = word.toLowerCase();
                wordQueue.add(word);
                System.out.println("split word:" + word);
            }
        }

    }

    //用来计算单词
    public void wordcounter(String word) {
        if (!counters.containsKey(word)) {
            counters.put(word, 1);
        } else {
            Integer c = counters.get(word) + 1;
            counters.put(word, c);
        }
        System.out.println("print map:" + counters);
    }


    public static void main(String[] args) {

        //线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        MyStormProcessMain stormProcess = new MyStormProcessMain();
        //发射句子到sentenceQuequ
        executorService.submit(new MySpout(stormProcess));
        //接受一个句子，并将句子切割
        executorService.submit(new MyBoltSplit(stormProcess));
        //接受一个单词，并进行据算
        executorService.submit(new MyBoltWordCount(stormProcess));
    }

    public BlockingQueue getSentenceQueue() {
        return sentenceQueue;
    }

    public void setSentenceQueue(BlockingQueue sentenceQueue) {
        this.sentenceQueue = sentenceQueue;
    }

    public BlockingQueue getWordQueue() {
        return wordQueue;
    }

    public void setWordQueue(BlockingQueue wordQueue) {
        this.wordQueue = wordQueue;
    }
}
