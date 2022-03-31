package mx.com.marr.example1.kotlin.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "user") // Maps Entity class objects to JSON formatted Documents
data class User(
    @Id
    var id: String? = ObjectId().toHexString(),
    var email: String,
    var password: String,
    var createdAt: Date,
    val lastChangedAt: Date?
) {}