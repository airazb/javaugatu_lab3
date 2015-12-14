/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

/**
 *
 * @author AAzbuhanov
 */
public class LiftMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        BusinessCenter bc = new BusinessCenter();
        
        Visitor v1 = new Visitor(bc);
        Visitor v2 = new Visitor(bc);
        
        Thread t1 = new Thread(v1);
        Thread t2 = new Thread(v2);
        
        t1.start();
        Thread.sleep(10);
        t2.start();
        Thread.sleep(3000);
        //System.out.println("finishing");
    }
    
}
