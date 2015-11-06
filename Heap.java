import java.util.Arrays;

/*
 *          Might want to add a method that we call that returns an error message
 * 
 */


//Parent Class for the heap
public class Heap
{
    protected static final int MAX_LENGTH = 32;     //Max length the array can be
    protected static int[] array;                   //Array to hold the values
    protected int currentLength;                    //Length of the current array
    protected boolean isFull;                         //Boolean if array full
    
    public Heap()
    //Post: Default constructor that will initialize values
    {
        currentLength = 0;                          //Default current value
        array = new int[MAX_LENGTH];                //New array with max size
        
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
            array[currentLength] = number;          //add number into array
            currentLength++;                        //increase current
            heapifyUp(currentLength-1);             //sort elements in array

            
        }
        
        if(currentLength == MAX_LENGTH)             //if current is max length
        {
            isFull = true;                          //if full is true
        }
    }
    
    
    public void removeRoot()
    //Post: Remove the top of the tree
    {
        array[0] = array[currentLength-1];          //Last leaf becomes root
        currentLength--;                            //Decrease length
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
    
    
    public int getParentIndex(int value)
    //Pre:  Value is a number in array
    //Post: Given a number gets the parent value's index
    {
        int n = search(value);                      //search for value to get index
        
        if(n != -1)                                 //index not -1 calculate parent index
            return (int)Math.floor( (n-1)/2);       //return parent index 
        
        return -1;                                  //return default/not found
    }
    
    public int getChildLeftIndex(int value)
    //Pre:  Value is a number in array
    //Post: Returns the index of left child given value
    {
        int n = search(value);                      //search for value to get index
        
        if(n != -1)                                 //index not -1 calculate left child index
            return (2*n)+1;                         
        
        return -1;                                  
    }
    
    public int getChildRightIndex(int value)
    //Pre:  Value is a number in array
    //Post: Returns the index of right child given value
    {
        int n = search(value);                      //search for value to get index
        
        if(n != -1)                                 //index not -1 calculate right child index
            return (2*n)+2;                         //return right child index
        
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
