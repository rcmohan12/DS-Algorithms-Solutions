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
        courseDependency.add(new Integer[]{1});
        courseDependency.add(new Integer[]{2});
        courseDependency.add(new Integer[]{});
        courseDependency.add(new Integer[]{0,4});
        courseDependency.add(new Integer[]{});
        courseDependency.add(new Integer[]{2,3,4});

        System.out.println("Brute force : Is Cyclic ? :"+bruteForce(courseDependency, 6));
        System.out.println("Topological sort : Is Cyclic ? :"+optmized(courseDependency, 6));
    }

    public static boolean bruteForce(List<Integer[]> courseDependency, int n) {

        for(int x = 0; x < courseDependency.size(); x++) {
            Queue<Integer> queue = new LinkedList<>();
            Set<Integer> seen = new HashSet<>();
            for(int y = 0; y < courseDependency.get(x).length; y++) {
                queue.add(courseDependency.get(x)[y]);
            }
            while (!queue.isEmpty()) {
                int curr = queue.remove();
                seen.add(curr);
                if(curr == x) {
                    return true;
                } else {
                    Integer[] arr = courseDependency.get(curr);
                    for(Integer i : arr) {
                        if(!seen.contains(i)) {
                            queue.add(i);
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Successfully being able to order the elements in a topological order indicates there is no cycle
     *
     * @param courseDependency
     * @param n
     * @return
     */
    public static boolean optmized(List<Integer[]> courseDependency, int n) {
        Map<Integer, Integer> indegree = new HashMap<>(); // can be constructed while building the dependency array from the course dependency inputs
        Map<Integer, Set<Integer>> freq = new HashMap<>();
        for(int x = 0; x < n; x++) {                    // TC - O(n)
            indegree.put(x, 0);
        }

        for(int x = 0; x < courseDependency.size(); x++) {  // TC - O(n*m)
            Integer[] arr = courseDependency.get(x);
            if(arr.length != 0) {
                for(int y = 0; y < arr.length; y++) {  // TC - O(m)
                    Integer count = 1;
                    if(indegree.containsKey(arr[y])) {
                        count = indegree.get(arr[y]);
                        count++;
                    }
                    indegree.put(arr[y], count);
                }
            }
        }

        if(n == topologicalSort(indegree,courseDependency)) {
            return false;
        }

        return true;
    }

    /**
     * Max complexity = O(n2)
     * @param indegree
     * @param courseDependency
     * @return
     */
    public static int topologicalSort(Map<Integer, Integer> indegree, List<Integer[]> courseDependency) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> topologicalOrder = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : indegree.entrySet()) {  // TC - O(n)
            if(entry.getValue() == 0) {
                stack.add(entry.getKey());
            }
        }

        while (!stack.isEmpty()) {  // TC - O(n2) -- > including the inner for loop
            int val = stack.pop();
            topologicalOrder.add(val);
            Integer[] dep = courseDependency.get(val);
            if(dep != null) {
                for(Integer d : dep) {
                    if(indegree.containsKey(d)) {
                        indegree.put(d, (indegree.get(d)-1));
                        if(indegree.get(d) == 0) {
                            stack.add(d);
                        }
                    }
                }
            }
        }

        return topologicalOrder.size();
    }

}
