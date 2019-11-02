package servlets.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0.
 * @since 29.10.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
public class CountryServlet extends HttpServlet {
    private final Map<String, List<String>> clubs = new ConcurrentHashMap<>();
    public CountryServlet() {
        clubs.put("Russia", Arrays.asList("Spartak", "Zenit", "Lokomotiv"));
        clubs.put("Spain", Arrays.asList("Barcelona", "Real Madrid", "Atletico Madrid"));
        clubs.put("England", Arrays.asList("Manchester City", "Liverpool", "Arsenal"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> countries = new ArrayList<>(this.clubs.keySet());
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(countries);
        String json = "{\"countries\" : " + result + "}";
        resp.setContentType("text/json");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append(json);
        pw.flush();
    }
}
