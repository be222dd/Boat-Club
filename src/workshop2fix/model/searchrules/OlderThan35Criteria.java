/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop2fix.model.searchrules;

import java.time.Year;
import java.util.ArrayList;
import workshop2fix.model.Member;

/**
 *
 * @author beysimeryalmaz
 */
public class OlderThan35Criteria implements Criteria{

    @Override
    public ArrayList<Member> meetCriteria(ArrayList<Member> memberList) {
        ArrayList<Member> certainAgedMembers=new ArrayList<Member>();
        
       
        for (Member member : memberList) {
            int CurrentYear = Year.now().getValue();
            int memberAge=CurrentYear-member.getBirthYear();
           
            if(memberAge>35){
            certainAgedMembers.add(member);
         }

      }
      return certainAgedMembers;
    }
    
}
