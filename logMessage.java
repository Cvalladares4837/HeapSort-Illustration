// Programmer:  Team 16: Christian Valladares, Joe Kallarackel, Fayaz Khan, Jaydeep Patel
// Assignment:  Project 3 Heap Illustration 
// Date:        November 12, 2015
// Description: A class that  updates the user every time a new action is performed. 


public class logMessage 
{
    private String completeLog[];             // A string to hold all the messages
                                              //   to be placed on the log.
   
    private String newLine;                   //newLine holds the new message to be 
                                              //   appended to the Feed        
    
    private int numMessages;                  //a count to determine the number of 
                                              //   messages in our completeLog 
    
    private int maxMessages;                  //a ceiling to the max number of messages
                                              //   that the log can hold.
    
    public logMessage()
    {
        completeLog = new String[10];         //Allocate 10 elements for the string array.        
        maxMessages = 10;                     //Keep track of the number of elements allocated        
        numMessages = 0;                      //There are no messages on initialization
    }
    
    private void growArray()
    //   PRE:  completeLog has reached its maximum capacity
    //   POST: Will Increase the capacity of the message log by twice as much, and
    //         will copy the elements in the old array into the new array
    {
        String [] tempLog;                  // tempLog is a temp array that will 
                                            //   hold the old array in addition to
                                            //   the newly allocated spaces
        
        int index;                          // an index to be used when traversing
                                            //   through the old array
        
        
        index = 0;                          // start counting indexes from 0
        
        tempLog = new String[maxMessages * 2]; // allocate twice as much space as current
                                               //   allocated space
        
        maxMessages = maxMessages * 2;      // Update the message ceiling
        
        for(String aMessage : completeLog)
        {
            tempLog[index] = aMessage;      // Copy the old message array into the
                                            //   new one
            
            index++;                        // Increment index
        }
        
        completeLog = tempLog;              // Update the message references
    }
    
    private void appendMessage(String theMessage)
    //
    
    {
        if (numMessages + 1 >= maxMessages -1)  //IF the new number of messages approaches
                                                //   the limit.
        {
            growArray();                        //Allocate more space.
        }
       
        completeLog[numMessages] = theMessage;  //Append the new message into the 
                                                //   array of messages.
        
        numMessages ++;                         //Increment the number of Messages.
    }
    
    public void feedCompare(int eltIndexA, int eltA, int eltIndexB, int eltB)
    //   PRE:  0 <= eltIndexA < currentLength
    //         0 <= eltIndexA < currentLength
    //         eltA is an integer corresponding to array[eltIndexA]
    //         eltB is an integer corresponding to array[eltIndexB]
    //   POST: Will append to the news feed a message that specifies that two elements are  
    //         being compared.                                                            
    {
        
        String newLine;             //newLine holds the new message to be appended 
                                    //   to the Feed
        
        newLine = String.format("%d in node %d compared with %d in node %d", 
                                    eltA, eltIndexA, eltB, eltIndexB);
        
        appendMessage(newLine);
       
    }
    
    public void feedSwap(int eltIndexA, int eltA, int eltIndexB, int eltB)
    //   PRE:  0 <= eltIndexA < currentLength
    //         0 <= eltIndexA < currentLength
    //         eltA is an integer corresponding to array[eltIndexA]
    //         eltB is an integer corresponding to array[eltIndexB]
    //   POST: Will append to the news feed a message that specifies that two elements are
    //         being swapped
    {
        
        String newLine;             //newLine holds the new message to be appended 
                                    //   to the Feed
        
        //Generate new message
        newLine = String.format("%d in node %d replaced with %d in node %d", 
                                    eltA, eltIndexA, eltB, eltIndexB);
        appendMessage(newLine);
    }
    
    public void feedInsert(int eltValue)
    //   PRE:  eltValue corresponds to the value inserted
    //   POST: will append to the news feed a message that specifies that a new
    //         element has been added
    {
        
        String newLine;             //newLine holds the new message to be appended 
                                    //   to the Feed

        newLine = String.format("%d added to the heap",  eltValue);  //Generate 
        appendMessage(newLine);
    }
    
    public void feedDelete(int eltValue)
    //   PRE:  eltValue corresponds to the value inserted
    //   POST: will append to the news feed a message that specifies that an
    //         element has been deleted
    {
        String newLine;             //newLine holds the new message to be appended 
                                    //   to the Feed

        newLine = String.format("%d deleted from the heap",  eltValue);  //Generate 
        appendMessage(newLine);
    }
    
    public String printUpTo (int stepNumber, int numSteps)
    //   PRE:  stepNumber <= numSteps
    //         numSteps = the number of steps the last iteration generated
    //   POST: returns a string that contains all the steps since app start
    //         up to steps Total - numSteps + stepNumber;
    //         FCTVAL = theMessage;
    {
        String theMessage;      // theMessage holds the total steps  
        int limit;              // limit holds the calculated number of steps
                                //   requested by the user
        
        theMessage = "";        // start with empty
        
        limit = numMessages - (numSteps-1) + (stepNumber);
        
        for(int i = 0; i < limit; i++)
        {
            theMessage = theMessage + (i+1) + ") " + completeLog[i] + "\n" ;                    
        }
        
        return theMessage;      // return the final string 
    }
   
}
