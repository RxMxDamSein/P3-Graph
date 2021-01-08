public class WeightedGraphImpl extends  GraphImpl implements WeightedGraph {

    double[][] weight;

    public WeightedGraphImpl(int[][] ints) {
        super(ints);
        weight=new double[graph.length][];
        for(int i=0;i<weight.length;i++)
            weight[i]=new double[graph[i].length];
    }

    public WeightedGraphImpl(int[][] ints, double[][] doubles) {
        super(ints);
        weight=doubles;
    }

    @Override
    public double weight(int v, int i) {
        return weight[v][i];
    }
}
