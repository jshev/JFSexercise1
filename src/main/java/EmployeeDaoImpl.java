import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{

    Connection connection;
    public EmployeeDaoImpl(){
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        String sql = "insert into employee (name, email, gender, country) values (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmail());
        preparedStatement.setString(3, employee.getGender());
        preparedStatement.setString(4, employee.getCountry());
        int count = preparedStatement.executeUpdate();
        if(count > 0){
            System.out.println("New employee added.");
        }else{
            System.out.println("Something went wrong with adding employee...");
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "Update employee set name = ?, email = ?, " +
                "gender = ?, country = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmail());
        preparedStatement.setString(3, employee.getGender());
        preparedStatement.setString(4, employee.getCountry());
        preparedStatement.setInt(5, employee.getId());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Employee updated.");
        } else {
            System.out.println("Something went wrong with updating employee...");
        }
    }

    @Override
    public void deleteEmployee(Employee employee) throws SQLException {
        String sql = "Delete from employee where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, employee.getId());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Something went wrong with deleting employee...");
        }
    }

    @Override
    public Employee getEmployeeById(int emId) throws SQLException {
        Employee employee = new Employee();
        String sql = "select * from employee where id = " + emId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if (resultSet != null) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String gender = resultSet.getString(4);
            String country = resultSet.getString(5);
            employee = new Employee(id, name, email, gender, country);
        }else{
            System.out.println("No employee found...");
        }
        return employee;
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from employee";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String gender = resultSet.getString(4);
            String country = resultSet.getString(5);
            Employee employee = new Employee(id, name, email, gender, country);
            System.out.println(employee);
            employees.add(employee);
        }
        return employees;
    }
}
