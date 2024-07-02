package br.com.consulta.VerCEP.model;

public class Cep {
	// Atributos necessários para a aplicação.
	//  Que devem ser IGUAIS aos referidos no arquivo JSON, para não dar nenhum problema de incompatiblidade
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	// Método para mostrar as informações obtidas com o CEP
	public String showInfo() {
		return String.format("De acordo com o CEP: %s. \nVocê mora em %s, %s. \nNo endereço: %s. \nSituado no bairro %s.", getCep(), getLocalidade(), getUf(), getLogradouro(), getBairro());
	}

	// Caso o usuário informe um CEP inexistente, as informações irão retornar como 'null'. 
	//  E esse método irá fazer a validação pra caso os resultado sejam diferentes de nulo
	public boolean validadeInfo() {
		if (getCep() != null || getBairro() != null || getLocalidade() != null || getLogradouro() != null || getUf() != null) {
			return true;
		} else {
			return false;
		}
	}
}
