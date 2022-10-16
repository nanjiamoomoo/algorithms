package graph.classicgraphexpression;

/**
 * How to convert a random graph expression to our standard classic graph expression
 */
public class GraphConverterTemplate {

    /**
     * int[][] matrix: [weight, fromNode, toNode]
     * when we solve algorithm problems, we can convert the matrix to our familiar classic graph
     * @param matrix
     * @return
     */
    public static Graph<Integer> createGraph(int[][] matrix) {
        Graph<Integer> graph = new Graph<>();
        for (int[] curr : matrix) {
            int weight = curr[0];
            int from = curr[1];
            int to = curr[2];

            if(!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node<>(from));
            }
            if(!graph.nodes.containsKey(to)) {
                graph.nodes.put(from, new Node<>(to));
            }

            Node<Integer> fromNode = graph.nodes.get(from);
            Node<Integer> toNode = graph.nodes.get(to);
            Edge<Integer> newEdge = new Edge<>(weight, fromNode, toNode);
            fromNode.neighbors.add(toNode);
            fromNode.edges.add(newEdge);
            fromNode.out++;
            toNode.in++;
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
