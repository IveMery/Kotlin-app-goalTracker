package roomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import roomDatabase.entity.Goals

@Dao
interface DaoGoals {
    @Insert
    fun addGoals(goals: Goals):Long



    @Query("UPDATE  Goals SET title=:title,expirationDate=:expirationDate,description=:description,state=:state WHERE id=:id")
     fun updateGoals(title:String,expirationDate: String,description:String,state:String,id:Long): Int


    @Query("SELECT * FROM Goals WHERE id = :goalId")
    fun getGoalById(goalId: Long): Goals?


    @Query("SELECT * FROM Goals WHERE user = :user")
    fun getGoalByUser(user: String): List<Goals>

    @Query("SELECT * FROM Goals")
     fun getAllGoals(): List<Goals>

    @Query("DELETE FROM Goals WHERE id = :goalId")
     fun deleteById(goalId: Long)

}