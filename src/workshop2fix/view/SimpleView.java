/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop2fix.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import workshop2fix.model.Boat;
import workshop2fix.model.Boat.BoatType;
import workshop2fix.model.BoatClub;
import workshop2fix.model.Member;

/**
 *
 * @author beysimeryalmaz
 */
public class SimpleView {
    
    private BoatClub boatClub;
    
    public SimpleView(BoatClub boatClub){
        this.boatClub=boatClub;
    }
    
    
    
        public void start(){
        int choice=0;
        
        
        printMenu();
    
        
        do{
        choice=getInputChar();
        switch(choice){
            case '1':{
                if(boatClub.IsLoggedIn()){
                    printSpace();
                    printCreateMemberUi();
                    printFooterMessage();
                }else
                    login();printFooterMessage();
                break;
            }
            case '2':{
                if(boatClub.IsLoggedIn()){
                    printSpace();
                    printDeleteMemberUi();
                    printFooterMessage();
                }else
                    login();printFooterMessage();
                break;
            }
            case '3':{
                if(boatClub.IsLoggedIn()){
                    printSpace();
                    printUpdateMemberUi();
                    printFooterMessage();
                }else
                    login();printFooterMessage();
                break;
            }
            case '4':{
                printSpace();
                printSpesificMemberUi();
                printFooterMessage();
                break;
            }
            case '5':{
                if(boatClub.IsLoggedIn()){
                    printSpace();
                    printRegisterBoatUi();
                    printFooterMessage();
                }else
                    login();printFooterMessage();
                break;
            }
            case '6':{
                if(boatClub.IsLoggedIn()){
                    printSpace();
                    printDeleteBoatUi();
                    printFooterMessage();
                }else
                    login();printFooterMessage();
                break;
            }
            case '7':{
                if(boatClub.IsLoggedIn()){
                    printSpace();
                    printUpdateBoatInfoUi();
                    printFooterMessage();
                }else
                    login();printFooterMessage();
                break;
            }
            case '8':{
                printSpace();
                listMenu();
                printFooterMessage();
                break;
            }
            case '9':{
                printSpace();
                loginMenu();
                printFooterMessage();
                break;
            }
            case '0':{
                printSpace();
                printSearchMenu();
                printFooterMessage();
                break;
            }
            default :{
               
                printMenu();
                
            }
        }
        
        }while(choice != 'q' && choice != 'Q' );
        
        boatClub.updateDataIO();
    }
    
    
    private int getInputChar() {
    try {
      int c = System.in.read();
      while (c == '\r' || c =='\n') {
        c = System.in.read();
      }
        return c;
    } catch (java.io.IOException e) {
      System.out.println("" + e);
      return 0;
    }
  }
    
    
    private void printMenu(){
        printSpace();
        System.out.println("==========Welcome to BoatClub==========");
        System.out.println("1-Create a member");
        System.out.println("2-Delete a member");
        System.out.println("3-Update Member Info");
        System.out.println("4-Look at a Members info");
        System.out.println("5-Register Boat");
        System.out.println("6-Delete Boat");
        System.out.println("7-Change Boats Info");
        System.out.println("8-List Menu");
        System.out.println("9-Login");
        System.out.println("0-Search");
        System.out.println("Q-Exit");
}
   
    private void printSpace(){
        for(int i=0;i<50;i++){System.out.println("");}
    }
    
    
    private void printCreateMemberUi(){
        printSpace();
        Scanner reader= new Scanner(System.in);
        System.out.println("Please enter member's personal number. Personal number should be YYMMDD-XXXX format");
        String pNum=reader.next();
        if(isValidPNum(pNum)){
            System.out.println("Please enter member's name:");
            String name=reader.next();
        
            boatClub.createMember(name, pNum);
            System.out.println("**************************");
            System.out.println("Member succesfully Created");
            System.out.println("**************************");
            
        }else{
            System.out.println("Personal number should be YYMMDD-XXXX format. Try Again!Type 0");
        }
        
    }
    
    
    private void printDeleteMemberUi(){
       int memberId=findMemberMenu();
       boatClub.deleteMember(memberId);
    }
    
    
    private void printUpdateMemberUi(){
    
        
        int memberId=findMemberMenu();
            Scanner reader=new Scanner(System.in);
            Member member=boatClub.getSpesificMember(memberId);
            if(member==null){
                System.out.println("Member with "+memberId+" doesn't exist");
            }else{
                System.out.println("Please enter member's personal number. Personal number should be YYMMDD-XXXX format");
                String pNum=reader.next();
                if(isValidPNum(pNum)){
                    System.out.println("Please enter member's name:");
                    String name=reader.next();

                    boatClub.updateMember(member, new Member(name,pNum));
                    System.out.println("**************************");
                    System.out.println("Member succesfully Updated");
                    System.out.println("**************************");

                }else{
                    System.out.println("Personal number should be YYMMDD-XXXX format. Try Again!Type 0");
                }
         
               }}
    
    
    private void printSpesificMemberUi(){
       int memberId=findMemberMenu();
       
            Member member=boatClub.getSpesificMember(memberId);
            if(member==null){
                System.out.println("Member with "+memberId+" doesn't exist");
            }else{
                System.out.println("Member name: "+member.getName()+" Member id: "+ member.getMemberId()+" Personal Number: "+ member.getPersonalNumber()+" Number of Boats: "+ member.getNumberOfBoats());
                System.out.println("Date of birth: "+member.getBirthYear()+"-"+member.getBirthMonth()+"-"+member.getBirthDate() );
                System.out.println("Gender: "+member.getGender());
            }
          
        
    }
    
    
    private void printRegisterBoatUi(){
        
        printCompactList();
        System.out.println("Choose the member you want to register boat to of.Type memberId");
        Scanner reader=new Scanner(System.in);
        try{
            int memberId=reader.nextInt();
            Member member=boatClub.getSpesificMember(memberId);
            if(member==null){
                System.out.println("Member with "+memberId+" doesn't exist");
            }else{
                BoatType type=boatTypeMenu();
                try{
                    System.out.println("Type Boat Length: ");
                    double length=reader.nextDouble();
                    member.addBoat(new Boat(type, length));
                }catch(InputMismatchException ex){
                    System.out.println("Type Number for boat Length");
                }
                
            }
          
         }catch(InputMismatchException ex){
            System.err.println("Please enter a valid member Id");
        
        }
   }
    
    private void printDeleteBoatUi(){
    
        int memberId=findMemberMenu();
        Member member=boatClub.getSpesificMember(memberId);
        if(member==null){
            System.out.println("Member doesn't exist");
        }else{
            if(member.getBoats().iterator().hasNext()){
            System.out.println("Printing Members boats");
            System.out.println("Type 1 to Delete/ 2 to continue");
            Scanner reader=new Scanner(System.in);
            
            for(Boat boat:member.getBoats()){
                System.out.println("Boat Type: "+boat.getB_type().toString()+"Boat Length: " +boat.getLength());
                try{
                    int choice=reader.nextInt();
                    if(choice==1){
                    member.removeBoat(boat);
                    break;
                    }
                }catch(InputMismatchException ex){
                    System.out.println("Invalid Input");
                }
                
                    
                }
            }else
                System.out.println("Member doesnt own a boat");
        
        }
        }
    
    
    private void printUpdateBoatInfoUi(){
        
        int memberId=findMemberMenu();
        Member member=boatClub.getSpesificMember(memberId);
        if(member==null){
            System.out.println("Member doesn't exist");
        }else{
            if(member.getBoats().iterator().hasNext()){
            System.out.println("Printing Members boats");
            System.out.println("Type 1 to Update/ 2 to continue");
            Scanner reader=new Scanner(System.in);
            
            for(Boat boat:member.getBoats()){
                System.out.println("Boat Type: "+boat.getB_type().toString()+"Boat Length: " +boat.getLength());
                try{
                    int choice=reader.nextInt();
                    if(choice==1){
                        BoatType type=boatTypeMenu();
                       
                        System.out.println("Type new boat length");
                        double boatLength=reader.nextDouble();
                        boat.setB_type(type);
                        boat.setLength(boatLength);
                   
                    break;
                    }
                }catch(InputMismatchException ex){
                    System.out.println("Invalid Input");
                }
                
                    
                }
            }else
                System.out.println("Member doesnt own a boat");
        
        }
    
    
    
    
    }
    
    
    
        
    
    
    
    
    private void printCompactList(){
        printSpace();
        System.out.println("----------CompactList----------");
    
        for(Member member:boatClub.getMembers()){
            System.out.println("Member name: "+member.getName()+" Member id: "+ member.getMemberId()+" Number of Boats: "+ member.getNumberOfBoats());
        }
    }
    
    
    public void printVerboseList(){
        printSpace();
        System.out.println("----------VerboseList----------");
        for(Member member:boatClub.getMembers()){
            System.out.println("Member name: "+member.getName()+" Member id: "+ member.getMemberId()+" Personal Number: "+ member.getPersonalNumber()+" Number of Boats: "+ member.getNumberOfBoats());
            for(Boat boat:member.getBoats()){
                System.out.println("\tBoat Type: "+boat.getB_type().toString()+" Boat Length: "+boat.getLength());
            
            }
        }
    
    }
    
    
    private int findMemberMenu(){
    
        printCompactList();
        System.out.println("Choose the member.Type memberId");
        Scanner reader=new Scanner(System.in);
        try{
            int memberId=reader.nextInt();
            return memberId;
            
         }catch(InputMismatchException ex){
            System.err.println("Please enter a valid member Id");
        
        }
        return -1;
    }
    
    
    
    private BoatType boatTypeMenu(){
         System.out.println("Please Select Boat Type");
         System.out.println("A- Motor");
         System.out.println("B- Sail");
         System.out.println("C- MotorSail");
         System.out.println("D- Canoe");
         System.out.println("E- Other");
         
         
         Scanner reader=new Scanner(System.in);
         String boatType=reader.next();
         
         switch(boatType){
            case "a":
            case "A":{
                return BoatType.Motor;
               
            }
            case "b":
            case "B":{
                return BoatType.Sail;
            }
            case "c":
            case "C":{
                return BoatType.MotorSail;
            }
            case "d":
            case "D":{
                return BoatType.Canoe;
            }
            
            default :{
               
                return BoatType.Other;
                
            }}
        
     }
    
    private void listMenu(){
        System.out.println("1-To see Verbose List");
        System.out.println("2-To see Compose List");
        
        Scanner reader=new Scanner(System.in);
        
        try {
            int choice=reader.nextInt();
            if(choice==1){
                printVerboseList();
            }else if(choice==2){
                printCompactList();
            }else{
                System.out.println("Wrong Input");
            }
            
        } catch (InputMismatchException ex) {
            System.out.println("Wrong Input");
        }
    }
    
    
    private void loginMenu(){
        if(boatClub.IsLoggedIn()){
            System.out.println("Type 1 to logOut.2 to go back");
            Scanner reader=new Scanner(System.in);
            
            try {
                int choice=reader.nextInt();
                if(choice==1){
                    logout();
                }else
                    System.out.println("Invalid Input");
                
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
            }
            
        }else{
            login();
        }
        
    }
    
    private void login(){
        Scanner reader=new Scanner(System.in);
        System.out.println("Username:");
        String username=reader.next();
        System.out.println("Password:");
        String password=reader.next();
        boatClub.login(username, password);
        System.out.println("Login process: "+boatClub.IsLoggedIn());
    }
    
    private void logout(){
        boatClub.logOut();
    }
    
    
    private void printFooterMessage(){
        System.out.println("Type M to return main menu");
    }
    
    private void printList(Iterable<Member> memberList){
        for(Member member:memberList){
            System.out.println("Member name: "+member.getName()+" Member id: "+ member.getMemberId()+" Personal Number: "+ member.getPersonalNumber()+" Number of Boats: "+ member.getNumberOfBoats());
                System.out.println("Date of birth: "+member.getBirthYear()+"-"+member.getBirthMonth()+"-"+member.getBirthDate() );
                System.out.println("Gender: "+member.getGender());
            
        }
    
    }
    
    
    private void printSearchMenu(){
        Scanner reader=new Scanner(System.in);
        System.out.println("1- Search Male Members");
        System.out.println("2- Search Members who owns Sailboat");
        System.out.println("3- Search Members who are older than 35 years old");
        System.out.println("4- Complex Search: Male && Owns SailBoat");
        System.out.println("5- Complex Search: Male || older than 35 years old");
        
        
        try{
            int choice=reader.nextInt();
        if(choice==1){
            printList(boatClub.getMaleMembers());
        }else if(choice==2){
            printList(boatClub.getSailBoatOwnerMembers());
        }else if(choice==3){
            printList(boatClub.getOlderThan35Members());
        }else if(choice==4){
            printList(boatClub.MaleAndOwnsSailBoatMembers());
        }else if(choice==5){
            printList(boatClub.MaleOrOlderThan35Members());
        }
        else{
            System.out.println("Invalid choice");
        }
        
        }catch(InputMismatchException ex){
            System.out.println("Wrong Input!");
        }
        
        
    }
    
    
    
    
    
    private boolean isValidPNum(String pNum){
        //check general length and '-' sign
        if(pNum.length()==11 && pNum.indexOf('-')==6){
            //check if chars are digit in YYMMDD part
            for(int i=0;i<6;i++){
                if(Character.isDigit(pNum.charAt(i))!=true){
                    return false;
                }
            }
            //check if chars are digit in XXXX part
            for(int i=7;i<11;i++){
                if(Character.isDigit(pNum.charAt(i))!=true){
                    return false;
                }
            
            }
           //Check month part and day part is valid 
           if(Integer.parseInt(pNum.substring(2, 4))>12 || Integer.parseInt(pNum.substring(4, 6))>31 ){
               return false;
           }
            
            return true;
            
        }
    
        return false;
    }
    
}
