package com.carolina.recipesapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carolina.recipesapp.data.Recipe

@Database(
    entities = [Recipe::class],
    version = 1,
)
abstract class RecipesDataBase : RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao

    companion object {
        @Volatile
        private var instance: RecipesDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RecipesDataBase::class.java,
                "recipe_db.db",
            ).build()
    }
}
