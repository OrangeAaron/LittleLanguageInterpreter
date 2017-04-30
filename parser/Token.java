package parser;

import java.util.logging.Level;
import java.util.logging.Logger;
import parser.Buffer;

public class Token implements Cloneable{

    static final String INVAL = "|InvalidTokenError";
    protected String value;
    protected Buffer buffer;

    public Token copy(){
        try {;            return (Token) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Token.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
    }
    Token get() {
        buffer.toNextChar();
        return this;
    }

    @Override
    public String toString() {
        return value;
    }

    void setBuffer(Buffer buffToSet) {
        buffer = buffToSet;
    }
    public boolean isTokenValid() {
        return !(this instanceof InvalidToken);
    }
}

class IDToken extends Token {

    public IDToken() {
        value = INVAL;
    }

    public static IDToken create() {
        return new IDToken();
    }

    @Override
    Token get() {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(buffer.thisAndToNextChar());
        } while (Character.isLetter(buffer.thisChar()));
        value = sb.toString();
        switch (value) {
            case "end":
                return EndToken.create(value);
            case "if":
            case "then":
            case "else":
            case "while":
            case "read":
            case "print":
            case "do":
                return KeywordToken.create(value);
        }
        return this;
    }
}

class EndToken extends IDToken {

    private EndToken(String setValue) {
        value = setValue;
    }

    protected static EndToken create(String setvalue) {
        return new EndToken(setvalue);
    }
}

class KeywordToken extends IDToken {

    private KeywordToken(String setValue) {
        value = setValue;
    }

    protected static KeywordToken create(String setvalue) {
        return new KeywordToken(setvalue);
    }
}

class SlashToken extends Token {

    public volatile static SlashToken me;

    private SlashToken() {
        value = "/";
    }

    public static SlashToken create() {
        synchronized (SlashToken.class) {
            if (me == null) {
                me = new SlashToken();
            }
            return me;
        }
    }
}

class RParToken extends Token {

    public volatile static RParToken me;

    public static RParToken create() {
        synchronized (RParToken.class) {
            if (me == null) {
                me = new RParToken();
            }
            return me;
        }
    }

    private RParToken() {
        value = ")";
    }
}

class StarToken extends Token {

    public volatile static StarToken me;

    public static StarToken create() {
        synchronized (StarToken.class) {
            if (me == null) {
                me = new StarToken();
            }
            return me;
        }
    }

    private StarToken() {
        value = "*";
    }
}

class PlusToken extends Token {

    public volatile static PlusToken me;

    public static PlusToken create() {
        synchronized (PlusToken.class) {
            if (me == null) {
                me = new PlusToken();
            }
            return me;
        }
    }

    private PlusToken() {
        value = "+";
    }
}

class MinusToken extends Token {

    public volatile static MinusToken me;

    public static MinusToken create() {
        synchronized (MinusToken.class) {
            if (me == null) {
                me = new MinusToken();
            }
            return me;
        }
    }

    private MinusToken() {
        value = "-";
    }
}

class LParToken extends Token {

    public volatile static LParToken me;

    public static LParToken create() {
        synchronized (LParToken.class) {
            if (me == null) {
                me = new LParToken();
            }
            return me;
        }
    }

    private LParToken() {
        value = "(";
    }
}

class InvalidToken extends Token {

    public volatile static InvalidToken me;

    public static InvalidToken create() {
        synchronized (InvalidToken.class) {
            if (me == null) {
                me = new InvalidToken();
            }
            return me;
        }
    }

    private InvalidToken() {
        value = INVAL;
    }
}

class NumToken extends Token {

    public static NumToken create() {
        return new NumToken();
    }

    public NumToken() {
        value = INVAL;
    }

    @Override
    Token get() {
        value = "";
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(buffer.thisAndToNextChar());
            buffer.skipBlanks();
        } while (Character.isDigit(buffer.thisChar()));
        value = sb.toString();
        return this;
    }
}

class ColonToken extends Token {

    public volatile static ColonToken me;

    public static ColonToken create() {
        synchronized (ColonToken.class) {
            if (me == null) {
                me = new ColonToken();
            }
            return me;
        }
    }

    @Override
    Token get() {
        buffer.toNextChar();
        if (buffer.thisAndToNextChar() == '=') {
            return AssignToken.create();
        }
        return me;
    }

    private ColonToken() {
        value = ":";
    }
}

class AssignToken extends Token {

    public volatile static AssignToken me;

    public static AssignToken create() {
        synchronized (AssignToken.class) {
            if (me == null) {
                me = new AssignToken();
            }
            return me;
        }
    }

    private AssignToken() {
        value = ":=";
    }
}

class CommaToken extends Token {

    public volatile static CommaToken me;

    public static CommaToken create() {
        synchronized (CommaToken.class) {
            if (me == null) {
                me = new CommaToken();
            }
            return me;
        }
    }

    private CommaToken() {
        value = ",";
    }
}

class LBraToken extends Token {

    public volatile static LBraToken me;

    public static LBraToken create() {
        synchronized (LBraToken.class) {
            if (me == null) {
                me = new LBraToken();
            }
            return me;
        }
    }

    private LBraToken() {
        value = "{";
    }
}

class RBraToken extends Token {

    public volatile static RBraToken me;

    public static RBraToken create() {
        synchronized (RBraToken.class) {
            if (me == null) {
                me = new RBraToken();
            }
            return me;
        }
    }

    private RBraToken() {
        value = "}";
    }
}
