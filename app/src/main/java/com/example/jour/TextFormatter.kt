package com.example.jour

import android.content.ClipDescription
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TextFormatter : AppCompatActivity() {
    lateinit var cancelFormatButton: FloatingActionButton
    lateinit var saveFormatButton: FloatingActionButton
    lateinit var startInt: EditText
    lateinit var endInt: EditText
    lateinit var resultTV: TextView
    lateinit var redRadioB: RadioButton
    lateinit var blueRadioB: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_formatter)

        cancelFormatButton=findViewById(R.id.formatBackButton)
        saveFormatButton=findViewById(R.id.formatSaveButton)
        startInt=findViewById(R.id.startInt)
        endInt=findViewById(R.id.endInt)
        resultTV=findViewById(R.id.resultTV)
        redRadioB=findViewById(R.id.colorRadioButton)




        cancelFormatButton.setOnClickListener{
            val intent = Intent(this@TextFormatter,AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        saveFormatButton.setOnClickListener{
            if(startInt.text.isNotEmpty() && endInt.text.isNotEmpty()){
                formatText()
            }else{
                Toast.makeText(this, "Please fill TO and FROM",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun formatText() {

        //Log.d("solutionnnnnn", (start+end).toString())
        //Log.d("the sringg ",spannableString.toString())


        var title: String = intent.getStringExtra("title").toString()
        var description: String = intent.getStringExtra("description").toString()
        val spannableString: SpannableString = SpannableString(description)

        val start = startInt.text.toString().toInt()
        val end = endInt.text.toString().toInt()

        /*var fColor= ForegroundColorSpan(Color.BLACK)
        spannableString.setSpan(fColor,start,end,Spannable.SPAN_INCLUSIVE_INCLUSIVE)*/

        if (redRadioB.isChecked){
            var fColor= ForegroundColorSpan(Color.RED)
            spannableString.setSpan(fColor,start,end,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }
       /* if (blueRadioB.isChecked){
            var fColor= ForegroundColorSpan(Color.BLUE)
            spannableString.setSpan(fColor,start,end,Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }*/


        resultTV.text= spannableString





        /*val intent = Intent(this@TextFormatter,AddEditNoteActivity::class.java)
        intent.putExtra("formattedTitle", title)
        intent.putExtra("formattedDesc", spannableString)
        startActivity(intent)
        this.finish()*/


    }
}