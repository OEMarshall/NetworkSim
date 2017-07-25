package my.contacteditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ochaun Marshall
 */
public class Message extends JLabel {

    public String text;
    public Color c;
    public boolean moved;
    public boolean madeit;

    public Message(){
        this.setPreferredSize(new Dimension(35,35));
            this.c = Color.red;
            this.text = "Hello World!";
            this.moved = false;
            this.madeit = false;
    }
 public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(c);
        g.drawOval(0, 0, 25, 25);
        g.fillOval(0, 0, 25, 25);
        ui.update(g, this);
    }
 public String getText(){
     return this.text;
 }
    }


