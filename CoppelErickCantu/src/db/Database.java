package db;

import bean.Employee;
import bean.EmployeeDelivery;
import bean.Role;
import db.report.MonthTotals;

import java.sql.*;

/**
 *
 * @author Erick
 */
public class Database {

    public static Connection getConnection(){

        try{
        Class.forName ("com.mysql.cj.jdbc.Driver").newInstance();

            String url = "jdbc:mysql://localhost:3306/coppel";
            String username ="root";
            String password = "Erick168.";
            String resultado ="";

            Connection connection = DriverManager.getConnection (url, username, password);
            return connection;


        }catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
            System.out.println(e.toString());
            return null;
        }

    }//Get connection

    public int validateUser(String user, String password) throws SQLException {
        Connection connection = getConnection();
        assert connection != null;
        Statement statement = connection.createStatement();
        String query = "SELECT count(1) as 'exists' FROM coppel.users where user = '"+user+"' and password = '"+password+"';";
        ResultSet resultSet = statement.executeQuery(query);

        if(resultSet.next()){
            return resultSet.getInt(1);
        } else {
            return 0;
        }
    }

    private void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private CallableStatement prepareCall(String call_InsertREQ_LINE) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    public int insertEmpoloyee(Employee employee){
        Connection connection = getConnection();
        String query = "{ call createEmployee(?,?,?) }";
        assert connection != null;
        CallableStatement cs;
        int jobId = 0;
        try{
            cs = connection.prepareCall(query);
            cs.setString(1,employee.getName());
            cs.setInt(2,employee.getRol().getIdRole());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);

            cs.execute();
            jobId = cs.getInt("pIdEmployee");

            return jobId;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return -1;
        }
    }

    public Employee getEmpoloyee(Employee employee){
        Connection connection = getConnection();
        String query = "{ call getEmployee(?,?,?) }";
        assert connection != null;
        CallableStatement cs;
        int jobId = 0;
        try{
            cs = connection.prepareCall(query);
            cs.setInt(1,employee.getIdEmployee());
            cs.registerOutParameter(2, Types.NVARCHAR);
            cs.registerOutParameter(3, Types.NVARCHAR);

            cs.execute();
            employee.setName(cs.getString("pName"));
            Role rol = new Role(0,cs.getString("pRole"));
            employee.setRol(rol);

            return employee;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public EmployeeDelivery insertEmployeeDelivery(EmployeeDelivery employeeDelivery){
        Connection connection = getConnection();
        String query = "{ call createEmployeeDelivery(?,?,?,?,?) }";
        assert connection != null;
        CallableStatement cs;

        try{
            cs = connection.prepareCall(query);
            cs.setInt(1,employeeDelivery.getIdEmployee());
            cs.setInt(2,employeeDelivery.getMonth());
            cs.setInt(3,employeeDelivery.getYear());
            cs.setInt(4,employeeDelivery.getDelivery());
            cs.registerOutParameter(5,java.sql.Types.INTEGER );

            cs.execute();
            employeeDelivery.setIdEmployeeDelivery(cs.getInt("pEmployeeDelivery"));

            return employeeDelivery;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }


    public MonthTotals getMonthTotals(MonthTotals monthTotals){
        Connection connection = getConnection();
        String query = "{ call getMonthTotals(?,?,?,?,?) }";
        assert connection != null;
        CallableStatement cs;
        int jobId = 0;
        try{
            cs = connection.prepareCall(query);
            cs.setInt(1,monthTotals.getIdEmployee());
            cs.setInt(2,monthTotals.getMonth());
            cs.setInt(3,monthTotals.getYear());
            cs.registerOutParameter(4, java.sql.Types.INTEGER);
            cs.registerOutParameter(5, java.sql.Types.INTEGER);

            cs.execute();
            monthTotals.setDeliveries(cs.getInt("pDeliveries"));
            monthTotals.setIdEmployeeRole(cs.getInt("pRole"));

            return monthTotals;

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

}

