public class MSFImpl implements MSF{


    public static void main(String[] args) {
        //ABCDEF
        //012345
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
        MSF msf=new MSFImpl();
        for(int s=0;s<w.size();s++){
            msf.compute(w,s);
            System.out.println("gestartet mit: "+s);
            for(int i=0;i<w.size();i++){
                System.out.println(i+" <- "+msf.pred(i));
            }
        }
        w = new WeightedGraphImpl(new int[][] {
                {1,2},
                {0,3},
                {0,4,5},
                {1,7},
                {2},
                {2,6},
                {5,7},
                {3,6}},
                new double[][] {
                        {1,5},
                        {1,4},
                        {5,5,8},
                        {4,6},
                        {5},
                        {8,5},
                        {5,3},
                        {6,3}});
        msf=new MSFImpl();
        for(int s=0;s<w.size() && s<1;s++){//works
            msf.compute(w,s);
            System.out.println("gestartet mit: "+s);
            for(int i=0;i<w.size();i++){
                System.out.println(i+" <- "+msf.pred(i));
            }
        }
        w = new WeightedGraphImpl(new int[][] {
                //ABCDEFG
                //0123456
                {1,2,3},
                {0,2,4},
                {5,4,1,0},
                {0,5},
                {5,2,1},
                {2,3,4,6},
                {5}},
                new double[][] {
                        {2,3,3},
                        {2,4,3},
                        {6,1,4,3},
                        {3,7},
                        {8,1,3},
                        {6,7,8,9},
                        {9}});
        msf=new MSFImpl();
        for(int s=0;s<w.size() && s<1;s++){//works
            msf.compute(w,s);
            System.out.println("gestartet mit: "+s);
            for(int i=0;i<w.size();i++){
                System.out.println(i+" <- "+msf.pred(i));
            }
        }

    }

    private int[] pi;
    @Override
    public void compute(WeightedGraph g, int s) {
        BinHeap<Double,Integer>binHeap = new BinHeap<>();
        BinHeap.Entry<Double,Integer>[] entrys=new BinHeap.Entry[g.size()];
        pi=new int[g.size()];
        for(int i=0;i<g.size();i++){
            if(i!=s){
                entrys[i]=binHeap.insert(Double.POSITIVE_INFINITY,i);
            }
            pi[i]= NIL;
        }
        int u=s;
        while (!binHeap.isEmpty()){
            for(int i=0;i<g.deg(u);i++){
                int v=g.succ(u,i);
                if(binHeap.contains(entrys[v])){
                    binHeap.changePrio(entrys[v],g.weight(u,i));
                    pi[v]=u;
                }
            }
            u=binHeap.extractMin().data();
        }
    }

    @Override
    public int pred(int v) {
        return pi[v];
    }
}
