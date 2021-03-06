/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Create_Account_jFrame_Form.java
 *
 * Created on Nov 6, 2012, 2:22:27 AM
 */

package bankingsystem;

/**
 *
 * @author Arron
 * @since Nov 6, 2012, 2:22:27 AM
 * @version 1
 */
public class Change_InterestRate extends javax.swing.JDialog {

    // End of variables declaration

        

    //-------------------------------------------------------------
    //          Constructor
    //-------------------------------------------------------------
    
    /** Creates new form Create_Account_jFrame_Form */
    public Change_InterestRate(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);//sets the jFram_Form in the center
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        acctnum = new javax.swing.JFormattedTextField();
        message = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        allrates = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnChange = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        rate = new javax.swing.JTextField();
        AllRatesChangebtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        acctnum.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("############"))));

        message.setText("Result:");

        jLabel8.setText("Account Num");

        jLabel3.setText("All Savings & G/D Accounts");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Apple Braille", 1, 14)); // NOI18N
        jLabel2.setText("Change InterestRate Panel");

        btnChange.setText("Change");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        jLabel1.setText("Rate");

        AllRatesChangebtn.setText("Change");
        AllRatesChangebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AllRatesChangebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(103, 103, 103))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCancel)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(acctnum, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(58, 58, 58)
                                    .addComponent(rate, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnChange))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(63, 63, 63)
                                            .addComponent(allrates))
                                        .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(AllRatesChangebtn))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(acctnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChange)
                    .addComponent(rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(allrates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AllRatesChangebtn))
                .addGap(40, 40, 40)
                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(btnCancel)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        int emptycount = 0;
        if(acctnum.getText().isEmpty()){
            message.setText("account number field empty");
            emptycount++;
            return;
        }
        if(rate.getText().isEmpty()){
            message.setText("rate field empty");
            emptycount++;
            return;
        }
        if(emptycount==0){
        double rat = Double.parseDouble(rate.getText());
        int accountnum = Integer.parseInt(acctnum.getText());
        AccountParser ap = new AccountParser("");
        if(ap.getAccountTypeByAccountNumber(accountnum)==3){
            message.setText("This is a TMB, not interest rate.");
        }
        if(ap.getAccountTypeByAccountNumber(accountnum)==1){
            SavingsAccount sav = ap.getSavingsAccount(accountnum);
            sav.setInterestRate(rat);
            message.setText("This is a simple saving account, rate updated " + rat);
            ap.WriteFile();
        }
        if(ap.getAccountTypeByAccountNumber(accountnum)==4){
            Checking chk = ap.getCheckingAccount(accountnum);
            chk.setGDinterest(rat);
            message.setText("This is a G/D account, rate updated " + rat);
            ap.WriteFile();
        }
        
        }else{
            message.setText("Check fields.");
        }
                
    }//GEN-LAST:event_btnChangeActionPerformed

    private void AllRatesChangebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllRatesChangebtnActionPerformed
        // Change all Checking account interest rate
        AccountParser ap = new AccountParser("");
        message.setText(ap.SetCheckingAndSavingIntrestRates(Double.parseDouble(allrates.getText())));
        
        //ap.SetCheckingAndSavingIntrestRates(WIDTH)
    }//GEN-LAST:event_AllRatesChangebtnActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Change_InterestRate dialog = new Change_InterestRate(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }//end main args

            public void run() {
                Change_InterestRate dialog = new Change_InterestRate(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AllRatesChangebtn;
    private javax.swing.JFormattedTextField acctnum;
    private javax.swing.JTextField allrates;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChange;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel message;
    private javax.swing.JTextField rate;
    // End of variables declaration//GEN-END:variables

}
