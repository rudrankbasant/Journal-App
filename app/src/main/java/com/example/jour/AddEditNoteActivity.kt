package com.example.jour

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.jour.MVVM.JourViewModel
import com.example.jour.MVVM.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.FileInputStream
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var backButton: FloatingActionButton
    lateinit var formatButton: FloatingActionButton
    lateinit var editTitle: EditText
    lateinit var editDesc: EditText
    lateinit var saveButton: FloatingActionButton
    lateinit var viewModel: JourViewModel
    lateinit var addImageButton: FloatingActionButton
    lateinit var theimage: ImageView
    private var ImageURI: Uri? = null
    private var bmp: Bitmap? = null
    var noteID= -1

    companion object{
        const val IMAGE_REQ_CODE=100

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        editTitle = findViewById(R.id.editNoteTitle)
        editDesc = findViewById(R.id.editNoteDescription)
        saveButton=findViewById(R.id.jourSaveButton)
        backButton=findViewById(R.id.backButton)
        addImageButton=findViewById(R.id.jourAddImgButton)
        formatButton=findViewById(R.id.jourFormatButton)
        theimage=findViewById(R.id.imageView1)

        viewModel= ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(JourViewModel::class.java)


        val noteType = intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteID= intent.getIntExtra("noteID",-1)
            editTitle.setText(noteTitle)
            editDesc.setText(noteDesc)



            //Getting Bitmap Image
            val filename = intent.getStringExtra("noteImage")
            try{val receiveImage: FileInputStream = this.openFileInput(filename)
            bmp=BitmapFactory.decodeStream(receiveImage)
            var bitmapDrawable = BitmapDrawable(resources, bmp)
            theimage.setImageDrawable(bitmapDrawable)
            if(bmp!=null){
                Log.d("hereboi",bmp.toString())
            }else{
                Log.d("hereboi","not here ")
            }
            receiveImage.close()
            }catch(e:Exception){
                e.printStackTrace()
            }
        }

        /*val formattedTitle = intent.getStringExtra("formattedTitle")
        val formattedDesc =intent.getStringExtra("formattedDesc")
        if(formattedTitle!="null" && formattedDesc!="null"){
            editTitle.setText(formattedTitle)
            editDesc.setText(formattedDesc)
        }
        Log.d("valuess", formattedDesc.toString())
*/



        saveButton.setOnClickListener{
            val noteTitle= editTitle.text.toString()
            val noteDesc= editDesc.text.toString()






            if(noteType.equals("Edit")){
                if(noteTitle.isNotEmpty() && noteDesc.isNotEmpty()){
                    val sdf= SimpleDateFormat("MMM, dd,yyyy")
                    val currentDate:String= sdf.format(Date())
                    val updateNote = Note(noteTitle, noteDesc, currentDate, bmp )
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
                    val sdf= SimpleDateFormat("MMM dd,yyyy")
                    val currentDate:String= sdf.format(Date())
                    viewModel.addNote(Note(noteTitle,noteDesc,currentDate,bmp))
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

        formatButton.setOnClickListener{
            val intent = Intent(this@AddEditNoteActivity,TextFormatter::class.java)
            intent.putExtra("title", editTitle.text.toString())
            intent.putExtra("description", editDesc.text.toString())
            startActivity(intent)
            this.finish()
        }


        addImageButton.setOnClickListener{
            pickImageGallery()

        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/"
        startActivityForResult(intent, IMAGE_REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== IMAGE_REQ_CODE && resultCode==RESULT_OK){

             ImageURI  = data?.data!!


            if (ImageURI!=null){
                theimage.setImageURI(data.data)

                try {
                    bmp = MediaStore.Images.Media.getBitmap(contentResolver, ImageURI)
                    theimage.setImageBitmap(bmp)

                }catch(exception: IOException){
                    exception.printStackTrace()

                }

            }

        }

    }

}