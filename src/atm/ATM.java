
package atm;

/*
Class Assignment CMIS 242
Author: Marcus Edwards
September 2016

Simple ATM application functionality. Two sample account objects used.

Dependencies: Account.java, InsufficientFundsException.java

*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ATM 
{
    public static void main(String[] args) 
    {
     
        Account checking = new Account(400.00);
        Account savings = new Account(55000.99);

        Frame main = new Frame(checking, savings);
           
    }//end main                   
}//end class



class Frame extends JFrame{

    //maybe initialize in constructor??    
    //Buttons
    private JButton jbtWithdraw = new JButton("Withdraw");        
    private JButton jbtDeposit = new JButton("Deposit");
    private JButton jbtTransferTo = new JButton("Transfer Funds");
    private JButton jbtBalance = new JButton("Balance");
    //Radio Button
    private JRadioButton jrbChecking = new JRadioButton("Checking", true);
    private JRadioButton jrbSavings = new JRadioButton("Savings", false);
    //txt field
    private JTextField jtfAmount = new JTextField(10);
    
    Account c;
    Account s;
    double d;
    
    
    public Frame(Account c, Account s){
        
        super("ATM");
        
        //reference account objects
        this.c = c;
        this.s = s;
        
        //frame properties
        setSize(300,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(); //create new panel
        
        //****PANEL BUTTONS*****
        panel.add(jbtWithdraw);
        jbtWithdraw.addActionListener(new listener());
    
        panel.add(jbtDeposit);
        jbtDeposit.addActionListener(new listener());
        
        panel.add(jbtTransferTo);
        jbtTransferTo.addActionListener(new listener());
        
        panel.add(jbtBalance);
        jbtBalance.addActionListener(new listener());
        
        
        panel.add(jrbChecking);
        panel.add(jrbSavings);
        
        ButtonGroup group = new ButtonGroup();//radio button group
        group.add(jrbChecking);
        group.add(jrbSavings);
        
       
        
        panel.add(jtfAmount);
        //***************
        
        add(panel);//add panel to frame
        setVisible(true);//make window visible
    }//end Frame 

    private class listener implements ActionListener{
        
        
        public void actionPerformed(ActionEvent e){
           
            if (e.getSource() == jbtWithdraw){ //if withdraw is clicked
                
                try{
                    
                    if (jrbChecking.isSelected()){ //if checking is selected  
                        c.withdraw(jtfAmount.getText()); //pass text field to withdraw method
                        System.out.println("Transactions: " + Account.transactions);                     
                    } else {
                        System.out.println("withdraw from savings" );
                        s.withdraw(jtfAmount.getText()); 
                        System.out.println("Transactions: " + Account.transactions);
                    }
                    
                } catch (InsufficientFundsException err){
                        JOptionPane.showMessageDialog(rootPane, err);
                } catch (NumberFormatException err){
                        JOptionPane.showMessageDialog(rootPane, "Please enter a numeric value");
                }
                
            } else if (e.getSource() == jbtDeposit){
                
                try{

                    if (jrbChecking.isSelected()){
                        c.deposit(jtfAmount.getText());
                    } else {
                        s.deposit(jtfAmount.getText());
                    }
                } catch (NumberFormatException err){
                    JOptionPane.showMessageDialog(rootPane, "Please enter a numeric value");
                }
                            
            } else if (e.getSource() == jbtBalance){
                    if (jrbChecking.isSelected()){
                        c.showBalance();
                    } else {
                        s.showBalance();
                    }
            } else if (e.getSource() == jbtTransferTo){

                try{
                    
                    if (jrbChecking.isSelected()){
                        Account.transfer(jtfAmount.getText(), c, s);
                    } else {
                        Account.transfer(jtfAmount.getText(), s, c);
                    }
                    
                } catch (InsufficientFundsException err){
                    JOptionPane.showMessageDialog(rootPane, "Insufficient funds to complete transfer");
                } catch (NumberFormatException err){
                        JOptionPane.showMessageDialog(rootPane, "Please enter a numeric value");
                }
            }                
        }//end action
    }//end listener
}//end Frame



