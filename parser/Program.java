/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author pegme
 */
public class Program extends Instruction {

    StatementList statements;

    Program(StatementList toExec) {
        statements = toExec;
    }

    static Program parse() {
        StatementList tempList;
        tempList = StatementList.parse();
        return new Program(tempList);
    }
    @Override
     int eval(){
     return statements.eval();
     }
}
