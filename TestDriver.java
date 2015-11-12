import java.util.Arrays;

public class TestDriver
{
    public static void main(String[] args)
    {
        /*
        Heap h = new Heap();
        
        h.addValue(10);
        h.addValue(9);
        h.addValue(13);
        h.addValue(4);
        h.addValue(6);
        h.addValue(8);
        h.addValue(2);
        System.out.println(h);
        System.out.println(h.search(8));
        
        h.removeRoot();
        System.out.println(h);
        
        h.removeValue(4);
        System.out.println(h);
        
        System.out.println("Parent: " + h.array[h.getParentIndex(13)]);
        System.out.println("lChild: " + h.array[h.getChildLeftIndex(9)]);
        System.out.println("rChild: " + h.array[h.getChildRightIndex(9)]);
        */
        
        /*
        System.out.println("Max Heap");
        MaxHeap h = new MaxHeap();
        
        h.addValue(10);
        System.out.println(h);
        h.addValue(9);
        System.out.println(h);
        h.addValue(13);
        System.out.println(h);
        h.addValue(4);
        System.out.println(h);
        h.addValue(6);
        System.out.println(h);
        h.addValue(8);
        System.out.println(h);
        h.addValue(12);
        System.out.println(h);
        
        System.out.println();
        System.out.println("End of building \n");
        
        h.removeValue(13);
        System.out.println(h);
        
        //Remove
        System.out.println(h.search(10));
                        
        //System.out.println("Parent: " + h.array[h.getParentIndex(13)]);
        //System.out.println("lChild: " + h.array[h.getChildLeftIndex(9)]);
        //System.out.println("rChild: " + h.array[h.getChildRightIndex(9)]);
        */
        
        
        
        System.out.println("Min Heap");
        MinHeap h = new MinHeap();
        
        h.addValue(13);
        System.out.println(h);
        h.addValue(9);
        System.out.println(h);
        h.addValue(12);
        System.out.println(h);
        h.addValue(4);
        System.out.println(h);
        h.addValue(6);
        System.out.println(h);
        h.addValue(10);
        System.out.println(h);
        h.addValue(8);
        System.out.println(h);
        
        System.out.println();
        System.out.println("End of building \n");
        
        //h.removeRoot();
        h.removeValue(6);
        System.out.println(h);
        
        //Remove
        System.out.println(h.search(10));
                        
        double logElements = (Math.log(4)/ Math.log(2));
        //Get the ceiling of the logarithm
        int treeHeight = (int) logElements + 1;
        
        System.out.println(treeHeight);
        //System.out.println("Parent: " + h.array[h.getParentIndex(13)]);
        //System.out.println("lChild: " + h.array[h.getChildLeftIndex(9)]);
        //System.out.println("rChild: " + h.array[h.getChildRightIndex(9)]);
        
    }
    
}
