package GUI;

import parser.ParserGUIAdaptor;

/**
 *
 * @author pegme
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        runButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        InputPane = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        OutputPane = new javax.swing.JTextArea();
        DebugModeButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        runButton.setText("Run Parser");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        InputPane.setColumns(20);
        InputPane.setRows(5);
        InputPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Input:"));
        InputPane.setMaximumSize(new java.awt.Dimension(170, 2147483647));
        jScrollPane2.setViewportView(InputPane);

        OutputPane.setEditable(false);
        OutputPane.setColumns(20);
        OutputPane.setLineWrap(true);
        OutputPane.setRows(5);
        OutputPane.setToolTipText("");
        OutputPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Output:"));
        OutputPane.setFocusable(false);
        OutputPane.setMaximumSize(new java.awt.Dimension(170, 2147483647));
        OutputPane.setName(""); // NOI18N
        OutputPane.setRequestFocusEnabled(false);
        jScrollPane4.setViewportView(OutputPane);

        DebugModeButton.setText("Debug Mode");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(runButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(DebugModeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(runButton, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(DebugModeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void outputTextAppender(String text){
    this.OutputPane.append(text);
    }    
    public void runTextSetter(String text){
    this.runButton.setText(text);
    }
    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        //Create a parser
        ParserGUIAdaptor parserRun = ParserGUIAdaptor.create(InputPane.getText(), DebugModeButton.isSelected());
        if (!ParserGUIAdaptor.debugFirst) {
            runButton.setText("Run Parser");
            if (DebugModeButton.isSelected()) {
                runButton.setText("Next");
                ParserGUIAdaptor.debugMode = true;
                ParserGUIAdaptor.debugFirst = true;
            }
            EnvVarWatcher watcher1 = new EnvVarWatcher(this);
            parserRun.register(watcher1);
            StateWatcher watcher2 = new StateWatcher(this);
            parserRun.register(watcher2);
            FinishWatcher watcher3 = new FinishWatcher(this);
            parserRun.register(watcher3);
            Runnable r = parserRun;
            Thread thread = new Thread(r);
            thread.start();
        } else {
            synchronized (parserRun) {
                //set ready flag to true (so isReady returns true)
                ParserGUIAdaptor.ready = true;
                parserRun.notifyAll();
            }
        }
    }//GEN-LAST:event_runButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton DebugModeButton;
    private javax.swing.JTextArea InputPane;
    private javax.swing.JTextArea OutputPane;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton runButton;
    // End of variables declaration//GEN-END:variables
}