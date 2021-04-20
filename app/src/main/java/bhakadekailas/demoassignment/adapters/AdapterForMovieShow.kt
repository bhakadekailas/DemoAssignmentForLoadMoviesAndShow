import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import bhakadekailas.demoassignment.R
import bhakadekailas.demoassignment.models.MovieShowModel
import com.squareup.picasso.Picasso
import java.util.*


class AdapterForMovieShow(
    private val movieShowModelArrayList: ArrayList<MovieShowModel>,
    internal var context: Context
) : androidx.recyclerview.widget.RecyclerView.Adapter<AdapterForMovieShow.MyViewHolder>() {
    companion object {
        private val TAG = AdapterForMovieShow::class.java.simpleName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.e(TAG, "onCreateViewHolder: ")
        val view =
            LayoutInflater.from(context).inflate(R.layout.row_for_movie_show_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.e(TAG, "onBindViewHolder: ")
        val mediaDataModel = movieShowModelArrayList[position]
        holder.textViewTitle.text = mediaDataModel.Title
        holder.textViewYear.text = mediaDataModel.Year

        Picasso.get()
            .load(mediaDataModel.Poster)
            .fit().centerCrop()
            .into(holder.imageViewPoster)
    }


    inner class MyViewHolder(view: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        var textViewYear: TextView = view.findViewById(R.id.textViewYear)
        var imageViewPoster: ImageView = view.findViewById(R.id.imageViewPoster)

    }

    override fun getItemCount(): Int {
        Log.e(TAG, "getItemCount: ")
        return movieShowModelArrayList.size
    }
}