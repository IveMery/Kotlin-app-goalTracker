package roomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import roomDatabase.entity.User

@Dao
interface DaoUser {
    @Query("SELECT * FROM User")
    fun getUsers(): List<User>


    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    fun login(email: String, password: String): List<User>


    @Insert
    fun addUser(user: User): Long


    //=: nos permite parametrizar
    @Query("UPDATE User SET email = :email, password = :password WHERE username = :username")
     fun updateUser(email: String, password: String, username: String): Int



    @Query("DELETE FROM User WHERE username = :username")
    fun deleteUserById(username: String)
}