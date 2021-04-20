package bhakadekailas.demoassignment.views

import AdapterForMovieShow
import ApiInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import bhakadekailas.demoassignment.R
import bhakadekailas.demoassignment.models.GetMoviesAndShowResponseModel
import bhakadekailas.demoassignment.util.GlobalConstant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This is main class from where project will start
 *
 */
class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    /**
     * override method
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Load list of movies and images from api call
     */
    fun loadMoviesAndShows(view: View) {
        Log.e(TAG, "loadMoviesAndShows: ")
        val apiInterface =
            ApiClient.createRetrofitService(ApiInterface::class.java, GlobalConstant.BASE_URL)
        apiInterface.getMoviesAndShows(
            editTextTextPersonName.text.toString(),
            GlobalConstant.API_KEY
        )
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<GetMoviesAndShowResponseModel>() {
                override fun onNext(getMoviesAndShowResponseModel: GetMoviesAndShowResponseModel) {
                    Log.e(TAG, "onNext: ")
                    if (getMoviesAndShowResponseModel.Search != null) {
                        Log.e(TAG, "KAILAS: " + getMoviesAndShowResponseModel.Response)
                        Log.e(TAG, "KAILAS: " + getMoviesAndShowResponseModel.Search!!.size)

                        val listOfMoviesAndShows = getMoviesAndShowResponseModel.Search
                        if (listOfMoviesAndShows!!.size > 0) {
                            recyclerViewForMovies.layoutManager =
                                androidx.recyclerview.widget.StaggeredGridLayoutManager(
                                    1,
                                    androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
                                )
                            val adapterForMovieShow =
                                AdapterForMovieShow(listOfMoviesAndShows, this@MainActivity)
                            recyclerViewForMovies.adapter = adapterForMovieShow
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "No Result Found",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "onError: " + e.message)
                }

                override fun onComplete() {
                    Log.e(TAG, "onComplete: ")
                }
            })
    }
}