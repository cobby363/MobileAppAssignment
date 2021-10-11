import mu.KotlinLogging
import org.wit.VideoManagement.console.models.VideoModel
import org.wit.VideoManagement.console.models.VideoStore

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class VideoMemStore : VideoStore {

    val placemarks = ArrayList<VideoModel>()

    override fun findAll(): List<VideoModel> {
        return placemarks
    }

    override fun findOne(id: Long) : VideoModel? {
        var foundVideo: VideoModel? = placemarks.find { p -> p.id == id }
        return foundVideo
    }

    override fun create(video: VideoModel) {
        video.id = getId()
        placemarks.add(video)
        logAll()
    }

    override fun update(video: VideoModel) {
        var foundPlacemark = findOne(video.id!!)
        if (foundPlacemark != null) {
            foundPlacemark.title = video.title
            foundPlacemark.description = video.description
        }
    }

    internal fun logAll() {
        placemarks.forEach { logger.info("${it}") }
    }
}