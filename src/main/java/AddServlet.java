import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class AddServlet extends HttpServlet {

    EmployeeDao employeeDao = EmployeeDaoFactory.getEmployeeDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out =  response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");

        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setGender(gender);
        employee.setCountry(country);
        try {
            employeeDao.addEmployee(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        out.println("<h1>Successfully added employee!</h1>");

        out.println("<form action='AddServlet' method='get'>");
        out.println("<label for='name'>Name</label>");
        out.println("<input type='text' name='name' id='name'>");
        out.println("<br>");
        out.println("<label for='email'>Email</label>");
        out.println("<input type='text' name='email' id='email'>");
        out.println("<br>");
        out.println("<p style='display:inline;'>Gender</p>");
        out.println("<input type='radio' id='female' name='gender' value='Female'>");
        out.println("<label for='female'>Female</label>");
        out.println("<input type='radio' id='male' name='gender' value='Male'>");
        out.println("<label for='male'>Male</label>");
        out.println("<br>");
        out.println("<label for='country'>Country</label>");
        out.println("<input type='text' name='country' id='country'>");
        out.println("<br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");

        out.println("<form action='ViewServlet' method='get'>");
        out.println("<input type='submit' value='View List'>");
        out.println("</form>");
    }
}
