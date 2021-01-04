public class BinheapTest {
    public static void main(String[] args) {
        BinHeap<String,String> binheap= new BinHeap<>();
        binheap.insert("a","b");
        System.out.println(binheap.extractMin().data());
    }
}
/*
Um Binheap verwenden zu können muss man in ProjectStructure im Reiter Libraries das Binheap Zip hinzufügen!
 */
