package parser;

public class Block extends Instruction {

    StatementList statementList;

    Block(StatementList copyList) {
        statementList = copyList;
    }

    static Block parse() {
        Token t = scanner.lastToken;
        StatementList tempList;
        if (t instanceof LBraToken) {
            t = scanner.getToken();
            tempList = StatementList.parse();
            scanner.getToken(); //throw away right bracket token.
            return new Block(tempList);
        } else {
            System.out.println("Expected a right bracket in the input stream");
            System.exit(-1);
        }
        return null;
    }
     int eval(){
     return statementList.eval();
     }
}
