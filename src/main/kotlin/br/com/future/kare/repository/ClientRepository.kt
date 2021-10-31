package br.com.future.kare.repository

import br.com.future.kare.model.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository: JpaRepository<Client, Long> {
    fun findByEmail(email: String): Client?
}