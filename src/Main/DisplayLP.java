package Main;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.gnu.glpk.GLPK;
import org.gnu.glpk.glp_prob;

public class DisplayLP {
	
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
    
    public static String getOutput(glp_prob lp, Map<String, List<AssignmentObject>> varbNames) {
    	StringBuilder builder = new StringBuilder();
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
        
        builder.append("\n"+"-----------Assignments Made-----------------");
        for (Entry<String, Double> e : map.entrySet()) {
        	List<AssignmentObject> objects = varbNames.get(e.getKey());
        	if (objects.size() == 2) builder.append("\n"+objects.get(0) + " assigned to " + objects.get(1));
        }
        return builder.toString();
    }
    
	/**
	 * write integer solution
	 * @param mip problem
	 */
	public static String getMipSolution(glp_prob prob, Map<String, List<AssignmentObject>> varbNames)
	{
		String name;
		int index;
		double value;
		StringBuilder builder = new StringBuilder();
		Map<String, Double> map = new HashMap<String, Double>();
		name = GLPK.glp_get_obj_name(prob);
		value  = GLPK.glp_mip_obj_val(prob);
		for(index=1; index <= GLPK.glp_get_num_cols(prob); index++)
		{
			value  = GLPK.glp_mip_col_val(prob, index);
			name = GLPK.glp_get_col_name(prob, index);
			if (value != 0) map.put(name, value);
		}
		
		builder.append("\n"+"-----------Assignments Made-----------------");
        for (Entry<String, Double> e : map.entrySet()) {
        	List<AssignmentObject> objects = varbNames.get(e.getKey());
        	if (objects.size() == 2) builder.append("\n"+objects.get(0) + " assigned to " + objects.get(1));
        }
        return builder.toString();
	}
}
