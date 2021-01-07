public class ZusatzMain {
    public static void main(String[] args) {
        GraphImpl g = new GraphImpl(new int [] [] {
                { 1, 2 },	// Knoten 0 hat als Nachfolger Knoten 1 und 2.
                { },	// Knoten 1 hat keine Nachfolger.
                { 2 },	// Knoten 2 hat als Nachfolger sich selbst.
                { 3,4 },
                {4   }
        });

        System.out.println("Size: "+g.size());
        System.out.println("Deg von 0: "+g.deg(0));
        System.out.println("Deg von 1: "+g.deg(1));
        System.out.println("Deg von 2: "+g.deg(2));
        System.out.println("2 Nachfolger von 0: "+g.succ(0,2));

        printGraph(g);
        g.transpose();
        printGraph(g);
    }

    public static void printGraph(GraphImpl g) {
        System.out.println("________________");
        System.out.println("Graph: ");
        for (int i=0;i<g.graph.length;i++) {
            System.out.print("Knoten "+i+": ");
            for (int j=0;j<g.graph[i].length;j++) {
                System.out.print(g.graph[i][j]+" ");
            }
        }
        System.out.println("__________________");
    }
    static void printKonten(int indx,String parent,GraphImpl g) {
        System.out.println(" "+parent+indx);
        for (int i = 0;i<g.graph[indx].length;i++) {
            printKonten(g.graph[indx][i],parent+" "+indx+" ->",g);
        }
    }
}
