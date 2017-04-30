package parser;

import java.util.*;

public class Expression extends Instruction {

    ArrayList<Instruction> terms;

    Expression(ArrayList<Instruction> ex) {
        terms = ex;
    }

    static Expression parse() {
        ArrayList<Instruction> temp = new ArrayList<>();
        temp.add(Term.parse());
        Token t = scanner.lastToken;
        while (t instanceof PlusToken | t instanceof MinusToken) {
            if (t instanceof PlusToken) {
                temp.add(AddOp.create());
                t = scanner.getToken();
            } else if (t instanceof MinusToken) {
                temp.add(SubtractionOp.create());
                t = scanner.getToken();
            } else {
                System.out.println("Unexpected token in input stream.");
                System.exit(-1);
            }
            temp.add(Term.parse());
            t = scanner.lastToken;
        }
        return new Expression(temp);
    }

    int eval() {
        int n = terms.get(0).eval();
        int pos = 1;
        while (pos < terms.size()) {
            Op instr = (Op) terms.get(pos);
            ++pos;
            Term term = (Term) terms.get(pos);
            ++pos;
            n = instr.eval(n, term.eval());
        }
        return n;
    }
}

class AddOp extends Op {

    final static AddOp ADD = new AddOp();

    AddOp() {
        operator = '+';
    }

    static AddOp create() {
        return ADD;
    }

    int eval(int left, int right) {
        return left + right;
    }
}

class SubtractionOp extends Op {

    final static SubtractionOp SUBTRACT = new SubtractionOp();

    SubtractionOp() {
        operator = '-';
    }

    static SubtractionOp create() {
        return SUBTRACT;
    }

    int eval(int left, int right) {
        return left - right;
    }
}
