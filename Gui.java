import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class GUI extends JApplet implements ActionListener, ItemListener
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
    
    
    //  Right Side
    private JTextArea feedContents;       // feedContents holds content for news feed.       
    private JScrollPane feedPane;         // feedPane holds feedContents for news feed.    
    private String feedMessage;           // message for news feed.        
    
   
    public void init()
    // POST: Initialize the GUI
    {


        
        
        initializePanels();
        initializeWidgets();
        setUpPanels();
        addToPanels();
        addToGui();
        
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
    }
    
    public void addToGui()
    // POST: Will add all widgets, layouts, and panels to the GUI
    {
        setLayout(base);   //Will set a borderLayout layout as the base 
                           //      Layout     
        
        add(base.NORTH, top);
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
    
    @Override
    public void itemStateChanged(ItemEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        
    }

}
