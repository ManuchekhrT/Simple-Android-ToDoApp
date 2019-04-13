package tj.unam.simpletodoapp.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import tj.unam.simpletodoapp.data.repository.AppRepository
import tj.unam.simpletodoapp.data.local.db.NoteEntity

class MainViewModel(application: Application) : AndroidViewModel(application) {
    fun addSampleData() {
        mRepository?.addSampleData()
    }

    fun deleteAllNotes() {
        mRepository?.deleteAllNotes()
    }

    var mNotes: LiveData<MutableList<NoteEntity>>
    private var mRepository: AppRepository? = null

    init {
        mRepository = AppRepository.getInstance(application.applicationContext)
        mNotes = mRepository?.mNotes!!
    }

}