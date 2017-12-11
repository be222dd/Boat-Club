/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop2fix;

import java.io.IOException;
import workshop2fix.model.BoatClub;
import workshop2fix.view.SimpleView;

/**
 *
 * @author beysimeryalmaz
 */
public class Workshop2Fix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
       
        BoatClub boatClub=new BoatClub();
        SimpleView ui=new SimpleView(boatClub);
        ui.start();
    }
    
}
