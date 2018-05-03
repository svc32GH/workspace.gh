package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/logggin.do")
public class LoginServlet3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("Dummy Stuff");
        writer.println("Dummy Stuff2");
        request.setAttribute("name", request.getParameter("name"));
        request.setAttribute("password", request.getParameter("password"));
        request.getRequestDispatcher("/WEB-INF/views/login2.jsp").forward(request, response);
    }
}
