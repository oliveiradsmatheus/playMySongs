package proj.musica.playmysongs;

import java.io.*;

import proj.musica.playmysongs.util.Usuario;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email"), senha = request.getParameter("senha");

        if (email != null && !email.isEmpty() && senha != null && !senha.isEmpty()) {
            int i = email.indexOf('@');
            if (i != -1) {
                if (senha.equals(email.substring(0, i))) {
                    Usuario usuario = new Usuario(email, true);
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("email", usuario);
                    response.sendRedirect("index.jsp");
                    return;
                }
            }
        }
        response.sendRedirect("login.html");
    }
}