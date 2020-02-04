package controller;

import dao.UserDao;
import dao.impl.UserCrudDaoImpl;
import dao.util.ConnectorDB;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class UserController extends HttpServlet {

    private final UserDao userDao;

    public UserController(){
        this.userDao = new UserCrudDaoImpl(new ConnectorDB("sqlconnection"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String email = req.getParameter("email");
       String password = req.getParameter("password");
        User byEmail = userDao.findByEmail(email).get();

        System.out.println(byEmail);
    }
}
