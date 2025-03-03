const campoBusca = document.getElementById("busca");
const listaMusicas = document.getElementById("listaMusicas");

function buscarMusica() {
    const busca = campoBusca.value.toLowerCase()
    if (listaMusicas) {
        const musicas = listaMusicas.querySelectorAll('.musica-item');
        console.log(musicas); // Adicione esta linha para depuração

        if (musicas && musicas.length > 0) {
            musicas.forEach(function (musica) {
                const nomeMusica = musica.querySelector(".musica").textContent.toLowerCase();
                if (nomeMusica.includes(busca))
                    musica.style.display = "block";
                else
                    musica.style.display = "none";
            });
        }
    }
}

campoBusca.addEventListener("keyup", function (e) {
    if (e.key === "Enter")
        buscarMusica();
});