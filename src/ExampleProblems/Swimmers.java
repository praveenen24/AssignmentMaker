package ExampleProblems;
import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_prob;
import org.gnu.glpk.glp_smcp;

public class Swimmers {

	public static void main(String[] args) {
		glp_prob lp;
		glp_smcp parm;
		SWIGTYPE_p_int ind;
		SWIGTYPE_p_double val;
		int ret;
		
		lp = GLPK.glp_create_prob();
		GLPK.glp_set_prob_name(lp, "Swimmer Problem");
		
		double s1 = 2.0;
		double s2 = 5.0;
		double s3 = 9.1;
		double s4 = 0.5;
		
		int age1 = 14;
		int age2 = 18;
		int age3 = 21;
		int age4 = 8;
		
		GLPK.glp_add_cols(lp, 16);
		
		GLPK.glp_set_col_name(lp, 1, "S1L1");
		GLPK.glp_set_col_kind(lp, 1, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 1, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 2, "S1L2");
		GLPK.glp_set_col_kind(lp, 2, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 2, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 3, "S1L3");
		GLPK.glp_set_col_kind(lp, 3, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 3, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 4, "S1L4");
		GLPK.glp_set_col_kind(lp, 4, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 4, GLPKConstants.GLP_DB, 0, 1);
		
		GLPK.glp_set_col_name(lp, 5, "S2L1");
		GLPK.glp_set_col_kind(lp, 5, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 5, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 6, "S2L2");
		GLPK.glp_set_col_kind(lp, 6, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 6, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 7, "S2L3");
		GLPK.glp_set_col_kind(lp, 7, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 7, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 8, "S2L4");
		GLPK.glp_set_col_kind(lp, 8, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 8, GLPKConstants.GLP_DB, 0, 1);
		
		GLPK.glp_set_col_name(lp, 9, "S3L1");
		GLPK.glp_set_col_kind(lp, 9, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 9, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 10, "S3L2");
		GLPK.glp_set_col_kind(lp, 10, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 10, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 11, "S3L3");
		GLPK.glp_set_col_kind(lp, 11, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 11, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 12, "S3L4");
		GLPK.glp_set_col_kind(lp, 12, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 12, GLPKConstants.GLP_DB, 0, 1);
		
		GLPK.glp_set_col_name(lp, 13, "S4L1");
		GLPK.glp_set_col_kind(lp, 13, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 13, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 14, "S4L2");
		GLPK.glp_set_col_kind(lp, 14, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 14, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 15, "S4L3");
		GLPK.glp_set_col_kind(lp, 15, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 15, GLPKConstants.GLP_DB, 0, 1);
		GLPK.glp_set_col_name(lp, 16, "S4L4");
		GLPK.glp_set_col_kind(lp, 16, GLPKConstants.GLP_CV);
		GLPK.glp_set_col_bnds(lp, 16, GLPKConstants.GLP_DB, 0, 1);
		
		//constraints
		GLPK.glp_add_rows(lp, 3);
		
		System.out.println("Swimmer 1: speed = " + s1 + " age = " + age1);
		System.out.println("Swimmer 2: speed = " + s2 + " age = " + age2);
		System.out.println("Swimmer 3: speed = " + s3 + " age = " + age3);
		System.out.println("Swimmer 4: speed = " + s4 + " age = " + age4);
		
		System.out.println("\nFamily Lane ages 1-13");
		System.out.println("Slow Lane: speed 0-3 kph");
		System.out.println("Medium Lane: speed 3.1-6 kph");
		System.out.println("Fast Lane: speed >6.1 kph");
		
	}

}
