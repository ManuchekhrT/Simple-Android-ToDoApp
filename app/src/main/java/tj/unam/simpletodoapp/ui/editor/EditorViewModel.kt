package tj.unam.simpletodoapp.ui.editor

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import tj.unam.simpletodoapp.data.repository.AppRepository
import tj.unam.simpletodoapp.data.local.db.NoteEntity
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class EditorViewModel(application: Application) : AndroidViewModel(application) {
    var mLiveData: MutableLiveData<NoteEntity> = MutableLiveData()
    private var mRepository: AppRepository? = null
    private var executor: Executor = Executors.newSingleThreadExecutor()

    init {
        mRepository = AppRepository.getInstance(application)
    }

    fun loadData(noteId: Int) {
        executor.execute {
            val note: NoteEntity = mRepository?.getNoteById(noteId)!!
            mLiveData.postValue(note)
        }
    }

    fun saveNote(noteText: String) {
        var note = mLiveData.value
        if (note == null) {
            if (TextUtils.isEmpty(noteText.trim()))
                return
            note = NoteEntity(Date(), noteText.trim())
        } else {
            note.description = noteText
        }
        mRepository?.insertNote(note)
    }

    fun deleteNote() {
        mRepository?.deleteNote(mLiveData.value!!)
    }
}