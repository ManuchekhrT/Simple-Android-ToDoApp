package tj.unam.simpletodoapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteEntity: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(noteList: MutableList<NoteEntity>)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Int): NoteEntity

    @Query("SELECT * FROM notes ORDER BY date DESC")
    fun getAll(): LiveData<MutableList<NoteEntity>>

    @Query("DELETE FROM notes")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM notes")
    fun getCount(): Int
}