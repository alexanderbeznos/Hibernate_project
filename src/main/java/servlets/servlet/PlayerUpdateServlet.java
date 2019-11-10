package servlets.servlet;

import servlets.models.FootballPosition;
import servlets.models.Player;
import servlets.storage.PlayerValidationException;
import servlets.validation.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0.
 * @since 26.10.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
public class PlayerUpdateServlet extends HttpServlet {
    /**
     * Метод обрабатывает запрос get сервлету PlayerUpdateServlet.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
    }

    /**
     * Метод обрабатывает запрос post.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answer = this.update(req);
        req.setAttribute("answer", answer);
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/updatePost.jsp").forward(req, resp);
    }

    /**
     * Метод для редактирования пользователя.
     */
    private String update(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        int marketValue = Integer.parseInt(req.getParameter("marketValue"));
        String country = req.getParameter("country");
        String club = req.getParameter("club");
        int positionId = Integer.parseInt(req.getParameter("area"));
        FootballPosition footballPosition = new FootballPosition(positionId);
        Player player = new Player(id, name, lastName, marketValue, country, club, footballPosition);
        String result;
        try {
            ValidateService.getInstance().addOrUpdate(player);
            result = "The player was update successfully";
        } catch (PlayerValidationException e) {
            result = e.getMessage();
        }
        return result;

    }
}
