package amitapps.media.apipractice.data.remote.model

import amitapps.media.apipractice.data.remote.model.PeopleData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("people/")
    fun getCharactors(@Query("page") page: Int) : PeopleData
}