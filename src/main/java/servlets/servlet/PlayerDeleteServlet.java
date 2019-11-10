package servlets.servlet;

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
public class PlayerDeleteServlet extends HttpServlet {

    /**
     * Метод обрабатывает запрос get сервлету PlayerDeleteServlet.
     * Получаемый параметр: id.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/delete.jsp").forward(req, resp);
    }

    /**
     * Метод обрабатывает запрос post.
     * Получаемый параметр: id.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answer = this.delete(req);
        req.setAttribute("answer", answer);
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/deletePost.jsp").forward(req, resp);
    }

    /**
     * Метод для удаления пользователя.
     * Считывает передаваемый параметр id.
     */
    private String delete(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String result;
        try {
            ValidateService.getInstance().delete(id);
            result = "The player was deleted successfully";
        } catch (PlayerValidationException e) {
            result = e.getMessage();
        }
        return result;

    }
}
