package GUI;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aaron
 */
//Class to observe changes in the parser
public abstract class Observer {

    protected GUI gui;
    public Observer(GUI guiSet){
        this.gui = guiSet;
    }
    public abstract void update(int topic, String id, int value);
}
//classes to watch and update the GUI

class EnvVarWatcher extends Observer {

    public EnvVarWatcher(GUI guiSet) {
        super(guiSet);
    }

    @Override
    public void update(int topic, String id, int value) {
        if (topic == 1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.outputTextAppender("The variable \"" + id + "\" was added/changed to " + value + ".\n");
        }
    }
}

class StateWatcher extends Observer {

    public StateWatcher(GUI guiSet) {
        super(guiSet);
    }

    @Override
    public void update(int topic, String state, int useless) {
        if (topic == 2) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.outputTextAppender(state + ".\n");
        }
    }
}

class FinishWatcher extends Observer {

    public FinishWatcher(GUI guiSet) {
        super(guiSet);
    }

    @Override
    public void update(int topic, String id, int value) {
        if (topic == 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.runTextSetter("Run Parser");
        }
    }
}
