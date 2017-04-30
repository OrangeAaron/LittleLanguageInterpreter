package parser;

public class Statement extends Instruction {

    static Instruction parse() {
        Token t = scanner.lastToken;
        if (t instanceof KeywordToken && "print".equals(t.value)) {
            return Print.parse();
        } else if (t instanceof KeywordToken && "read".equals(t.value)) {
            return Read.parse();
        } else if (t instanceof KeywordToken && "if".equals(t.value)) {
            return If.parse();
        } else if (t instanceof KeywordToken && "while".equals(t.value)) {
            return While.parse();
        } else if (t instanceof LBraToken) {
            return Block.parse();
        } else if (t instanceof IDToken) {
            return Assignment.parse();
        } else {
            System.out.println("Unexpected token in input stream.");
            System.exit(-1);
        }
        return null;
    }
    int eval(){
     return 0;
     }
}
/*
class StatementAssign extends Statement{
    Assignment assign;
    StatementAssign(Assignment assignThis){
        assign = assignThis;
    }
}
class StatementPrint extends Statement{
    Print print;
    StatementPrint(Print printThis){
        print = printThis;
    }
}
class StatementRead extends Statement{
    Read read;
    StatementRead(Read readThis){
        read = readThis;
    }
}
*/
