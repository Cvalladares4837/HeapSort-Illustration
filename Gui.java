//
// Programmer:  Joe Kallarackel, Fayaz Khan, Jaydeep Patel, Christian Valladares
// Assignment:  Project 3 - Heap Illustration
// Date:        11/12/2015
// Description: This is the GUI class, and it handles the buttons, and graph printing
//              by referencing the array representation of the heap.  We design a 
//              GUI by using nested panels, and we also implement a message log
//              that contains all the actions performed on the heap from the beginning
//              of the application. We build a resizable heap image by referencing
//              the scaledPoint class.  
//             
//              
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class Gui extends JApplet implements ActionListener, ItemListener
{

    BorderLayout base;                // base is the base layout for all the GUI
    
    private JPanel top;               // top is the top panel of the GUI and contains
                                      //    drawings
    private JPanel bottom;            // bottom is the bottom panel of the GUI and 
                                      //    contains controls
    
    private JPanel bottomLeft;        // bottomLeft panel contains the buttons
    private JPanel bottomRight;       // bottomRight panel contains the newsFeed section
    
    
    private JButton insertToHeap;     // insertToHeap will let the user insert 
                                      //    insert to the heap
    private JButton searchValue;      // searchValue button will let the user search
                                      //    a value
    private JButton heapify;         // heapify button will move one frame forward
                                      //    in our minHeap animation
    private JButton unHeapify;         // unHeapify button will move one frame backwards
                                      //    in our minHeap animation
    private JButton fullHeapify;        // fullHeapify button will fast forward to the 
                                      //    last frame in our minHeap animation
    private JButton deleteValue;      // deleteValue button will prompt the user to 
                                      //    delete a value (min or max depending on
                                      //    heap).
    
    private JTextField valueToInsert; // valueToInsert will hold the text value to be inserted
                                      
    private JTextField valueToSearch; // valueToSearch will hold the value to be searched
    
    private ScaledPoint scaler;       // scaler is a scalepoint class that will return
                                      //    the exact location for GUI Elements

    private JTextArea feedContents;       // feedContents holds content for news feed.       
    private JScrollPane feedPane;         // feedPane holds feedContents for news feed.    
    private String feedMessage;           // feedMessage holds message for news feed.        
   
    private MinHeap theMinHeap;           // theMinHeap is a min heap

    private int heapSearchIndex;          // heapSearchIndex will contain 
                                          //   the index of the search
    private boolean searchSucceeded;      // searchSucceeded will flag if the
                                          //   search was a success
    
    private boolean enableDelete;
    
    private int [][] animateFrames;       // animateFrames contains frames to be animated
                                          //    (by heapifying or unheapifying). 
    private int numFrames;                // numFrames contains number of frames
                                          //    in the heap animation
    private int currFrame;                // currFrame contains current frame of
                                          //    animation
    
    private String theMessage;            // theMessage holds the entire log of actions
    
    
    public void init()
    // POST: Initialize the GUI
    {
    	
    	displayTutorial();				 // method display's the initial tutorial for program
    	
        
        theMinHeap = new MinHeap(); 	 // Initialize the Heaps.
        
        
        searchSucceeded = false;		 // Initialize Flags for search
        heapSearchIndex = -1;
        
        initializePanels();
        initializeWidgets();
        setUpPanels();
        addAllListeners();
        addToPanels();
        addToGui();
        
        heapify.setEnabled(false);
        unHeapify.setEnabled(false);
        fullHeapify.setEnabled(false);
        
        scaler = new ScaledPoint(31);
    }
    
    public void paint(Graphics g)
    {
        int xOrigin;       				//xOrigin to Hold a Node's horizontal Origin
        int yOrigin;       				//yOrigin to Hold a Node's vertical Origin
        int nodeDimension; 				//nodeDimension to hold a Node's square dimension
        int nodeValue;     				//to Hold a Node's Value
        
        int edgeXOrigin;
        int edgeXEnd;
        int edgeYOrigin;
        int edgeYEnd;
        
        Color nodeBackground; 			// nodeBackground holds the background color for the node
        Color fontColor;                               
        
        int numberOfElements; 			// holds the number of nodes for a heap (max or min)
        
        super.paint(g);
        
        
        nodeDimension = 15;
        nodeBackground = Color.BLACK;
        fontColor = Color.WHITE;
        
       
        
        animateFrames = theMinHeap.getState();      //Get all frames for animation
        numFrames = theMinHeap.getStateIndex();     //Get number of frames index.

        
        numberOfElements = animateFrames[currFrame].length;
        
        if(numFrames != 0)          				// If numFrames is zero (basecase) then do not
                                    				//   paint
        {
        	
            for(int i = 0; i < numberOfElements; i++)
            {
                xOrigin = scaler.getNodeXPosition(top, i);
                yOrigin = scaler.getNodeYPosition(top, i);
                
                edgeXOrigin = scaler.getEdgeXStartPosition(top, i, nodeDimension);
                edgeXEnd = scaler.getEdgeXEndPosition(top, i, nodeDimension);
                edgeYOrigin = scaler.getEdgeYStartPosition(top, i, nodeDimension);
                edgeYEnd = scaler.getEdgeYEndPosition(top, i, nodeDimension);
                
                nodeValue = animateFrames[currFrame][i];
                
                drawNode(xOrigin, yOrigin, nodeDimension, nodeValue, nodeBackground,
                            fontColor, g);
                
                g.setColor(Color.BLACK);
                g.drawLine(edgeXOrigin, edgeYOrigin, edgeXEnd, edgeYEnd);
            }
        }
        
        if(searchSucceeded == true)
        {
            xOrigin = scaler.getNodeXPosition(top, heapSearchIndex);
            yOrigin = scaler.getNodeYPosition(top, heapSearchIndex);
            
            nodeValue = theMinHeap.getByIndex(heapSearchIndex);
            
            nodeBackground = Color.BLUE;
            
            drawNode(xOrigin, yOrigin, nodeDimension, nodeValue, nodeBackground,
                    fontColor, g);
            
            
            searchSucceeded = false;				// Reset The flags and search indexes
            heapSearchIndex = -1;
        }
        
        theMessage = theMinHeap.actionsUpTo(currFrame, numFrames);
        feedContents.setText(""); 
        feedContents.setText(theMessage); 
        
        checkFramPosition();
    }
    
    public void displayTutorial()
    // POST: Displays a message box giving a quick tutorial of the program
    {
    	JOptionPane.showMessageDialog(null, "Welcome to our program! \n\nWe will be learning about "
    			+ "Min heaps \n\nGet PUMPED!!\n\n To build a tree please type in a integer in the "
    			+ "bottom left corner of the screen and then press Insert to Heap, repeat as "
    			+ "desired\n\nTo search for a value in your newly made tree type an integer and then "
    			+ "press Search value...peak a boo, I see you\n\nYou may notice the Heapify is visible"
    			+ " after inserting a few values.\nWe have to keep our tree balanced after pressing "
    			+ "Heapify it will attempt to balance your tree.\nPending on the value entered you "
    			+ "may have to press a heapify a few times but pay close attention on what is "
    			+ "happening each time\n\nAfter you Heapify, you can also UnHeapify this will "
    			+ "reverse the animation to the previous state, repeat till desired \n\nFull "
    			+ "Heapify does the same thing as Heapify but on steriods this will jump to "
    			+ "balanced tree, OH YEAH!!!\n\nWant to see a lame magic trick press the Delete Root"
    			+ "button and pay special attention to very top node.\n\nEnjoy and Behave yourself"
    			+  ":)");
    }
    
    public void addAllListeners()
    {
        insertToHeap.addActionListener(this);
        searchValue.addActionListener(this);
        deleteValue.addActionListener(this);
        heapify.addActionListener(this);
        unHeapify.addActionListener(this);
        fullHeapify.addActionListener(this);      
    }
    public void addToGui()
    // POST: Will add all widgets, layouts, and panels to the GUI
    {
        setLayout(base);   							//Will set a borderLayout layout as the base 
                           							//      Layout     
        
        add(base.CENTER, top);
        add(base.SOUTH, bottom);        
    }
    
    public void addToPanels()
    // POST: Will add all widgets to their respective panels
    {        
        
        bottomLeft.add(insertToHeap);					// Insert Bottom Left Widgets
        bottomLeft.add(valueToInsert);
        bottomLeft.add(searchValue);
        bottomLeft.add(valueToSearch);

        bottomLeft.add(unHeapify );
        bottomLeft.add(heapify );
        bottomLeft.add(fullHeapify);
        
        bottomLeft.add(deleteValue);
        
        
        bottomRight.add(feedPane, BorderLayout.CENTER); // Insert the News Feed
        
        
    }
    
    public void setUpPanels()
    // POST: Will initialize the panel Hierarchy to before adding buttons and
    //       elements.   It will also set proportions and Initial Sizes for 
    //       these panels
    {
        
        top.setMinimumSize(new Dimension(600,400 - 130));//Will set the minimum size
                                                         //  for the TOP panel to 
                                                         //  2/3 the required project t
                                                         //  heighT and a full width

        bottom.setMinimumSize(new Dimension(600,130));   //Will set the minimum size
                                                         //  for the bottom panel to 
                                                         //  1/3 the required project 
                                                         //  height and a full width
        
        
        
        bottom.add(bottomLeft);
        bottom.add(bottomRight);       
    }
    
    public void initializeWidgets()                                         
    // POST: Will initialize all the widgets in the applet                  
    {         
        
                                                               
        insertToHeap = new JButton("Insert To Heap");    //Left Side  
        searchValue = new JButton ("Search Value");
        deleteValue = new JButton("Delete Root");
        heapify = new JButton("Heapify"); 
        unHeapify = new JButton("UnHeapify");  
        fullHeapify = new JButton("Full Heapify"); 
                
        
        valueToInsert = new JTextField (); 
        valueToSearch = new JTextField (); 
        
        
        feedContents = new JTextArea(5,20);              //Right Side                    
        feedContents.setEditable(false);                    
        feedContents.setText(" ");                          
        
        feedPane = new JScrollPane(feedContents);
    }                                                                       
    
    public void initializePanels()                        
    //POST:  Will initialize all Panels and Layouts      
    {             
        base = new BorderLayout();        
                                        
        top = new JPanel();                              //Set up all panels    
        bottom = new JPanel();                             
        bottomRight  = new JPanel();                              
        bottomLeft = new JPanel();   
        
        top.setLayout(new FlowLayout());				 //Set the layout for all panels
        bottom.setLayout(new GridLayout(1,2));
        bottomLeft.setLayout(new GridLayout(4,2));
        bottomRight.setLayout(new BorderLayout()); 
        bottomLeft.setMinimumSize(new Dimension(600,130));
    }                                                     
    
    public void drawNode(int originX, int originY, int diceDimention, int diceFace,
            Color diceBackground, Color diceNumberColor, Graphics g)
    // PRE:  originX >=0, originY >= 0, and diceDimention >= 0 with all of them
    //       having pixels as units. The diceFace >0 and diceFace <= 6. 
    //       diceBackground and diceNumberColor objects must be initialized, 
    //       to different colors from each other. The Graphics object g is 
    //       necessary to paint into the JApplet. The best results will occur
    //       on a 96 DPI screen.
    // POST: Draws a dice with the specified colors in the JApplet.  The cube's 
    //       upper right corner will be located at the coordinates (originX, originY).
    //       diceDimention will be the dice's length and width. 
    //       The dice will have different background and font colors
    {
    int numberOriginX;      // Horizontal Origin for the Die's Number
    int numberOriginY;      // Vertical Origin for the Die's Number
    
    double numberBuffer;    // The spacing between the die number and the die edge 
    double fontSize;        // To hold the size of the Font (UNIT: Points)
    
    Font faceFont;          // To store the font to be used on the die's face        
   
    fontSize = diceDimention * (72.0/96.0); // There are approximately 72 points
                                       //    in an inch, and we assume that
                                       //    our display will have 96 pixels
                                       //    per Inch.  Using Stoichometry,
                                       //    we convert the number buffer
                                       //    from pixels to points
    
    numberOriginX = originX; 					 // Let die's number's horizontal
                                                 //   origin be placed at 1/3
                                                 //   of the die's width
    
    numberOriginY = originY + (diceDimention) - (diceDimention/4); // Let the die's dimension be
                                            					   //   placed at 3/4 of the die's
                                            					   //   height.  Calculated like 
                                            					   //   this to avoid dealing with
                                            					   //   doubles
    
    faceFont = new Font("SansSerif",Font.BOLD,(int) fontSize);     // Create a new font object
                                            
    
    
    g.setColor(diceBackground);                    			    // Set the color for the background
                                                             
    g.fillRect(originX, originY,diceDimention, diceDimention); // Draw the background     
    
    g.setColor(diceNumberColor);                  			   // Set the color for the die's font
                                                             
    g.setFont(faceFont);                          			   // Update the font                                                     
    

    g.drawString("" + diceFace, numberOriginX, numberOriginY); // Draw the Number in  the die's face                                                      
    
    }
    
    public void disableAll()
    // POST:  Enables all buttons 
    {
        insertToHeap.setEnabled(false);
        searchValue.setEnabled(false);
        deleteValue.setEnabled(false);                
    }
    
    public void enableAll()
    // POST:  Enable all buttons except for animation buttons
    {
        insertToHeap.setEnabled(true);
        searchValue.setEnabled(true);
        
        if(enableDelete == true)            		//If enableDelete flag is set to true
            deleteValue.setEnabled(true);   		//   enable the deleteValue button             
    }
    
    public void checkFramPosition()
    // POST: Disable or disables buttons based on whether the current animation
    //       frame is the last frame or not
    {
        
        if (numFrames == 0 || numFrames == 1) 		// If there are no frames (base case)
        {
            enableAll();                 			// enable all the buttons 
            heapify.setEnabled(false); 		    	// disable the forward button
            fullHeapify.setEnabled(false); 			// disable the fast forward button
            unHeapify.setEnabled(false);  			// enable all the move back button
        }
        
        else if(currFrame == (numFrames - 1) )   	// If the current frame is at last frame
        {
            enableAll();                 			// enable all buttons
            heapify.setEnabled(false);  			// disable the forward button
            fullHeapify.setEnabled(false); 			// disable the fast forward button
            unHeapify.setEnabled(true);   			// enable all the move back button
        }
        

        else if( currFrame == 0)
        {
            disableAll();
            unHeapify.setEnabled(false); 
            heapify.setEnabled(true);  
            fullHeapify.setEnabled(true); 
        }
        
        else                             			// If the current frame is not at last
                                         			//   frame
        {
            disableAll();
            heapify.setEnabled(true);  				//disable the forward button
            fullHeapify.setEnabled(true); 			//disable the fast forward button
            unHeapify.setEnabled(true);  			//enable all the move back button
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent arg0) 
    {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    // POST: Handles all actions performed on the GUI
    {
        int targetValue;  						 // target value holds the value to be searched or
                          						 //    inserted         
        checkFramPosition();
        
        if (e.getSource() == insertToHeap) 		//when user presses insert To Heap button 
        {
            targetValue = Integer.parseInt(valueToInsert.getText());
            
            theMinHeap.addValue(targetValue);
            enableDelete = true;    			//Whenever we add a value, enable the delete
                                    			//   button
            
            currFrame = 0;          			//Reset the current frame
        }
        
        if (e.getSource() == searchValue)		//when user presses Search value button 
        {
            targetValue = Integer.parseInt(valueToSearch.getText());
            
            heapSearchIndex = theMinHeap.search(targetValue);
            
            if(heapSearchIndex != -1)
            {
                searchSucceeded = true;
            }
            
            currFrame = 0;                       		//Reset the current frame
        }
        
        if (e.getSource() == deleteValue)				//when user presses delete root button 
        {
            
            theMinHeap.removeRoot();                    
            currFrame = 0;                              //Reset the current frame
            
            numFrames = theMinHeap.getStateIndex();
            
            if (animateFrames[numFrames-1].length == 0) //If there are no nodes to 
            {
                enableDelete = false;                   //Do not allow delete to be enables
                deleteValue.setEnabled(false);          //Disable delete
            }
            
        }
        
        if (e.getSource() == heapify)					//when user presses heapify button
        {
            currFrame++;
            checkFramPosition();
        }
        
        if (e.getSource() == unHeapify)					//when user presses unHeapify root button
        {
            currFrame--;           						//   decrement.
            
            if (currFrame == -1)       			 // If the current frame is not at the first frame.
                currFrame = 0;
            
            checkFramPosition();
        }
        
        if (e.getSource() == fullHeapify)				//when user presses Full Heapify button
        {
            currFrame = numFrames - 1;
            checkFramPosition();
        }        
        
        repaint();
    }

}
