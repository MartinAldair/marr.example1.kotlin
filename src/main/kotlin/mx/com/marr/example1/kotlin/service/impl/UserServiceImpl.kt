package mx.com.marr.example1.kotlin.service.impl

import mx.com.marr.example1.kotlin.model.User
import mx.com.marr.example1.kotlin.repository.UserRepository
import mx.com.marr.example1.kotlin.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private val userRepository: UserRepository? = null

    override fun saveUser(user: User): User {
        log.info("Method to save data for an user in the db: {}", user);
        return userRepository!!.save(user)
    }

    override fun findUserByEmail(email: String): User {
        log.info("Method to look for an user by email in the db: {}", email);
        return userRepository!!.findUserByEmail(email);
    }

    override fun existsUserByEmail(email: String): Boolean {
        log.info("Method to check an user by email if exist in the db: {}", email);
        return userRepository!!.existsUserByEmail(email);
    }

}