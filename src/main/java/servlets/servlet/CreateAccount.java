package servlets.servlet;

import servlets.models.User;
import servlets.storage.UserValidationException;
import servlets.validation.ValidateServiceUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateAccount extends HttpServlet {

    /**
     * Метод обрабатывает запрос get сервлету CreateAccount.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/createAccountPost.jsp").forward(req, resp);
    }

    /**
     * Метод обрабатывает запрос post.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answer = this.create(req);
        req.setAttribute("answer", answer);
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    /**
     * Метод для создания пользователя.
     */
    private String create(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User u1 = new User(login, password);
        String result;
        try {
            ValidateServiceUsers.getInstance().addOrUpdate(u1);
            result = "The user was create successfully";
        } catch (UserValidationException e) {
            result = e.getMessage();
        }
        return result;

    }
}
