package tj.unam.simpletodoapp.ui.editor

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.content_edit.*
import tj.unam.simpletodoapp.R
import tj.unam.simpletodoapp.utils.Constants

class EditorActivity : AppCompatActivity() {

    private var mViewModel: EditorViewModel? = null
    private var isNewNote: Boolean = false

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, EditorActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setSupportActionBar(editToolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_check_black_24dp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViewModel()
    }

    private fun initViewModel() {
        mViewModel = ViewModelProviders.of(this)
            .get(EditorViewModel::class.java)
        mViewModel?.mLiveData?.observe(this, Observer {
            if (it != null) {
                noteEdt.setText(it.description)
            }
        })
        val extras = intent.extras
        if (extras == null) {
            title = getString(R.string.new_note)
            isNewNote = true
        } else {
            title = getString(R.string.edit_note)
            val noteId = extras.getInt(Constants.NOTE_ID_KEY)
            mViewModel?.loadData(noteId)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (!isNewNote) {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.menu_edit, menu)

        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            android.R.id.home -> {
                saveAndReturn()
                true
            }
            R.id.action_delete -> {
                mViewModel?.deleteNote()
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        saveAndReturn()
    }

    private fun saveAndReturn() {
        mViewModel?.saveNote(noteEdt.text.toString())
        finish()
    }

}
