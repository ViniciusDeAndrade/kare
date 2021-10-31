package br.com.future.kare.controller

import br.com.future.kare.dto.ClientDTO
import br.com.future.kare.dto.toDTO
import br.com.future.kare.form.ClientForm
import br.com.future.kare.form.toDomain
import br.com.future.kare.service.IClientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("v1/client")
class ClientController (
    private val clientService: IClientService
){

    @PutMapping
    fun setProfile(@RequestBody @Valid clientForm: ClientForm): ResponseEntity<ClientDTO> {
        val client = clientService.saveNewClient(clientForm.toDomain())
        return ResponseEntity.ok(client.toDTO())

    }
}