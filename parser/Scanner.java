//strategy pattern at its finest
//Sets up to scan certain tokens.
package parser;

import parser.Buffer;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Scanner{

    final private Token charToTokenList[] = new Token[128];
    public Token lastToken;
    private Buffer scanBuffer;

    public Scanner(Buffer buffer) {
        //construct scanner
        scanBuffer = buffer;
        this.init(InvalidToken.create(), "");
        final HashMap<Token, String> tokenlist = new HashMap<>();
        tokenlist.put(NumToken.create(), "1234567890");
        tokenlist.put(IDToken.create(), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        tokenlist.put(MinusToken.create(), "-");
        tokenlist.put(PlusToken.create(), "+");
        tokenlist.put(SlashToken.create(), "/");
        tokenlist.put(StarToken.create(), "*");
        tokenlist.put(LParToken.create(), "(");
        tokenlist.put(RParToken.create(), ")");
        tokenlist.put(ColonToken.create(), ":");
        tokenlist.put(CommaToken.create(), ",");
        tokenlist.put(LBraToken.create(), "{");
        tokenlist.put(RBraToken.create(), "}");
        tokenlist.keySet().stream().forEach((tokenlistObj) -> {
            this.init(tokenlistObj, tokenlist.get(tokenlistObj));
        });
    }

    public Token getToken() {
        scanBuffer.skipBlanks();
        if (scanBuffer.stringPosition < scanBuffer.input.length()) {
            char ch = scanBuffer.thisChar();
            Token temp = charToTokenList[ch];
            temp.setBuffer(scanBuffer);
            return (lastToken = temp.get());
        } else {
            lastToken = InvalidToken.create();
            return lastToken;
        }
    }
    public Token getCurrentToken() {
       Token newt = (Token) this.lastToken.copy();
       newt.value = this.lastToken.value;
       return newt;
    }
    private void init(Token token, String chars) {
        if (chars.isEmpty()) {
            for (int i = 0; i < 128; ++i) {
                charToTokenList[i] = token;
            }
        }
        for (int i = 0; i < chars.length(); ++i) {
            charToTokenList[chars.charAt(i)] = token;
        }
    }

}
