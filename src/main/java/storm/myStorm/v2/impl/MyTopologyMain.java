package storm.myStorm.v2.impl;

/**
 * Describe:
 * Author:   maoxiangyi
 * Domain:   www.maoxiangyi.cn
 * Data:     2016/1/6.
 */
public class MyTopologyMain {

    public static void main(String[] args) {
        TopoBuilder topoBuilder = new TopoBuilder();
        topoBuilder.setSpout(new MySentenceSpout());
        topoBuilder.setBolt(new MySplitBolt());
        topoBuilder.setBolt(new MyWordCounterBolt());
        topoBuilder.submitTopology();
    }

}
