/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrika;

/**
 *
 * @author aleksandarmilanovic
 */
public class Fabrika {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      FabrikaFrame home = new FabrikaFrame();
      home.setVisible(true);
      home.t1.start();
      home.t2.start();
    }
    
}
