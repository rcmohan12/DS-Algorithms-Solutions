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

        int[][] adjMatrix = {{0,1,0,1,0,0,0,0,0},
                {1,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,1},
                {1,0,1,0,1,1,0,0,0},
                {0,0,0,1,0,0,1,0,0},
                {0,0,0,1,0,0,0,0,0},
                {0,0,0,0,1,0,0,1,0},
                {0,0,0,0,0,0,1,0,0},
                {0,0,1,0,0,0,0,0,0}
        };

        int[] elements = breadthFirstSearch(adjList);
        System.out.println("BFS Adjacency list");
        for(int x = 0; x < elements.length; x++) {
            System.out.print(" "+ elements[x]);
        }

        System.out.println("");
        int[] els = breadthFirstSearchAdjacencyMatrix(adjMatrix);
        System.out.println("BFS Adjacency Matrix");
        for(int x = 0; x < els.length; x++) {
            System.out.print(" "+ els[x]);
        }

        List<Integer> elements1 = depthFirstSearch(adjList,0, new ArrayList<Integer>(), new HashSet<Integer>());
        System.out.println("");
        System.out.println("DFS");

        for(Integer i : elements1) {
            System.out.print(" "+ i);
        }

        List<Integer> elements2 = depthFirstSearchAdjacencyMatrix(adjMatrix,0, new ArrayList<Integer>(), new HashSet<Integer>());
        System.out.println("");
        System.out.println("DFS Adjacency Matrix");

        for(Integer i : elements2) {
            System.out.print(" "+ i);
        }

    }


    /**
     * Breadth first search starting from node 0
     * * * * * 1 * * 4 --------- 6
     * * * * / * * / * * * * * * |
     * * * 0 --- 3 ----- 5 * * * 7
     * * * * * * / * * * * * * * *
     * * * * * 2 ----- 8 * * * * *
     * BFS order --> 0, 1, 3, 4, 5, 2, 6, 8, 7
     *
     * @param adjList : adjacency list for each element in the graph
     * @return : elements ordered by BFS
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

    /**
     * Breadth first search starting from node 0
     * * * * * 1 * * 4 --------- 6
     * * * * / * * / * * * * * * |
     * * * 0 --- 3 ----- 5 * * * 7
     * * * * * * / * * * * * * * *
     * * * * * 2 ----- 8 * * * * *
     * BFS order --> 0, 1, 3, 2, 4, 5, 8, 6, 7
     *
     * @param adjMatrix : adjacency matrix for each element in the graph
     * @return : elements ordered by BFS
     */
    public static int[] breadthFirstSearchAdjacencyMatrix(int[][] adjMatrix) {
        int[] res = new int[adjMatrix.length];
        Set<Integer> visited = new HashSet<Integer>();
        Queue<Integer> queue = new LinkedList();

        int[] row = adjMatrix[0];
        queue.add(0);
        visited.add(0);
        int count = 0;
        for(int x = 0; x < row.length; x++) {
            if(row[x] == 1) {
                queue.add(x);
            }
        }

       while(!queue.isEmpty()) {
           int el = queue.remove();
           res[count] = el;
           count++;
           if(!visited.contains(el)) {
               int[] r = adjMatrix[el];
               for(int z = 0; z < r.length; z++) {
                   if(r[z] == 1 && !visited.contains(z)) {
                       queue.add(z);
                   }
               }
               visited.add(el);
           }
       }

        return res;
    }


    /**
     * Depth first search starting from node 0
     * * * * * 1 * * 4 --------- 6
     * * * * / * * / * * * * * * |
     * * * 0 --- 3 ----- 5 * * * 7
     ** * * * * / * * * * * * * *
     * * * * * 2 ----- 8 * * * * *
     * DFS order --> 0, 1, 3, 4, 6, 7, 5, 2, 8
     *
     *
     * Depth first search starting from node 0. Impl with recursion
     * @param adjList : adjacency list for each element in the graph
     * @param index : index of node whose depth needs to be determined
     * @param finl : list of elements ordered by DFS
     * @param visited : nodes that have already been visited
     * @return : finl
     */
    public static List<Integer> depthFirstSearch(List<Integer[]> adjList, int index, List<Integer> finl,  Set<Integer> visited) {

        Integer[] adj = adjList.get(index);
        visited.add(index);
        finl.add(index);
        for(Integer i : adj) {
            if(!visited.contains(i)) {
                finl =  depthFirstSearch(adjList, i, finl, visited);
            }
        }

        return finl;
    }

    /**
     * Depth first search starting from node 0
     * * * * * 1 * * 4 --------- 6
     * * * * / * * / * * * * * * |
     * * * 0 --- 3 ----- 5 * * * 7
     ** * * * * / * * * * * * * *
     * * * * * 2 ----- 8 * * * * *
     * DFS order --> 0, 1, 3, 2, 8, 4, 6, 7, 5
     *
     *
     * Depth first search starting from node 0. Impl with recursion
     * @param adjMatrix : adjacency matrix for each element in the graph
     * @param index : index of node whose depth needs to be determined
     * @param finl : list of elements ordered by DFS
     * @param visited : nodes that have already been visited
     * @return : finl
     */
    public static List<Integer> depthFirstSearchAdjacencyMatrix(int[][] adjMatrix, int index, List<Integer> finl,  Set<Integer> visited) {

        int[] adj = adjMatrix[index];
        visited.add(index);
        finl.add(index);
        for(int x = 0; x < adj.length; x++) {
            System.out.println("i ----->"+adj[x]);
            if(adj[x] == 1 && !visited.contains(x)) {
                finl =  depthFirstSearchAdjacencyMatrix(adjMatrix, x, finl, visited);
            }
        }

        return finl;
    }


}
