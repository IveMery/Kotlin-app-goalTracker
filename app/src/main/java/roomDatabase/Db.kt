package roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import roomDatabase.dao.DaoGoals
import roomDatabase.dao.DaoUser
import roomDatabase.entity.Goals
import roomDatabase.entity.User

@Database(
    entities = [User::class, Goals::class],
    version = 12


)
abstract class Db: RoomDatabase(){
    abstract fun daoUser():DaoUser
    abstract fun daoGoals():DaoGoals
}
