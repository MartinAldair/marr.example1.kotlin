package mx.com.marr.example1.kotlin.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.util.*

@Document(collection = "user") // Maps Entity class objects to JSON formatted Documents
data class User (
    @Id
    var id: String? = ObjectId().toHexString(),
    @Field("email")
    var email: String,
    @Field("password")
    var password: String,
    @Field("created_at")
    val apiKey: Date,
    @Field("created_at")
    val lastChangedAt: Date? = null

)