package tj.unam.simpletodoapp.utils

import tj.unam.simpletodoapp.db.NoteEntity
import java.util.*

object FakeData {

    private const val FAKE_DATA_1 = "A simple note"
    private const val FAKE_DATA_2 = "A note with a line feed"
    private const val FAKE_DATA_3 = "It is a pleasure to meet such a talented man like u"

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