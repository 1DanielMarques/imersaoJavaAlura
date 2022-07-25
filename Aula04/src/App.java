import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // Fazer uma conexao HTTP e buscar os top 250 filmes

        // String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        // ExtratorConteudo extrator = new ExtratorConteudoImdb();

        // String url =
        // "https://api.nasa.gov/planetary/apod?api_key=XZ3WnwfAVXNbToqhS1ZCb313KZpq0x7VdHv3Ri9c&start_date=2022-06-12&end_date=2022-06-14";
        // ExtratorConteudo extrator = new ExtratorConteudoNasa();

        String url = "http://localhost:8080/linguagens";
        ExtratorConteudo extrator = new ExtratorConteudoImdb();

        
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < 10; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo().replace(":","-") + ".png";

            geradora.cria(inputStream, nomeArquivo);
            System.out.println(conteudo.getTitulo());
            System.out.println();
        }

    }
}
