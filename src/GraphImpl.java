public class GraphImpl implements Graph{
    public int[][] graph;
    public GraphImpl(int[][] ints) {
        graph = ints;
    }

    @Override
    public int size() {
        return graph.length;
    }

    @Override
    public int deg(int v) {
        return graph[v].length;
    }

    @Override
    public int succ(int v, int i) {
        return v+i;
    }

    @Override
    public Graph transpose() {
        for (int l = graph.length-1;l>=0;l--) {
            if (graph[l].length == 0) {
                continue;
            }
            Knotenswitch(l);
        }
        return null;
    }

    public void Knotenswitch(int x) {
        int deg = graph[x].length;
        if (deg != 2) {
            return;
        }
        int temp = graph[x][0];
        graph[x][0] = graph[x][1];
        graph[x][1] = temp;
        return;
    }
}
