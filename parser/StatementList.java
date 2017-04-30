package parser;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatementList extends Instruction {

    ArrayList<Instruction> listOfStatements;

    StatementList(ArrayList<Instruction> copyList) {
        listOfStatements = copyList;
    }

    static StatementList parse() {
        ArrayList<Instruction> tempList = new ArrayList<>();
        Token t = scanner.lastToken;
        //Cannot be EndToken because will try to execute a statement
        while ((t instanceof LBraToken || t instanceof IDToken) && !(t instanceof EndToken)) {
            tempList.add(Statement.parse());
            t = scanner.lastToken;
        }
        //if its an end token will return back to the while class and finish there
        if (!(t instanceof InvalidToken) && !(t instanceof EndToken)) {
            System.out.println("Unexpected character at end of program.");
            System.exit(-1);
        }
        return new StatementList(tempList);
    }

    @Override
    int eval() {
        int ans = 0;
        for (Instruction inst : listOfStatements) {
            if (ParserGUIAdaptor.debugMode) {
                synchronized (adult) {
                    while (!ParserGUIAdaptor.ready) {
                        try {
                            adult.wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(StatementList.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                ans = inst.eval();
                String state = "The result of the last executed instruction is " + Integer.toString(ans);
                adult.notifyObs(2, state, Integer.MAX_VALUE);
                ParserGUIAdaptor.ready = false;
            } else {
                ans = inst.eval();
            }
        }
        return ans;
    }
}
