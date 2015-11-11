import java.util.Arrays;

/*
 *          Might want to add a method that we call that returns an error message
 * 
 */


//Parent Class for the heap
public class Heap
{
    protected static final int MAX_LENGTH = 32;     //Max length the array can be
    protected static final int MAX_ROW = 10;
    protected static int[] array;                   //Array to hold the values
    protected int currentLength;                    //Length of the current array
    protected boolean isFull;                       //Boolean if array full
    
    //NEW
    protected int[][] state;                        //Array holding intermediate state changes
    int stateIndex;                                 //Max rows in state
    
    
    public Heap()
    //Post: Default constructor that will initialize values
    {
        currentLength = 0;                          //Default current value
        array = new int[MAX_LENGTH];                //New array with max size
        
        //NEW
        state = new int[MAX_ROW][MAX_LENGTH];       //Initializing state
        stateIndex = 0;                             //Initialize state index
 
    }
    
    public boolean isEmpty()
    //Post: true id array empty else    
    {
        if(currentLength == 0 )                     //current length is zero
            return true;                            //empty 
        
        return false;                               //otherwise false
    }
    
    public void addValue(int number)
    //Pre:  number is a valid number within bounds of integer
    //Post: Adds a value into the heap array     
    {
        if(!isFull)                                 //if not full
        {
            //New
            stateIndex = 0;                         //reset stateIndex
            
            array[currentLength] = number;          //add number into array
            currentLength++;                        //increase current
            
            //NEW
            state[stateIndex] =                     //Coping board into state
                    Arrays.copyOf(array, currentLength);
            stateIndex++;                           //Increase index
            
            heapifyUp(currentLength-1);             //sort elements in array
            
            //NEW : TESTING
            for(int i = 0; i < stateIndex;i++ )
                System.out.println(Arrays.toString(state[i]));
            
        }
        
        if(currentLength == MAX_LENGTH)             //if current is max length
        {
            isFull = true;                          //if full is true
        }
    }
    
    
    public void removeRoot()
    //Post: Remove the top of the tree
    {
        stateIndex = 0;                             //reaset stateIndex
        
        //NEW
        state[stateIndex] =                         //Copying board into state
                Arrays.copyOf(array, currentLength);
        stateIndex++;                               //Increase index
        
        array[0] = array[currentLength-1];          //Last leaf becomes root
        currentLength--;                            //Decrease length
        
        //NEW
        state[stateIndex] =                         //Copying board into state
                Arrays.copyOf(array, currentLength);
        stateIndex++;                               //Increase index

        
        heapifyDown(0);                             //Sort
        
        if(currentLength < MAX_LENGTH)              //if current is less than max length
        {
            isFull = false ;                        //if full is false
        }
    }
    
    public void removeValue(int value)
    //Pre:  values is valid number on the list
    //Post: Removes the values and adjusts the heap
    {
        
    }
    
    /* Changes
    public int getParentIndex(int value)
    //Pre:  Value is a number in array
    //Post: Given a number gets the parent value's index
    {
        int n = search(value);                      //search for value to get index
        
        if(n != -1)                                 //index not -1 calculate parent index
            return (int)Math.floor( (n-1)/2);       //return parent index 
        
        return -1;                                  //return default/not found
    }*/
    
    public int getParentIndex(int childIndex)
    //Pre:  childIndex is an index of array within currentLength and 0
    //Post: Given an index gets the parent value's index
    {             
        
        if(childIndex != -1)                        //index not -1 calculate parent index
            return (int)Math.                       //calculate parent index
                    floor((childIndex-1)/2);
       
        return -1;                                  //return default/not found
    }
    
    /* Changes
    public int getChildLeftIndex(int value)
    //Pre:  Value is a number in array
    //Post: Returns the index of left child given value
    {
        int n = search(value);                      //search for value to get index
        
        if(n != -1)                                 //index not -1 calculate left child index
            return (2*n)+1;                         
        
        return -1;                                  
    }
    */
    
    public int getChildLeftIndex(int pIndex)
    //Pre:  pIndex is an index of the array within 0 and currentLength
    //Post: Returns the index of left child given value
    {
        int clIndex;                                //left child index
        
        if( pIndex != -1)                           //index not -1 calculate left child index
        {
            clIndex = (2*pIndex)+1;                 //calculate left child index
            
            if(clIndex >= currentLength)            //out of bounds check
                return -1;                          //return invalid
            else                                    //inbounds
                return clIndex;                     //return left child index
        }
        
        return -1;                                  //default return -1
    }
    
    /* Changes
    public int getChildRightIndex(int value)
    //Pre:  pIndex is an index of the array within 0 and currentLength
    //Post: Returns the index of right child given value
    {
        int n = search(value);                      //search for value to get index
        
        if(n != -1)                                 //index not -1 calculate right child index
            return (2*n)+2;                         //return right child index
        
        return -1;                                  //return default/not found
    }
    */
    
    public int getChildRightIndex(int pIndex)
    //Pre:  pIndex is an index of the array within 0 and currentLength
    //Post: Returns the index of right child given value
    {
        int rcIndex;                                //right child index
        
        if(pIndex != -1)                            //index not -1 calculate right child index
        {
            rcIndex = (2*pIndex)+2;                 //calculate right child index
            
            if(rcIndex >= currentLength)            //out of bounds checks
                return -1;                          //return invalid
            else                                    //inbounds
                return rcIndex;                     //return right child index
            
        }
        
        return -1;                                  //return default/not found
    }
    
    public int search(int value)
    //Pre:  values being search in within the tree
    //Post: returns the index of value
    {
        for(int i = 0; i < currentLength;i++)      //loop through array
        {
            if(value == array[i])                  //value is in array
                return i;                          //return index
        }
        
        return -1;                                 //return not found
    }
    
    public void heapifyDown(int index)
    //Pre:  Index must be between 0 and currentLength
    //Post: Sorts the elements in the array for removing
    {
    }
    
    public void heapifyUp(int index)
    //Pre:  Index must be between 0 and currentLength
    //Post: Sorts the elements in array
    {
    }
    
    public int getCurrentLength()
    // POST: Will return the current length for a Heap
    //       FCTVAL = currentLength
    {
        return currentLength;
    }
    
    public int getByIndex(int index)
    // PRE: index >= 0 <= currentLength
    // POST: Will return the value of the heap at given index
    //       FCTVAL = array[index];
    {
        return array[index];
    }
    
    public int[][] getState()
    //Pre: FCTVAL = state array hold transitions
    {
        return state;                            //Returning state
    }
    
    public int getStateIndex()
    //Pre: FCTVAl = stateIndex which represents max rows in state
    {
        return stateIndex;                       //Returning state index       
    }
    
    public String toString()
    //Post: FCTVAL = srring version of current array, current array length, full status
    {    
        String s;                                //String
        s= "";                                   //Initializing string
        
        for(int i = 0; i < currentLength; i++)   //Loop through array
        {
            s = s + array[i] + ",";              //Build string
        }
        
        //return string of values
        return "Current Length = " + currentLength + "\n"+ s + " FULL " + isFull;
    }
    
    
}
