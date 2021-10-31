package br.com.future.kare.config.security

import br.com.future.kare.model.Client
import br.com.future.kare.utils.JWT_EXPIRATION
import br.com.future.kare.utils.JWT_SECRET
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService {
    fun createToken(authentication: Authentication): String {
        val logged = authentication.principal as Client
        val today = Date()
        val expirationDate = Date(today.time + JWT_EXPIRATION)
        return Jwts.builder()
                .setIssuer("")
                .setSubject(logged.id.toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact()
    }

    fun isTokenValid(token: String?): Boolean =
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token)
            true
        }catch (ex: Exception){
            false
        }

    fun getUserId(token: String): Long = Jwts.parser()
            .setSigningKey(JWT_SECRET)
            .parseClaimsJws(token)
            .body.subject.toLong()




}
