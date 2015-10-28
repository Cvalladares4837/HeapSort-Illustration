import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.*;

//New changes comments

public class Gui extends JFrame
{
    JPanel jp = new JPanel();
    JButton but = new JButton("Hi");
    JMenuBar bar ;
    JMenu file;
    JMenuItem newItem ;
    JMenuItem exit ;
    
    public Gui()
    {
        
        
        this.setTitle("Min/Max Heap Tutorial");
        this.setSize(1500, 1500);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        bar = new JMenuBar();
        this.setJMenuBar(bar);
        
        file = new JMenu("File");
        bar.add(file);
        
        newItem= new JMenuItem("New");
        exit = new JMenuItem("Exit");
        file.add(newItem);
        file.addSeparator();
        file.add(exit);

        
        
        
        
    }
    
    public void paint(Graphics g)
    {
        
    }
    

    public static void main(String args[])
    {
        Gui g = new Gui();
    }
}
