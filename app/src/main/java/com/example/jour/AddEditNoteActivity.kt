package com.example.jour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.jour.MVVM.JourViewModel
import com.example.jour.MVVM.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var backButton: FloatingActionButton
    lateinit var editTitle: EditText
    lateinit var editDesc: EditText
    lateinit var saveButton: FloatingActionButton
    lateinit var viewModel: JourViewModel
    var noteID= -1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        editTitle = findViewById(R.id.editNoteTitle)
        editDesc = findViewById(R.id.editNoteDescription)
        saveButton=findViewById(R.id.jourSaveButton)
        backButton=findViewById(R.id.backButton)


        viewModel= ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(JourViewModel::class.java)


        val noteType = intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteID= intent.getIntExtra("",-1)
            editTitle.setText(noteTitle)
            editDesc.setText(noteDesc)
        }


        saveButton.setOnClickListener{
            val noteTitle= editTitle.text.toString()
            val noteDesc= editDesc.text.toString()

            if(noteType.equals("Edit")){
                if(noteTitle.isNotEmpty() && noteDesc.isNotEmpty()){
                    val sdf= SimpleDateFormat("MM dd,yyyy")
                    val currentDate:String= sdf.format(Date())
                    val updateNote = Note(noteTitle, noteDesc, currentDate)
                    updateNote.id=noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this,"Updated!",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                    this.finish()
                }else{
                    Toast.makeText(this,"Please fill both the columns!",Toast.LENGTH_SHORT).show()
                }
            }else{
                if(noteTitle.isNotEmpty() && noteDesc.isNotEmpty()){
                    val sdf= SimpleDateFormat("MM dd,yyyy")
                    val currentDate:String= sdf.format(Date())
                    viewModel.addNote(Note(noteTitle,noteDesc,currentDate))
                    Toast.makeText(this,"Added!",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                    this.finish()
                }else{
                    Toast.makeText(this,"Please fill both the columns!",Toast.LENGTH_SHORT).show()
                }
            }



        }

        backButton.setOnClickListener{
            val intent = Intent(this@AddEditNoteActivity,MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }


}