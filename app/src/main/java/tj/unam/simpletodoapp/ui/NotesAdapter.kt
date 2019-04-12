package tj.unam.simpletodoapp.ui

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_note_list.view.*
import tj.unam.simpletodoapp.ui.editor.EditorActivity
import tj.unam.simpletodoapp.R
import tj.unam.simpletodoapp.db.NoteEntity
import tj.unam.simpletodoapp.utils.Constants

class NotesAdapter(private val mNotes: MutableList<NoteEntity>, private var context: Context) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //which called each time rows refreshed with the data object
        val noteEntity = mNotes[position]
        holder.itemView.noteTv.text = noteEntity.description

        holder.itemView.editFab.setOnClickListener {
            val intent = Intent(context, EditorActivity::class.java)
            intent.putExtra(Constants.NOTE_ID_KEY, noteEntity.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = mNotes.size

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NotesAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note_list, parent, false)
        return ViewHolder(itemView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}