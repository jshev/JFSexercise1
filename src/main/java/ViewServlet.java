import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewServlet extends HttpServlet {
    EmployeeDao employeeDao = EmployeeDaoFactory.getEmployeeDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out =  response.getWriter();

        // create a form that have hidden text field
        /*out.println("<form action='Servlet3' method='get'>");
        out.println("<input type='hidden' name='uname' value='"+name+"' />");
        out.println("<input type='submit' value='submit' />");
        out.println("<form>");*/

        out.println("<form action='AddServlet' method='get'>");
        out.println("<input type='submit' value='Add Employee'>");
        out.println("</form>");

        List<Employee> employeesList = new ArrayList<Employee>();
        try {
            employeesList = employeeDao.getEmployees();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Employee employee:employeesList) {
            out.println("<span>"+employee.getId()+"</span>");
            out.println("<span>"+employee.getName()+"</span>");
            out.println("<span>"+employee.getEmail()+"</span>");
            out.println("<span>"+employee.getGender()+"</span>");
            out.println("<span>"+employee.getCountry()+"</span>");
            out.println("<form action='UpdateServlet' method='get'>");
            out.println("<input type='hidden' name='id' value='"+employee.getId()+"'/>");
            out.println("<input type='submit' value='Update' />");
            out.println("<form>");
            out.println("<form action='DeleteServlet' method='get'>");
            out.println("<input type='hidden' name='id' value='"+employee.getId()+"'/>");
            out.println("<input type='submit' value='Delete' />");
            out.println("<form>");
        }

    }
}
