package br.com.future.kare.controller

import br.com.future.kare.config.security.TokenService
import br.com.future.kare.dto.TokenDTO
import br.com.future.kare.form.LoginForm
import br.com.future.kare.form.convert
import br.com.future.kare.service.ClientService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController (
    private val authManager: AuthenticationManager,
    private val tokenService: TokenService,
    private val clientService: ClientService
){

    @PostMapping
    fun auth(@RequestBody @Valid form: LoginForm) = try {
            val authentication = this.authManager.authenticate(form.convert())
            ResponseEntity.ok(
                TokenDTO(
                    token = tokenService.createToken(authentication),
                    type = "Bearer"
                )
            )
        }catch (ex: AuthenticationException){
             ResponseEntity.badRequest()
        }

    @PostMapping("/newUser")
    fun createNewUser(@RequestBody @Valid form: LoginForm) = try {
        this.clientService.saveUser(form)
        this.auth(form)
    } catch (ex: AuthenticationException) {
        ResponseEntity.badRequest()
    }

}