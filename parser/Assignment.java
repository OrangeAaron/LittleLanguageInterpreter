/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

public class Assignment extends Instruction {

    IDToken id;
    Expression expr;

    Assignment(IDToken lhs, Expression rhs) {
        id = lhs;
        expr = rhs;
    }

    static Assignment parse() {
        Token tempid;
        tempid = scanner.getCurrentToken();
        if (!(tempid instanceof IDToken)) {
            System.out.println("Expected a IDtoken in input stream.");
            System.exit(-1);
        }
        Token t = scanner.getToken();
        if (!(t instanceof AssignToken)) {
            System.out.println("Expected an assignment token in input stream.");
            System.exit(-1);
        }
        scanner.getToken();
        Expression expression = Expression.parse();
        return new Assignment((IDToken) tempid, expression);
    }
     int eval(){
     int ans = expr.eval();
     env.put(id.value, ans);
     if (ParserGUIAdaptor.debugMode) adult.notifyObs(1, id.value, ans);
     return ans;
     }
}
