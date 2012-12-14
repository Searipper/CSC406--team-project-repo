package bankingsystem;

import java.util.ArrayList;

/**
 *
 * @author Arron
 */
public class ATM_Interface extends javax.swing.JDialog {

    /**
     * Creates new form ATM_Interface
     */
    public ATM_Interface(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        cardNo = new javax.swing.JTextField();
        amt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        sourceAccount = new javax.swing.JComboBox();
        btnSubmit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        message = new javax.swing.JLabel();
        pin = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ATM Card No.");

        jLabel2.setText("PIN");

        jLabel3.setText("Amount");

        sourceAccount.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Checking", "Saving" }));
        

        btnSubmit.setText("submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        jLabel4.setText("Source Account");

        btnCancel.setText("cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("BANK of MWSU, ATM Machine Interface");

        message.setText("Result:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sourceAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cardNo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(pin, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(amt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btnSubmit)
                        .addGap(35, 35, 35)
                        .addComponent(btnCancel)))
                .addContainerGap(110, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sourceAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnCancel))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed

        //fielderror check to insure all field are filled
        int fielderror = 0;
        if (cardNo.getText().isEmpty()) {
            message.setText("Please Fill all fields");
            fielderror++;
            return;
        }
        if (pin.getText().isEmpty()) {
            message.setText("Please Fill all fields");
            fielderror++;
            return;
        }
        if (amt.getText().isEmpty()) {
            message.setText("Please Fill all fields");
            fielderror++;
            return;
        }

        if (fielderror == 0) {
            //login data
            int cardnumber = Integer.parseInt(this.cardNo.getText());
            int pinnumber = Integer.parseInt(this.pin.getText());
            //amount
            double amount = Double.parseDouble(this.amt.getText());
            //parsers
            UserParser up = new UserParser("");
            AccountParser ap = new AccountParser("");

            //get ssnindex and ssn
            int ssnindex = up.LoginToATM(cardnumber, pinnumber);//-3=over usage ,-2= pin error, -1=no cardnum
            int ssn = -99;
            
            switch (ssnindex){
                case -1:
                    message.setText("Card Number not found.");
                    break;
                case -2: 
                    message.setText("PIN error");
                    break;
                case -3: 
                    message.setText("Reach Usage Limit today");
                    break;
                default:
                    ssn = up.getSSN(ssnindex);
            }
            System.out.println(ssn);
            //account numbers
            int account = ap.getAccountNumberBySSN(ssn);
            System.out.println(account);
            //ap.getSavingsAccountNumberBySSN(ssn);
            //Check for correctness
//
//            if (ap.getAccountTypeByAccountNumber(account)== -1) {
//                message.setText("This ATM Card Owner, doesn't not have an account." + account);
//                //message.setText("Fieldcheck failed, Please fill all the fields");
//                return;
//            }else{
                
                        if ((this.sourceAccount.getSelectedIndex() == 1) && (ssnindex >= 0)) {
                            account = ap.getSavingsAccountBySSN(ssn).getAccountNum();
                            SavingsAccount sav=ap.getSavingsAccount(account);
                            if(sav.getAccountNum()>-1){
                                sav.Withdrawl(amount);
                                ap.WriteFile();
                                message.setText("Withdraw from Saving Acount, amount " + amount);
                            }else{
                                message.setText("Account does not exist");
                            }
                            return;
                        }
                        if ((this.sourceAccount.getSelectedIndex() == 0) && (ssnindex >= 0)) {
                            account = ap.getCheckingAccountBySSN(ssn).getAccountNum();
                            Checking chk = ap.getCheckingAccount(account);
                            if(chk.getAccountNum()>-1){
                                chk.Withdrawl(amount);
                                ap.WriteFile();
                                message.setText("Withdraw from Checking Acount, amount " + amount);
                            }else{
                                message.setText("Account does not exist");
                            }
                            return;
                        }
                    
        
            
//            }//end else
        }//end if field error
    }//GEN-LAST:event_btnSubmitActionPerformed



    /**
     * @param args the command line arguments
     */

    public void run() {
        ATM_Interface dialog = new ATM_Interface(new javax.swing.JFrame(), true);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        dialog.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amt;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JTextField cardNo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel message;
    private javax.swing.JTextField pin;
    private javax.swing.JComboBox sourceAccount;
    // End of variables declaration//GEN-END:variables
}
