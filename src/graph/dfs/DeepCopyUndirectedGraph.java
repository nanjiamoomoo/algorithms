package graph.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GraphNode {
    public int key;
    public List<GraphNode> neighbors;
    public GraphNode(int key) {
        this.key = key;
        this.neighbors = new ArrayList<GraphNode>();
    }
}
public class DeepCopyUndirectedGraph {


    /**
     * Make a deep copy of an undirected graph, there could be cycles in the original graph.
     * Assumption: The given graph is not null
     * @param graph
     * @return
     */
    public List<GraphNode> copy(List<GraphNode> graph) {
        /*
            We can use recursion DFS to solve this problem

            Define a method copy(GraphNode graph) returns the deep copy of the graph
            on current recursion level
                We make copy of current node
                make copy of each neighbor and add to the neighbors of current node

            What if there is cycle in the graph and we have already made a copy in previous recursion level?
            We can use a one on one map between the previous graph and the copied one to check if a sub graph has been copied


         */
        Map<GraphNode, GraphNode> map = new HashMap<>();
        List<GraphNode> copyGraph = new ArrayList<>();
        for (GraphNode node : graph) {
            copyGraph.add(copy(node, map));
        }
        return copyGraph;
    }

    private GraphNode copy(GraphNode graph, Map<GraphNode, GraphNode> map) {
        if (map.containsKey(graph)) {
            return map.get(graph);
        }

        GraphNode copyGraph = new GraphNode(graph.key);
        map.put(graph, copyGraph);
        copyGraph.neighbors = new ArrayList<GraphNode>();
        for (GraphNode neighbor : graph.neighbors) {
            copyGraph.neighbors.add(copy(neighbor, map));
        }
        return copyGraph;
    }
}
