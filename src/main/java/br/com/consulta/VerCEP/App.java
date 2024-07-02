package br.com.consulta.VerCEP;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import com.google.gson.Gson;

import br.com.consulta.VerCEP.model.Cep;

public class App {
	public static void main(String[] args) throws Exception, IOException {
		Scanner scanner = new Scanner(System.in);
		// Pedindo ao usuário o CEP
		System.out.println("Digite o CEP em que você mora:");
		System.out.println("(Por favor, não coloque o hífen)");
		// Leitura do CEP informado pelo usuário
		String cepBusca = scanner.nextLine();

		// Processo de validação do CEP (PT. 1), 
		//  caso o usuário tenha o digitado corretamente com 8 números
		if (cepBusca.length() == 8) {
			// REQUEST do CEP
			HttpRequest getRequest = HttpRequest.newBuilder()
					.uri(new URI("https://viacep.com.br/ws/" + cepBusca + "/json/"))
					.GET()
					.build();

			HttpClient client = HttpClient.newHttpClient();

			// Obtenção do RESPONSE
			HttpResponse<String> getResponse = client.send(getRequest, BodyHandlers.ofString());

			Cep cep = new Cep();
			Gson gson = new Gson();

			// Conversão do arquivo Json para um objeto Java
			cep = gson.fromJson(getResponse.body(), Cep.class);
			
			// Caso CEP seja inválido/inexistente
			if (cep.validadeInfo()) {
				System.out.println(cep.showInfo());
			} else {
				System.out.println("Este endereço não existe...");
			}

		// Processo de validação do CEP (PT. 2), 
	    //  caso o usuário tenha digitado um CEP ou longo demais, ou pequeno demais
		} else if (cepBusca.length() > 8 || cepBusca.length() < 8) {
			System.out.println("CEP inválido.");
		}
		scanner.close();
	}
}
