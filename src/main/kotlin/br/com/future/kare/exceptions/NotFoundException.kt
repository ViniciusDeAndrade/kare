package br.com.future.kare.exceptions

class NotFoundException(
    override val message: String
): Exception(
    message = message
)