package tj.unam.simpletodoapp.data.local.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "date")
    var date: Date? = null
    @ColumnInfo(name = "description")
    var description: String? = null

    @Ignore
    constructor()

    @Ignore
    constructor(date: Date, description: String) {
        this.date = date
        this.description = description
    }

    constructor(id: Int, date: Date, description: String) {
        this.id = id
        this.date = date
        this.description = description
    }

    override fun toString(): String {
        return "NoteEntity{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\''.toString() +
                '}'.toString()
    }
}