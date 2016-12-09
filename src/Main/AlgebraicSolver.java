package Main;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class AlgebraicSolver {

	public static double calculateExpression(String expression) throws ScriptException {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
		return (double) scriptEngine.eval(expression);
	}

	public static void main(String[] args) {
		try {
			System.out.println(calculateExpression("((40+2.0) * 3) / 2.4"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
