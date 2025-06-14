package org.scoula.ex06;

import org.scoula.ex06.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServlet", value="/")
public class FrontControllerServlet extends HttpServlet {

    Map<String, Command> getMap;
    Map<String,Command> postMap;

    public void init(){
        getMap = new HashMap<>();
        postMap = new HashMap<>();
    }

    // 요청 url에서 요청명을 찾아 리턴하는 메서드
    private String getCommandName(HttpServletRequest req){

        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();

        return requestURI.substring(contextPath.length());
    }

    // 요청 url에서 요청명을 추출한 후, 위의 메서드에 따라 해당 Command를 찾아 리턴하는 메서드
    private Command getCommand(HttpServletRequest req) {

        String commandName = getCommandName(req); // url에서 요청명 추출

        Command command;
        if(req.getMethod().equalsIgnoreCase("GET")){
            command = getMap.get(commandName);
        } else {
            command = postMap.get(commandName);
        }

        return command;
    }

    private void execute(Command command, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        String viewName = command.execute(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doGet(req, res);
    }
}
