import java.util.ArrayList;

public class GraphImpl implements Graph{
    protected int[][] graph;
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
        return graph[v][i];//einfahc v+i zu returnen wäre einfach den v+i ten Knoten zurückgeben und nicht den i ten Nachfolger
    }


    private int[][] transposeArray(){
        ArrayList<Integer>[] arrayLists= new ArrayList[graph.length];
        for(int i=0;i<graph.length;i++){
            arrayLists[i]=new ArrayList<>();
        }
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[i].length;j++){
                arrayLists[graph[i][j]].add(i);
            }
        }
        int[][] newg=new int[graph.length][];
        for(int i=0;i<newg.length;i++){
            newg[i]=new int[arrayLists[i].size()];
            for(int j=0;j<arrayLists[i].size();j++){
                newg[i][j]=arrayLists[i].get(j);
            }
        }
        return newg;
    }
    @Override
    public Graph transpose() {
        return new GraphImpl(transposeArray());
    }

    /*public Graph graphANDtranpose(){
        ArrayList<Integer>[] arrayLists= new ArrayList[graph.length];
        for(int i=0;i<graph.length;i++){
            arrayLists[i]=new ArrayList<>();
        }
        int[][] ret=new int[graph.length][];
        int[][] trans=transposeArray();
        for(int i=0;i< graph.length;i++){
            for(int j=0;j<graph[i].length;j++)
                arrayLists[i].add(graph[i][j]);
        }
        return new GraphImpl(ret);
    }*/


}
