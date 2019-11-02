package servlets.servlet;

import servlets.models.Player;
import servlets.storage.PlayerValidationException;
import servlets.validation.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @version 1.0.
 * @since 26.10.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
public class PlayersServlet extends HttpServlet {

    /**
     * Открывает страницу, на которой есть:
     * кнопка "Add player" - отправляет запрос get сервлету UserCreateServlet,
     * таблица с пользователями, возле каждого по 2 кнопки,
     * кнопка "Update" отправляет запрос get сервлету PlayerUpdateServlet
     * кнопка "Delete" отправляет запрос get сервлету PlayerDeleteServlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try {
            Collection<Player> players = ValidateService.getInstance().findAll();
            req.setAttribute("answer", "ok");
            req.setAttribute("players", players);
        } catch (PlayerValidationException e) {
            req.setAttribute("answer", e.getMessage());
        }
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }
}
