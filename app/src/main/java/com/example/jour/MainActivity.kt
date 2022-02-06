package com.example.jour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jour.MVVM.JourViewModel
import com.example.jour.MVVM.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickEditInterface, NoteClickDeleteInterface {
    lateinit var jourRV: RecyclerView
    lateinit var addButton: FloatingActionButton
    lateinit var viewModel: JourViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jourRV= findViewById(R.id.jourRecyclerView)
        addButton=findViewById(R.id.jourAddButton)
        jourRV.layoutManager=LinearLayoutManager(this)


        val jourRVAdapter = JourRVAdapter(this,this,this)
        jourRV.adapter = jourRVAdapter
        viewModel= ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(JourViewModel::class.java)
        viewModel.allEntries.observe(this, Observer { list->
            list?.let {
                jourRVAdapter.updateList(it)
            }

        })

        addButton.setOnClickListener{
            val intent = Intent(this@MainActivity,AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.jourTitle} was deleted", Toast.LENGTH_SHORT).show()

    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity,AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.jourTitle)
        intent.putExtra("noteDescription", note.jourDescription)
        intent.putExtra("noteID",note.id)
        startActivity(intent)
        this.finish()
    }


}