
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
    
    public int getEdgeXStartPosition(JPanel target, int elementIndex, int nodeWidth)
    // PRE: target -- must be the panel that holds the tree
    //      elementIndex -- the Index of the current Index.  Must be greater than 1
    //      nodeWidth -- the width of the nodes for the drawing
    // POST: will return the start value for a node's edge.  We intend to draw from
    //          child to parent.
    //       FCTVAL =
    //
    {        
        int xStart = getNodeXPosition(target,elementIndex) + (nodeWidth/2);
        
        return xStart;
    }
    
    public int getEdgeXEndPosition(JPanel target, int elementIndex, int nodeWidth)
    // PRE: target -- must be the panel that holds the tree
    //      elementIndex -- the Index of the current Index.  Must be greater than 0
    //      nodeWidth -- the width of the nodes for the drawing
    // POST: will return the start value for a node's edge.  We intend to draw from
    //          child to parent.
    //       FCTVAL =
    //
    {        
        int parent;
        int xEnd;
        
        parent = (int)Math.floor((elementIndex-1)/2);
        
        xEnd = getNodeXPosition(target,parent) + (nodeWidth/2);
        
        return xEnd;
    }
    
    public int getEdgeYStartPosition(JPanel target, int elementIndex, int nodeWidth)
    // PRE: target -- must be the panel that holds the tree
    //      elementIndex -- the Index of the current Index.  Must be greater than 1
    //      nodeWidth -- the width of the nodes for the drawing
    // POST: will return the start value for a node's edge.  We intend to draw from
    //          child to parent.
    //       FCTVAL =
    //
    {        
        int yStart = getNodeYPosition(target,elementIndex);
        
        return yStart;
    
    }
    public int getEdgeYEndPosition(JPanel target, int elementIndex, int nodeWidth)
    // PRE: target -- must be the panel that holds the tree
    //      elementIndex -- the Index of the current Index.  Must be greater than 0
    //      nodeWidth -- the width of the nodes for the drawing
    // POST: will return the start value for a node's edge.  We intend to draw from
    //          child to parent.
    //       FCTVAL =
    //
    {        
        int parent;
        int xEnd;
        
        parent = (int)Math.floor((elementIndex-1)/2);
        
        xEnd = getNodeYPosition(target,parent) + (nodeWidth);
        
        return xEnd;
    }
    
    public int getNodeXPosition(JPanel target, int elementIndex)
    // POST: Returns the Horizontal origin for a node in the heap. based on the 
    //       element number, and the total number of elements
    //       FCTVAL = Width *(elementIndex / (2^treeHeight)
    {
        int width;      // width holds the width of the panel
        int position;   // position holds the pixel position of a given node element.                
        int nodeLevel;  // nodeLevel holds the level in which the current element resides
        int iterator;   // iterator holds the index of the current element in terms of
                        //    its level.   element 5 is in level 3 and it is the
                        //    second element of its level.        
        int numerator;  // numerator holds the end value which the numerator will hold when it
                        //    comes to the horizontal positioning. Numerator is an
                        //    odd number

        // Fetch the pane Width
        width = target.getWidth();
        // Compute the node level log_2(elementIndex); (to avoid zero values)
        nodeLevel = (int) (Math.log(elementIndex + 1)/ Math.log(2));
        // Compute the node's index for its level, which is the element's index
        //      modded with the total number of elements for a given level.
        iterator = ((elementIndex+1) % (int) Math.pow(2, nodeLevel));
        // THe numerator is the node's level-index, turned to an odd number
        numerator = (iterator)*2 + 1;
                
                
        // Get the position as a percentage of the panel width.   This will be
        //      the element number divided by 2 to the Tree Height.
        position = (int) (width  * numerator / (Math.pow(2.0,(double) nodeLevel + 1)));
        
        return position;
    }
    
    public int getNodeYPosition(JPanel target, int elementIndex)
    // POST: Returns the Vertical origin for a node in the heap. based on the 
    //       element number, and the total number of elements
    //       FCTVAL = Width *(elementIndex / (2^treeHeight)
    {
        int height;     // Holds the height of the panel
        int position;   // Holds the pixel position of a given node element.                
        int nodeLevel;  // Holds the expected level of the node
        
        height = target.getHeight();
        
        nodeLevel = (int) (Math.log(elementIndex + 1)/ Math.log(2));
        
        // Get the position as a percentage of the panel width.   This will be
        //      the element number divided by 2 to the Tree Height.
        position = (int) (height * (nodeLevel)/ (treeHeight*2));
        
        
        return position;
    }
}
