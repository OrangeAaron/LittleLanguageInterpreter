package parser;

public class No extends Instruction {

    int number;

    No(int i) {
        number = i;
    }

    static No parse() {
        boolean negative = false;
        Token t = scanner.lastToken;
        while (t instanceof MinusToken | t instanceof PlusToken) {
            if (t instanceof MinusToken) {
                negative = !negative;
            }
            t = scanner.getToken();
        }
        if (t instanceof NumToken) {
            int j = Integer.valueOf(((NumToken) t).value);
            if (negative) {
                j = -j;
            }
            scanner.getToken();
            return new No(j);
        }
        return null;
    }
    @Override
    int eval(){
        return number;
    }
}
