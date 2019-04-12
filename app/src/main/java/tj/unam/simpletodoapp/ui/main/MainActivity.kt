package tj.unam.simpletodoapp.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import tj.unam.simpletodoapp.R
import tj.unam.simpletodoapp.db.NoteEntity
import tj.unam.simpletodoapp.ui.NotesAdapter
import tj.unam.simpletodoapp.ui.editor.EditorActivity

class MainActivity : AppCompatActivity() {

    private var notesEntityList: MutableList<NoteEntity> = mutableListOf()
    private var mAdapter: NotesAdapter? = null
    private var mViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)

        initRecyclerView()
        initViewModel()
    }

    fun addFabClickHandler(view: View) {
        Log.d("TAG_MAIN", "CLICKED")
        val intent = EditorActivity.newIntent(this)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_down, R.anim.slide_up)
    }


    private fun initRecyclerView() {
        recyclerView.hasFixedSize()
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
    }

    private fun initViewModel() {
        val notesObserver: Observer<MutableList<NoteEntity>> = Observer {
            notesEntityList.clear()
            if (it != null) {
                notesEntityList.addAll(it)
            }
            if (mAdapter == null) {
                mAdapter = NotesAdapter(notesEntityList, this)
                recyclerView.adapter = mAdapter
            } else {
                mAdapter?.notifyDataSetChanged()
            }
        }
        mViewModel = ViewModelProviders.of(this)
            .get(MainViewModel::class.java)

        mViewModel?.mNotes?.observe(this, notesObserver)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_add_sample_data -> {
                addSampleData()
                true
            }
            R.id.action_delete_all -> {
                deleteAllNotes()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllNotes() {
        mViewModel?.deleteAllNotes()
    }

    private fun addSampleData() {
        mViewModel?.addSampleData()
    }
}
