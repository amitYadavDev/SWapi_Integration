package amitapps.media.apipractice

import amitapps.media.apipractice.data.RvResult
import amitapps.media.apipractice.data.remote.model.PeopleData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)



        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<ApiInterface>(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getCharactors()

        retrofitData.enqueue(object : Callback<PeopleData?> {
            override fun onResponse(call: Call<PeopleData?>, response: Response<PeopleData?>) {
                val responseBody = response.body()
                val result = responseBody?.results

                recyclerAdapter = RecyclerAdapter(this@MainActivity, result)
                recyclerView.adapter = recyclerAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            }


            override fun onFailure(call: Call<PeopleData?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}