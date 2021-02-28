package com.rcm.dsa.graphs;

import java.util.*;

public class UnDirectedGraph {


    public static void main(String[] args) {
        List<Integer[]> adjList = new ArrayList<>();
        adjList.add(new Integer[]{1,3});
        adjList.add(new Integer[]{0});
        adjList.add(new Integer[]{3, 8});
        adjList.add(new Integer[]{0, 4, 5, 2});
        adjList.add(new Integer[]{3, 6});
        adjList.add(new Integer[]{3});
        adjList.add(new Integer[]{4, 7});
        adjList.add(new Integer[]{6});
        adjList.add(new Integer[]{2});

        int[] elements = breadthFirstSearch(adjList);

        for(int x = 0; x < elements.length; x++) {
            System.out.print(" "+ elements[x]);
        }

    }


    /**
     *
     * @param adjList : adjacency list for each element in the graph
     * @return : elements ordered by bfs search
     */
    public static int[] breadthFirstSearch(List<Integer[]> adjList) {
        int[] res = new int[adjList.size()]; // contains the elements searched in bfs order
        Set<Integer> visited = new HashSet<>(); // keeps track of the visited elements so as to not duplicate the paths
        Queue<Integer> queue = new LinkedList(); // queue of all the elements in the visited order
        int count = 0;
        queue.add(0);
        while (!queue.isEmpty()) {
            int index = queue.remove();
            Integer[] e = adjList.get(index);
            res[count] = index;
            visited.add(index);
            for(Integer el : e) {
                if(!visited.contains(el)) {
                    queue.add(el);
                }
            }
            count++;
        }
        return res;
    }

}
