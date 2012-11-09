/*
 * Landing_Page_jFrame_Form.java
 *
 * Created on Nov 6, 2012, 2:16:46 AM
 */

package databaseparser;

/**
 *
 * @author Arron
 */
public class Landing_Page_jFrame_Form extends javax.swing.JFrame {

    //-------------------------------------------------------------
    //          Variables
    //-------------------------------------------------------------
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CreateUser;
    private javax.swing.JTable accounttable;
    private javax.swing.JButton deposite;
    private javax.swing.JMenu edit;
    private javax.swing.JMenu exit;
    private javax.swing.JMenu exit2;
    private javax.swing.JMenu help;
    private javax.swing.JButton history;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton transfer;
    private javax.swing.JButton withdrawl;
    // End of variables declaration//GEN-END:variables
	
    //-------------------------------------------------------------
    //          Constructor
    //-------------------------------------------------------------
    /** Creates new form Landing_Page_jFrame_Form */
    public Landing_Page_jFrame_Form() {
        initComponents();
    }

    //-------------------------------------------------------------
    //          Methods
    //-------------------------------------------------------------
	
    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        accounttable = new javax.swing.JTable();
        withdrawl = new javax.swing.JButton();
        transfer = new javax.swing.JButton();
        history = new javax.swing.JButton();
        deposite = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        exit2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        CreateUser = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        edit = new javax.swing.JMenu();
        help = new javax.swing.JMenu();
        exit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        accounttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Checking", "Saving", "Balance", "Available Balance", "null"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(accounttable);

        withdrawl.setText("Withdraw");

        transfer.setText("Transfer");

        history.setText("Transaction History");

        deposite.setText("Deposit");
        deposite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositeActionPerformed(evt);
            }
        });

        exit2.setText("File");

        jMenu3.setText("Open");
        exit2.add(jMenu3);

        CreateUser.setText("Create User");
        CreateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateUserActionPerformed(evt);
            }
        });
        exit2.add(CreateUser);

        jMenu6.setText("Exit");
        exit2.add(jMenu6);

        jMenuBar1.add(exit2);

        edit.setText("Edit");
        jMenuBar1.add(edit);

        help.setText("Help");
        jMenuBar1.add(help);

        exit.setText("Exit");
        jMenuBar1.add(exit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(transfer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deposite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(withdrawl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(history, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(209, 209, 209)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(withdrawl, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deposite, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(history, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transfer, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void depositeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_depositeActionPerformed

    private void CreateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateUserActionPerformed
        Create_Account_jFrame_Form newuser = new Create_Account_jFrame_Form();
        newuser.run();
    }//GEN-LAST:event_CreateUserActionPerformed

    /**
    * @param args the command line arguments
    */
    public void run() {
       new Landing_Page_jFrame_Form().setVisible(true);
    }




}
