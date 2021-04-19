package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("posts", new ArrayList<>(PsqlStore.instOf().findAllPosts()));
        HttpSession session = req.getSession();
        req.setAttribute("user", session.getAttribute("user"));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("posts.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if ("delete".equals(req.getParameter("action"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            PsqlStore.instOf().deletePost(id);
        } else if ("update".equals(req.getParameter("action"))) {
            req.setCharacterEncoding("UTF-8");
            PsqlStore.instOf().savePost(
                    new Post(
                            Integer.valueOf(req.getParameter("id")),
                            req.getParameter("name")
                    )
            );
        }
        resp.sendRedirect(req.getContextPath() + "/posts.do");
    }
}
