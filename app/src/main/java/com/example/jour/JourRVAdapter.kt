package com.example.jour

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.jour.MVVM.Note

class JourRVAdapter(
    val context: Context,
    val noteClickEditInterface: NoteClickEditInterface,
    val noteClickDeleteInterface: NoteClickDeleteInterface
): RecyclerView.Adapter<JourRVAdapter.ViewHolder>( ){

    private val allEntries = ArrayList<Note>()
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTV =itemView.findViewById<TextView>(R.id.TitleTextView)
        val descTV= itemView.findViewById<TextView>(R.id.descTextView)
        val dateTV =itemView.findViewById<TextView>(R.id.dateTextView)
        val deleteTV=itemView.findViewById<ImageView>(R.id.deleteImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_card,parent,false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTV.setText(allEntries.get(position).jourTitle)
        holder.descTV.setText(allEntries.get(position).jourDescription)
        holder.dateTV.setText(allEntries.get(position).jourDate)
        holder.deleteTV.setOnClickListener{
            noteClickDeleteInterface.onDeleteIconClick(allEntries.get(position))
        }
        holder.itemView.setOnClickListener{
            noteClickEditInterface.onNoteClick((allEntries.get(position)))
        }
    }

    override fun getItemCount(): Int {
        return allEntries.size
    }

    fun updateList(newList: List<Note>){
        allEntries.clear()
        allEntries.addAll(newList)
        notifyDataSetChanged()
    }
}

interface  NoteClickDeleteInterface{
    fun onDeleteIconClick(note: Note)
}
interface  NoteClickEditInterface{
    fun onNoteClick(note: Note)
}