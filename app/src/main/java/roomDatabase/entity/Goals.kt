package roomDatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Goals{
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var title: String? = null
    var expirationDate: String? = null
    var description: String? = null
    var state: String? = null
    var user: String? = null


    constructor(

        title: String,
        expirationDate: String,
        description: String,
        state: String,
        user: String

    ) {

        this.title = title
        this.expirationDate = expirationDate
        this.description = description
        this.state = state
        this.user = user
    }

    override fun toString(): String {
        return "GoalsDB(id=$id, title='$title', expirationDate='$expirationDate', description='$description', state='$state',user='$user' )"
    }

}