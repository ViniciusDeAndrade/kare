package br.com.future.kare.model

import br.com.future.kare.domain.AddressDomain
import br.com.future.kare.domain.ClientDomain
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.ArrayList
import javax.persistence.*

@Entity
@Table(name = "clientes")
data class Client (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "nome")
    val name: String,

    @Column(name = "cpf", unique = true)
    val cpf: String,

    @Column(name = "email", unique = true)
    val email: String,

    @Column(name = "secret_cliente")
    private val password: String,

    @Column(name = "reset_token")
    var resetToken: String = "",

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "clients")
    val address: Address? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "perfis")
    private val profiles: MutableList<Profiles> = ArrayList<Profiles>()
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        this.profiles

    override fun getPassword(): String = this.password
    override fun getUsername(): String = this.email

    override fun isEnabled(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun toString(): String =
        "Client(name=$name, email='$email', resetToken='$resetToken', address=$address)"

}

fun Client.toDomain() = ClientDomain(
    name = name,
    email = email,
    cpf = cpf,
    address = address.let {
        AddressDomain(
            street = it!!.street,
            city = it.city,
            number = it.number,
            neighbourhood = it.neighborhood
        )
    }
)