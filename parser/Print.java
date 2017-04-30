package parser;

import java.util.*;

public class Print extends Instruction {

    ArrayList<Instruction> printList = new ArrayList<>();

    Print(ArrayList<Instruction> copyList) {
        printList = copyList;
    }

    static Print parse() {
        Token t = scanner.lastToken;
        ArrayList<Instruction> tempList = new ArrayList<>();
        if (t instanceof KeywordToken && "print".equals(t.value)) {
            do {
                scanner.getToken();
                Expression exp = Expression.parse();
                tempList.add(exp);
                t = scanner.lastToken; //could be the comma
            } while (t instanceof CommaToken);
        } else {
            System.out.println("Expected a print keyword in input stream.");
            System.exit(-1);
        }
        return new Print(tempList);
    }
     int eval(){
        for (Instruction inst : printList) {
            System.out.println(inst.eval());
        }
        return 0;
    }
}
