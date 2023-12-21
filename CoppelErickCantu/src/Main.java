import bean.Employee;
import bean.EmployeeDelivery;
import bean.Role;
import db.Database;
import db.report.MonthTotals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    private JTabbedPane tabbedPane1;
    private JButton createSaveButton;
    private JButton limpiarButton;
    private JTextField textFieldCreateEmployeId;
    private JTextField textFieldCreateName;
    private JTextField captureName;
    private JTextField captureDeliverys;
    private JButton captureSave;
    private JButton captureClean;
    private JComboBox createRolCombo;
    private JTabbedPane tabbedPane2;
    private JTextField searchEmployee;
    private JTextField searchName;
    private JTextField searchRole;
    private JButton reportGenerateButton;
    private JButton reportCleanButton;
    private JButton createCleanButton;
    private JButton searchCleanButton;
    private JButton searchButton;
    private JButton consultarButton;
    private JTextField captureEmployee;
    private JTextField captureRole;
    private JComboBox captureMonth;
    private JComboBox captureYear;
    private JLabel workingHours;
    private JLabel totalAmountDeliverys;
    private JLabel totalAmountVouchers;
    private JLabel taxes;
    private JLabel vouchers;
    private JLabel total;
    private JTextField reportIdEmployee;
    private JLabel labelReportIdEmployee;
    private JComboBox reportMonth;
    private JComboBox reportYear;

    public JTabbedPane getTabbedPane1() {
        return tabbedPane1;
    }

    public void setTabbedPane1(JTabbedPane tabbedPane1) {
        this.tabbedPane1 = tabbedPane1;
    }

    public Main() {


        createCleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldCreateEmployeId.setText("");
                textFieldCreateName.setText("");
            }
        });

        createSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO SAVE
                Role role = new Role(createRolCombo.getSelectedIndex() + 1,createRolCombo.getSelectedItem().toString());
                Employee employee = new Employee(0,textFieldCreateName.getText(),role);
                Database db = new Database();
                employee.setIdEmployee(db.insertEmpoloyee(employee));
                textFieldCreateEmployeId.setText(Integer.toString(employee.getIdEmployee()));
            }
        });
        searchCleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchEmployee.setText("");
                searchName.setText("");
                searchRole.setText("");
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEmployee = Integer.parseInt(searchEmployee.getText());
                Employee employee = new Employee(idEmployee,"",new Role(0,""));
                Database db = new Database();
                employee = db.getEmpoloyee(employee);
                searchName.setText(employee.getName());
                searchRole.setText(employee.getRol().getDescription());
            }
        });
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEmployee = Integer.parseInt(captureEmployee.getText());
                Employee employee = new Employee(idEmployee,"",new Role(0,""));
                Database db = new Database();
                employee = db.getEmpoloyee(employee);
                captureName.setText(employee.getName());
                captureRole.setText(employee.getRol().getDescription());
            }
        });
        captureClean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                captureEmployee.setText("");
                captureName.setText("");
                captureRole.setText("");
                captureDeliverys.setText("");
            }
        });
        reportCleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportIdEmployee.setText("");
                workingHours.setText("");;
                totalAmountDeliverys.setText("");;
                totalAmountVouchers.setText("");;
                taxes.setText("");;
                vouchers.setText("");;
                total.setText("");;
            }
        });
        captureSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEmployee = Integer.parseInt(captureEmployee.getText());
                int year = Integer.parseInt(captureYear.getSelectedItem().toString());
                int deliverys = Integer.parseInt(captureDeliverys.getText());

                EmployeeDelivery employeeDelivery = new EmployeeDelivery(
                        0,idEmployee,captureMonth.getSelectedIndex() + 1,year,deliverys);
                Database db = new Database();
                employeeDelivery = db.insertEmployeeDelivery(employeeDelivery);

                if(employeeDelivery.getIdEmployeeDelivery() > 0){
                    JOptionPane.showMessageDialog(null,"Registro guardado exitosamente.","Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    captureEmployee.setText("");
                    captureName.setText("");
                    captureRole.setText("");
                    captureDeliverys.setText("");
                }
            }
        });
        reportGenerateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MonthTotals monthTotals = new MonthTotals();
                int idEmployee = Integer.parseInt(reportIdEmployee.getText());
                int month = reportMonth.getSelectedIndex() + 1;
                int year = Integer.parseInt(reportYear.getSelectedItem().toString());

                monthTotals.setIdEmployee(idEmployee);
                monthTotals.setMonth(month);
                monthTotals.setYear(year);

                Database db = new Database();
                monthTotals = db.getMonthTotals(monthTotals);

                int intWorkingHours;
                intWorkingHours = 24 * 8;
                int intTotalAmountDeliverys = 0;
                intTotalAmountDeliverys = monthTotals.getDeliveries() * 5;
                int intTotalAmountVouchers = 0;
                double totalTaxes = 0;
                double totalVouchers = 0;
                double finalTotal = 0;
                double totalAmount = 0;
                Double ISR;

                if(monthTotals.getIdEmployeeRole() == 1)//Chofer
                {
                    intTotalAmountVouchers = intWorkingHours * 10;
                }else if(monthTotals.getIdEmployeeRole() == 2){
                    intTotalAmountVouchers = intWorkingHours * 5;
                }

                totalAmount = intTotalAmountDeliverys + intTotalAmountVouchers;

                if(totalAmount >= 10000 ){
                    ISR = .12;
                }else {
                    ISR = .09;
                }
                totalTaxes = totalAmount * ISR;
                totalVouchers = (float) (totalAmount * .04);
                finalTotal = intTotalAmountDeliverys + intTotalAmountVouchers - totalTaxes + totalVouchers;

                workingHours.setText(Integer.toString(intWorkingHours));
                totalAmountDeliverys.setText(Integer.toString(intTotalAmountDeliverys));
                totalAmountVouchers.setText(Integer.toString(intTotalAmountVouchers));
                taxes.setText(Double.toString(totalTaxes));
                vouchers.setText(Double.toString(totalVouchers));
                total.setText(Double.toString(finalTotal));

            }
        });


    }
}
