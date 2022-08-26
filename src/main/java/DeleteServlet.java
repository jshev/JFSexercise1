import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class DeleteServlet extends HttpServlet {

    EmployeeDao employeeDao = EmployeeDaoFactory.getEmployeeDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setEmail(email);
        employee.setGender(gender);
        employee.setCountry(country);
        try {
            employeeDao.deleteEmployee(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        out.println("<span>Id:</span>");
        out.println("<span>"+employee.getId()+"</span>");
        out.println("<br>");
        out.println("<span>Name:</span>");
        out.println("<span>"+employee.getName()+"</span>");
        out.println("<br>");
        out.println("<span>Email:</span>");
        out.println("<span>"+employee.getEmail()+"</span>");
        out.println("<br>");
        out.println("<span>Gender:</span>");
        out.println("<span>"+employee.getGender()+"</span>");
        out.println("<br>");
        out.println("<span>Country:</span>");
        out.println("<span>"+employee.getCountry()+"</span>");
        out.println("<form action='DeleteServlet' method='get'>");
        out.println("<input type='hidden' name='id' value='"+employee.getId()+"'/>");
        out.println("<input type='submit' value='Delete' />");
        out.println("<form>");

        out.println("<form action='ViewServlet' method='get'>");
        out.println("<input type='submit' value='View List'>");
        out.println("</form>");
    }
}
