package parser;

public abstract class Instruction{
    static ParserGUIAdaptor adult;
    static Environment env = new Environment();
    static Scanner scanner;
    static void setScanner(Scanner setScan) {
        scanner = setScan;  
    }    
    static void setAdult(ParserGUIAdaptor master) {
        adult = master;
    }
     abstract int eval();
}
