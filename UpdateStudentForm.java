package com.jframes;

import StudentDaoImpl.StudentDaoImpl;
import com.beans.Student;
import com.dao.StudentDao;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MUKHTIAR ALI CHANDIO
 */
public class UpdateStudentForm extends javax.swing.JFrame {

     StudentDao stdDao=new StudentDaoImpl();
     
       Student std=new Student();
    

    
     //showDataTable table;
    DefaultTableModel model;
 
    public UpdateStudentForm() {
        initComponents();
    }

    public void loadGenderData(int studentId) {
     
        StudentDao stdDao=new StudentDaoImpl();
    String gender = stdDao.getGenderFromDatabase(studentId);

      int stdId = Integer.parseInt(StudentIDTextField.getText());

        if (gender.equals("Male")) {
            maleRadioButton.setSelected(true);
        } else if (gender.equals("Female")) {
            femaleRadioButton.setSelected(true);
        }
    
    
}
    
    public void clearInputfields(){
        
        this.StudentIDTextField.setText("");
        this.firstNameTextField.setText("");
        this.dateofbirthTextField.setText("");
        this.fathernameTextField.setText("");
        this.lastNameTextField.setText("");
        this.surnameTextField.setText("");
        this.cnicTextField.setText("");
        this.surnameTextField.setText("");
        this.contactNumberTextField.setText("");
        this.emailIdTextField.setText("");
        this.addressTextArea.setText("");
        
        
    }
   
    
    
  public void getStudentdataByid() {

    StudentDao daoImpl = new StudentDaoImpl();

    // ✅ These should already be declared as your Swing components (not locally created)
    // JComboBox<String> provinceComboBox;
    // JComboBox<String> countryComboBox;

    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(new String[]{
        "ID", "First Name", "Last Name", "FatherName", "DateOfBirth", "Surname",
        "CNIC", "Contact No", "Email", "Gender", "Hobbies", "Province", "Country", "Address"
    });

    // ✅ Get all students from database
    List<Student> students = daoImpl.getAllStudents();

    // ✅ Clear JComboBoxes before adding items (avoid duplicates)
    provinceComboBox.removeAllItems();
    countryComboBox.removeAllItems();

    // ✅ Add unique provinces and countries while populating table
    Set<String> provinces = new HashSet<>();
    Set<String> countries = new HashSet<>();

    for (Student s : students) {
        model.addRow(new Object[]{
            s.getId(), s.getFirstName(), s.getLastName(), s.getFatherName(),
            s.getDateOfbirth(), s.getSurname(), s.getCnic(), s.getContactNo(),
            s.getEmailId(), s.getGender(), s.getHobbies(),
            s.getProvince(), s.getCountry(), s.getAddress()
        });

        // Collect province/country for dropdowns
        if (s.getProvince() != null && !s.getProvince().trim().isEmpty()) {
            provinces.add(s.getProvince().trim());
        }
        if (s.getCountry() != null && !s.getCountry().trim().isEmpty()) {
            countries.add(s.getCountry().trim());
        }
    }

      // ✅ Add unique province & country values to JComboBoxes
      provinceComboBox.removeAllItems();
      countryComboBox.removeAllItems();

      for(String p : provinces) {
        provinceComboBox.addItem(p);
      }
      for(String c : countries) {
       countryComboBox.addItem(c);
      }

    // ✅ Add mouse listener for row selection
        showDataTable.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            int selectedRow = showDataTable.getSelectedRow();

            if (selectedRow != -1) {
                Object id = showDataTable.getValueAt(selectedRow, 0);
                Object firstName = showDataTable.getValueAt(selectedRow, 1);
                Object lastName = showDataTable.getValueAt(selectedRow, 2);
                Object fatherName = showDataTable.getValueAt(selectedRow, 3);
                Object dateOfbirth = showDataTable.getValueAt(selectedRow, 4);
                Object surname = showDataTable.getValueAt(selectedRow, 5);
                Object cnic = showDataTable.getValueAt(selectedRow, 6);
                Object contactNo = showDataTable.getValueAt(selectedRow, 7);
                Object email = showDataTable.getValueAt(selectedRow, 8);
                Object gender = showDataTable.getValueAt(selectedRow, 9);
                Object hobbiesObj = showDataTable.getValueAt(selectedRow, 10);
                Object provinceObj = showDataTable.getValueAt(selectedRow, 11);
                Object countryObj = showDataTable.getValueAt(selectedRow, 12);
                Object address = showDataTable.getValueAt(selectedRow, 13);

                // ✅ Fill text fields
                StudentIDTextField.setText(String.valueOf(id));
                firstNameTextField.setText(String.valueOf(firstName));
                lastNameTextField.setText(String.valueOf(lastName));
                fathernameTextField.setText(String.valueOf(fatherName));
                dateofbirthTextField.setText(String.valueOf(dateOfbirth));
                surnameTextField.setText(String.valueOf(surname));
                cnicTextField.setText(String.valueOf(cnic));
                contactNumberTextField.setText(String.valueOf(contactNo));
                emailIdTextField.setText(String.valueOf(email));
                addressTextArea.setText(String.valueOf(address));

                // ✅ Handle gender
                maleRadioButton.setSelected(false);
                femaleRadioButton.setSelected(false);
                if (gender != null) {
                    String gen = gender.toString().trim().toLowerCase();
                    //JOptionPane.showMessageDialog(null,"Gender"+gen);
                    if (gen.equals("male")) maleRadioButton.setSelected(true);
                    else if (gen.equals("female")) femaleRadioButton.setSelected(true);
                }

                // ✅ Handle hobbies
                writingCheckbox.setSelected(false);
                readingCheckBox.setSelected(false);
                paintingCheckbox.setSelected(false);
                singingBox.setSelected(false);
                musicCheckbox.setSelected(false);

                if (hobbiesObj != null) {
                    String hobbies = hobbiesObj.toString()
                        .replace("[", "")
                        .replace("]", "")
                        .trim()
                        .toLowerCase();
                    String[] hobbyList = hobbies.split("[,;\\s]+");
                    for (String h : hobbyList) {
                        h = h.trim();
                        if (h.equals("writing")) writingCheckbox.setSelected(true);
                        else if (h.equals("reading")) readingCheckBox.setSelected(true);
                        else if (h.equals("painting")) paintingCheckbox.setSelected(true);
                        else if (h.equals("singing")) singingBox.setSelected(true);
                        else if (h.equals("music")) musicCheckbox.setSelected(true);
                    }
                }

                // ✅ Handle Province ComboBox
                if (provinceObj != null) {
                 provinceComboBox.setSelectedItem(provinceObj.toString().trim());
                }
                if (countryObj != null) {
                countryComboBox.setSelectedItem(countryObj.toString().trim());
                }

            }
        }
    });
}

    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StudentPicPanelOne = new javax.swing.JPanel();
        StudentsLabel = new javax.swing.JLabel();
        stdIDtextField = new javax.swing.JPanel();
        StudentRegisteratinLabel = new javax.swing.JLabel();
        FirstNameLabel = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        lastNameTextField = new javax.swing.JTextField();
        genderLabel = new javax.swing.JLabel();
        fatherNameLabel = new javax.swing.JLabel();
        fathernameTextField = new javax.swing.JTextField();
        MotherNameLabel = new javax.swing.JLabel();
        emailIdTextField = new javax.swing.JTextField();
        cnicLabel = new javax.swing.JLabel();
        cnicTextField = new javax.swing.JTextField();
        cityLabel = new javax.swing.JLabel();
        provinceLabel = new javax.swing.JLabel();
        provinceComboBox = new javax.swing.JComboBox();
        countryLabel = new javax.swing.JLabel();
        countryComboBox = new javax.swing.JComboBox();
        dateofregisterLabel = new javax.swing.JLabel();
        dateofbirthTextField = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        surnameTextField = new javax.swing.JTextField();
        contactNumberTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addressTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        showDataTable = new javax.swing.JTable();
        StudentLabel = new javax.swing.JLabel();
        StudentIDTextField = new javax.swing.JTextField();
        maleRadioButton = new javax.swing.JRadioButton();
        femaleRadioButton = new javax.swing.JRadioButton();
        readingCheckBox = new javax.swing.JCheckBox();
        writingCheckbox = new javax.swing.JCheckBox();
        paintingCheckbox = new javax.swing.JCheckBox();
        singingBox = new javax.swing.JCheckBox();
        musicCheckbox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        StudentPicPanelOne.setBackground(new java.awt.Color(0, 153, 153));

        StudentsLabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\MUKHTIAR ALI CHANDIO\\Desktop\\Student Mangent System Pics\\stdspic3.jpg")); // NOI18N

        javax.swing.GroupLayout StudentPicPanelOneLayout = new javax.swing.GroupLayout(StudentPicPanelOne);
        StudentPicPanelOne.setLayout(StudentPicPanelOneLayout);
        StudentPicPanelOneLayout.setHorizontalGroup(
            StudentPicPanelOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentPicPanelOneLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(StudentsLabel)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        StudentPicPanelOneLayout.setVerticalGroup(
            StudentPicPanelOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StudentPicPanelOneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(StudentsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );

        stdIDtextField.setBackground(new java.awt.Color(204, 204, 204));

        StudentRegisteratinLabel.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        StudentRegisteratinLabel.setText("Update  Student  Registeration  Form");

        FirstNameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        FirstNameLabel.setText("First Name");

        firstNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameTextFieldActionPerformed(evt);
            }
        });

        lastNameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lastNameLabel.setText("Last Name");

        lastNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameTextFieldActionPerformed(evt);
            }
        });

        genderLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        genderLabel.setText("Gender");

        fatherNameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fatherNameLabel.setText("Father Name");

        fathernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fathernameTextFieldActionPerformed(evt);
            }
        });

        MotherNameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        MotherNameLabel.setText("email Id");

        cnicLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cnicLabel.setText("Cnic");

        cnicTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnicTextFieldActionPerformed(evt);
            }
        });

        cityLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cityLabel.setText("Contact Number");

        provinceLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        provinceLabel.setText("Province");

        provinceComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provinceComboBoxActionPerformed(evt);
            }
        });

        countryLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        countryLabel.setText("Country");

        dateofregisterLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dateofregisterLabel.setText("Date of Birth");

        dateofbirthTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateofbirthTextFieldActionPerformed(evt);
            }
        });

        updateButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        updateButton.setText("Update Data");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        clearButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        exitButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Surname");

        contactNumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactNumberTextFieldActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Hobbies");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Address");

        addressTextArea.setColumns(20);
        addressTextArea.setRows(5);
        jScrollPane1.setViewportView(addressTextArea);

        showDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        showDataTable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                showDataTableAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane2.setViewportView(showDataTable);

        StudentLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        StudentLabel.setText("Student ID");

        StudentIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentIDTextFieldActionPerformed(evt);
            }
        });

        maleRadioButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        maleRadioButton.setText("Male");

        femaleRadioButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        femaleRadioButton.setText("Female");

        readingCheckBox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        readingCheckBox.setText("Reading");
        readingCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readingCheckBoxActionPerformed(evt);
            }
        });

        writingCheckbox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        writingCheckbox.setText("Writing");

        paintingCheckbox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        paintingCheckbox.setText("painting");

        singingBox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        singingBox.setText("Singing");
        singingBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singingBoxActionPerformed(evt);
            }
        });

        musicCheckbox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        musicCheckbox.setText("Music");
        musicCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musicCheckboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout stdIDtextFieldLayout = new javax.swing.GroupLayout(stdIDtextField);
        stdIDtextField.setLayout(stdIDtextFieldLayout);
        stdIDtextFieldLayout.setHorizontalGroup(
            stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(StudentRegisteratinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(StudentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(StudentIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(FirstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(lastNameLabel)
                        .addGap(17, 17, 17)
                        .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(surnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(fatherNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(fathernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(cnicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(cnicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(cityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(contactNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(MotherNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(emailIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(genderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maleRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(femaleRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(provinceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(provinceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(countryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(countryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(dateofregisterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(dateofbirthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(readingCheckBox)
                                .addGap(18, 18, 18)
                                .addComponent(writingCheckbox)
                                .addGap(30, 30, 30)
                                .addComponent(paintingCheckbox)
                                .addGap(29, 29, 29)
                                .addComponent(singingBox)
                                .addGap(30, 30, 30)
                                .addComponent(musicCheckbox))
                            .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(64, 64, 64)
                                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(81, 81, 81)
                                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        stdIDtextFieldLayout.setVerticalGroup(
            stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(StudentRegisteratinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StudentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StudentIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FirstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameLabel)
                    .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(surnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cnicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fatherNameLabel)
                            .addComponent(fathernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cnicLabel))))
                .addGap(20, 20, 20)
                .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(cityLabel))
                    .addComponent(contactNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MotherNameLabel)
                    .addComponent(emailIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(genderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(maleRadioButton)
                        .addComponent(femaleRadioButton))
                    .addComponent(provinceLabel)
                    .addComponent(provinceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateofregisterLabel)
                    .addComponent(dateofbirthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(readingCheckBox)
                    .addComponent(writingCheckbox)
                    .addComponent(paintingCheckbox)
                    .addComponent(singingBox)
                    .addComponent(musicCheckbox)
                    .addComponent(jLabel9))
                .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124))
                    .addGroup(stdIDtextFieldLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(stdIDtextFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(StudentPicPanelOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stdIDtextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(StudentPicPanelOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(stdIDtextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void firstNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameTextFieldActionPerformed

    private void lastNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameTextFieldActionPerformed

    private void fathernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fathernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fathernameTextFieldActionPerformed

    private void cnicTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnicTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cnicTextFieldActionPerformed

    private void dateofbirthTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateofbirthTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateofbirthTextFieldActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed

            int stdId = Integer.parseInt(StudentIDTextField.getText());
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String fatherName = fathernameTextField.getText();
            String surname = surnameTextField.getText();
            String cnic = cnicTextField.getText();
            String contactNumber = contactNumberTextField.getText();
            String email = emailIdTextField.getText();
            String dateOfbirth = dateofbirthTextField.getText();
            String selectedProvince = provinceComboBox.getSelectedItem().toString();
            String selectedCountry = countryComboBox.getSelectedItem().toString();
            String address = addressTextArea.getText();
            String hobbies = ""; 

            // ✅ FIXED: Determine selected gender
            String gender = "";
            if (maleRadioButton.isSelected()) {
                gender = "Male";
            } else if (femaleRadioButton.isSelected()) {
                gender = "Female";
            } else {
                gender = "Not Selected";
            }
            
            // ✅ Collect selected hobbies


            if (readingCheckBox.isSelected()) {
                hobbies += "Reading, ";
            }
            if (writingCheckbox.isSelected()) {
                hobbies += "Writing, ";
            }
            if (paintingCheckbox.isSelected()) {
                hobbies += "Painting, ";
            }
            if (singingBox.isSelected()) {
                hobbies += "Singing, ";
            }
            if (musicCheckbox.isSelected()) {
                hobbies += "Music, ";
            }

            // ✅ Remove last comma and space, if any
            if (hobbies.endsWith(", ")) {
                hobbies = hobbies.substring(0, hobbies.length() - 2);
            }

            // ✅ If no hobby selected
            if (hobbies.isEmpty()) {
                hobbies = "No hobbies selected";
            }


            // ✅ Display all details in JOptionPane
            /*JOptionPane.showMessageDialog(null,
                "First Name: " + firstName +
                "\nLast Name: " + lastName +
                "\nFather Name: " + fatherName +
                "\nSurname: " + surname +
                "\nCNIC: " + cnic +
                "\nContact Number: " + contactNumber +
                "\nEmail: " + email +
                "\nDate of Birth: " + dateOfbirth +
                "\nProvince: " + selectedProvince +
                "\nCountry: " + selectedCountry +
                "\nAddress: " + address +
                "\nGender: " + gender+"\nHobbies: "+hobbies
            );*/

     
           stdDao.updateStudentdata(stdId, firstName, lastName, surname,  cnic, contactNumber, email, dateOfbirth, selectedProvince, selectedCountry, address, hobbies, gender );

        
  
    
        
    }//GEN-LAST:event_updateButtonActionPerformed

    private void contactNumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactNumberTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactNumberTextFieldActionPerformed

    private void provinceComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provinceComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_provinceComboBoxActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        
        hide();
        
        
    }//GEN-LAST:event_exitButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed

       clearInputfields();

    }//GEN-LAST:event_clearButtonActionPerformed

    private void StudentIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentIDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StudentIDTextFieldActionPerformed

    private void showDataTableAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_showDataTableAncestorMoved

        
        int selectedRow = showDataTable.getSelectedRow();
        if(selectedRow >= 0) {
                     // Get student ID from the first column (index 0)
           int studentId = Integer.parseInt(showDataTable.getValueAt(selectedRow, 0).toString());
           loadGenderData(studentId); // Call method to check radio button
           
           
        }
          getStudentdataByid();
                 
        StudentDao daoImpl = new StudentDaoImpl();
        List<Student> students = daoImpl.getAllStudents();

        DefaultTableModel model = new DefaultTableModel();

             // Add column names
             model.addColumn("ID");
             model.addColumn("First Name");
             model.addColumn("Last Name");
             model.addColumn("Father Name");
             model.addColumn("Date Of Birth");
             model.addColumn("Surname");
             model.addColumn("CNIC");
             model.addColumn("Contact No");
             model.addColumn("Email");

             model.addColumn("Gender");
             model.addColumn("Hobbies");
             model.addColumn("Province");
             model.addColumn("Country");
             model.addColumn("Address");
             // Add rows
             for (Student s : students) {
                 model.addRow(new Object[]{
                     s.getId(),
                     s.getFirstName(),
                     s.getLastName(),
                     s.getFatherName(),
                     s.getDateOfbirth(),
                     s.getSurname(),

                     s.getCnic(),
                     s.getContactNo(),
                     s.getEmailId(),
                     s.getGender(),
                     s.getHobbies(),
                     s.getProvince(),
                     s.getCountry(),
                     s.getAddress(),

                 });
             }

             // Set model to table
             showDataTable.setModel(model);

        
    }//GEN-LAST:event_showDataTableAncestorMoved

    private void readingCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readingCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_readingCheckBoxActionPerformed

    private void singingBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singingBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_singingBoxActionPerformed

    private void musicCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musicCheckboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_musicCheckboxActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateStudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateStudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateStudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateStudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateStudentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FirstNameLabel;
    private javax.swing.JLabel MotherNameLabel;
    private javax.swing.JTextField StudentIDTextField;
    private javax.swing.JLabel StudentLabel;
    private javax.swing.JPanel StudentPicPanelOne;
    private javax.swing.JLabel StudentRegisteratinLabel;
    private javax.swing.JLabel StudentsLabel;
    private javax.swing.JTextArea addressTextArea;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel cnicLabel;
    private javax.swing.JTextField cnicTextField;
    private javax.swing.JTextField contactNumberTextField;
    private javax.swing.JComboBox countryComboBox;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JTextField dateofbirthTextField;
    private javax.swing.JLabel dateofregisterLabel;
    private javax.swing.JTextField emailIdTextField;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel fatherNameLabel;
    private javax.swing.JTextField fathernameTextField;
    private javax.swing.JRadioButton femaleRadioButton;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JRadioButton maleRadioButton;
    private javax.swing.JCheckBox musicCheckbox;
    private javax.swing.JCheckBox paintingCheckbox;
    private javax.swing.JComboBox provinceComboBox;
    private javax.swing.JLabel provinceLabel;
    private javax.swing.JCheckBox readingCheckBox;
    private javax.swing.JTable showDataTable;
    private javax.swing.JCheckBox singingBox;
    private javax.swing.JPanel stdIDtextField;
    private javax.swing.JTextField surnameTextField;
    private javax.swing.JButton updateButton;
    private javax.swing.JCheckBox writingCheckbox;
    // End of variables declaration//GEN-END:variables
}
