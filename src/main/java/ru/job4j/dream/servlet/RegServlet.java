package ru.job4j.dream.servlet;

import ru.job4j.dream.model.User;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User existingUser = PsqlStore.instOf().findUserByEmail(email);
        if (existingUser == null) {
            req.setCharacterEncoding("UTF-8");
            PsqlStore.instOf().saveUser(
                    new User(
                            0,
                            name,
                            email,
                            password
                    )
            );
            HttpSession sc = req.getSession();
            User sessionUser = new User(name, email);
            sc.setAttribute("user", sessionUser);
            resp.sendRedirect(req.getContextPath() + "/posts.do");
        } else {
            req.setAttribute("error", "Пользователь с таким email уже существует!");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
    }
}
