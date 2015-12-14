/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AAzbuhanov
 */
class Visitor implements Runnable{
    private BusinessCenter place;
    private static int totalCount = 0;
    private int num;
    private int floor;

    public int getFloor() {
        return floor;
    }

    public Visitor(BusinessCenter p) {
        totalCount += 1;
        Random random = new Random();
        this.floor = random.nextInt(8)+2;
        this.num = totalCount;
        this.place = p;
        
        System.out.println("Пришел "+ this);
    }

    @Override
    public void run() {
        try {
            this.passControl();
            this.goUp();
            this.doSomeWork();
            this.goDown();
            
            System.out.println(this + " вышел");
        } catch (InterruptedException ex) {
            Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void passControl() throws InterruptedException{
        synchronized (this.place){
            boolean b = true;
            while (b) {
                if (place.isControlFree() == true){
                    this.place.enterControl(this);
                    System.out.println(this + " показывает документы");
                    Thread.sleep(50);
                    
                    this.place.exitFromControl(this);
                    System.out.println(this + " показал документы");
                    Thread.sleep(100);
                    b = false;
                } else {
                    place.wait();   
                }                
            }            
            this.place.notifyAll();
        }        
    }
    private void goUp() throws InterruptedException{
        System.out.println(this + " вызвал лифт");
        synchronized (this.place){
            boolean b = true;
            if (place.getLiftFloor() != 1){
                System.out.println(this + " ждет лифт с " + place.getLiftFloor() + " этажа");
            }
            while (b){
                if (place.isLiftFree()){
                    this.place.enterLift(this);
                    System.out.println(this + " Лифт приехал на 1 этаж");
                    System.out.println(this + " едет c 1 этажа на " + this.floor+ " этаж");
                    this.place.moveLift(this);
                    this.place.exitFromLift(this);
                    System.out.println(this + " вышел из лифта");
                    Thread.sleep(100);
                    b = false;
                    } else {
                    place.wait();
                }
                }
            place.notifyAll();
        }
    }
    private void doSomeWork() throws InterruptedException{
        System.out.println(this + " чтото делает"); 
        Thread.sleep(100);
    }
    private void goDown() throws InterruptedException{
        System.out.println(this + " вызвал лифт");
        synchronized (this.place){
            boolean b = true;
            if (place.getLiftFloor() != this.floor){
                System.out.println(this + " ждет лифт с " + place.getLiftFloor() + " этажа");
            }
            while (b){
                if (place.isLiftFree()){
                    this.place.enterLift(this);
                    System.out.println(this + " едет c " + this.floor +  " этажа на 1 этаж");
                    this.place.moveLift(this);
                    this.floor = 1;
                    this.place.exitFromLift(this);
                    System.out.println(this + " вышел из лифта");
                    Thread.sleep(100);
                    
                    b = false;
                } else {
                    place.wait();
                }
            }
            place.notifyAll();
        }
    }

    @Override
    public String toString() {
        return "Посетитель" + num;
    }
   
    
}

/*Visitor
- place : BusinessCenter
- totalCount : int
- num : int
- floor: int
<<constructors>>
+ Visitor( p : BusinessCenter)
<<methods>>
+ run()
- passControl()
- goUp()
- doSomeWork()
- goDown()
+ toString() : String
+ toString() : String
*/
