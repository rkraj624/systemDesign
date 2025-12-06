package dsa.practice.graphs;

import java.util.*;

class BFSTopoSort {
    private final int V;
    private final ArrayList<ArrayList<Integer>> adj;

    BFSTopoSort(int v) {
        V = v;
        adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    void topoSort() {
        int[] indegree = new int[V];

        // calculate indegree
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                indegree[neighbor]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        // add all nodes with indegree 0
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        ArrayList<Integer> topo = new ArrayList<>();

        while (!q.isEmpty()) {
            int node = q.poll();
            topo.add(node);

            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        // Check for cycle
        if (topo.size() != V) {
            System.out.println("Graph has a cycle! No TopoSort possible.");
            return;
        }

        System.out.println("Topological Sort (BFS/Kahn): " + topo);
    }

    public static void main(String[] args) {
        BFSTopoSort g = new BFSTopoSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        g.topoSort();
    }
}
