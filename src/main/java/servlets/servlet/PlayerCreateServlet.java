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
public class PlayerCreateServlet extends HttpServlet {

    /**
     * Метод обрабатывает запрос get сервлету PlayerCreateServlet.
     * На странице есть форма для создания пользователя.
     * Данные формы обрабатываются методом post сервлету PlayerCreateServlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);

    }

    /**
     * Метод обрабатывает запрос post.
     * Добавляет нового игрока.
     * Кнопка 'Return to players' отправляет запрос get сервлету PlayersServlet.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answer = this.create(req);
        req.setAttribute("answer", answer);
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/createPost.jsp").forward(req, resp);
    }

    /**
     * Метод для добавления игрока.
     */
    private String create(HttpServletRequest req) {
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        int marketValue = Integer.parseInt(req.getParameter("marketValue"));
        String country = req.getParameter("country");
        String club = req.getParameter("club");
        int positionId = Integer.parseInt(req.getParameter("area"));
        FootballPosition footballPosition = new FootballPosition(positionId);
        String response;
        Player player = new Player(name, lastName, marketValue, country, club, footballPosition);
        try {
            ValidateService.getInstance().addOrUpdate(player);
            response = "Player was added.";

        } catch (PlayerValidationException e) {
            response = e.getMessage();
        }
        return response;
    }
}
