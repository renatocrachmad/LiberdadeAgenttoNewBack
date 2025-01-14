package br.com.liberdade.Agentto.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

	public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthRequest(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
    

}
