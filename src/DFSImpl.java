
import java.util.Arrays;

public class DFSImpl implements DFS{

    public static void main(String[] args) {
        Graph g=new GraphImpl(new int[][]{
                {1,9},      //0
                {0,8},      //1
                {3},        //2
                {2,4,5,7},  //3
                {3},        //4
                {3,6},      //5
                {5,7},      //6
                {3,6,8,10,11},
                {1,7,9},    //8
                {0,8},      //9
                {7,11},     //10
                {7,10},     //11
                {}         //12
        });
        DFS dfs=new DFSImpl();
        dfs.search(g);
        for(int i=0;i< g.size();i++){
            System.out.println("from "+0+" to "+i+" det: "+ dfs.det(i)+" fin: "+dfs.fin(i));
        }
        for(int i=0;i< g.size();i++){
            System.out.println("Abschluss "+i+" "+dfs.sequ(i));
        }
        DFS dfs2=new DFSImpl();
        dfs2.search(g,dfs);
        for(int i=0;i< g.size();i++){
            System.out.println("from "+dfs.sequ(g.size()-1)+" to "+i+" det: "+ dfs2.det(i)+" fin: "+dfs2.fin(i));
        }
        for(int i=0;i< g.size();i++){
            System.out.println("Abschluss "+i+" "+dfs2.sequ(i));
        }

        DFS dfs3=new DFSImpl();
        System.out.println("Der Graph ist "+((dfs3.sort(g))?"":"nicht ")+"topologisch");
        GraphImpl g2 = new GraphImpl(new int [] []{
                {1, 2},
                {},
                {}
        });
        DFS dfs4=new DFSImpl();
        System.out.println("Der Graph ist "+((dfs4.sort(g2))?"":"nicht ")+"topologisch");
    }

    private int[] visited;//-1 not visited weiß 0 we are working with it grau 1 we are done so we visited schwarz
    private int[] entdeckung;
    private int[] abschluss;
    private int count;
    private Graph g;
    private boolean topologisch;

    private void searchinit(){
        count=1;
        topologisch=true;
        entdeckung=new int[g.size()];
        abschluss=new int[g.size()];
        visited=new int[g.size()];
        for(int i=0;i<g.size();i++){
            entdeckung[i]=Integer.MAX_VALUE;
            abschluss[i]=Integer.MAX_VALUE;
            visited[i]=-1;//weiß
        }
    }
    @Override
    public void search(Graph g) {
        this.g=g;
        searchinit();

        for(int i=0;i<g.size();i++)
            fahreKnoten(i);
    }

    @Override
    public void search(Graph g, DFS d) {

        this.g=g;
        searchinit();

        for(int i=g.size()-1;i>=0;i--)
            fahreKnoten(d.sequ(i));
    }

    private void fahreKnoten(int v){
        if(visited[v]>=0){
            if(visited[v]==0)//kommt zu einem grauen Knoten
            {
                topologisch=false;//nicht topologisch wegen Rückwärtskante!
                //System.out.println("Zyklus bei "+v);
            }
            return;
        }
        visited[v]=0;//grau
        entdeckung[v]=count;
        count++;
        for(int i=0;i<g.deg(v);i++){
            fahreKnoten(g.succ(v,i));
        }
        visited[v]=1;//schwarz
        abschluss[v]=count;
        count++;
        return;
    }

    @Override
    public boolean sort(Graph g) {
        search(g);
        return topologisch;
    }

    @Override
    public int det(int v) {
        return entdeckung[v];
    }

    @Override
    public int fin(int v) {
        return abschluss[v];
    }

    private int[] oldabshluss=null;
    int[] aASC;
    @Override
    public int sequ(int i) {
        //i=0 ret kleinste abschlusszeit
        if(abschluss!=oldabshluss){
            int[]sorted=abschluss.clone();
            Arrays.sort(sorted);
            oldabshluss=abschluss;
            aASC=new int[g.size()];
            for(int j=0;j<g.size();j++){
                for(int k=0;k<g.size();k++){
                    if(sorted[j]==abschluss[k])
                        aASC[j]=k;
                }
            }
        }
        return  aASC[i];
    }

    private int gruppenNr;
    private int[] gruppen;
    //Die eigentliche SCCImpl verwendet aber DFS also ist es hier praktischer
    public int[] computeSCC(Graph g){
        this.g=g;
        DFS d=new DFSImpl();
        d.search(g);
        searchinit();
        gruppenNr=1;
        gruppen=new int[g.size()];
        for(int i=g.size()-1;i>=0;i--){
            fahreKnoten_gruppen(d.sequ(i));
            gruppenNr++;
        }
        return gruppen;
    }

    private void fahreKnoten_gruppen(int v){
        if(visited[v]>=0){
            return;
        }
        visited[v]=0;//grau
        entdeckung[v]=count;
        gruppen[v]=gruppenNr;
        count++;
        for(int i=0;i<g.deg(v);i++){
            fahreKnoten_gruppen(g.succ(v,i));
        }
        visited[v]=1;//schwarz
        abschluss[v]=count;
        count++;
        return;
    }

}
