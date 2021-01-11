public class SPImpl implements SP{
    // Resultatwert true genau dann, wenn es im Graphen keinen vom
    // Startknoten aus erreichbaren Zyklus mit negativem Gewicht gibt.
    @Override
    public boolean bellmanFord(WeightedGraph g, int s) {
        return false;
    }

    @Override
    public void dijkstra(WeightedGraph g, int s) {

    }

    @Override
    public double dist(int v) {
        return 0;
    }

    @Override
    public int pred(int v) {
        return 0;
    }
}
