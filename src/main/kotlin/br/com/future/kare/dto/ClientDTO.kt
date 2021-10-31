package br.com.future.kare.dto

import br.com.future.kare.domain.ClientDomain

class ClientDTO (
    val name: String,
    val email: String,
    val cpf: String,
    val address: AddressDTO
)

class AddressDTO(
    val neighbourhood: String,
    val street: String,
    val number: String,
    val city: String
)

fun ClientDomain.toDTO() = ClientDTO(
    name = name,
    email = email,
    cpf = cpf,
    address = address.let {
        AddressDTO(
            neighbourhood = it.neighbourhood,
            street = it.street,
            number = it.number,
            city = it.city
        )
    }
)