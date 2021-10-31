package br.com.future.kare.service

import br.com.future.kare.domain.ClientDomain

interface IClientService {

    fun saveNewClient(client: ClientDomain): ClientDomain
}