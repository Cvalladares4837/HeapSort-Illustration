//
// Programmer:  
// Assignment:  
// Date:        
// Description: 
//              
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class Gui extends JApplet implements ActionListener, ItemListener
{

    //Layouts
    BorderLayout base;                // The base layout for all the GUI
    
    //Panels
    private JPanel top;               // top is the top panel of the GUI and contains
                                      //    drawings
    private JPanel bottom;            // bottom is the bottom panel of the GUI and 
                                      //    contains controls
    
    private JPanel bottomLeft;        // bottomLeft contains the buttons
    private JPanel bottomRight;       // bottomRight contains the newsFeed section
    
    
    //Widgets
    //  Left Side
    private JButton insertToHeap;     // insertToHeap will let the user insert 
                                      //    insert to the heap
    private JButton searchValue;      // searchValue button will let the user search
                                      //    a value
    private JButton deleteValue;      // deleteValue button will promt the user to 
                                      //    delete a value (min or max depending on
                                      //    heap.
    private JTextField valueToInsert; // valueToInsert will hold the text value to be inserted
                                      
    private JTextField valueToSearch; // valueToSearch will hold the value to be searched
    
    private ScaledPoint scaler;       // scaler is a scalepoint class that will return
                                      //    the exact location for GUI Elements
    //  Right Side
    private JTextArea feedContents;       // feedContents holds content for news feed.       
    private JScrollPane feedPane;         // feedPane holds feedContents for news feed.    
    private String feedMessage;           // message for news feed.        
       
    // Node Elemenents
    private Node aNode = new Node();
   
    // Heaps
    private MinHeap theMinHeap;           // theMinHeap is a min heap
    private MaxHeap theMaxHeap;           // theMaxHeap is a max heap

    // Searching
    private int heapSearchIndex;          // heapSearchIndex will contain 
                                          //   the index of the search
    private boolean searchSucceeded;      // searchSucceeded will flag if the
                                          //   search was a success
    
    public void init()
    // POST: Initialize the GUI
    {
        // Initialize the Heaps.
        theMinHeap = new MinHeap(); 
        theMaxHeap = new MaxHeap(); 
        
        // Initialize Flags for search
        searchSucceeded = false;
        heapSearchIndex = -1;
        
        initializePanels();
        initializeWidgets();
        setUpPanels();
        addAllListeners();
        addToPanels();
        addToGui();
        
        scaler = new ScaledPoint(31);
    }
    
    public void paint(Graphics g)
    {
        int xOrigin;       //xOrigin to Hold a Node's horizontal Origin
        int yOrigin;       //yOrigin to Hold a Node's vertical Origin
        int nodeDimension; //nodeDimension to hold a Node's square dimension
        int nodeValue;     //to Hold a Node's Value
        
        Color nodeBackground; // nodeBackground holds the background color for the node
        Color fontColor;                               
        
        int numberOfElements; // holds the number of nodes for a heap (max or min)
        
        super.paint(g);
        
        
        nodeDimension = 30;
        nodeBackground = Color.BLACK;
        fontColor = Color.WHITE;
        
        numberOfElements = theMinHeap.getCurrentLength();
        
        for(int i = 0; i < numberOfElements; i++)		 
        {
            xOrigin = scaler.getNodeXPosition(top, i);
            yOrigin = scaler.getNodeYPosition(top, i);
            
            nodeValue = theMinHeap.getByIndex(i);
            
            drawNode(xOrigin, yOrigin, nodeDimension, nodeValue, nodeBackground,
                        fontColor, g);
        }
        
        if(searchSucceeded == true)
        {
            xOrigin = scaler.getNodeXPosition(top, heapSearchIndex);
            yOrigin = scaler.getNodeYPosition(top, heapSearchIndex);
            
            nodeValue = theMinHeap.getByIndex(heapSearchIndex);
            
            nodeBackground = Color.BLUE;
            
            drawNode(xOrigin, yOrigin, nodeDimension, nodeValue, nodeBackground,
                    fontColor, g);
            
            // Reset The flags and search indexes
            searchSucceeded = false;
            heapSearchIndex = -1;
        }
    }
    
    public void addAllListeners()
    {
        insertToHeap.addActionListener(this);
        searchValue.addActionListener(this);
        deleteValue.addActionListener(this);
        
    }
    
    public void addToGui()
    // POST: Will add all widgets, layouts, and panels to the GUI
    {
        setLayout(base);   //Will set a borderLayout layout as the base 
                           //      Layout     
        
        add(base.CENTER, top);
        add(base.SOUTH, bottom);        
    }
    
    public void addToPanels()
    // POST: Will add all widgets to their respective panels
    {        
        // Insert Bottom Left Widgets
        bottomLeft.add(insertToHeap);
        bottomLeft.add(valueToInsert);
        bottomLeft.add(searchValue);
        bottomLeft.add(valueToSearch);
        bottomLeft.add(deleteValue);
        
        // Insert the News Feed
        bottomRight.add(feedPane);
        
        
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
        
        //Left Side                                                         
        insertToHeap = new JButton("Insert To Heap");          
        searchValue = new JButton ("Search Value");
        deleteValue = new JButton("Delete Value");
        
        valueToInsert = new JTextField (); 
        valueToSearch = new JTextField (); 
        
        //Right Side
        feedContents = new JTextArea();                                  
        feedContents.setEditable(false);                    
        feedContents.setText(" ");                          
        
        feedPane = new JScrollPane(feedContents);
    }                                                                       
    
    public void initializePanels()                        
    //POST:  Will initialize all Panels and Layouts      
    {             
        base = new BorderLayout();        
        
        //Set up all panels                               
        top = new JPanel();                             
        bottom = new JPanel();                             
        bottomRight  = new JPanel();                              
        bottomLeft = new JPanel();   
        
        //Set the layout for all panels
        top.setLayout(new FlowLayout());
        bottom.setLayout(new GridLayout(1,2));
        bottomLeft.setLayout(new GridLayout(5,1));
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
    
    // Reference: http://stackoverflow.com/questions/139655/convert-pixels-to-points
    //            researched on how to convert from pixels to Points for use
    //            on fontSize.
    fontSize = diceDimention * (72.0/96.0); // There are approximately 72 points
                                       //    in an inch, and we assume that
                                       //    our display will have 96 pixels
                                       //    per Inch.  Using Stoichometry,
                                       //    we convert the number buffer
                                       //    from pixels to points
    
    //NOTE: draw string draws strings with origins on the bottom left corner.
    //      For this reason, the origin of the string will be calculated 
    //      accordingly.
    numberOriginX = originX + (diceDimention/3); // Let die's number's horizontal
                                                 //   origin be placed at 1/3
                                                 //   of the die's width
    numberOriginY = originY + (diceDimention)
                       - (diceDimention/4); // Let the die's dimension be
                                            //   placed at 3/4 of the die's
                                            //   height.  Calculated like 
                                            //   this to avoid dealing with
                                            //   doubles
    
    faceFont = new Font("SansSerif", 
                   Font.BOLD,
                   (int) fontSize);         // Create a new font object
                                            
    
    // Draw the Dice's background.
    g.setColor(diceBackground);                                // Set the color for 
                                                               //   the background
    
    g.fillRect(originX, originY,diceDimention, diceDimention); // Draw the background     
    
    // Draw the Dice's face Number
    g.setColor(diceNumberColor);                               // Set the color for 
                                                               //   the die's font
    
    g.setFont(faceFont);                                       // Update the font                                                     
    
    // NOTE: In order to cast the diceFace from an integer to a string, I concatenated
    //       an empty string to the diceFace integer
    g.drawString("" + diceFace, numberOriginX, numberOriginY); // Draw the Number in 
                                                               //   the die's face
    
    }
    
    @Override
    public void itemStateChanged(ItemEvent arg0) {
    // TODO Auto-generated method stub
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    // POST: Handles all actions performed on the GUI
    {
        int targetValue;  // target value holds the value to be searched or
                          //    inserted        
        
        if (e.getSource() == insertToHeap)
        {
            targetValue = Integer.parseInt(valueToInsert.getText());
            
            theMinHeap.addValue(targetValue);
        }
        
        if (e.getSource() == searchValue)
        {
            targetValue = Integer.parseInt(valueToSearch.getText());
            
            heapSearchIndex = theMinHeap.search(targetValue);
            
            if(heapSearchIndex != -1)
            {
                searchSucceeded = true;
            }
        }
        
        if (e.getSource() == deleteValue)
        {
            
        }
        
        repaint();
    }

}
