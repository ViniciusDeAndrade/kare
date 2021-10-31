package br.com.future.kare.service

import br.com.future.kare.domain.ClientDomain
import br.com.future.kare.form.LoginForm

interface IClientService {

    fun saveNewClient(client: ClientDomain): ClientDomain
    fun saveUser(loginForm: LoginForm): ClientDomain
}