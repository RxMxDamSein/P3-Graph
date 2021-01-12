public class SPImpl implements SP{

    public static void main(String[] args) {
        int algo=0;
        WeightedGraphImpl w = new WeightedGraphImpl(new int[][] {
                {1,4,5},
                {0,2,3,4,5},
                {1,3,4},
                {1,2,4},
                {0,1,2,3,5},
                {0,1,4}},
                new double[][] {
                        {3.0,1.0,5.0},
                        {3.0,8.0,7.0,2.0,5.0},
                        {8.0,5.0,7.0},
                        {7.0,5.0,8.0},
                        {1.0,2.0,7.0,8.0,4.0},
                        {5.0,5.0,4.0}});

        SP sp=new SPImpl();
        if(algo==0){
            System.out.println("kein negativ Zyklus? : "+sp.bellmanFord(w,0));
        }else if(algo==1){
            sp.dijkstra(w,0);
        }

        for(int i=0;i<w.size();i++){
            System.out.println("v"+i+" dist: "+sp.dist(i)+" parent: "+sp.pred(i));
        }//passt für 0&1
        System.out.println();

        w = new WeightedGraphImpl(new int[][] {
                {1,2},
                {2},
                {1}},
                new double[][] {
                        {1,1},
                        {-1},
                        {-1}});
        sp=new SPImpl();
        if(algo==0){
            System.out.println("kein negativ Zyklus? : "+sp.bellmanFord(w,0));
        }else if(algo==1){
            sp.dijkstra(w,0);
        }
        for(int i=0;i<w.size();i++){
            System.out.println("v"+i+" dist: "+sp.dist(i)+" parent: "+sp.pred(i));
        }//wtf für 0
        System.out.println();

        w = new WeightedGraphImpl(new int[][] {
                {3},
                {0},
                {0,1},
                {1,2}},
                new double[][] {
                        {4},
                        {3},
                        {6,2},
                        {5,1}});
        sp=new SPImpl();
        if(algo==0){
            System.out.println("kein negativ Zyklus? : "+sp.bellmanFord(w,0));
        }else if(algo==1){
            sp.dijkstra(w,0);
        }
        for(int i=0;i<w.size();i++){
            System.out.println("v"+i+" dist: "+sp.dist(i)+" parent: "+sp.pred(i));
        }//passt für 0&1
        System.out.println();

        w = new WeightedGraphImpl(new int[][] {
                {},
                {0}},
                new double[][] {
                        {},
                        {-0.5}});
        sp=new SPImpl();
        if(algo==0){
            System.out.println("kein negativ Zyklus? : "+sp.bellmanFord(w,0));
        }else if(algo==1){
            sp.dijkstra(w,0);
        }
        for(int i=0;i<w.size();i++){
            System.out.println("v"+i+" dist: "+sp.dist(i)+" parent: "+sp.pred(i));
        }
        System.out.println();

        w = new WeightedGraphImpl(new int[][] {
                {1,2},
                {6,5,3,0},
                {0,4},
                {7,1},
                {2,6},
                {1},
                {1,4},
                {3}},
                new double[][] {
                        {7,6},
                        {3,7,1,7},
                        {6,4},
                        {6,1},
                        {4,7},
                        {7},
                        {3,7},
                        {6}});
        sp=new SPImpl();
        if(algo==0){
            System.out.println("kein negativ Zyklus? : "+sp.bellmanFord(w,0));
        }else if(algo==1){
            sp.dijkstra(w,0);
        }
        for(int i=0;i<w.size();i++){
            System.out.println("v"+i+" dist: "+sp.dist(i)+" parent: "+sp.pred(i));
        }//passt für 0&1
        System.out.println();

        w = new WeightedGraphImpl(new int[][] {
                //SABCDE
                //012345
                {5,1},
                {3},
                {1},
                {2},
                {1,3},
                {4}},
                new double[][] {
                        {8,10},
                        {2},
                        {1},
                        {-2},
                        {-4,-1},
                        {1}});
        sp=new SPImpl();
        if(algo==0){
            System.out.println("kein negativ Zyklus? : "+sp.bellmanFord(w,0));
        }else if(algo==1){
            sp.dijkstra(w,0);
        }
        for(int i=0;i<w.size();i++){
            System.out.println("v"+i+" dist: "+sp.dist(i)+" parent: "+sp.pred(i));
        }//passt für 0&1
        System.out.println();
    }

    private double distanz[];
    private int parent[];
    // Resultatwert true genau dann, wenn es im Graphen keinen vom
    // Startknoten aus erreichbaren Zyklus mit negativem Gewicht gibt.
    @Override
    public boolean bellmanFord(WeightedGraph g, int s) {
        distanz=new double[g.size()];
        parent=new int[g.size()];
        for (int i=0;i<g.size();i++){
            distanz[i]=INF;
            parent[i]=NIL;
        }
        distanz[s]=0;
        for(int i=0;i<g.size()-1;i++){
            for(int u=0;u<g.size();u++){
                for(int v=0;v<g.deg(u);v++){
                    int z=g.succ(u,v);
                    if((distanz[u]+g.weight(u,v))<distanz[z]){
                        distanz[z]=distanz[u]+g.weight(u,v);
                        parent[z]=u;
                    }
                }
            }
        }

        for(int v=0;v<g.size();v++){
            for(int i=0;i<g.deg(v);i++){
                int u=g.succ(v,i);
                if(distanz[u]+g.weight(v,i)<distanz[v]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void dijkstra(WeightedGraph g, int s) {
        distanz=new double[g.size()];
        parent=new int[g.size()];
        for (int i=0;i<g.size();i++){
            distanz[i]=INF;
            parent[i]=NIL;
        }
        distanz[s]=0;
        BinHeap<Double,Integer> binHeap=new BinHeap<>();
        BinHeap.Entry<Double,Integer>[] entrys=new BinHeap.Entry[g.size()];
        for(int i=0;i<g.size();i++){
            entrys[i]=binHeap.insert(distanz[i],i);
        }
        while (!binHeap.isEmpty()){
            int u=binHeap.extractMin().data();
            for(int i=0;i<g.deg(u);i++){
                int v=g.succ(u,i);
                if(binHeap.contains(entrys[v])){
                    if((distanz[u]+g.weight(u,i))<distanz[v]){
                        distanz[v]=distanz[u]+g.weight(u,i);
                        parent[v]=u;
                        binHeap.changePrio(entrys[v],distanz[v]);
                    }
                }
            }
        }
    }

    @Override
    public double dist(int v) {
        return distanz[v];
    }

    @Override
    public int pred(int v) {
        return parent[v];
    }
}
