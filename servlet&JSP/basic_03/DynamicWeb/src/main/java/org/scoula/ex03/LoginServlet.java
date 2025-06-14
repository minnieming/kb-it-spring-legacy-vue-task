package org.scoula.ex03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userid = req.getParameter("userid");
        String password = req.getParameter("password");

        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();

        out.print("<html><body>");
        out.print("아이디 값 : " + userid +"<br>");
        out.print("비밀번호 값 : " + password + "<br>");
        out.print("</body></html>");

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

        req.setCharacterEncoding("UTF-8");

        doGet(req, resp);
    }
}
