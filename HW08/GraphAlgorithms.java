import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Your implementation of various graph algorithms.
 *
 * @author - Rutvik Marakana
 * @version 1.0
 * @userid - rmarakana3
 * @GTID - 903401647
 *
 * Collaborators: None
 *
 * Resources: None
 */
public class GraphAlgorithms {

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph is null, cannot do DFS");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start vertex is null, cannot do DFS");
        }
        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Vertex is not present in the graph, cannot do DFS");
        }
        List<Vertex<T>> list = new ArrayList<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        return dfsHelper(start, graph, list, visitedSet);
    }

    /**
     * Helper method for dfs
     *
     * The graph performs a depth first search and returns the list of vertices that are visited in order.
     *
     * @param current The current Vertex
     * @param graph The graph to search through
     * @param list List to store vertices in visited order
     * @param visitedSet The set that stores the vertices that are visited
     * @param <T> The generic typing
     * @return The list of vertices in visited order
     */
    private static <T> List<Vertex<T>> dfsHelper(Vertex<T> current, Graph<T> graph, List<Vertex<T>> list, Set
            <Vertex<T>> visitedSet) {

        visitedSet.add(current);
        list.add(current);
        for (VertexDistance<T> v: graph.getAdjList().get(current)) {
            if (!visitedSet.contains(v.getVertex())) {
                dfsHelper(v.getVertex(), graph, list, visitedSet);
            }
        }
        return list;
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {

        if (graph == null) {
            throw new IllegalArgumentException("Graph is null, cannot do dijkstras");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start vertex is null, cannot do dijkstras");
        }
        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Vertex is not present in the graph, cannot do dijkstras");
        }

        Set<Vertex<T>> visitedSet = new HashSet<>();
        Map<Vertex<T>, Integer> distanceMap = new HashMap<>();
        PriorityQueue<VertexDistance<T>> queue = new PriorityQueue<>();

        for (Vertex<T> v : graph.getVertices()) {
            if (!v.equals(start)) {
                distanceMap.put(v, Integer.MAX_VALUE);
            } else {
                distanceMap.put(v, 0);
            }
        }

        queue.add(new VertexDistance<>(start, 0));

        while (!queue.isEmpty() && visitedSet.size() < graph.getVertices().size()) {
            VertexDistance<T> temp = queue.remove();
            if (!visitedSet.contains(temp.getVertex())) {
                visitedSet.add(temp.getVertex());
                List<VertexDistance<T>> adjList = graph.getAdjList().get(temp.getVertex());
                for (VertexDistance<T> v : adjList) {
                    if ((v.getDistance() + temp.getDistance() < distanceMap.get(v.getVertex())) && !visitedSet
                            .contains(v.getVertex())) {
                        queue.add(new VertexDistance<>(v.getVertex(), v.getDistance() + temp.getDistance()));
                        distanceMap.put(v.getVertex(), v.getDistance() + temp.getDistance());
                    }
                }
            }
        }

        return distanceMap;

    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph is null, cannot do DFS");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start vertex is null, cannot do DFS");
        }
        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Vertex is not present in the graph, cannot do DFS");
        }

        Set<Vertex<T>> visitedSet = new HashSet<>();
        Set<Edge<T>> edgeSet = new HashSet<>();
        PriorityQueue<Edge<T>> queue = new PriorityQueue<>();

        for (VertexDistance<T> v : graph.getAdjList().get(start)) {
            queue.add(new Edge<>(start, v.getVertex(), v.getDistance()));
        }
        visitedSet.add(start);

        while (!queue.isEmpty() && visitedSet.size() < graph.getVertices().size()) {
            Edge<T> temp = queue.remove();
            if (!visitedSet.contains(temp.getV())) {
                visitedSet.add(temp.getV());
                edgeSet.add(new Edge<>(temp.getU(), temp.getV(), temp.getWeight()));
                edgeSet.add(new Edge<>(temp.getV(), temp.getU(), temp.getWeight()));

                List<VertexDistance<T>> adjList = graph.getAdjList().get(temp.getV());
                for (VertexDistance<T> v : adjList) {
                    if (!visitedSet.contains(v.getVertex())) {
                        queue.add(new Edge<>(temp.getV(), v.getVertex(), v.getDistance()));
                    }
                }
            }
        }
        if (edgeSet.size() < 2 * (graph.getVertices().size() - 1)) {
            return null;
        }
        return edgeSet;
    }
}
