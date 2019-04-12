package tj.unam.simpletodoapp.db

import android.arch.persistence.room.TypeConverter
import java.util.*

//Object declaration's initialization is thread-safe.
class DateConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toDate(timestamp: Long?): Date? {
            return if (timestamp == null)
                null
            else
                Date(timestamp)
        }

        @TypeConverter
        @JvmStatic
        fun toTimestamp(date: Date?): Long? = date?.time
    }

}
