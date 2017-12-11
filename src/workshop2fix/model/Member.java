/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop2fix.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author beysimeryalmaz
 */
public class Member implements Serializable  {
    
    private String name;
    private String personalNumber;
    private int memberId;
    private ArrayList<Boat> boatList;
    private int BirthYear;
    private int BirthMonth;
    private int BirthDate;
    private String gender;
    
    
    public Member(String name,String personalNumber){
        this.name=name;
        this.personalNumber=personalNumber;
        setBirthDate(personalNumber);
        setBirthMonth(personalNumber);
        setBirthYear(personalNumber);
        setGender(personalNumber);
        boatList=new ArrayList();
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the personalNumber
     */
    public String getPersonalNumber() {
        return personalNumber;
    }

    /**
     * @param personalNumber the personalNumber to set
     */
    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
        setBirthDate(personalNumber);
        setBirthMonth(personalNumber);
        setBirthYear(personalNumber);
        setGender(personalNumber);
    }

    /**
     * @return the memberId
     */
    public int getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the BirthYear
     */
    public int getBirthYear() {
        return BirthYear;
    }

   
    private void setBirthYear(String pNum) {
        
        String year="19"+pNum.substring(0, 2);
        this.BirthYear=Integer.parseInt(year);
        
    }

    /**
     * @return the BirthMonth
     */
    public int getBirthMonth() {
        return BirthMonth;
    }

    
    private void setBirthMonth(String pNum) {
        this.BirthMonth = Integer.parseInt(pNum.substring(2, 4));
    }

    /**
     * @return the BirthDate
     */
    public int getBirthDate() {
        return BirthDate;
    }

   
    private void setBirthDate(String pNum) {
        this.BirthDate = Integer.parseInt(pNum.substring(4, 6));
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /*
        I made a research about Swedish personal number and found out that odd 9th number in 
        YYMMDD-XXXX format determines male gender, even 9th number female gender
        this method sets gender according to this rule
    */
    private void setGender(String pNum) {
       int genderDigit=Integer.parseInt(pNum.substring(9, 10));
       if(genderDigit%2==0){
           this.gender="female";
       }else{
           this.gender="male";
       }
    }
    
    public void addBoat(Boat boat){
        boatList.add(boat);
    }
    
    
    public void removeBoat(Boat boat){
        boatList.remove(boat);
    }
    
    public void updateBoatInfo(Boat boat, Boat newBoat ){
        boat.setB_type(newBoat.getB_type());
        boat.setLength(newBoat.getLength());
    }
    
    public int getNumberOfBoats(){
        return boatList.size();
    }
    
    public Iterable<Boat> getBoats(){
    
        Iterable<Boat> boats=boatList;
        return boats;
    }
    
}
