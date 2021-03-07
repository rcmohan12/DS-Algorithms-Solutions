package com.rcm.dsa.trees;

import java.util.*;

/**
 *
 */
public class InformEmployees {

    public static void main(String[] args) {
      /*  List<Integer[]> adjList = returnAdjacencyList(new int[]{2, 2, 4, 6, -1, 4, 4, 5});
        for(int x = 0; x < adjList.size(); x++) {
            if(adjList.get(x) != null) {
                System.out.println("");
                System.out.print(x + " : [");
                for(int y = 0; y < adjList.get(x).length; y++) {
                    System.out.print(y + " ");
                }
                System.out.print("]");
            }
        }*/

        List<Integer[]> adjList1 = new ArrayList<>();
        adjList1.add(null);
        adjList1.add(null);
        adjList1.add(new Integer[]{0, 1});
        adjList1.add(null);
        adjList1.add(new Integer[]{2, 5, 6});
        adjList1.add(new Integer[]{7});
        adjList1.add(new Integer[]{3});
        adjList1.add(new Integer[]{8});
        adjList1.add(null);


        int maxITime = determineInformTime(adjList1, new int[]{0, 0, 4, 0, 7, 3, 6, 8, 0}, 4, new HashSet<Integer>(), 0, 0);
        System.out.println("Depth time :"+maxITime);
    }

    /**
     * method that accepts the headID and the managers array and returns the adjacency list
     * @return
     *
     * Ex: {2, 2, 4, 6, -1, 4, 4, 5}
     *     [0, 1, 2, 3, 4, 5, 6, 7]
     */
    public static List<Integer[]> returnAdjacencyList(int[] managers) {

        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        List<Integer> tmpList = null;
        List<Integer[]> adjList = new ArrayList<>();

        for(int x = 0; x < managers.length; x++) {
            tmpList = adjMap.getOrDefault(managers[x], new ArrayList<Integer>());
            tmpList.add(x);
            adjMap.put(managers[x], tmpList);
        }

        for(Map.Entry<Integer, List<Integer>> entry : adjMap.entrySet()) {
            if(adjList.get(entry.getKey()) == null) {

            }
        }


        for(int x = 0; x < adjMap.size(); x++) {
            adjList.add((Integer[]) adjMap.get(x).toArray());
        }

        return adjList;
    };

    public static int determineInformTime(List<Integer[]> adjList, int[] informTime, int index, Set<Integer> visited, int depthTime, int maxITime) {
        Integer[] adj = adjList.get(index);
        visited.add(index);
        depthTime = depthTime + informTime[index];
        if(adj != null) {
            for(Integer i : adj) {
                if(!visited.contains(i)) {
                    maxITime  =  determineInformTime(adjList, informTime, i, visited, depthTime, maxITime);
                } else {
                    maxITime = Math.max(depthTime, maxITime);
                }
            }
        }
        maxITime = Math.max(depthTime, maxITime);
        return maxITime;
    }

}
