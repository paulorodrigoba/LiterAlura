package br.com.LiterAlura.api.service;

import br.com.LiterAlura.api.livro.Livro;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ConverterDados implements IConverteDados {

    private static final String BASE_URL = "https://gutendex.com/books";
    private final HttpClient client;
    private final ObjectMapper mapper;
    private final ConsumoApi consumo;

    public ConverterDados() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
        this.consumo = new ConsumoApi();
    }

    @Override
    public List<Livro> buscarLivro(String query) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?search=" + query))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode root = mapper.readTree(response.body());
        JsonNode results = root.get("results");

        return consumo.listaDeLivros(results);
    }
}
