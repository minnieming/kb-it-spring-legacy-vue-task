package org.scoula.ex05;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String userid = req.getParameter("userid");
        String password = req.getParameter("password");

        req.setAttribute("userid", userid);
        req.setAttribute("password", password);

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
