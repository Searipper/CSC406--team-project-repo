/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Landing_Page_jFrame_Form.java
 *
 * Created on Nov 6, 2012, 2:16:46 AM
 */

package bankingsystem;



/**
 *
 * @author Arron
 */
public class Landing_Page_jFrame_Teller extends javax.swing.JFrame {

          
	
    //-------------------------------------------------------------
    //          Constructor
    //-------------------------------------------------------------
    /** Creates new form Landing_Page_jFrame_Form */
    public Landing_Page_jFrame_Teller() {
        initComponents();
        setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);


    }
    
    //-------------------------------------------------------------
    //          Methods
    //-------------------------------------------------------------

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        accountNum = new javax.swing.JFormattedTextField();
        btnStopPayment = new javax.swing.JButton();
        message2 = new javax.swing.JLabel();
        btnCloseAccount = new javax.swing.JButton();
        btnWithdraw = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        createAcct = new javax.swing.JButton();
        btnLookupAccount = new javax.swing.JButton();
        btnTransfer = new javax.swing.JButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jTextField1 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        accountNumber = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        bill = new javax.swing.JTextField();
        rate = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        fname = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        op = new javax.swing.JTextField();
        lname = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        transactionHistory2 = new java.awt.TextArea();
        jTextField5 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        balance = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        accountType = new javax.swing.JTextField();
        ssn = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuOpen = new javax.swing.JMenu();
        menuLogin = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        menuExit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        accountNum.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("############"))));
        accountNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountNumActionPerformed(evt);
            }
        });
        accountNum.setBounds(20, 10, 131, 20);
        jLayeredPane1.add(accountNum, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnStopPayment.setText("Stop Payment");
        btnStopPayment.setMaximumSize(new java.awt.Dimension(150, 50));
        btnStopPayment.setMinimumSize(new java.awt.Dimension(150, 50));
        btnStopPayment.setPreferredSize(new java.awt.Dimension(150, 50));
        btnStopPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopPaymentActionPerformed(evt);
            }
        });
        btnStopPayment.setBounds(180, 190, 150, 50);
        jLayeredPane1.add(btnStopPayment, javax.swing.JLayeredPane.DEFAULT_LAYER);

        message2.setText("Enter account number to search");
        message2.setBounds(20, 40, 350, 14);
        jLayeredPane1.add(message2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnCloseAccount.setText("Close Account");
        btnCloseAccount.setMaximumSize(new java.awt.Dimension(150, 50));
        btnCloseAccount.setMinimumSize(new java.awt.Dimension(150, 50));
        btnCloseAccount.setPreferredSize(new java.awt.Dimension(150, 50));
        btnCloseAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseAccountActionPerformed(evt);
            }
        });
        btnCloseAccount.setBounds(180, 70, 150, 50);
        jLayeredPane1.add(btnCloseAccount, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnWithdraw.setText("Withdraw");
        btnWithdraw.setMaximumSize(new java.awt.Dimension(150, 50));
        btnWithdraw.setMinimumSize(new java.awt.Dimension(150, 50));
        btnWithdraw.setPreferredSize(new java.awt.Dimension(150, 50));
        btnWithdraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWithdrawActionPerformed(evt);
            }
        });
        btnWithdraw.setBounds(180, 130, 150, 50);
        jLayeredPane1.add(btnWithdraw, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton9.setText("Deposit");
        jButton9.setMaximumSize(new java.awt.Dimension(150, 50));
        jButton9.setMinimumSize(new java.awt.Dimension(150, 50));
        jButton9.setPreferredSize(new java.awt.Dimension(150, 50));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jButton9.setBounds(20, 130, 150, 50);
        jLayeredPane1.add(jButton9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        createAcct.setText("Create Account");
        createAcct.setMaximumSize(new java.awt.Dimension(150, 50));
        createAcct.setMinimumSize(new java.awt.Dimension(150, 50));
        createAcct.setPreferredSize(new java.awt.Dimension(150, 50));
        createAcct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAcctActionPerformed(evt);
            }
        });
        createAcct.setBounds(20, 70, 150, 50);
        jLayeredPane1.add(createAcct, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnLookupAccount.setText("Look up Account");
        btnLookupAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLookupAccountActionPerformed(evt);
            }
        });
        btnLookupAccount.setBounds(170, 10, 124, 23);
        jLayeredPane1.add(btnLookupAccount, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnTransfer.setText("Transfer");
        btnTransfer.setMaximumSize(new java.awt.Dimension(150, 50));
        btnTransfer.setMinimumSize(new java.awt.Dimension(150, 50));
        btnTransfer.setPreferredSize(new java.awt.Dimension(150, 50));
        btnTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferActionPerformed(evt);
            }
        });
        btnTransfer.setBounds(20, 190, 150, 50);
        jLayeredPane1.add(btnTransfer, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField1.setText("Account Number");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.setBounds(20, 60, 120, 20);
        jLayeredPane2.add(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField16.setText("Overdraft Protection");
        jTextField16.setBounds(20, 180, 120, 20);
        jLayeredPane2.add(jTextField16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        accountNumber.setBounds(150, 60, 206, 20);
        jLayeredPane2.add(accountNumber, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField3.setText("SSN");
        jTextField3.setBounds(20, 90, 120, 20);
        jLayeredPane2.add(jTextField3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bill.setBounds(150, 210, 206, 20);
        jLayeredPane2.add(bill, javax.swing.JLayeredPane.DEFAULT_LAYER);
        rate.setBounds(150, 240, 206, 20);
        jLayeredPane2.add(rate, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField11.setText("Bill of the Account");
        jTextField11.setBounds(20, 210, 120, 20);
        jLayeredPane2.add(jTextField11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        fname.setBounds(150, 0, 206, 20);
        jLayeredPane2.add(fname, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField14.setText("Last Name");
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jTextField14.setBounds(20, 30, 120, 20);
        jLayeredPane2.add(jTextField14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        op.setBounds(150, 180, 206, 20);
        jLayeredPane2.add(op, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lname.setBounds(150, 30, 206, 20);
        jLayeredPane2.add(lname, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField12.setText("First Name");
        jTextField12.setBounds(20, 0, 120, 20);
        jLayeredPane2.add(jTextField12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        transactionHistory2.setBounds(20, 290, 340, 160);
        jLayeredPane2.add(transactionHistory2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField5.setText("Balance");
        jTextField5.setBounds(20, 120, 120, 20);
        jLayeredPane2.add(jTextField5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setText("Transaction History");
        jLabel1.setBounds(20, 270, 150, 14);
        jLayeredPane2.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        balance.setBounds(150, 120, 206, 20);
        jLayeredPane2.add(balance, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField17.setText("Interest Rate");
        jTextField17.setBounds(20, 240, 120, 20);
        jLayeredPane2.add(jTextField17, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField7.setText("Account Type");
        jTextField7.setBounds(20, 150, 120, 20);
        jLayeredPane2.add(jTextField7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        accountType.setBounds(150, 150, 206, 20);
        jLayeredPane2.add(accountType, javax.swing.JLayeredPane.DEFAULT_LAYER);
        ssn.setBounds(150, 90, 206, 20);
        jLayeredPane2.add(ssn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenu1.setText("File");

        menuOpen.setText("Open");

        menuLogin.setText("Login");
        menuLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLoginActionPerformed(evt);
            }
        });
        menuOpen.add(menuLogin);

        jMenu1.add(menuOpen);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu4.setText("Help");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Exit");

        menuExit.setText("Exit");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        jMenu5.add(menuExit);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                        .addGap(211, 211, 211))
                    .addComponent(jLayeredPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferActionPerformed
        transfer_JDialog transfer = new transfer_JDialog(this,true);
        transfer.run();
    }//GEN-LAST:event_btnTransferActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Deposit_JDialog deposit = new Deposit_JDialog(this,true);
        deposit.run();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void createAcctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAcctActionPerformed
        Create_Account_jFrame_Form create = new Create_Account_jFrame_Form(this,true);
        create.run();
    }//GEN-LAST:event_createAcctActionPerformed

    private void btnCloseAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseAccountActionPerformed
        Close_Accout_page_JDialog close = new Close_Accout_page_JDialog(this,true);
        close.run();
    }//GEN-LAST:event_btnCloseAccountActionPerformed

    private void btnWithdrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWithdrawActionPerformed
        Withdraw_JDialog wd = new Withdraw_JDialog(this,true);
        wd.run();
    }//GEN-LAST:event_btnWithdrawActionPerformed

    private void btnStopPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopPaymentActionPerformed
        //Apply stop payment function
        Stop_Payment stop = new Stop_Payment(this, true);
        stop.run();    
    }//GEN-LAST:event_btnStopPaymentActionPerformed

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuExitActionPerformed

    private void menuLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoginActionPerformed
        Login_Jframe_Form login = new Login_Jframe_Form();
        login.run();
    }//GEN-LAST:event_menuLoginActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnLookupAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLookupAccountActionPerformed
        UserParser up = new UserParser("");
        AccountParser ap = new AccountParser("");
        
        //input information requested from xml
        int acctNum = Integer.parseInt(accountNum.getText());//from lookup field input
        int ssn1 = ap.getAccount(acctNum).getCustomerID();//get CustomerID from Account Object, -1 = no account
        
        //Search in user.xml for ssn,-1 = not match in user.xml

        if(up.getIndexFromSSN(ssn1)==-1){
            System.out.println("SSN did found match in user.xml");
        }else{System.out.println("SSN found match in user.xml, SSN = " + ssn1 + " at index = " + up.getIndexFromSSN(ssn1));}


        System.out.println("ssn1 = " + ssn1);//no this SSN found in user.xml
        if(ssn1==-1){
            //
            message2.setText("No Customer Found in Accounts.xml, CustomerID = " + ssn1);
            fname.setText("");
            lname.setText("");
            balance.setText("");
            accountNumber.setText("");
            ssn.setText("");
            accountType.setText("");
            op.setText("");
            bill.setText("");
            rate.setText("");
            transactionHistory2.setText("No Record available");
        }else{
            message2.setText("Found in Accounts.xml, but not in user.xml");
            double balance1 = ap.getAccount(acctNum).checkBalance();
            String balance2 = String.valueOf(balance1);//convert int to string
            String acctNum1 = String.valueOf(acctNum);//convert int to string
            int acctType = ap.getAccount(acctNum).getAccountType();
            int overprotection;
            String odp;
            
            //bill, cc and loans
            String billstr = "";
            if(ap.getAccountTypeByAccountNumber(acctNum)==7){
                /*cc*/
                CreditCards cc = ap.getCreditCardAccount(acctNum);
                billstr = String.valueOf(cc.getBillamount());
            }
            if(ap.getAccountTypeByAccountNumber(acctNum)==5||ap.getAccountTypeByAccountNumber(acctNum)==6){
                // Loan of short and long
               
                LoanAccounts loan = ap.getLoanAccount(acctNum);
                billstr = String.valueOf(loan.getBillamount());
            }
            if(ap.getAccountTypeByAccountNumber(acctNum)==1){bill.setText("Saving, N/A");}
            if(ap.getAccountTypeByAccountNumber(acctNum)==2){bill.setText("CD, N/A");}
            if(ap.getAccountTypeByAccountNumber(acctNum)==3){bill.setText("TMB, N/A");}
            if(ap.getAccountTypeByAccountNumber(acctNum)==4){bill.setText("G/D, N/A");}
            // bill done


            if(acctType==3||acctType==4){
                //checking account
                overprotection = ap.getCheckingAccount(acctNum).getProtectingAcc();
                odp = String.valueOf(overprotection);
                op.setText(odp);
            }else{
                op.setText("This is not a checking account, so no OverProtection.");
            }
            
            //Transaction History
            String history = ap.getAccount(acctNum).getTransactionHistory();
            System.out.println("~~~~~~history");
            System.out.println(history); 
            
            String acctType1;//convert int to string
            /*
            * <li>1=SimpleSavings</li>
            * <li>2=CDs</li>
            * <li>3=TMB</li>
            * <li>4=G/D</li>
            * <li>5=LLoans</li>
            * <li>6=SLoans</li>
            * <li>7=CreditCards</li>
            * */
            switch(acctType){
                case 1: acctType1 = "SimpleSaving";
                    break;
                case 2: acctType1 = "CD";
                    break;
                case 3: acctType1 = "TMB";
                    break;
                case 4: acctType1 = "G/D";
                    break;
                case 5: acctType1 = "LLoans";
                    break;
                case 6: acctType1 = "SLoans";
                    break;
                case 7: acctType1 = "CreditCards";
                    break;
                default: acctType1 = "Unknown Type,in default. Check for acctType error";
            }
            
            String ssn2 = String.valueOf(ssn1);

            //out put account information
            balance.setText(balance2);
            accountNumber.setText(acctNum1);
            ssn.setText(ssn2);
            accountType.setText(acctType1);
            bill.setText(billstr);
            transactionHistory2.setText(history);
            //further check in user.xml
            if(up.getIndexFromSSN(ssn1)>-1){
                message2.setText("Found in Accounts.xml, and user.xml. CustomerID(ssn) = " + ssn1);
                
                String fname1 = up.getFName(up.getIndexFromSSN(ssn1));//if ssn1=-1,this will be outofbound
                String lname1 = up.getLName(up.getIndexFromSSN(ssn1));//if ssn1=-1,this will be outofbound

                //output informations when found match in user.xml
                fname.setText(fname1);
                lname.setText(lname1);
            }//end user.xml part
        }
        
    }//GEN-LAST:event_btnLookupAccountActionPerformed

    private void accountNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountNumActionPerformed
        this.btnLookupAccountActionPerformed(evt);
        
    }//GEN-LAST:event_accountNumActionPerformed

    /**
    * @param args the command line arguments
    */
    public void run() {
                new Landing_Page_jFrame_Teller().setVisible(true);
            }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Landing_Page_jFrame_Teller().setVisible(true);
            }
        });
    }//end main args

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField accountNum;
    private javax.swing.JTextField accountNumber;
    private javax.swing.JTextField accountType;
    private javax.swing.JTextField balance;
    private javax.swing.JTextField bill;
    private javax.swing.JButton btnCloseAccount;
    private javax.swing.JButton btnLookupAccount;
    private javax.swing.JButton btnStopPayment;
    private javax.swing.JButton btnTransfer;
    private javax.swing.JButton btnWithdraw;
    private javax.swing.JButton createAcct;
    private javax.swing.JTextField fname;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField lname;
    private javax.swing.JMenu menuExit;
    private javax.swing.JMenuItem menuLogin;
    private javax.swing.JMenu menuOpen;
    private javax.swing.JLabel message2;
    private javax.swing.JTextField op;
    private javax.swing.JTextField rate;
    private javax.swing.JTextField ssn;
    private java.awt.TextArea transactionHistory2;
    // End of variables declaration//GEN-END:variables

}
