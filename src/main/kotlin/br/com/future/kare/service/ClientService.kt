package br.com.future.kare.service

import br.com.future.kare.domain.ClientDomain
import br.com.future.kare.model.toDomain
import br.com.future.kare.repository.ClientRepository
import br.com.future.kare.exceptions.NotFoundException
import br.com.future.kare.form.LoginForm
import br.com.future.kare.model.Client
import br.com.future.kare.utils.encode
import br.com.sign.exceptions.ApplicationException
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.Throws

class ClientService(
    private val clientRepository: ClientRepository
) : IClientService {

    @Transactional
    @Throws(NotFoundException::class)
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

    @Transactional
    override fun saveUser(form: LoginForm): ClientDomain {
        clientRepository.findByEmail(form.email) ?: throw ApplicationException("user.already.registered")

        val client = Client(
            email = form.email,
            password = encode(form.password)
        )

        return clientRepository.save(client).toDomain()
    }
}