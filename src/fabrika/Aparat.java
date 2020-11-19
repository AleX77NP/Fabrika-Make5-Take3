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
public class Aparat implements Runnable {
    
    private Proizvodnja proizvodnja;
    
    public Aparat(Proizvodnja proizvodnja) {
        this.proizvodnja = proizvodnja;
    }

    @Override
    public void run() {
        //int i=0;
        while(true) {
            proizvodnja.removeThree();
           // i++;
       }
    }
    
}
