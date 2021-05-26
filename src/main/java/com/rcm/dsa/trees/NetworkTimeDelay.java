package com.rcm.dsa.trees;

import java.util.*;

public class NetworkTimeDelay {

    public static void main(String[] args) {

        System.out.println("Max n/w delay ---->"+getNetworkDelay(1));

    }

    public static int getNetworkDelay(int startNode) {
        Map<Integer, List<NodeTime>> adjMap = getAdjMap();
        int networkDelay = 0;
        Map<Integer, Integer> nodeTimeMap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> lowestVals = new HashSet<>();
        visited.add(startNode);
        nodeTimeMap.put(startNode, 0);
        nodeTimeMap.put(2, Integer.MAX_VALUE);
        nodeTimeMap.put(3, Integer.MAX_VALUE);
        nodeTimeMap.put(4, Integer.MAX_VALUE);
        nodeTimeMap.put(5, Integer.MAX_VALUE);
        boolean cont = true;

        while (cont) {
            if(visited.size()==adjMap.size()) {
                cont = false;
            }
            int node = getNodeFromMap(nodeTimeMap, lowestVals);
            List<NodeTime> nodeTimes = adjMap.get(node);
            if(nodeTimes != null) {
                for(NodeTime nt : nodeTimes) {
                   if(visited.contains(node)) {
                      int newTime =  nodeTimeMap.get(node) + nt.time;
                      if(nodeTimeMap.containsKey(nt.node) && nodeTimeMap.get(nt.node) > newTime) {
                          nodeTimeMap.put(nt.node, newTime);
                          networkDelay = Integer.max(networkDelay, newTime);
                          visited.add(nt.node);
                      }

                   } else {
                       nodeTimeMap.put(nt.node, nt.time);
                       networkDelay = Integer.max(networkDelay, nt.time);
                       visited.add(nt.node);
                   }
                }
            }
        }


        return networkDelay;
    }

    /**
     * Use this method to get the next lowest weighted node
     *
     * @param nodeTimeMap
     * @param lowestVals
     * @return
     */
    public static int getNodeFromMap(Map<Integer, Integer> nodeTimeMap, Set<Integer> lowestVals) {
        int lowVal = Integer.MAX_VALUE;
        int lowValKey = 0;
        for(Map.Entry<Integer, Integer> entry : nodeTimeMap.entrySet()) {

            if(entry.getValue() == 0 && !lowestVals.contains(entry.getKey())) {
                lowVal = entry.getValue();
                lowValKey = entry.getKey();
                lowestVals.add(lowValKey);
                return lowValKey;
            }

            if(entry.getValue() <= lowVal && !lowestVals.contains(entry.getKey())) {
                lowVal = entry.getValue();
                lowValKey = entry.getKey();
            }
        }
        lowestVals.add(lowValKey);
        return lowValKey;
    }

    /**
     * Pre construct the adj list with the delays
     *
     * @return
     */
    public static Map<Integer, List<NodeTime>> getAdjMap() {
        Map<Integer, List<NodeTime>> adjMap = new HashMap<>();

        List<NodeTime> l1 = new ArrayList<>();
        l1.add(new NodeTime(2, 9));
        l1.add(new NodeTime(4, 2));
        adjMap.put(1, l1);

        List<NodeTime> l2 = new ArrayList<>();
        l2.add(new NodeTime(5, 9));
        adjMap.put(2, l2);

        List<NodeTime> l3 = new ArrayList<>();
        l3.add(new NodeTime(2, 3));
        l3.add(new NodeTime(1, 5));
        adjMap.put(3, l3);

        List<NodeTime> l4 = new ArrayList<>();
        l4.add(new NodeTime(2, 4));
        l4.add(new NodeTime(5, 6));
        adjMap.put(4, l4);

        List<NodeTime> l5 = new ArrayList<>();
        l5.add(new NodeTime(3, 7));
        adjMap.put(5, l5);

        return adjMap;
    }

}

/**
 * custom obj that holds the target node and the time taken to reach it from a specific source node
 *
 */
class NodeTime implements Comparable<NodeTime> {

    public int node;
    public int time;

    public NodeTime(int node, int time) {
        this.node = node;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof NodeTime)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        NodeTime n = (NodeTime) o;

        // Compare the data members and return accordingly
        return node == n.node;
    }

    @Override
    public int compareTo(NodeTime o) {

        if(0 == o.time) {
            return -1;
        }

       if(this.time ==  o.time) {
           return 0;
       } else if(this.time < o.time) {
           return -1;
       }
        return 1;
    }
}
