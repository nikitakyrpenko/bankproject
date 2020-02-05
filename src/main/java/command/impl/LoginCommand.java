package command.impl;

import command.Command;
import entity.UserEntity;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

public class LoginCommand implements Command {

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        UserEntity user;
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            user = userService.login(email, password);
        }catch (NoSuchElementException e){
            request.setAttribute("exception", e.getMessage());
            return "index.jsp";
        }
        return "profile.jsp";
    }
}
