<%@ page import="proj.musica.playmysongs.util.Usuario" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <title>Play My Songs - Meu Web Player</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./estilos/index.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono:ital,wght@0,100..800;1,100..800&display=swap"
          rel="stylesheet">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&icon_names=headphones"/>
</head>
<%
    Usuario usuario = (Usuario) session.getAttribute("email");
%>
<body>
    <header>
        <div class="nomepag">
            <span class="material-symbols-outlined">
                headphones
            </span>
            <h1 class="titulo">Play My Songs</h1>
        </div>
        <div class="arealogin">
            <% if (usuario != null) {%>
            <p class="usuario">
                Seja bem-vindo <%=usuario.getEmail()%>
            </p>
            <a href="logoff-servlet">
                <button>Sair</button>
            </a>
            <% } else { %>
            <p class="usuario">
                Possui cadastro?
            </p>
            <a href="login.html">
                <button>Entre</button>
            </a>
            <% } %>
        </div>
    </header>
    <div class="pagina">
        <div class="container">
            <div class="ancora">
                <a href="enviar.jsp">
                    <button>Você pode enviar suas músicas aqui</button>
                </a>
            </div>
            <div class="busca">
                <label for="busca"></label>
                <input id="busca" type="search">
                <button onclick="buscarMusica()">Pesquisar</button>
            </div>
            <div class="player">
                <p class="mdisp">Músicas disponíveis:</p>
                <div class="musicas" id="listaMusicas">
                    <%
                        File pastaweb = new File(request.getServletContext().getRealPath("musicas"));
                        File[] arquivos = pastaweb.listFiles();
                        if (arquivos != null)
                            for (File file : arquivos)
                                if (file.isFile()) {
                                    String nomeArquivo = file.getName();
                                    String caminho = "musicas/" + nomeArquivo;
                    %>
                    <div class="musica-item">
                        <audio controls>
                            <source src="<%= caminho %>" type="audio/mpeg">
                        </audio>
                        <p class="musica">
                            <%= nomeArquivo %>
                        </p>
                    </div>
                    <%
                                }
                    %>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="javaScript/index.js"></script>
</html>
