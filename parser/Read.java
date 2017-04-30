package parser;

import java.util.*;

public class Read extends Instruction {

    ArrayList<IDToken> readList = new ArrayList<>();

    Read(ArrayList<IDToken> copyList) {
        readList = copyList;
    }

    static Read parse() {
        Token t = scanner.lastToken;
        ArrayList<IDToken> tempList = new ArrayList<>();
        if (t instanceof KeywordToken && "read".equals(t.value)) {
            Boolean first = true;
            do {
                scanner.getToken();
                Token addToken = scanner.getCurrentToken();
                if (addToken instanceof IDToken) {
                    tempList.add((IDToken) addToken);
                }
                else if(first == true && !(addToken instanceof IDToken)){
                System.out.println("Expected an ID in the input stream.");
                System.exit(-1);
                }
                t = scanner.getToken(); //could be the comma
            } while (t instanceof CommaToken);
        } else {
            System.out.println("Expected a print keyword in input stream.");
            System.exit(-1);
        }
        return new Read(tempList);
    }

    int eval() {
        for (IDToken token : readList) {
            if(!(env.valueCheck(token.value)))
            {
            env.put(token.value, 0);
            if (ParserGUIAdaptor.debugMode) adult.notifyObs(1, token.value, 0);
            }
        }
        return 0;
    }
}
