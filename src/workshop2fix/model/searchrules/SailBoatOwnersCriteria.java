/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop2fix.model.searchrules;

import java.util.ArrayList;
import workshop2fix.model.Boat;
import workshop2fix.model.Member;

/**
 *
 * @author beysimeryalmaz
 */
public class SailBoatOwnersCriteria implements Criteria{

    @Override
    public ArrayList<Member> meetCriteria(ArrayList<Member> memberList) {
        ArrayList<Member> sailBoatOwnerMembers=new ArrayList<Member>();
        
       
        for (Member member : memberList) {
            Iterable<Boat> boatList=member.getBoats();
            for(Boat boat: boatList){
                if(boat.getB_type()==Boat.BoatType.Sail){
                    sailBoatOwnerMembers.add(member);
                }
            }

      }
      return sailBoatOwnerMembers;
    }
    
}
