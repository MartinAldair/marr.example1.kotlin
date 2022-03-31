package mx.com.marr.example1.kotlin.service.impl

import mx.com.marr.example1.kotlin.model.User
import mx.com.marr.example1.kotlin.repository.UserRepository
import mx.com.marr.example1.kotlin.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

class UserServiceImpl : UserService {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private val userRepository: UserRepository? = null

    override fun saveUser(user: User?): User? {
        TODO("Not yet implemented")
    }


}