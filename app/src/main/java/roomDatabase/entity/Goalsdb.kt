package roomDatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Goalsdb(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val expirationDate: String,
    val description: String,
    val state: String,
    val userId: Long
) {
    // Constructor secundario para crear un objetivo sin proporcionar el ID


    override fun toString(): String {
        return "Goal(id=$id, title='$title', expirationDate='$expirationDate', description='$description', state='$state', userId=$userId)"
    }
}
