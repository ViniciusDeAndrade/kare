package br.com.future.kare.service

import br.com.future.kare.domain.ClientDomain
import br.com.future.kare.model.toDomain
import br.com.future.kare.repository.ClientRepository
import br.com.future.kare.exceptions.NotFoundException

class ClientService(
    private val clientRepository: ClientRepository
) : IClientService {
    override fun saveNewClient(client: ClientDomain): ClientDomain {
        val user = clientRepository.findByEmail(client.email) ?: throw NotFoundException("client.not.found")
        return clientRepository.save(
            user.copy(
                name = client.name,
                cpf = client.cpf,
                email = client.email
            )
        ).toDomain()
    }
}