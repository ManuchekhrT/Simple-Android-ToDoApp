package tj.unam.simpletodoapp

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import tj.unam.simpletodoapp.data.local.db.AppDB
import tj.unam.simpletodoapp.data.local.db.NoteDAO
import tj.unam.simpletodoapp.utils.FakeData
import org.junit.Assert.*
import tj.unam.simpletodoapp.data.local.db.NoteEntity

@RunWith(AndroidJUnit4::class)
class DBTest {
    private val TAG: String = "JUnit"
    private var mDB: AppDB? = null
    private var mDAO: NoteDAO? = null

    @Before
    fun createDB() {
        val context = InstrumentationRegistry.getTargetContext()
        mDB = Room.inMemoryDatabaseBuilder(context,
            AppDB::class.java).build()
        mDAO = mDB?.noteDAO()
        Log.d(TAG, "createDB")
    }

    @After
    fun closeDB() {
        mDB?.close()
        Log.d(TAG, "closedDB")
    }

    @Test
    fun createAndRetrieveNotes() {
        mDAO?.insertAll(FakeData.getNotes())
        val count = mDAO?.getCount()
        Log.d(TAG, "createAndRetrieveNotes: count=$count")
        assertEquals(FakeData.getNotes().size, count)
    }

    @Test
    fun compareStrings() {
        mDAO?.insertAll(FakeData.getNotes())
        val original: NoteEntity = FakeData.getNotes()[0]
        val fromDB = mDAO?.getNoteById(1)
        assertEquals(original.description, fromDB?.description)
        assertEquals(1, fromDB?.id)

    }


}