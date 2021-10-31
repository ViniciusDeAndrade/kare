package br.com.future.kare.domain

data class ClientDomain (
    val name: String,
    val email: String,
    val cpf: String,
    val address: AddressDomain
)

data class AddressDomain(
    val neighbourhood: String,
    val street: String,
    val number: String,
    val city: String
)