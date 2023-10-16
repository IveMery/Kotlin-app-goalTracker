package roomDatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User {
    @PrimaryKey(autoGenerate = true)
    var id:Long=0
    var email:  String? = null
    var username: String? = null
    var birthDate: String? = null
    var password: String? = null



    constructor(
        email: String?,
        username: String?,
        birthDate: String?,
        password: String?,

    ) {

        this.email = email
        this.username = username
        this.birthDate = birthDate
        this.password = password

    }

    override fun toString(): String {
        return "User(id=$id, email=$email, username=$username, birthDate=$birthDate, password=$password)"
    }


}
