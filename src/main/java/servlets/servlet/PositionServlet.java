package servlets.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import servlets.models.FootballPosition;
import servlets.models.CollectionJson;
import servlets.storage.DBStore;
import servlets.storage.PlayerValidationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

/**
 * @version 1.0.
 * @since 07.11.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
public class PositionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<FootballPosition> positions = null;
        try {
            positions = DBStore.getInstance().findAllPositions();
        } catch (PlayerValidationException e) {
            e.printStackTrace();
        }
        CollectionJson collectionJson = new CollectionJson(positions);

        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        mapper.writeValue(sw, collectionJson);
        String json = sw.toString();

        resp.setContentType("text/json");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append(json);
        pw.flush();
    }
}
