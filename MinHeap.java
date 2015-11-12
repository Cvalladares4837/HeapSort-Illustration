import java.util.Arrays;


//
//
//      Search to delete find first occurance
//
//


//Class that focus with Min Heap
public class MinHeap extends Heap
{    
    
    public MinHeap()
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
            
            //Changes//
            //rightIndex = getChildRightIndex(parentVal);
            //leftIndex = getChildLeftIndex(parentVal);
            
            rightIndex = getChildRightIndex(i);             //get right index
            leftIndex = getChildLeftIndex(i);               //get left index
            
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
                
                if(rightVal <= leftVal)                      //if right child less then left
                {
                    if(rightVal <= parentVal)                //if right value less then parent
                    {
                        array[i] = rightVal;                //swap parent and child
                        array[rightIndex] = parentVal;
                        
                        //NEW
                        state[stateIndex] =                 //Copying board into state
                                Arrays.copyOf(array, currentLength);
                        stateIndex++;                       //Increase index
                        
                        i = rightIndex;                     //right child new parent
                    }
                }
                else                                        //left greater then right value
                {
                    if(leftVal < parentVal)                 //left value less then parent
                    {
                        array[i] = leftVal;                 //swap left child and parent
                        array[leftIndex] = parentVal;
                        
                        //NEW
                        state[stateIndex] =                 //Copying board into state
                                Arrays.copyOf(array, currentLength);
                        stateIndex++;                       //Increase index
                        
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
                
                if(leftVal < parentVal)                     //Left child less then parent
                {
                                        
                    array[i] = leftVal;                     //Swap parent and child                   
                    array[leftIndex] = parentVal;
                    
                    //NEW
                    state[stateIndex] =                     //Copying board into state
                            Arrays.copyOf(array, currentLength);
                    stateIndex++;                           //Increase index
                    
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
            
            
            //NEW
            stateIndex = 0;                                 //reset stateIndex
            
            //NEW
            state[stateIndex] =                             //Copying board into state
                    Arrays.copyOf(array, currentLength);
            stateIndex++;                                   //Increase index
            
            
            array[valIndex] =                               //Swap removed value and last leaf
                    array[currentLength-1];
            
            //Changes//pIndex = getParentIndex(array[valIndex]);     //Get parent index of new spot
            
            pIndex = getParentIndex(valIndex);              //Get parent index of new spot
            
            //Changes//if(array[valIndex] > array[pIndex])
            
            currentLength--;                                //decrement counter
            
            //NEW
            state[stateIndex] =                             //Copying board into state
                    Arrays.copyOf(array, currentLength);
            stateIndex++;                                   //Increase index
            
            if(array[valIndex] >= array[pIndex])            //Current value is greater
            {
                heapifyDown(valIndex);                      //Heapify downward
            }
            else                                            //Parent greater
            {
                heapifyUp(valIndex);                        //Heapify value upward
            }
            
            //currentLength--;                                //decrement counter
        }
        
        //NEW::TEST
        for(int i = 0; i < stateIndex;i++ )
            System.out.println(Arrays.toString(state[i]));
        
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
                      
            //Changes//pIndex = getParentIndex(val);                 //get parent index
            
            pIndex = getParentIndex(i);                     //get parent index  
            
            //System.out.println("pIndex " + pIndex);
            
            
            if( array[pIndex] > val)                        //if child value is less than parent
            {
                array[i] = array[pIndex];                   //swap parent and child          
                array[pIndex] = val;
                
                //NEW
                state[stateIndex] =                         //Copying board into state
                        Arrays.copyOf(array, currentLength);
                stateIndex++;                               //Increase index
                
              
            }          
            
            i = pIndex;                                     //parent is new child
            
        }
    } //end of method
    
    
}
