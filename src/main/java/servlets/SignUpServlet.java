
package servlets;

import accounts.UserHuuzer;
import com.google.gson.Gson;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final UserService userService;

    public SignUpServlet(UserService userService) {
        this.userService = userService;
    }

    //sign up
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (userService.findByLogin(login) != null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Пользователь уже существует");
            return;

        }

        UserHuuzer newUser = new UserHuuzer(login, pass);
        userService.saveUser(newUser);


        Gson gson = new Gson();
        String json = gson.toJson(newUser);
        response.setContentType("text/html;charset=utf-8");

        response.getWriter().println("Пользователь добавлен в базу, теперь можете воспользоваться полями для авторизации:" + json);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}

