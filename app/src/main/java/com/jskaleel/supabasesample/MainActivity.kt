package com.jskaleel.supabasesample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jskaleel.supabasesample.databinding.ActivityMainBinding
import io.supabase.postgrest.PostgrestDefaultClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.net.URI

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnHitMe.setOnClickListener {
            MainScope().launch(Dispatchers.IO) {
                fetchBooks()
            }
        }
    }

    private fun fetchBooks() {
        val SUPA_BASE_KEY = ""
        val postgrestClient = PostgrestDefaultClient(
            uri = URI("https://URL/rest/v1"),
            headers = mapOf(
                "apiKey" to SUPA_BASE_KEY,
                "Authorization" to SUPA_BASE_KEY
            )
        )

        val response: List<Any> = postgrestClient.from<Any>("my_table_name")
            .select(head = false)
            .executeAndGetList()

        Log.d(TAG, "$response")
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
}