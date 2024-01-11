package amitapps.media.apipractice

import amitapps.media.apipractice.data.remote.model.ApiInterface
import amitapps.media.apipractice.data.remote.model.PeopleData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var resultViewModel: ResultViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        resultViewModel = ViewModelProvider(this).get(ResultViewModel::class.java)



        val adpter = ResultPagingAdapter()
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adpter

        resultViewModel.list.observe(this) {
            adpter.submitData(lifecycle, it)
        }


    }
}