package proj.musica.playmysongs;

import java.io.*;

import proj.musica.playmysongs.util.Usuario;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email"), senha = request.getParameter("senha");

        if (!email.isEmpty() && !senha.isEmpty()) {
            int i = email.indexOf('@');
            if (i != -1) {
                if (email.substring(0, i).equals(senha)) {
                    Usuario usuario = new Usuario(email, true);
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("email", usuario);
                    response.sendRedirect("index.jsp");
                    return;
                }
            }
        }
        response.sendRedirect(".");
    }
}