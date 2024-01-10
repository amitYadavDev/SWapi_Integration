package amitapps.media.apipractice.data.remote.model

data class PeopleData(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)