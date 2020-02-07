package command.impl;

import command.Command;
import entity.enums.Role;
import domain.User;
import service.UserService;
import service.validator.DuplicateException;
import service.validator.ValidateException;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {

    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String password = request.getParameter("password");
        String confPassword = request.getParameter("confirmedPassword");
        if (!password.equals(confPassword)){
            request.setAttribute("registerException","Password should match");
            return "index.jsp";
        }
        try {
            userService.register(parseRequestToUserEntity(request));
        }catch (DuplicateException | ValidateException e){
            request.setAttribute("registerException",e.getMessage());
            return "index.jsp";
        }
        return "index.jsp";
    }

    private User parseRequestToUserEntity(HttpServletRequest request){
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        return User.builder()
                .withName(name)
                .withSurname(surname)
                .withEmail(email)
                .withTelephone(telephone)
                .withPassword(password)
                .withRole(Role.CLIENT)
                .build();
    }
}
