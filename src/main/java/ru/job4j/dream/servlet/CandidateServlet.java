package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.Store;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // получаем список кандидатов
        Collection<Candidate> candidates = PsqlStore.instOf().findAllCandidates();
        // записываем список кандидатов в req в аттрибут candidates
        req.setAttribute("candidates", candidates);

        // формируем список названий файлов с картинками
        List<String> images = new ArrayList<>();
        for (File name : new File("c:\\images\\").listFiles()) {
            images.add(name.getName());
        }
        // записываем список названий файлов с картинками в req в аттрибут images
        req.setAttribute("images", images);

        // формируем список названий файлов с картинками без расширения, чтобы была только цифра
        List<Integer> imagesNames = images.stream()
                .map(s -> {
                    String[] str = s.split("\\.");
                    return Integer.parseInt(str[0]);
                })
                .collect(Collectors.toList());
        // записываем список названий файлов с картинками без расширения в req в аттрибут imagesNames
        req.setAttribute("imagesNames", imagesNames);

        // с объектом диспатчер ассоциируем путь к jsp файлу
        RequestDispatcher dispatcher = req.getRequestDispatcher("candidates.jsp");
        // отправляем диспатчеру параметры req и resp
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if ("delete".equals(req.getParameter("action"))) {
            Integer integer = Integer.valueOf(req.getParameter("id"));
            Integer i3nteger = Integer.valueOf(req.getParameter("id"));
        }



//        req.setCharacterEncoding("UTF-8");
//        PsqlStore.instOf().saveCandidate(
//                new Candidate(
//                        Integer.valueOf(req.getParameter("id")),
//                        req.getParameter("name")
//                )
//        );

        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
