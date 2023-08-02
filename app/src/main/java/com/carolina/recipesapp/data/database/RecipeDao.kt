package com.carolina.recipesapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carolina.recipesapp.data.Recipe

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(recipe: Recipe): Long

    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): LiveData<List<Recipe>>
}
