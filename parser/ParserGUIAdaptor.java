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
 * @author Aaron Wilson
 */
public class ParserGUIAdaptor implements Runnable{

    /**
     *  The input code for the interpretor to run.
     */
    static public String input = "";

    /**
     *  A flag value set true when debugging mode for the interpretor is on,
     * and the code will be interpreted step by step.
     */
    static public boolean debugMode = false;

    /**
     *  A flag value set true when debugging mode is on, and the program is ready
     *  to continue running until the next statement.
     */
    static public boolean ready = false;

    /**
     *  A flag value that is false when debugging mode needs to be first initialized.
     *  It's set true when debugging mode is first turned on, and turned off after debugging for the current code is completed.
     * 
     */
    static public boolean debugFirst = false;

    /**
     *  The ParserGUIAdaptor instance for the current GUI.  Uses the Singleton pattern so that only one can be made.
     */
    static public ParserGUIAdaptor parserrun;
    /**
     * Attachment to a GUI package that uses observers to track the environment variables.
     */
    static ArrayList<Observer> Observers = new ArrayList<>();
    private ParserGUIAdaptor(){
      input = "";
      debugMode = false;
    }
    private ParserGUIAdaptor(String in, boolean debug){
        input = in;
        debugMode = debug;
    }

    /**
     * Creates the ParserGUIAdaptor instance, if an instance already exists, it just overwrites it in order to save space.  Will need to remove the singleton pattern if running more than one interpretor at a time is wished. 
     * @param in Takes the input string for the interpretor.
     * @param debug Flag that is true when debugging mode is desired.
     * @return Returns the ParserGUIAdaptor that is created or re-initialized (In the case that an instance already exists.)
     */
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

    /**
     * Adds an observer to the list of observers that are notified on updates.
     * @param obs The observer to be registered to be notified of updates.
     */
    public void register(Observer obs){
        Observers.add(obs);
    }

    /**
     * Removes an observer from the list of observers that are notified on updates.
     * @param obs The observer to be unregistered to be notified of updates.
     */
    public void unregister(Observer obs){
        Observers.remove(obs);
    }

    /**
     * Notifies all the observers in the current list of an update.
     * @param topic The subject of information to sent out, in the form of a categorical number.
     * @param id  String information to be sent out to observers. Specified at top of class file.
     * @param value Integer information to be sent out to observers. Specified at top of class file.
     */
    public void notifyObs(int topic, String id, int value){
        for(int i = 0; i < Observers.size(); i++){
            Observers.get(i).update(topic, id, value);
    }
    }

    /**
     * Runs the interpretor. Multiple interpretors at once will require removing the singleton pattern.
     */
    @Override
    public void run(){
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
