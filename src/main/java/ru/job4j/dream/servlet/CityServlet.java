package ru.job4j.dream.servlet;

import com.google.gson.Gson;
import ru.job4j.dream.model.City;
import ru.job4j.dream.store.MemStore;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        resp.setContentType("text/plain");
//        resp.setCharacterEncoding("UTF-8");
//        String name = req.getParameter("name");
//        PrintWriter writer = new PrintWriter(resp.getOutputStream());
//        writer.println("Nice to meet you, " + name);
//        writer.flush();

//        Collection<City> cities = PsqlStore.instOf().findAllCities();
        Collection<City> cities = MemStore.instOf().findAllCities();

        String json = new Gson().toJson(cities);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(json);


//        List<String> citiesNames = cities.stream()
//                .map(City::getName)
//                .collect(Collectors.toList());
//
//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json");
//
//
//        JSONObject obj = new JSONObject();
//        obj.put("city", citiesNames.get(0));
//        PrintWriter out = resp.getWriter();
//        out.print(obj);
//
//        out.flush();
    }

}
