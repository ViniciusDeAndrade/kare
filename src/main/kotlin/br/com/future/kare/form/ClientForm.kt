package br.com.future.kare.form

data class ClientForm(
    val name: String,
    val cpf: String,
    val address: AddressForm
)

data class AddressForm(
    val street: String,
    val neighboorhood: String,
    val number: String = "s/n",
    val city: String,
)