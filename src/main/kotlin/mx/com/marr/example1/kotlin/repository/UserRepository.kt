package mx.com.marr.example1.kotlin.repository

import mx.com.marr.example1.kotlin.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: MongoRepository<User, String> {

    @Query("{ 'email' : :#{#email} }")
    fun findUserByEmail(@Param("email") email: String): User

    @Query(value = "{ 'email' : :#{#email}}", exists = true)
    fun existsUserByEmail(@Param("email") email: String): Boolean

}