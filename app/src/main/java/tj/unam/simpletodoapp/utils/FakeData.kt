package tj.unam.simpletodoapp.utils

import tj.unam.simpletodoapp.data.local.db.NoteEntity
import java.util.*

object FakeData {

    private const val FAKE_DATA_1 = "First simple note"
    private const val FAKE_DATA_2 = "Second simple note"
    private const val FAKE_DATA_3 = "Third simple note"

    private fun getDate(diff: Int): Date {
        val calendar = GregorianCalendar()
        calendar.add(Calendar.MILLISECOND, diff)
        return calendar.time
    }

    fun getNotes(): MutableList<NoteEntity> {
        val notes = ArrayList<NoteEntity>()
        notes.add(NoteEntity(getDate(0), FAKE_DATA_1))
        notes.add(NoteEntity(getDate(-1), FAKE_DATA_2))
        notes.add(NoteEntity(getDate(-2), FAKE_DATA_3))
        return notes
    }

}