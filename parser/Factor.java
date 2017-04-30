package parser;

public abstract class Factor extends Instruction {

    static Factor parse() {
        Token t = scanner.getCurrentToken();
         final Factor factor;
        if (t instanceof NumToken | t instanceof MinusToken | t instanceof PlusToken) {
            No no = No.parse();
            factor = new FactorNo(no);
        } else if (t instanceof IDToken) {
            factor = new FactorID((IDToken) t);
            scanner.getToken();
        } else if (t instanceof LParToken) {
            scanner.getToken();
            Expression exp = Expression.parse();
            t = scanner.lastToken;
            if (!(t instanceof RParToken)) {
                System.out.println("Expected a right paranthesis in input stream.");
                System.exit(-1);
            }
            scanner.getToken();
            factor = new FactorExp(exp);
        } else {
            factor = null;
            System.out.println("Unexpected token in input stream.");
            System.exit(-1);
        }
        return factor;
    }
}

class FactorNo extends Factor {

    No no;

    FactorNo(No num) {
        no = num;
    }
    @Override
     int eval(){
     return no.eval();
     }
}
class FactorID extends Factor {

    IDToken id;

    FactorID(IDToken identification) {
        id = identification;
    }
    @Override
     int eval(){
     return env.get(id.value);
     }
}


class FactorExp extends Factor {

    Expression expr;

    FactorExp(Expression ex) {
        expr = ex;
    }
    @Override
     int eval(){
     return expr.eval();
     }
}
