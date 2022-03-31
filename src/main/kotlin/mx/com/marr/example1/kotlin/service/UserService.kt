package mx.com.marr.example1.kotlin.service

import mx.com.marr.example1.kotlin.model.User

interface UserService {

    fun saveUser(user: User): User

    fun findUserByEmail(email: String): User

    fun existsUserByEmail(email: String): Boolean

}