package tj.unam.simpletodoapp.db

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.content.Context
import tj.unam.simpletodoapp.utils.FakeData
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@SuppressLint("StaticFieldLeak")
class AppRepository(var context: Context) {

    private var mDB: AppDB? = null
    var mNotes: LiveData<MutableList<NoteEntity>>? = null
    private var executor: Executor = Executors.newSingleThreadExecutor()

    init {
        mDB = AppDB.getAppDB(context)
        mNotes = getAllNotes()
    }

    companion object {
        private var instance: AppRepository? = null
        fun getInstance(context: Context): AppRepository {
            if (instance == null)
                instance = AppRepository(context)

            return instance as AppRepository
        }
    }

    fun addSampleData() {
        executor.execute { mDB?.noteDAO()?.insertAll(FakeData.getNotes()) }
    }

    private fun getAllNotes(): LiveData<MutableList<NoteEntity>>? {
        return mDB?.noteDAO()?.getAll()
    }

    fun deleteAllNotes() {
        executor.execute {
            mDB?.noteDAO()?.deleteAll()
        }
    }

    fun getNoteById(noteId: Int): NoteEntity? {
        return mDB?.noteDAO()?.getNoteById(noteId)
    }

    fun insertNote(note: NoteEntity) {
        executor.execute {
            mDB?.noteDAO()?.insertNote(note)
        }
    }

    fun deleteNote(note: NoteEntity) {
        executor.execute {
            mDB?.noteDAO()?.deleteNote(note)
        }
    }

}