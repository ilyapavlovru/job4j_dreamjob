package ru.job4j.dream.servlet;

import org.apache.log4j.Logger;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CandidateServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(CandidateServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Candidate> candidates = PsqlStore.instOf().findAllCandidates();
        req.setAttribute("candidates", candidates);
        List<String> images = new ArrayList<>();
        for (File name : new File("c:\\images\\").listFiles()) {
            images.add(name.getName());
        }
        req.setAttribute("images", images);

        List<Integer> imagesNames = images.stream()
                .map(s -> {
                    String[] str = s.split("\\.");
                    return Integer.parseInt(str[0]);
                })
                .collect(Collectors.toList());
        req.setAttribute("imagesNames", imagesNames);

        RequestDispatcher dispatcher = req.getRequestDispatcher("candidates.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if ("delete".equals(req.getParameter("action"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            PsqlStore.instOf().deleteCandidate(id);
            String fileName = "c:\\images\\" + id + ".jpg";
            try {
                File file = new File(fileName);
                if (file.delete()) {
                    logger.info(file.getName() + " is deleted!");
                } else {
                    logger.warn("Sorry, unable to delete the file.");
                }
            } catch (Exception e) {
                logger.warn("candidate doPost(delete) error", e);
            }
        } else if ("update".equals(req.getParameter("action"))) {
            req.setCharacterEncoding("UTF-8");
            PsqlStore.instOf().saveCandidate(
                    new Candidate(
                            Integer.valueOf(req.getParameter("id")),
                            req.getParameter("name")
                    )
            );
        }
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
