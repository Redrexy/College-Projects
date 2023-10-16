/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject2;
/**
 *
 * @author Arpan Subba and David Newman
 */
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Scanner;

public class Student2 implements ActionListener {
    Scanner in = new Scanner(System.in);
    private static int studentID = 1000;
    private double[] totalSemesterCost = new double[100];
    JFrame frame = new JFrame();
    private double[] total = new double[100];
     
    private String[] gender = {"M", "F"};
    private JLabel fName = new JLabel("First Name");
    private JLabel lName = new JLabel("Last Name");
    private JLabel genderLabel = new JLabel("Gender");
    private JLabel dateOfBirth = new JLabel("Date Of Birth");
    private JLabel address = new JLabel("Address");
    private JLabel phoneN = new JLabel("Phone #");
    private JLabel major = new JLabel("Major");
    private JLabel levelEnroll = new JLabel("Level Of Enrollment");
    private JLabel totalCost = new JLabel("Total Cost Of Semester");
     
    private JTextField fNameTextField = new JTextField();
    private JTextField lNameTextField = new JTextField();
    private JComboBox genderComboBox = new JComboBox(gender);
    private JTextField dateOfBirthTextField = new JTextField();
    private JTextField addressTextField = new JTextField();
    private JTextField phoneTextField = new JTextField();
    private JTextField majorTextField = new JTextField();
    private JTextField levelEnrollTextField = new JTextField();
    private JTextField totalCostTextField = new JTextField();
    private JTextField studentIDTextField = new JTextField();
     
    private JButton addButton = new JButton("Add Student");
    private JButton resetButton = new JButton("Reset");
    
    private String[] showClassesComboBox = {"100 Calculus 2", "211 Physics", "360 Computer Engeering", "3000 Computer Science"};
    private JLabel toAddClasses = new JLabel("Enter student ID and choose option below: ");
    private JLabel enterStudentID = new JLabel("Enter Student ID");
    private JLabel showClasses = new JLabel("Classes:");
    private JComboBox classComboBox = new JComboBox(showClassesComboBox);
    private JButton addClassesButton = new JButton("Add Class");
    //
    private JButton generateInvoiceButton = new JButton("Display Personal Information");
    private JButton totalCostButton = new JButton("Total Cost For Semester");
    //
     
    Student2(){
        totalCostTextField.setText("0");
        window();
        setLabelSizeAndLocation();
        addToFrame();
        actionEvent();
    }
    public static void maxStudent(){
        studentID++;
    }
     
    public void window(){
        frame.setTitle("Student form");
        frame.setBounds(50,50,650,550);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
     
    public void setLabelSizeAndLocation(){         
        fName.setBounds(20,20,100,70);
        lName.setBounds(20,60,100,70);
        genderLabel.setBounds(20,100,100,70);
        dateOfBirth.setBounds(20,140,100,70);
        address.setBounds(20,180,100,70);
        phoneN.setBounds(20,220,100,70);
        major.setBounds(20,260,100,70);
        levelEnroll.setBounds(20,300,150,70);
        totalCost.setBounds(20,340,150,70);
        toAddClasses.setBounds(380,40,250,23);
        enterStudentID.setBounds(380,80,165,23);
        showClasses.setBounds(380, 120, 165,23);
         
        fNameTextField.setBounds(180,43,165,23);
        lNameTextField.setBounds(180,83,165,23);
        genderComboBox.setBounds(180,123,165,23);
        dateOfBirthTextField.setBounds(180,163,165,23);
        addressTextField.setBounds(180,203,165,23);
        phoneTextField.setBounds(180,243,165,23);
        majorTextField.setBounds(180,283,165,23);
        levelEnrollTextField.setBounds(180,323,165,23);
        totalCostTextField.setBounds(180,363,165,23);
        studentIDTextField.setBounds(480,80,100, 23);
        classComboBox.setBounds(440,120,180,23);
                
        addButton.setBounds(70,400,103,35);
        resetButton.setBounds(220,400,100,35);
        addClassesButton.setBounds(380,160,100,35);
        //
        generateInvoiceButton.setBounds(380, 200, 200, 40);
        totalCostButton.setBounds(380, 250, 200, 40);
        //
    }
     
    public void addToFrame(){
        frame.add(fName);
        frame.add(lName);
        frame.add(genderLabel);
        frame.add(dateOfBirth);
        frame.add(address);
        frame.add(phoneN);
        frame.add(major);
        frame.add(levelEnroll);
        frame.add(totalCost);
        frame.add(enterStudentID);
        frame.add(toAddClasses);
        frame.add(showClasses);
        
        frame.add(fNameTextField);
        frame.add(lNameTextField);
        frame.add(genderComboBox);
        frame.add(dateOfBirthTextField);
        frame.add(addressTextField);
        frame.add(phoneTextField);
        frame.add(majorTextField);
        frame.add(levelEnrollTextField);
        frame.add(totalCostTextField);
        frame.add(studentIDTextField);
        frame.add(classComboBox);
        
        frame.add(addButton);
        frame.add(resetButton);
        frame.add(addClassesButton);
        frame.add(generateInvoiceButton);
        frame.add(totalCostButton);
    }
    
    public void actionEvent(){
        addButton.addActionListener(this);
        resetButton.addActionListener(this);
        addClassesButton.addActionListener(this);
        generateInvoiceButton.addActionListener(this);
        totalCostButton.addActionListener(this);
    }
    private boolean[] classTaken = {false, false, false, false};
    @Override
    public void actionPerformed(ActionEvent a){
        if(a.getSource() == addButton)
        {
            try {
                maxStudent();
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject?zeroDateTimeBehavior=convertToNull","root","arpan");
                PreparedStatement Pstatement = connection.prepareStatement("INSERT INTO student VALUES(?,?,?,?,?,?,?,?,?,?);");
                Pstatement.setInt(1, studentID);
                Pstatement.setString(2,fNameTextField.getText());
                Pstatement.setString(3,lNameTextField.getText());
                Pstatement.setString(4,genderComboBox.getSelectedItem().toString());
                Pstatement.setString(5,dateOfBirthTextField.getText());
                Pstatement.setString(6,addressTextField.getText());
                Pstatement.setString(7,phoneTextField.getText());
                Pstatement.setString(8,majorTextField.getText());
                Pstatement.setString(9,levelEnrollTextField.getText());
                Pstatement.setString(10,totalCostTextField.getText());

                Pstatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Registered Successfully" + "\nYour Student ID: " + studentID);
            } 
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
   
        if(a.getSource()==resetButton) {
        fNameTextField.setText("");
        lNameTextField.setText("");
        genderComboBox.setSelectedItem("M");
        dateOfBirthTextField.setText("");
        addressTextField.setText("");
        phoneTextField.setText("");
        majorTextField.setText("");
        levelEnrollTextField.setText("");
        totalCostTextField.setText("0");
        }
        
        if(a.getSource() == generateInvoiceButton){
        try{
            String ID = studentIDTextField.getText();
            int stuID = Integer.parseInt(ID);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject?zeroDateTimeBehavior=convertToNull","root","arpan");
        PreparedStatement st = connection.prepareStatement("SELECT * FROM Student WHERE student_ID = ?");
        st.setInt(1, stuID);
        ResultSet r1 = st.executeQuery();
        
        while(r1.next()){
        String fn = r1.getString("First_Name");
        String ln = r1.getString("Last_Name");
        String gender = r1.getString("Gender");
        String birth = r1.getString("Date_Of_Birth");
        String addy = r1.getString("Address");
        String pn = r1.getString("Phone");
        String major = r1.getString("Major");
        String level = r1.getString("Level_Of_Enroll");
        String tCost = r1.getString("Total_Cost_Semester");
        
        JOptionPane.showMessageDialog(null, "First Name: " + fn + "\nLast Name: " + ln + "\nGender: " + gender
        + "\nDate Of Birth: " + birth + "\nAddress: " + addy + "\nPhone Number: " + pn + "\nMajor: " + major
        + "\nLevel Of Enrollment: " + level + "\nTotal Cost: $" + tCost, "Student Information" , JOptionPane.INFORMATION_MESSAGE);
        //JLabel toAddClasses = new JLabel("To add classes put in your student ID");
            }
        }
        catch (SQLException e1) {
            e1.printStackTrace();
          }
        
        }
        
        if(a.getSource() == addClassesButton){
            try{
                String ID = studentIDTextField.getText();
                int stuID = Integer.parseInt(ID);
                int class_ID = 0;
                
                String chosenClass = (String) classComboBox.getSelectedItem();
                if (chosenClass == "100 Calculus 2"){
                    if (classTaken[0] == false){
                        class_ID = 100;
                        classTaken[0] = true;
                    }     
                }
                else if (chosenClass == "211 Physics"){
                    if (classTaken[1] == false){
                        class_ID = 211;
                        classTaken[1] = true;
                    }
                }
                else if (chosenClass == "360 Computer Engeering"){
                    if (classTaken[2] == false){
                        class_ID = 360;
                        classTaken[2] = true;
                    }
                }
                else if (chosenClass == "3000 Computer Science"){
                    if (classTaken[3] == false){
                        class_ID = 3000;
                        classTaken[3] = true;
                    }
                }
                
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject?zeroDateTimeBehavior=convertToNull","root","arpan");
                PreparedStatement Pstatement = connection.prepareStatement("INSERT INTO student_financial_responsibilities VALUES (?,?,?);");
                Pstatement.setInt(1, stuID);
                Pstatement.setString(2,"Fall");
                Pstatement.setInt(3, class_ID);
                Pstatement.executeUpdate();
                
                Pstatement = connection.prepareStatement("SELECT * FROM student_financial_responsibilities WHERE student_ID = ?");
                Pstatement.setInt(1, stuID);
                ResultSet result = Pstatement.executeQuery();
                
                int courseID = 0;
                while (result.next()){
                    courseID = result.getInt("Course_ID");
                }

                Pstatement = connection.prepareStatement("SELECT * FROM university_course_catalog WHERE Course_ID = ?");
                Pstatement.setInt(1, courseID);
                ResultSet result1 = Pstatement.executeQuery();
                //ResultSetMetaData metaData = result.getMetaData();  
        
                double costPerCredit = 0;
                int credits = 0;
                int totalArray = stuID - 1001;
                while (result1.next()){
                    credits = result1.getInt("Credits");
                    costPerCredit = result1.getDouble("Cost_Per_Credit");
                    total[totalArray] = total[totalArray] + (credits * costPerCredit);
                }
                
                Pstatement = connection.prepareStatement("UPDATE Student SET Total_Cost_Semester = ? WHERE Student_ID = ?");
                Pstatement.setDouble(1, total[totalArray]);
                Pstatement.setDouble(2, stuID);
                
                Pstatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Class Added!");               
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }            
        }
    
        if(a.getSource() == totalCostButton){
            try {
            String ID = studentIDTextField.getText();
            int stuID = Integer.parseInt(ID);               
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject?zeroDateTimeBehavior=convertToNull","root","arpan");
            PreparedStatement Pstatement = connection.prepareStatement("SELECT * FROM Student WHERE student_ID = ?");
            Pstatement.setInt(1, stuID);
            ResultSet result = Pstatement.executeQuery();
            
            while (result.next()){
                double total = result.getDouble("Total_Cost_Semester");
                JOptionPane.showMessageDialog(null, "Student ID: " + stuID + "\nTotal Cost: $" + total, "Invoice", JOptionPane.INFORMATION_MESSAGE);
            }     
            
            }
            
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
} 