package parser;

public class If extends Instruction {

    Expression condExp;
    Instruction thenState;
    Instruction elseState = null;

    If(Expression condE, Instruction thenS, Instruction elseS) {
        condExp = condE;
        thenState = thenS;
        elseState = elseS;
    }

    If(Expression condE, Instruction thenS) {
        condExp = condE;
        thenState = thenS;
    }

    static If parse() {
        Token t = scanner.lastToken;
        Expression condExp;
        Instruction thenState;
        if (t instanceof KeywordToken && "if".equals(t.value)) {
            t = scanner.getToken();
            condExp = Expression.parse();
            t = scanner.lastToken;
            if (t instanceof KeywordToken && "then".equals(t.value)) {
                t = scanner.getToken();
                thenState = Statement.parse();
                t = scanner.lastToken;
                if (t instanceof KeywordToken && "else".equals(t.value)) {
                    Instruction elseState;
                    t = scanner.getToken();
                    elseState = Statement.parse();
                    return new If(condExp, thenState, elseState);
                }
                return new If(condExp, thenState);
            } else {
                System.out.println("Expected then token in input stream.");
                System.exit(-1);
            }
        } else {
            System.out.println("Expected if token in input stream.");
            System.exit(-1);
        }
        return null;
    }

    int eval() {
        final int cond = condExp.eval();
        int ans = 0;
        if (cond != 0) {
            ans = thenState.eval();
        } else if (elseState != null) {
            ans = elseState.eval();
        }
        return ans;
    }
}
