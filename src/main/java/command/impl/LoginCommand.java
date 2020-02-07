package command.impl;

import command.Command;
import dao.util.Page;
import dao.util.Pageable;
import domain.Account;
import domain.User;
import service.AccountService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

public class LoginCommand implements Command {

    private final UserService userService;
    private final AccountService accountService;

    public LoginCommand(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user;
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            user = userService.login(email, password);
        }catch (NoSuchElementException e){

            request.setAttribute("loginException", e.getMessage());
            return "index.jsp";
        }
        Pageable<Account> accounts = accountService.findAllAccountsByUserId(user.getId(), new Page(0, 1));
        session.setAttribute("user", user);
        request.setAttribute("accounts", accounts);
        return "profile.jsp";
    }
}
