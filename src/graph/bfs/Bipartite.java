package graph.bfs;


import java.util.*;

public class Bipartite {

    /**
     * Determine if an undirected graph is bipartite. A bipartite graph is one in which the nodes can be divided into two groups such that no nodes have direct edges to other nodes in the same group.
     * Assumption: The graph is represented by a list of nodes, and the list of nodes is not null.
     * Examples:
     * 1 -- 2
     * /
     * 3 -- 4
     * is bipartite (1, 3 in group 1 and 2, 4 in group 2).
     * <p>
     * 1 -- 2
     * / |
     * 3 -- 4
     * is not bipartite.
     *
     * @param graph
     * @return
     */
    public boolean isBipartite(List<GraphNode> graph) {

        /*
            we can mark the graph node with two different colors
                we traverse the graph use BFS algorithm, for each node we visited, we mark it as one color, for its neighbors, we mark them with different color.
                we traverse the entire tree and see if we will find a contradiction. If not, then the graph is bipartite

            e.g. first example
                1.  1 (red) --> offer in the queue
                2.  poll 1(red), offer 2(black) in the queue
                3.  poll 2(black), offer 3(red) in the queue
                4.  poll 3(red), offer 4 (red) in the queue
                5.  4(red) --> end

              Q: how to check contradiction?
                Use a Map<GraphNode, Integer>, Integer is used to indicate color, 0 represents red, 1 represents black.
                    for each neighbor, we check the node in the map and see if the color matches
                        1. if the node is not in the map, we add the node and color indicator in the map
                        2. if the node is in the map
                            if the color matches, we can continue, since the node has been visited.
                            if the color does not match, we return false

               e.g. second example
                map[ <1, red>, <2, black>, <3, red>, <4, red>
               1. 1 offer in the queue, add <1, red> in the map
               2. 1 poll, offer 2, add <2, black> in the map.
               3. 2 poll, offer 1? since 1 is in the map, and the color matches, so we don't need to offer 1
                          offer 3, add < 3, red> in the map.
                          offer 4, add <4, red> in the map.
               4. 3 poll, offer 2? since 2 is the map and the color matches, so we don't need to offer 2
                          offer 4? since 4 is the map, the map color is red, but current 4 should be black, we find a contradiction. so return false;

               Q: is the List<GraphNode> graph all connected? Maybe not
                  we can start from each graph node in the graph, and check if the graph is bipartite. If all graphs are bipartite, then it is bipartite
                  if there is one is not bipartite, then it graph is not bipartite.

               Q: What if a graph node has been visited under previous graph node? do we still need to check this sub graph?
                    No, we can continue on the next one. Since this subgraph must be bipartite. why? if it is not, then already returned false;
                    we can use a Set<GraphNode> to check all visited node.
                    since Map<GraphNode, Integer> can be used to check visited as well. so we don need to create this set

         */
        if (graph.size() == 0) {
            return true;
        }
        //keep record of visited graphNode
        Map<GraphNode, Integer> visited = new HashMap<>();
        for (GraphNode node : graph) {
            if (!isBipartite(node, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBipartite(GraphNode node, Map<GraphNode, Integer> visited) {
        if (visited.containsKey(node)) {
            return true;
        }
        visited.put(node, 0);
        Queue<GraphNode> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            GraphNode curr = queue.poll();
            List<GraphNode> neis = curr.neighbors;
            int neiFlag = 1 - visited.get(curr);
            for (GraphNode nei : neis) {
                Integer flag = visited.get(nei);
                if (flag == null) {
                    visited.put(nei, neiFlag);
                    queue.offer(nei);
                } else if (!flag.equals(neiFlag)) {
                    return false;
                }
            }
        }
        return true;
    }


}
