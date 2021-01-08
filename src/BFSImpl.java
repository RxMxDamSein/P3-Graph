import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFSImpl implements BFS{

    public static void main(String[] args) {
        Graph g=new GraphImpl(new int[][]{
                //oDqjPvD54Ss
                {7,9,11},//0
                {8,10},//1
                {3,12},//2
                {2,4,7},//3
                {3},//4
                {6},//5
                {5,7},//6
                {0,3,6,11},//7
                {1,9,12},//8
                {0,8,10},//9
                {1,9},//10
                {0,7},//11
                {2,8},//12
                {}//13
        });
        BFS bfs=new BFSImpl();
        int s=0;
        bfs.search(g,s);
        for(int i=0;i< g.size();i++){
            System.out.println("from "+s+" to "+i+" dist: "+ bfs.dist(i)+" pred: "+bfs.pred(i));
        }
        //ZusatzMain.printGraph((GraphImpl) g);
    }
    private Queue<Integer> queue;
    private boolean[] visited;
    private int[] parent;
    private int start;
    @Override
    public void search(Graph g, int s) {
        start=s;
        queue=new LinkedList<>();
        queue.add(s);
        visited=new boolean[g.size()];
        visited[s]=true;
        parent=new int[g.size()];
        for(int i=0;i<parent.length;i++)
            parent[i]=-1;
        while (!queue.isEmpty()){
            int v=queue.poll();
            for(int i=0;i< g.deg(v);i++){
                int n=g.succ(v,i);
                if(!visited[n]){
                    queue.add(n);
                    visited[n]=true;
                    parent[n]=v;
                }
            }
        }
    }

    @Override
    public int dist(int v) {
        //Queue<Integer> path=new LinkedList<>();
        //path.add(v);
        int dist=0;
        while (parent[v]>=0){
            v=parent[v];
            //path.add(v);
            dist++;
        }
        if(v!=start){
            return INF;
        }else {
            return dist;
        }

    }

    @Override
    public int pred(int v) {
        int p=parent[v];
        if(p>=0)
            return p;
        else
            return NIL;
    }
}
