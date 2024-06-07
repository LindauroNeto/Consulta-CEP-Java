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
		System.out.print("Digite o CEP em que você mora: \n");
		System.out.print("(Por favor, não coloque o hífen)\n");
		String cepBusca = scanner.nextLine();
		
		if (cepBusca.length() == 8) {
			HttpRequest getRequest = HttpRequest.newBuilder()
					.uri(new URI("https://viacep.com.br/ws/"+ cepBusca +"/json/"))
					.GET()
					.build();
			
			HttpClient cliente = HttpClient.newHttpClient();
			
			HttpResponse<String> getResponse = cliente.send(getRequest, BodyHandlers.ofString());
			
			Cep cep = new Cep();
			Gson gson = new Gson();
			
			cep = gson.fromJson(getResponse.body(), Cep.class);
			if (cep.validadeInfo()) {
				System.out.println(cep.showInfo());
			} else {
				System.out.println("Este endereço não existe...");
			}
			
		} else if(cepBusca.length() > 8 || cepBusca.length() < 8) {
			System.out.println("CEP inválido.");
		}
		scanner.close();
	}
}
