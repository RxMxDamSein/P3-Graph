public class SCCImpl implements SCC{

    public static void main(String[] args) {
        Graph g=new GraphImpl(new int[][]{
                {1},
                {2},
                {3},
                {0},
                {5},
                {6},
                {7},
                {4,5,6,7},
        });
        SCC scc=new SCCImpl();
        scc.compute(g);
        for(int i=0;i< g.size();i++){
            System.out.println("Knoten "+i+" ist in Zusammenhangskomponente "+scc.component(i));
        }
        System.out.println();
        Graph g2=new GraphImpl(new int[][]{
                {},
                {0},
                {1},
                {2},
                {5},
                {6},
                {7},
                {},
        });
        scc=new SCCImpl();
        scc.compute(g2);
        for(int i=0;i< g2.size();i++){
            System.out.println("Knoten "+i+" ist in Zusammenhangskomponente "+scc.component(i));
        }
        System.out.println();
        Graph g3=new GraphImpl(new int[][]{
                {},         //0
                {0,7,2},    //1
                {5},        //2
                {},         //3
                {3},        //4
                {4},        //5
                {1},        //6
                {6},        //7
                {9},        //8
                {},         //9
                {},         //10
                {10},       //11
                {10},       //12
                {10}        //13
        });
        scc=new SCCImpl();
        scc.compute(g3);
        for(int i=0;i< g3.size();i++){
            System.out.println("Knoten "+i+" ist in Zusammenhangskomponente "+scc.component(i));
        }
    }

    private int[] gruppen;
    @Override
    public void compute(Graph g) {
        gruppen=new DFSImpl().computeSCC(g);
    }

    @Override
    public int component(int v) {
        return gruppen[v];
    }
}
