import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class main {

    public static void main(String[] args) throws IOException, InterruptedException {

        String moedaBase = null;
        String moedaConvertida = "BRL";

        System.out.println("Bem vindo ao Conversor de moedas, escolha uma destas opções: ");
        System.out.println("1) Dólar Americano => Real");
        System.out.println("2) Yen => Real");
        System.out.println("3) Peso Argentino => Real");
        System.out.println("4) Kwanza => Real");
        System.out.println("5) Euro => Real");
        System.out.println("6) Rúpia => Real");
        System.out.println("7) Sair");

        Scanner sc = new Scanner(System.in);
        int escolha = sc.nextInt();
        if (escolha == 7) {
            System.out.println("Saindo...");
            sc.close();
            return;
        }

        System.out.println("Qual a quantidade que deseja converter?");
        int quantidade = sc.nextInt();
        sc.close();

        switch (escolha) {
            case 1:
                moedaBase = "USD";
                break;
            case 2:
                moedaBase = "JPY";
                break;
            case 3:
                moedaBase = "ARS";
                break;
            case 4:
                moedaBase = "AOA";
                break;
            case 5:
                moedaBase = "EUR";
                break;
            case 6:
                moedaBase = "INR";
                break;
            default:
                System.out.println("Opção inválida");
                return;
        }

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/b5de028a1424992c6b3bb9dc/pair/" + moedaBase + "/" + moedaConvertida + "/" + quantidade))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        double conversionRate = jsonObject.get("conversion_rate").getAsDouble();

        double convertedAmount = quantidade * conversionRate;
        System.out.println("Quantidade convertida: " + convertedAmount + " " + moedaConvertida);
    }
}
