package com.example.layoutexample

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.layoutexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val myName: MyName = MyName("Name1")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName

        // Click listener for the Done button.
        binding.nameButton.setOnClickListener {
            addNickname(it)
        }
    }

    private fun addNickname(view: View) {
        binding.apply {
            // Set the text for nicknameText to the value in nicknameEdit.
            myName?.nickname = nameEdittext.text.toString()
            // Invalidate all binding expressions and request a new rebind to refresh UI
            invalidateAll()
            // Change which views are visible.
            // Remove the EditText and the Button.
            // With GONE they are invisible and do not occupy space.
            nameEdittext.visibility = View.GONE
            nameButton.visibility = View.GONE

            // Make the TexView with the nickname visible.
            nameEdittext.visibility = View.VISIBLE
        }
        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
