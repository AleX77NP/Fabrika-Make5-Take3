/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrika;

import java.awt.Color;
import java.util.concurrent.Semaphore;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author aleksandarmilanovic
 */
public class Proizvodnja implements CircularBuffer {
    
    public static final int SIZE = 20;
    private int lastPut,firstTake;
    public int[] buffer;
    public String datum;
    private Semaphore hasSomething;
    private Semaphore hasSpace;
    private Semaphore mut;
    public JButton[] buttons;
    public JLabel label, label2;
    int total = 0;
    
    
    public Proizvodnja(JButton[] button, JLabel label1, JLabel labels) {
        buffer = new int[SIZE];
        buttons = button;
        label = label1;
        label2 = labels;
        for(int i=0; i<SIZE; i++) {
            buffer[i] = 0;
        }
        lastPut = 0;
        firstTake = 0;
        datum = "10/11/2020";
        hasSpace = new Semaphore(1);
        hasSomething = new Semaphore(0);
        mut = new Semaphore(1);
    }


    @Override
    public void insertFive() {
        while(total>= SIZE-4) {
        try {
        hasSpace.acquire();
        mut.acquire();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        }
        for(int k=0; k<5; k++) {
            try {
            Thread.sleep(1000);
        }
            catch(InterruptedException e){
            e.printStackTrace();
        }
            buffer[lastPut] = 1;
            buttons[lastPut].setBackground(Color.RED);
            buttons[lastPut].setOpaque(true);
            label.setIcon(new ImageIcon("/Users/aleksandarmilanovic/Desktop/Fabrika/worker-active.png"));
            label2.setIcon(new ImageIcon("/Users/aleksandarmilanovic/Desktop/Fabrika/robot.png"));
            lastPut = (lastPut + 1) % SIZE;
            total ++;
            }
        hasSomething.release();
        ispis();
        mut.release();
       }

    @Override
    public void removeThree() {
        while(total<3) {
        try {
        hasSomething.acquire();
        mut.acquire();
        } 
         catch(InterruptedException e){
            e.printStackTrace();
        }
        }
         try {
            Thread.sleep(1000);
        }
            catch(InterruptedException e){
            e.printStackTrace();
        }
        for(int m=0; m<3; m++) {
            buffer[firstTake] = 0;
            buttons[firstTake].setBackground(new JButton().getBackground());
            label2.setIcon(new ImageIcon("/Users/aleksandarmilanovic/Desktop/Fabrika/robot-active.png"));
            label.setIcon(new ImageIcon("/Users/aleksandarmilanovic/Desktop/Fabrika/worker.png"));
            firstTake = (firstTake + 1) % SIZE;
            total --;
        }
        System.out.println("Datum: " + datum);
        if(total<=15){
        hasSpace.release();
        }
        ispis();
        mut.release();
  }
    
    public void ispis() {
        System.out.println(java.util.Arrays.toString(buffer));
    }
    
    public boolean imaLiMesta() {
       int num = 0;
       for (int t=0; t<SIZE; t++) {
           if(buffer[t]== 0)
               num++;
       }
       if(num<5) return false;
       return true;
    }
    
    public boolean imaLiZaUzet() {
        int num = 0;
       for (int t=0; t<SIZE; t++) {
           if(buffer[t]== 1)
               num++;
       }
       if(num<3) return false;
       return true;
    }
    
}
