package br.com.future.kare.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

fun encode(string : String) = BCryptPasswordEncoder().encode(string)

