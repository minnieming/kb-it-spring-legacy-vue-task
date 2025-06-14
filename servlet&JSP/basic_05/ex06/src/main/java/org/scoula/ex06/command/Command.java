package org.scoula.ex06.command;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    String execute(HttpServletRequest req, HttpServletResponse res) throws IOException;
}
