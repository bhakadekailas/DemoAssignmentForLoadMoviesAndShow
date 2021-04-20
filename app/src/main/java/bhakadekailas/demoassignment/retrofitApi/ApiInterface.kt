import bhakadekailas.demoassignment.models.GetMoviesAndShowResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET(".")
    fun getMoviesAndShows(
        @Query("s") nameOfMovie: String,
        @Query("apikey") key: String
    ): Observable<GetMoviesAndShowResponseModel>
}