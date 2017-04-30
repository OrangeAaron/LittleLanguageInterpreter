/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.*;

public class Term extends Instruction {

    ArrayList<Instruction> factors;

    Term(ArrayList<Instruction> tl) {
        factors = tl;
    }

    static Term parse() {
        ArrayList<Instruction> temp = new ArrayList<>();
        temp.add(Factor.parse());
        Token t = scanner.lastToken;
        while (t instanceof StarToken | t instanceof SlashToken) {
            if (t instanceof StarToken) {
                temp.add(MultiplyOp.create());
                t = scanner.getToken();
            } else if (t instanceof SlashToken) {
                temp.add(DivideOp.create());
                t = scanner.getToken();
            } else {
                System.out.println("Unexpected token in input stream.");
                System.exit(-1);
            }
            temp.add(Factor.parse());
            t = scanner.lastToken;
        }
        return new Term(temp);
    }
     int eval(){        
        int n = factors.get(0).eval();
        int pos = 1;
        while (pos < factors.size()) {
            Op instr = (Op) factors.get(pos);
            ++pos;
            Factor factor = (Factor) factors.get(pos);
            ++pos;
            n = instr.eval(n, factor.eval());
        }
        return n;
    }
}

class Op extends Instruction {

    char operator;
    int eval() {
        System.out.println("An error occured. Check input.");
        System.exit(-1);
        return 0;
    }
    int eval(int left, int right) {
        return 0;
    }
}

class DivideOp extends Op {

    final static DivideOp DIVIDE = new DivideOp();

    static DivideOp create() {
        return DIVIDE;
    }

    DivideOp() {
        operator = '/';
    }

    int eval(int left, int right) {
        return left / right;
    }
}

class MultiplyOp extends Op {

    final static MultiplyOp MULTIPLY = new MultiplyOp();

    static MultiplyOp create() {
        return MULTIPLY;
    }

    MultiplyOp() {
        operator = '*';
    }
    int eval(int left, int right) {
        return left*right;
    }
}
