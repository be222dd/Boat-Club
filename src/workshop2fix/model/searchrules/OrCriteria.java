/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop2fix.model.searchrules;

import java.util.ArrayList;
import workshop2fix.model.Member;

/**
 *
 * @author beysimeryalmaz
 */
public class OrCriteria implements Criteria{
    
   private Criteria criteria;
   private Criteria otherCriteria;

   public OrCriteria(Criteria criteria, Criteria otherCriteria) {
      this.criteria = criteria;
      this.otherCriteria = otherCriteria; 
   }

    @Override
    public ArrayList<Member> meetCriteria(ArrayList<Member> memberList) {
        ArrayList<Member> firstCriteriaItems = criteria.meetCriteria(memberList);
        ArrayList<Member> otherCriteriaItems = otherCriteria.meetCriteria(memberList);
        
        for(Member member:otherCriteriaItems){
            if(!firstCriteriaItems.contains(member)){
            firstCriteriaItems.add(member);
         }
        }
        return firstCriteriaItems;
    }
    
}
