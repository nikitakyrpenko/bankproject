package command.impl;
import command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter("lang");
        HttpSession session = request.getSession();
        session.setAttribute("lang",language);
        return "index.jsp";
    }
}
