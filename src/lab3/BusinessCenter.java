/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AAzbuhanov
 */
public class BusinessCenter {
    private boolean liftFree;
    private boolean controlFree;
    private int liftFloor;

    public boolean isLiftFree() {
        return liftFree;
    }

    public boolean isControlFree() {
        return controlFree;
    }

    public int getLiftFloor() {
        return liftFloor;
    }

    public BusinessCenter() {
        this.liftFloor = 1;
        this.liftFree = true;
        this.controlFree = true;
    }
    
    public void enterLift(Visitor v){
        this.liftFree = false;        
        this.liftFloor = v.getFloor();
    }
    public void exitFromLift(Visitor v){
        this.liftFree = true;
        this.liftFloor = v.getFloor();
    }
    public void moveLift(Visitor v){        
        this.liftFree = false;
        try {
            Thread.sleep(v.getFloor()*100 + 200);
        } catch (InterruptedException ex) {
            Logger.getLogger(BusinessCenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void enterControl(Visitor v){
        this.controlFree = false;
    }
    public void exitFromControl(Visitor v){
        this.controlFree = true;
    }
}
/*
- liftFree : boolean
- controlFree : boolean
- liftFloor : int
<<constructors>>
+ BusinessCenter()
<<methods>>
+ enterLift(v: Visitor )
+ exitFromLift(v: Visitor)
+ moveLift(targetFloor: int)
+ enterControl(v: Visitor)
+ exitFromControl(v: Visitor)
*/