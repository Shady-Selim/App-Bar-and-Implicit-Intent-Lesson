package com.shady.appbarandimplicitintent

import android.content.Intent
import android.icu.util.Calendar.SUNDAY
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.util.*
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.LifecycleOwner


lateinit var getContent : ActivityResultLauncher<String>


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, owner: LifecycleOwner) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //getContent = registry.register("key", LifecycleOwner, ActivityResultContracts.GetContent()) { uri ->
            // Handle the returned Uri
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.shareText -> {
                Toast.makeText(this, "Sharing the text", Toast.LENGTH_LONG).show()
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Sharing the text")
                    type = "text/plain"
                }
                startActivity(shareIntent)
                true
            }
            R.id.openLink ->{
                val openLink = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
                startActivity(openLink)
                true
            }
            R.id.openCalendar -> {
                //registry.register("key", owner, GetContent()) { uri ->
                    // Handle the returned Uri
                true
                }


                true
            }
            else -> false
        }
    }
}