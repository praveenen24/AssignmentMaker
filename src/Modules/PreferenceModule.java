package Modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.gnu.glpk.GLPKConstants;

import Main.AssignmentObject;
import Main.Bound;
import Main.Constraint;
import Main.LinearProblem;
import Main.ObjectList;

/**
 * This is a simple module to demonstrate that the solver can work for 
 * preference based assignments
 *
 */
public class PreferenceModule {
	
	public static ObjectList createObjects(String listName, int n) {
		ObjectList objects = new ObjectList(listName);
		for (int i = 1; i < n+1; i++) {
			AssignmentObject a = new AssignmentObject(listName + i);
			objects.add(a);
		}
		return objects;
	}
	
	public static ArrayList<Constraint> createBoundConstraints(int n, int lowerBound, int upperBound) {
		ArrayList<Constraint> constraints = new ArrayList<Constraint>();
		int index = 1;
		for (int i = 1; i < n+1; i++) {
			Constraint c1;
			if (lowerBound == upperBound) {
				c1 = new Constraint("c"+i, new Bound(GLPKConstants.GLP_FX, lowerBound, upperBound));
			} else {
				c1 = new Constraint("c"+i, new Bound(GLPKConstants.GLP_DB, lowerBound, upperBound));
			}
			for (int j = 1; j < n+1; j++) {
				c1.addValue(index, 1.0);
				index++;
			}
			constraints.add(c1);
		}
		return constraints;
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("------------Starting Preference Module-------------------");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        System.out.print("Enter Name of List1 : ");
        String list1Name = br.readLine();
        System.out.print("Enter # of " + list1Name + " : ");
        String size1 = br.readLine();
        int list1Size = Integer.parseInt(size1);
        
        System.out.print("Enter Name of List2 : ");
        String list2Name = br.readLine();
        System.out.print("Enter # of " + list2Name + " : ");
        String size2 = br.readLine();
        int list2Size = Integer.parseInt(size2);
        
        System.out.print("Enter Minimum Number of " + list2Name + " a " + list1Name + " can be assigned to : ");
        String lb = br.readLine();
        int lowBound = Integer.parseInt(lb);
        
        System.out.print("Enter Maximum Number of " + list2Name + " a " + list1Name + " can be assigned to : ");
        String up = br.readLine();
        int upBound = Integer.parseInt(up);
        
        ObjectList list1 = createObjects(list1Name, list1Size);
        ObjectList list2 = createObjects(list2Name, list2Size);
        List<Constraint> constraints = createBoundConstraints(list1Size, lowBound, upBound);
        
        List<Integer> preferences = new ArrayList<Integer>();
        for (AssignmentObject o1 : list1) {
        	for (AssignmentObject o2 : list2) {
        		 System.out.print("Enter Preference of " + o1 + " for " + o2 + " : ");
        	     String pref = br.readLine();
        	     int preference = Integer.parseInt(up);
        	     preferences.add(preference);
        	}
        }
      
        LinearProblem lp = new LinearProblem("lp", list1, list2, constraints, GLPKConstants.GLP_MIN, preferences);
        System.out.println("Solving...");
        Thread.sleep(3000);
        lp.solve();
	}
}
