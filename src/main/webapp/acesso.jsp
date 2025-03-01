<%@ page import="proj.musica.playmysongs.util.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("email");
    if (usuario == null || !usuario.isAtivo())
        response.sendRedirect(".");
%>