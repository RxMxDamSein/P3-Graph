public class ZusatzMain {
    public static void main(String[] args) {

        GraphImpl g = new GraphImpl(new int [] [] {
                { 1, 2 },	// Knoten 0 hat als Nachfolger Knoten 1 und 2.
                { },	// Knoten 1 hat keine Nachfolger.
                { 2 },	// Knoten 2 hat als Nachfolger sich selbst.
                {1,2,3},
                {0}
        });

        System.out.println("Size: "+g.size());
        System.out.println("Deg von 0: "+g.deg(0));
        System.out.println("Deg von 1: "+g.deg(1));
        System.out.println("Deg von 2: "+g.deg(2));
        System.out.println("2 Nachfolger von 0: "+g.succ(0,1));

        printGraph(g);
        Graph g2=g.transpose();
        printGraph((GraphImpl) g2);

        WeightedGraphImpl wg=new WeightedGraphImpl(new int [] [] {
                { 1, 2 },	// Knoten 0 hat als Nachfolger Knoten 1 und 2.
                { },	// Knoten 1 hat keine Nachfolger.
                { 2 }	// Knoten 2 hat als Nachfolger sich selbst.
        }, new double [] [] {
                { 1.5, 0 },	// Gewichte der Kanten (0, 1) und (0, 2).
                { },
                { -3.7 }	// Gewicht der Kante (2, 0).
        });
        printWeightedGraph(wg);
        printGraph((GraphImpl) wg.transpose());
    }

    public static void printGraph(GraphImpl g) {
        System.out.println("_________________☺");
        System.out.println("Graph: ");
        for (int i=0;i<g.graph.length;i++) {
            System.out.print("Knoten "+i+": ");
            for (int j=0;j<g.graph[i].length;j++) {
                System.out.print(g.graph[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("_________________☻");
    }
    public static void printWeightedGraph(WeightedGraphImpl g) {
        System.out.println("_________________☺");
        System.out.println("Graph: ");
        for (int i=0;i<g.graph.length;i++) {
            System.out.print("Knoten "+i+": ");
            for (int j=0;j<g.graph[i].length;j++) {
                System.out.print(g.graph[i][j]+"("+g.weight[i][j]+") ");
            }
            System.out.println();
        }
        System.out.println("_________________☻");
    }

}
