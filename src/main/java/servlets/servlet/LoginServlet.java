package servlets.servlet;

import servlets.models.User;
import servlets.storage.UserValidationException;
import servlets.validation.ValidateServiceUsers;

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
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    /**
     * Метод получает параметры login, password из запроса.
     * По полученному логину находится соответствующий пользователь и проверяется пароль.
     * Атрибут login будет использоваться, чтобы проверить произвел ли пользователь вход.
     * Если пользователь не найден по логину или пароль неверный,
     * то устанавливается атрибут error для запроса и вызывается снова метод doGet.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User search = ValidateServiceUsers.getInstance().findByLogin(login);
            if (password.equals(search.getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute("login", login);
                resp.sendRedirect(String.format("%s/list", req.getContextPath()));
            } else {
                req.setAttribute("error", "Password is incorrect.");
                doGet(req, resp);
            }
        } catch (UserValidationException e) {
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
        }
    }
}
