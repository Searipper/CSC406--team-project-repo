package bankingsystem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Account_Checking extends Account{
    int checkingAccountType;//1-Gold,2-Diamond,3-TMB
    String backupAccount;
    int monthOD;//overdrafts of this month
    //String today;
    long date;
    
    public Account_Checking(String uid, int cat,Date date){
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.date = date.getTime();
        //today = formatter.format(date);
        this.balance=0.00;
    }

    public double checkBalance() {
        return balance;
    }


    public double updateBalance() {
        return balance;
    }

    @Override
    public void setBalance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getBalance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setUserID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getUserID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setAccountFlag() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getAccountFlag() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setAccountType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getAccountType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int setAccountNum() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void getAccountNum() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCreatedDate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Date getCreatedDate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
