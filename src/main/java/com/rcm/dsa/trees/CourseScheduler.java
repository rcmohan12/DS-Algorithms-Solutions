package com.rcm.dsa.trees;

import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to
 * take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 */
public class CourseScheduler {

    public static void main(String[] args) {

        List<Integer[]> courseDependency = new ArrayList<>();
        courseDependency.add(new Integer[]{1,0});
        courseDependency.add(new Integer[]{2,1});
        courseDependency.add(new Integer[]{2,5});
        courseDependency.add(new Integer[]{0,3});
        courseDependency.add(new Integer[]{4,3});
        courseDependency.add(new Integer[]{3,5});
        courseDependency.add(new Integer[]{4,5});

        System.out.println("Cyclic ? :"+bruteForce(courseDependency, 6));
    }

    public static boolean bruteForce(List<Integer[]> courseDependency, int n) {

        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        List<Integer> tmpList = null;
        List<Integer[]> adjList = new ArrayList<>();

        for(int x = 0; x < courseDependency.size(); x++) {
            tmpList = adjMap.getOrDefault(courseDependency.get(x)[1], new ArrayList<Integer>());
            tmpList.add(courseDependency.get(x)[0]);
            adjMap.put(courseDependency.get(x)[1], tmpList);
        }

        for(int x = 0; x < n; x++) {
            if(adjMap.containsKey(x)) {
                adjList.add((Integer[]) adjMap.get(x).toArray());
            } else {
                adjList.add(null);
            }
        }

        return identifyCycle(adjList, 0, new HashSet<>() );
    }

    private static boolean identifyCycle(List<Integer[]> adjList, int index, Set<Integer> visited) {
        boolean isCyclic = false;
        Integer[] adj = adjList.get(index);
        visited.add(index);

        for(Integer i : adj) {
            if(!visited.contains(i)) {
                isCyclic  =  identifyCycle(adjList, i,  visited);
            } else {
                return true;
            }
        }

        return isCyclic;
    }

}
