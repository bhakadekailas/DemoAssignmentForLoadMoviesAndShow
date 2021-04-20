package bhakadekailas.demoassignment.models

class MovieShowModel {
    var Title: String? = null
    var Year: String? = null
    var Poster: String? = null

    override fun toString(): String {
        return "MovieShowModel(Title=$Title, Year=$Year, Poster=$Poster)"
    }
}