package roomDatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val email: String,
    val username: String,
    val birthDate: String,
    val password: String
) {


    override fun toString(): String {
        return "User(id=$id, email='$email', username='$username', birthDate='$birthDate')"
    }
}
