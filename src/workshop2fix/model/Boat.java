/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop2fix.model;

import java.io.Serializable;

/**
 *
 * @author beysimeryalmaz
 */
public class Boat implements Serializable {
    
    public enum BoatType{
        Motor ,
        Sail,
        MotorSail,
        Canoe,
        Other
}
    
    private BoatType b_type;
    private double length;
    
    public Boat(BoatType b_type,double length){
        this.b_type=b_type;
        this.length=length;
    }

    /**
     * @return the b_type
     */
    public BoatType getB_type() {
        return b_type;
    }

    /**
     * @param b_type the b_type to set
     */
    public void setB_type(BoatType b_type) {
        this.b_type = b_type;
    }

    /**
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(double length) {
        this.length = length;
    }
    
}
