/**
 * 
 */
package topcoder;

/**
 * 	Return the result of evaluating a given boolean expression, represented as a string.
 *
 * An expression can either be:
 *
 * "t", evaluating to True;
 * "f", evaluating to False;
 * "!(expr)", evaluating to the logical NOT of the inner expression expr;
 * "&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
 * "|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...
 *
 * Example 1:
 *
 * Input: expression = "!(f)"
 * Output: true
 * Example 2:
 *
 * Input: expression = "|(f,t)"
 * Output: true
 * Example 3:
 *
 * Input: expression = "&(t,f)"
 * Output: false
 * Example 4:
 *
 * Input: expression = "|(&(t,f,t),!(t))"
 * Output: false
 *
 * Constraints:
 *
 * 1 <= expression.length <= 20000
 * expression[i] consists of characters in {'(', ')', '&', '|', '!', 't', 'f', ','}.
 * expression is a valid expression representing a boolean, as given in the description.

 * @author Chauncey
 * Runtime: 1 ms, faster than 96.06%
 * Memory Usage: 37.8 MB, less than 100.00%
 */
public class LC_1106_Parsing_A_Boolean_Expression
{
    public boolean parseBoolExpr(String expression) {
        int[] rs = parseBoolExprHelper(expression, 0);
        if (rs[0] == 1) return true;
        else return false;
    }

    int[] parseBoolExprHelper(String expression, int start) {
        if (expression == null || expression.length()<=start)
            return new int[]{0,0,0};

        int res;
        int end = start;
        switch (expression.charAt(end)) {
            case 't':
                res = 1;
                end++;
                break;
            case 'f':
                res = 0;
                end++;
                break;
            case '&':
                res = 1;
                int[] rs = parseBoolExprHelper(expression, end+2);
                int r = rs[0]; int s = rs[1]; end = rs[2];
                if (r==0) res = 0;
                while (s != 0) {
                    rs = parseBoolExprHelper(expression, end);
                    r = rs[0]; s = rs[1]; end = rs[2];
                    if (r==0) res = 0;
                }
                break;
            case '|':
                res = 0;
                rs = parseBoolExprHelper(expression, end+2);
                r = rs[0]; s = rs[1]; end = rs[2];
                if (r==1) res = 1;
                while (s != 0) {
                    rs = parseBoolExprHelper(expression, end);
                    r = rs[0]; s = rs[1]; end = rs[2];
                    if (r==1) res = 1;
                }
                break;
            case '!':
                rs = parseBoolExprHelper(expression, end+2);
                r = rs[0]; s = rs[1]; end = rs[2];
                if (r==0) res = 1;
                else res = 0;
                break;
            default:
                res = -1;
                break;
        }

        if (end >= expression.length())
            return new int[]{res, 0, end};
        if (expression.charAt(end)==')')
            return new int[]{res, 0, end+1};
        return new int[]{res, 1, end+1};

    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1106_Parsing_A_Boolean_Expression solution = new LC_1106_Parsing_A_Boolean_Expression();
        System.out.println(solution.parseBoolExpr("!(f)")); //true
        System.out.println(solution.parseBoolExpr("|(f,t)")); //true
        System.out.println(solution.parseBoolExpr("&(t,f)")); //false
        System.out.println(solution.parseBoolExpr("|(&(t,f,t),!(t))")); //false
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
