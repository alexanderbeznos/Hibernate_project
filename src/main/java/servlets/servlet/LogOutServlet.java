package servlets.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @version 1.0.
 * @since 29.10.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
public class LogOutServlet extends HttpServlet {
    /**
     * Метод для log out пользователя.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("login");
        resp.sendRedirect(String.format("%s/login", req.getContextPath()));
    }
}
