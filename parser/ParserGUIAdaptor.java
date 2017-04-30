/*
 *Adapts the parser so that it will use the GUI. 
 *Observers will recieve information depending on the update topic.
 *topic 1 will notify of change in variables
 *topic 2 will notify of change in the state of the program
 *topic 3 will notify of the finish of the parser.
*/
package parser;

import GUI.Observer;
import java.util.ArrayList;

/**
 *
 * @author pegme
 */
public class ParserGUIAdaptor implements Runnable{
    static public String input = "";
    static public boolean debugMode = false;
    static public boolean ready = false;
    static public boolean debugFirst = false;
    static public ParserGUIAdaptor parserrun;
    //Attachment to a GUI package that uses observers to track the enviroment variables.
    static ArrayList<Observer> Observers = new ArrayList<>();
    private ParserGUIAdaptor(){
      input = "";
      debugMode = false;
    }
    private ParserGUIAdaptor(String in, boolean debug){
        input = in;
        debugMode = debug;
    }
    static public ParserGUIAdaptor create(String in, boolean debug){
        if(parserrun == null){
            parserrun = new ParserGUIAdaptor(in, debug);
        }
        else{
            input = in;
            debugMode = debug;
        }
        return parserrun;
    }
    public void register(Observer obs){
        Observers.add(obs);
    }
    public void unregister(Observer obs){
        Observers.remove(obs);
    }
    public void notifyObs(int topic, String id, int value){
        for(int i = 0; i < Observers.size(); i++){
            Observers.get(i).update(topic, id, value);
    }
    }
    public void run(){
        //Run all parser routines.
        Buffer readInput = new Buffer(input);
        Scanner scan = new Scanner(readInput);
        scan.getToken();
        Instruction.setScanner(scan);
        Instruction.setAdult(this);
        Instruction instr = Program.parse();
        int result = instr.eval();
        String state = "Finished running with result of " + Integer.toString(result) + "\n\n\n";
        notifyObs(2, state, Integer.MAX_VALUE);
        notifyObs(3, "", 0);
        Observers.clear();
        debugFirst = false;
    }
}
