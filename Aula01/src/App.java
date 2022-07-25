import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexao HTTP e buscar os top 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair so os dados que interessam (titulo, poster, classificacao)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            var titulo = filme.get("title");
            var imagem = filme.get("image");
            var voto = filme.get("imDbRating");
            
            System.out.println("\u001b[37m\u001b[44m" + "Titulo: " + titulo + "\u001b[m");
            System.out.println("\u001b[1m" + "Classificação : " + voto + "\u001b[m");
            
            for (int i = 0; i < Math.round(Float.parseFloat(voto)); i++) {
                 System.out.print("\u2b50");
            }
           
            System.out.println(" \u001b[1m" + "Poster: " + imagem + "\u001b[m");
            System.out.println();
        }
    }
}
