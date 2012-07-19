/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.hnatek.plainjsf;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Markus
 */
public class MyDataDAO {
    
    public List<MyData> list() {
        ArrayList<MyData> l = new ArrayList<MyData>();
        System.out.println("Loading Data ...");
        for (long i = 1; i <= 15; i++) 
            l.add(new MyData(i, "name_" + i, UUID.randomUUID().toString()));
        
        return l;
    }
    
}
