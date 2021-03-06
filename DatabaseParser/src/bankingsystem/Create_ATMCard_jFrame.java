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
public class Create_ATMCard_jFrame extends javax.swing.JDialog {

    // End of variables declaration

        

    //-------------------------------------------------------------
    //          Constructor
    //-------------------------------------------------------------
    
    /** Creates new form Create_Account_jFrame_Form */
    public Create_ATMCard_jFrame(java.awt.Frame parent, boolean modal) {
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

        cardNo = new javax.swing.JFormattedTextField();
        pin = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        message = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        ssn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cardNo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("############"))));

        jLabel9.setText("PIN");

        message.setText("Result:");

        jLabel8.setText("Card No");

        jLabel3.setText("SSN");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Apple Braille", 1, 14)); // NOI18N
        jLabel2.setText("Create ATM Card Interface.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(11, 11, 11))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cardNo)
                            .addComponent(ssn, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cardNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ssn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(32, 32, 32)
                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnCancel))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        //Inputs check
        int emptycount = 0;
        if(ssn.getText().isEmpty()){
            message.setText("Please Fill all fields");
            emptycount++;
            return;
        }
        if(cardNo.getText().isEmpty()){
            message.setText("Please Fill all fields");
            emptycount++;
            return;
        }
        if(pin.getText().isEmpty()){
            message.setText("Please Fill all fields");
            emptycount++;
            return;
        }
        //end Inputs check
        
        int sn = Integer.parseInt(ssn.getText());
        //System.out.println("in btnCreateActionPerformed, ssn.getText = "+ssn.getText());
        
        //if there is no error, starting Search for SSN in user.xml
        if(emptycount==0){
        UserParser up = new UserParser("");
        int userExist = up.getIndexFromSSN(sn);//check if SSN is already in the user.xml. -1 means no match
        
        if(userExist==-1){
            //if SSN not found in user.xml then open creating screen to create a new user.
            Create_User_jFrame_Form2 user = new Create_User_jFrame_Form2(new javax.swing.JFrame(), true, sn);
            System.out.println("in Form2, sn = " + sn);
            message.setText("SSN not found match in user.xml");
            user.run(sn);
        }else{
            //if SSN found in user.xml, create this Account
            this.createAccountAfterCreateUser();
        }
        }else{
            message.setText("Please check your fields.");
        }
        
    }//GEN-LAST:event_btnCreateActionPerformed

    public void createAccountAfterCreateUser(){//Method for creating account

        //input check
        int emptycount = 0;
        if(ssn.getText().isEmpty()){
            message.setText("Please Fill all fields");
            emptycount++;
            return;
        }
        if(cardNo.getText().isEmpty()){
            message.setText("Please Fill all fields");
            emptycount++;
            return;
        }
        if(pin.getText().isEmpty()){
            message.setText("Please Fill all fields");
            emptycount++;
            return;
        }
        
        //if all field are filled, then keep going
        if(emptycount==0){
        int sn = Integer.parseInt(ssn.getText());
        int cardnumber = Integer.parseInt(cardNo.getText());
        int pinnummber = Integer.parseInt(pin.getText());

        //a safe check before creating. Make sure all inputs are not null.

        UserParser up = new UserParser("");
        //further check, make sure user has checking account or saving account.
        
        String createAtmMsg = up.RegisterUserForATM(sn, cardnumber, pinnummber);
        message.setText(createAtmMsg);
        }else{
            message.setText("Please fill all fields.");
        }
  

    }//end method createAccountAfterCreateUser()
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Create_ATMCard_jFrame dialog = new Create_ATMCard_jFrame(new javax.swing.JFrame(), true);
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
                Create_ATMCard_jFrame dialog = new Create_ATMCard_jFrame(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreate;
    private javax.swing.JFormattedTextField cardNo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel message;
    private javax.swing.JTextField pin;
    private javax.swing.JTextField ssn;
    // End of variables declaration//GEN-END:variables

}
