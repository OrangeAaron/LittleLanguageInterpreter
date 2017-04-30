package parser;

public class While extends Instruction {

    Expression condExp;
    StatementList stateList;

    While(Expression copyExp, StatementList copyList) {
        condExp = copyExp;
        stateList = copyList;
    }

    static While parse() {
        Expression tempExp;
        StatementList tempList;
        Token t = scanner.lastToken;
        if (t instanceof KeywordToken && "while".equals(t.value)) {
            t = scanner.getToken();
            tempExp = Expression.parse();
            t = scanner.getCurrentToken();
            if (t instanceof KeywordToken && "do".equals(t.value)) {
                t = scanner.getToken();
                tempList = StatementList.parse();
                t = scanner.getCurrentToken();
                if (t instanceof EndToken && "end".equals(t.value)) {
                    scanner.getToken(); // throw the end and go to next
                    return new While(tempExp, tempList);
                } else {
                    System.out.println("Expected a end token in the input stream");
                    System.exit(-1);

                }
            } else {
                System.out.println("Expected a do token in the input stream");
                System.exit(-1);

            }
        } else {
            System.out.println("Expected a while token in the input stream");
            System.exit(-1);
        }
        return null;
    }

    int eval() {
        int cond = condExp.eval();
        int ans = 0;
        int infinitebreak = 1000000;
        while (cond != 0 && infinitebreak > 0) {
            ans = stateList.eval();
            cond = condExp.eval();
            infinitebreak--;
        }
        if (infinitebreak <= 0) System.out.println("Infinite Loop");
        return ans;
    }
}
