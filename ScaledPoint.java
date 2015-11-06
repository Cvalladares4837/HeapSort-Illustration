
//
// Programmer:  
// Assignment:  
// Date:        
// Description: 
//              

import javax.swing.*;

public class ScaledPoint extends JApplet
{
    int numElements;    // Counts the number of elements present in the heap
    int treeHeight;     // Counts the height of the tree, including the root.
    
    public ScaledPoint(int eltNumber)
    // POST: Creates an instance of Scaled Point and initializes the member variable:
    //       numElements = eltNumber
    {
        double logElements; //To hold the log_2 of the number of elements
        
        numElements = eltNumber;    
        //Will use the property of logarithms log_2 (a) = log_e(x)/log_2(e)to find
        //  the height of the tree, which happens to be lg_2(numberOfElements)
        logElements = (Math.log(numElements)/ Math.log(2));
        //Get the ceiling of the logarithm
        treeHeight = (int) (logElements) + 1;
    }
    
    public ScaledPoint()                                                
    // POST: Creates an instance of Scaled Point and initializes the member variable:
    //       numElements = 32
    {
        this(32);
    }
    
    public int getNodeXPosition(JPanel target, int elementNumber)
    // POST: Returns the Horizontal origin for a node in the heap. based on the 
    //       element number, and the total number of elements
    //       FCTVAL = Width *(elementNumber / (2^treeHeight)
    {
        int width;      // Holds the width of the panel
        int position;   // Holds the pixel position of a given node element.                
        int nodeLevel;
        int iterator;
        
        width = target.getWidth();
        nodeLevel = (int) (Math.log(elementNumber)/ Math.log(2)) + 1;
        //  iterator = elementNumber % (int) Math.pow(2.0,(nodeLevel - 1));
                
                
        // Get the position as a percentage of the panel width.   This will be
        //      the element number divided by 2 to the Tree Height.
        position = (int) (width  * elementNumber / (Math.pow(2.0,(double) nodeLevel)));
        
        return position;
    }
    
    public int getNodeYPosition(JPanel target, int elementNumber)
    // POST: Returns the Vertical origin for a node in the heap. based on the 
    //       element number, and the total number of elements
    //       FCTVAL = Width *(elementNumber / (2^treeHeight)
    {
        int height;     // Holds the height of the panel
        int position;   // Holds the pixel position of a given node element.                
        int nodeLevel;  // Holds the expected level of the node
        
        height = target.getHeight();
        
        nodeLevel = (int) (Math.log(elementNumber+1)/ Math.log(2)) + 1;
        
        // Get the position as a percentage of the panel width.   This will be
        //      the element number divided by 2 to the Tree Height.
        position = (int) height * (nodeLevel/ treeHeight);
        
        
        return position;
    }
}
