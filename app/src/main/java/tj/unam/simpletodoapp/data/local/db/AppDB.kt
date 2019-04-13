package tj.unam.simpletodoapp.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context

@Database(entities = [NoteEntity::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDB : RoomDatabase() {

    abstract fun noteDAO(): NoteDAO

    companion object {
        private const val DB_NAME = "AppDB.db"
        @Volatile
        private var instance: AppDB? = null

        fun getAppDB(context: Context): AppDB? {
            if (instance == null) {
                //synchronized - to make sure 2 parts of the application try to create db at the same time
                synchronized(AppDB::class) {
                    if(instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDB::class.java,
                            DB_NAME
                        ).build()
                    }
                }
            }
            return instance
        }
    }
}