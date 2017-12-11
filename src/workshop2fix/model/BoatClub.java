/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop2fix.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import workshop2fix.model.searchrules.AndCriteria;
import workshop2fix.model.searchrules.Criteria;
import workshop2fix.model.searchrules.MaleCriteria;
import workshop2fix.model.searchrules.OlderThan35Criteria;
import workshop2fix.model.searchrules.OrCriteria;
import workshop2fix.model.searchrules.SailBoatOwnersCriteria;

/**
 *
 * @author beysimeryalmaz
 */
public class BoatClub {
    private ArrayList<Member> memberList;
    private DataIO data;
    private int CurrentMemberId=0;
    private String loginUsername="admin";
    private String loginPassword="admin";
    private boolean isLoggedIn=false;
    
    
    
    public BoatClub() throws IOException, ClassNotFoundException {
        data=new DataIO();
        memberList=data.readMemberDataFromFile();
        findLastUsedMemberId();
        
    }
    
    public void updateDataIO(){
        try {
            data.writeMemberDatatoFile(memberList);
           
        } catch (IOException ex) {
            Logger.getLogger(BoatClub.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void findLastUsedMemberId(){
        if(memberList.size()!=0)
            CurrentMemberId=memberList.get(memberList.size()-1).getMemberId();
    }
    
    public void login(String username,String password){
        if(loginUsername.equals(username) && loginPassword.equals(password)){
            isLoggedIn=true;
        }
    }
    
    public void logOut(){
        isLoggedIn=false;
    }
    
    public void createMember(String name,String personalNumber){
        if(isLoggedIn){
            Member member=new Member(name, personalNumber);
            member.setMemberId(++CurrentMemberId);
            memberList.add(member);
            updateDataIO();}
    
    }
    
    
    public void updateMember(Member member,Member newMember){
        if(isLoggedIn){
            member.setName(newMember.getName());
            member.setPersonalNumber(newMember.getPersonalNumber());
            updateDataIO();}
    
    }
    
    public void deleteMember(int memberId){
        if(isLoggedIn){
            for(Member member:memberList){
                if(member.getMemberId()==memberId)
                    memberList.remove(member);
            }
            updateDataIO();}
    }
    
    public Iterable<Member> getMembers(){
    
        Iterable members=memberList;
        
        return members;
    
    }
    
    public Member getSpesificMember(int memberId){
        for(Member member:memberList){
            if(member.getMemberId()==memberId)
                return member;
            
        }
        
        return null;
    }
    
    public ArrayList<Member> getOlderThan35Members(){
    
        Criteria olderThan35Members=new OlderThan35Criteria();
        return olderThan35Members.meetCriteria(memberList);
    
    }
    
    public ArrayList<Member> getMaleMembers(){
    
        Criteria maleMembers=new MaleCriteria();
        
        return maleMembers.meetCriteria(memberList);
    
    }
    
    public ArrayList<Member> getSailBoatOwnerMembers(){
    
        Criteria sailBoatOwnerMembers=new SailBoatOwnersCriteria();
        return sailBoatOwnerMembers.meetCriteria(memberList);
    
    }
    
    public ArrayList<Member> MaleAndOwnsSailBoatMembers(){
        Criteria maleAndOwnsSailBoat=new AndCriteria(new MaleCriteria(), new SailBoatOwnersCriteria());
        return maleAndOwnsSailBoat.meetCriteria(memberList);
    }
    
    
    public ArrayList<Member> MaleOrOlderThan35Members(){
        Criteria maleOrOlderThan35Members=new OrCriteria(new MaleCriteria(), new OlderThan35Criteria());
        return maleOrOlderThan35Members.meetCriteria(memberList);
    
    }

    /**
     * @return the isLoggedIn
     */
    public boolean IsLoggedIn() {
        return isLoggedIn;
    }
    
    
   
    
    
    
    
}
