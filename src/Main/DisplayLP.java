package Main;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.gnu.glpk.GLPK;
import org.gnu.glpk.glp_prob;

public class DisplayLP {
	
	public static void displayAssignments(glp_prob lp) {
        int i;
        int n;
        String name;
        Map<String, Double> map = new HashMap<String, Double>();
        double val;

        name = GLPK.glp_get_obj_name(lp);
        val = GLPK.glp_get_obj_val(lp);
        n = GLPK.glp_get_num_cols(lp);
        for (i = 1; i <= n; i++) {
            name = GLPK.glp_get_col_name(lp, i);
            val = GLPK.glp_get_col_prim(lp, i);
            if (val != 0) map.put(name, val);
        }
        
        System.out.println("---------------------Assignments Made---------------------------");
        for (Entry<String, Double> e : map.entrySet()) {
        	System.out.println(e);
        }
	}
	
	
    /**
     * write simplex solution
     * @param lp problem
     */
    public static void displayAllValues(glp_prob lp) {
        int i;
        int n;
        String name;
        Map<String, Double> map = new HashMap<String, Double>();
        double val;

        System.out.println("---------------------Solution---------------------------");
        name = GLPK.glp_get_obj_name(lp);
        val = GLPK.glp_get_obj_val(lp);
        System.out.print(name);
        System.out.print(" = ");
        System.out.println(val);
        n = GLPK.glp_get_num_cols(lp);
        for (i = 1; i <= n; i++) {
            name = GLPK.glp_get_col_name(lp, i);
            val = GLPK.glp_get_col_prim(lp, i);
            if (val != 0) map.put(name, val);
            System.out.print(name);
            System.out.print(" = ");
            System.out.println(val);
        }
        
        System.out.println("---------------------Assignments Made---------------------------");
        for (Entry<String, Double> e : map.entrySet()) {
        	System.out.println(e);
        }
    }
}
