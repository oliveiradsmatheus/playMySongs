package proj.musica.playmysongs;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;

@MultipartConfig(
        location = "/home/matheus/Downloads",
        fileSizeThreshold = 1024 * 1024,    // 1MB
        maxFileSize = 1024 * 1024 * 100,      // 100MB
        maxRequestSize = 1024 * 1024 * 10 * 10  // 100MB
)

@WebServlet(name = "uploadServlet", value = "/upload-servlet")
public class UploadServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        OutputStream out;
        InputStream filecontent;
        PrintWriter writer = null;
        try {
            String nome = request.getParameter("nome").replace(" ", "");
            String artista = request.getParameter("artista").replace(" ", "");
            String estilo = request.getParameter("estilo");

            writer = response.getWriter();
            Part filePart = request.getPart("file");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = nome + "_" + estilo + "_" + artista + ".mp3";
                File fpasta = new File(getServletContext().getRealPath("/") + "/" + "musicas");
                fpasta.mkdir();
                out = new FileOutputStream(fpasta.getAbsolutePath() + "/" + fileName);
                filecontent = filePart.getInputStream();
                int read;
                byte[] bytes = new byte[1024];
                while ((read = filecontent.read(bytes)) != -1)
                    out.write(bytes, 0, read);
                writer.println("Novo arquivo " + fileName + " criado na pasta " + "musicas");
                out.close();
                filecontent.close();
                writer.close();
            } else
                writer.println("Nenhum arquivo foi selecionado.");
        } catch (Exception fne) {
            if (writer != null) {
                writer.println("Erro ao receber o arquivo");
                writer.println("<br/> ERRO: " + fne.getMessage());
            }
        }
    }
}
