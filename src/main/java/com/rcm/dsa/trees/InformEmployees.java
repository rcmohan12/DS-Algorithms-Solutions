package com.rcm.dsa.trees;

import java.util.*;

/**
 * A company has n employees with a unique ID for each employee from 0 to n - 1.
 * The head of the company is the one with headID.
 * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee,
 * manager[headID] = -1.
 * Also, it is guaranteed that the subordination relationships have a tree structure.
 * The head of the company wants to inform all the company employees of an urgent piece of news.
 * He will inform his direct subordinates, and they will inform their subordinates, and so on until all employees know about the
 * urgent news.
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e., After informTime[i] minutes,
 * all his direct subordinates can start spreading the news).
 *
 * Return the number of minutes needed to inform all the employees about the urgent news.
 *
 */
public class InformEmployees {

    /**
     * The strategy to solve this solution is to first construct and adjacency list that captures the reporting order
     * between manager and employee. An adjacency list is more suitable here than the adjacency matrizx since not all
     * employees are connected to each other
     *
     * The next step is to use the adjacency list and the time to inform array and perform a DFS traversal to calculate
     * the max time taken to inform all employees.
     * @param args
     */

    static int manager = 0;
    public static void main(String[] args) {
        List<Integer[]> adjList = returnAdjacencyList(new int[]{2, 2, 4, 6, -1, 4, 4, 5});

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


        int maxITime = determineInformTime(adjList, new int[]{0, 0, 4, 0, 7, 3, 6, 8, 0}, manager, new HashSet<Integer>(), 0, 0);
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
            if(adjMap.containsKey(managers[x])) {
                tmpList = adjMap.get(managers[x]);
            } else {
                tmpList = adjMap.getOrDefault(managers[x], new ArrayList<Integer>());
            }
            tmpList.add(x);
            adjMap.put(managers[x], tmpList);
            if(managers[x] == -1) {
                manager = x;
            }
        }


        for(int x = 0; x < managers.length; x++) {
            if (adjMap.containsKey(x)) {
                Integer[] iarr = new Integer[adjMap.get(x).size()];
                for(int y = 0 ; y < adjMap.get(x).size(); y++) {
                    iarr[y] = adjMap.get(x).get(y);
                }
                adjList.add(iarr);
            } else {
                adjList.add(null);
            }
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
