package br.com.future.kare.model

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
    val cpf: String
)