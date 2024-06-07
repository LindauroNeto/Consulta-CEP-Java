package br.com.consulta.VerCEP;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import br.com.consulta.VerCEP.model.Cep;

public class App {
	public static void main(String[] args) throws Exception, IOException {
		String cepBusca = "22290160";
		
		HttpRequest getRequest = HttpRequest.newBuilder()
				.uri(new URI("https://viacep.com.br/ws/"+ cepBusca +"/json/"))
				.GET()
				.build();
		
		HttpClient cliente = HttpClient.newHttpClient();
		
		HttpResponse<String> getResponse = cliente.send(getRequest, BodyHandlers.ofString());
		
		Cep cep = new Cep();
		Gson gson = new Gson();
		cep = gson.fromJson(getResponse.body(), Cep.class);
		System.out.println(cep.showInfo());
	}
}
