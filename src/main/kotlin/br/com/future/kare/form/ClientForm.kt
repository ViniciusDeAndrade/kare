package br.com.future.kare.form


import br.com.future.kare.domain.AddressDomain
import br.com.future.kare.domain.ClientDomain
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ClientForm(
    @field:Email
    @field:NotNull
    val email: String?,
    @field:NotNull
    @field:NotBlank
    val name: String?,
    @field:NotNull
    @field:NotBlank
    @field:Size(min = 11, max = 11)
    val cpf: String?,
    @field:NotNull
    val address: AddressForm?
)

data class AddressForm(
    @field:NotNull
    @field:NotBlank
    val street: String?,
    @field:NotNull
    @field:NotBlank
    val neighbourhood: String?,
    @field:NotNull
    @field:NotBlank
    val number: String? = "s/n",
    @field:NotNull
    @field:NotBlank
    val city: String?,
)

fun ClientForm.toDomain() = ClientDomain(
    name = name!!,
    email = email!!,
    cpf = cpf!!,
    address = AddressDomain(
        street = address!!.street!!,
        neighbourhood = address.neighbourhood!!,
        number = address.number!!,
        city = address.city!!
    )
)