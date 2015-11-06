//Class that deals with Max Heap
public class MaxHeap extends Heap
{
    public MaxHeap()
    //Post: Calling super to set up default constructor
    {
        super();                                            //default constructor
    }
    
    @Override
    public void heapifyDown(int index)
    //Pre:  Index must be between 0 and currentLength
    //Post: Sorts the elements in the array for removing
    {
        int i;                                              //Current index
        i = index;                                          //Setting index
        
        while(i != -1)                                      //While not invalid index
        {            
            int parentVal;                                  //Value of parent
            int leftIndex;                                  //index of left child
            int rightIndex;                                 //index of right child
            
            parentVal = array[i];                           //get parent value
            rightIndex = getChildRightIndex(parentVal);     //get right index
            leftIndex = getChildLeftIndex(parentVal);       //get left index
            
            if(rightIndex >= currentLength)                 //if rightIndex over currentLength
                rightIndex = -1;                            //set rightIndex to invalid
            if(leftIndex >= currentLength)                  //if leftIndex over currentLength
                leftIndex = -1;                             //set leftIndex to invalid
            
            if( rightIndex != -1 &&  leftIndex != -1)       //current has 2 children
            {
                int rightVal;                               //value of right child
                int leftVal;                                //value of left child
                
                rightVal= array[rightIndex];                //get right value
                leftVal = array[leftIndex];                 //get left value
                
                if(rightVal > leftVal)                      //if right child greater then left
                {
                    if(rightVal > parentVal)                //if right value greater then parent
                    {
                        array[i] = rightVal;                //swap parent and child
                        array[rightIndex] = parentVal;
                        
                        i = rightIndex;                     //right child new parent
                    }
                }
                else                                        //left greater then right value
                {
                    if(leftVal > parentVal)                 //left value greater then parent
                    {
                        array[i] = leftVal;                 //swap left child and parent
                        array[leftIndex] = parentVal;
                        
                        i = leftIndex;                      //left child new parent
                    }
                }
                
            }
            
            /*
            else if(rightIndex != -1)                       //No left index
            {
                int rightVal;                               //Right child value
                rightVal= array[rightIndex];
                
                if(rightVal > parentVal)
                {
                    array[i] = rightVal;
                    array[rightIndex] = parentVal;
                    i = rightIndex;
                }
                else 
                    i = -1;
            }
            */
            
            else if(leftIndex != -1)                        //No right child
            {
                int leftVal;                                //Left child value
                leftVal= array[leftIndex];                  //Get value of left child
                
                if(leftVal > parentVal)                     //Left child greater then parent
                {
                                        
                    array[i] = leftVal;                     //Swap parent and child                   
                    array[leftIndex] = parentVal;
                    
                    i = leftIndex;                          //Index is left child
                }
                else                                        //Parent greater then left child
                    i = -1;                                 //break loop index
            }
            else                                            //No children and other cases  
            {
                i = -1;                                     //Break loop index
            }
            
        }
        
    }
    
    @Override
    public void removeValue(int value)
    //Pre:  Index must be between 0 and currentLength
    //Post: Removes value from array and heapify accordingly 
    {
        int valIndex;                                       //index location
        valIndex = search(value);                           //get index from search
                
        if(valIndex != -1)                                  //index valid
        {
            int pIndex;                                     //Parent Index
            
            array[valIndex] =                               //Swap removed value and last leaf
                    array[currentLength-1];
            
            pIndex = getParentIndex(array[valIndex]);       //Get parent index of new spot
            
            if(array[valIndex] > array[pIndex])             //Current value is greater
            {
                heapifyUp(valIndex);                        //Heapify value upward
            }
            else                                            //Parent greater
            {
                heapifyDown(valIndex);                      //Heapify downward
            }
            
            currentLength--;                                //decrement counter
        }
        
        if(currentLength < MAX_LENGTH)                      //if current is less max length
        {
            isFull = false;                                 //if full is false
        }
    }
    
    @Override
    public void heapifyUp(int index)
    //Pre:  Index must be between 0 and currentLength
    //Post: Sorts the elements in array
    {
        int i;                                              //Index variable
        i = index;                                          //Index of where added
        
        while(i != 0)                                       //Loop up to root
        {
            int val;                                        //Value of last element
            int pIndex;                                     //parent index
            
            val = array[i];                                 //set value        
            pIndex = getParentIndex(val);                   //get parent index
            
            if( array[pIndex] < val)                        //if parent value is smaller than child
            {
                array[i] = array[pIndex];                   //swap parent and child          
                array[pIndex] = val;                
            }
            
            i = pIndex;                                     //parent is new child
            
        }
    } //end of method
}
