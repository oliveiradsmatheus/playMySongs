const envio = document.getElementById("file");
const nomeArq = document.getElementById("nomearquivo");
const tam = document.getElementById("tam");

function validarEnvio() {
    let nome = document.getElementById("nome").value.replaceAll(" ", "").toLowerCase();
    let artista = document.getElementById("artista").value.replaceAll(" ", "").toLowerCase();
    let i = 0;

    if (nomeArq.innerHTML.endsWith(".mp3")) {
        while (i < nome.length && ((nome[i] >= 'a' && nome[i] <= 'z')))
            i++;
        if (i === nome.length) {
            i = 0;
            while (i < artista.length && ((artista[i] >= 'a' && artista[i] <= 'z')))
                i++;
            if (i === artista.length) {
                enviarArquivo();
            } else
                document.getElementById("mensagem").innerHTML = "Nome do Artista Inválido!";
        } else
            document.getElementById("mensagem").innerHTML = "Nome da Música Inválido!";
    } else
        document.getElementById("mensagem").innerHTML = "Formato de arquivo não é .mp3!";
}

function enviarArquivo() {
    const mensagem = document.getElementById("mensagem");
    const URL_TO_FETCH = 'upload-servlet';
    let formData = new FormData(document.getElementById("fform"));

    fetch(URL_TO_FETCH, {method: 'post', body: formData})
        .then(response => {
            return response.text();
        })
        .then(dados => {
            mensagem.innerHTML = dados;
        })
        .catch(error => mensagem.innerHTML = error);
}

envio.addEventListener("change", () => {
    if (envio.files.length > 0) {
        const file = envio.files[0];
        nomeArq.textContent = `Nome do arquivo: ${file.name}`;
        tam.textContent = `Tamanho: ${Math.floor(file.size / 1024)} KB`;
    } else {
        nomeArq.textContent = "Nenhum arquivo selecionado";
        tam.textContent = "";
    }
});