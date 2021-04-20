package bhakadekailas.demoassignment.models

import java.util.ArrayList

class GetMoviesAndShowResponseModel {
    var Response: String? = null
    var totalResults: String? = null
    var Search: ArrayList<MovieShowModel>? = null
    override fun toString(): String {
        return "GetMoviesAndShowResponseModel(Response=$Response, totalResults=$totalResults, Search=$Search)"
    }
}