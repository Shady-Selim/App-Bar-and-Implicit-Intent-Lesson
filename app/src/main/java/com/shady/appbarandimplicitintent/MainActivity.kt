package com.shady.appbarandimplicitintent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner

class MainActivity : AppCompatActivity() {
    private val REQUEST_CONTACT = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //getContent = registry.register("key", LifecycleOwner, ActivityResultContracts.GetContent()) { uri ->
            // Handle the returned Uri
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
                val contact = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                startActivityForResult(contact, REQUEST_CONTACT)
                true
            }
            R.id.callNumber -> {
                val contact = Intent(Intent.ACTION_DIAL, Uri.parse("tel:5551234"))
                startActivity(contact)
                true
            }
            else -> false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CONTACT && resultCode == RESULT_OK && data != null) {
                val contactUri: Uri = data.data!!
                // Specify which fields you want your query to return values for
                val queryFields = arrayOf(ContactsContract.Contacts.DISPLAY_NAME)
                // Perform your query - the contactUri is like a "where" clause here
                val cursor = this.contentResolver
                    .query(contactUri, queryFields, null, null, null)
                cursor?.use {
                    // Verify cursor contains at least one result
                    if (it.count == 0) {
                        return }
                    // Pull out the first column of the first row of data -
                    // that is your suspect's name
                    it.moveToFirst()
                    Toast.makeText(this, "Contact user name: " + it.getString(0), Toast.LENGTH_LONG).show()
                }
        } }
}