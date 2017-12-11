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
public interface Criteria {
    
    public ArrayList<Member> meetCriteria(ArrayList<Member> memberList);
    
}
