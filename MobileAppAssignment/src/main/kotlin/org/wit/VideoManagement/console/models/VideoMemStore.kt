import mu.KotlinLogging
import org.wit.videoManagement.console.models.VideoModel
import org.wit.videoManagement.console.models.VideoStore

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class VideoMemStore : VideoStore {

    val videos = ArrayList<VideoModel>()

    override fun findAll(): List<VideoModel> {
        return videos
    }

    override fun findOne(id: Long) : VideoModel? {
        var foundVideo: VideoModel? = videos.find { p -> p.id == id }
        return foundVideo
    }

    override fun create(video: VideoModel) {
        video.id = getId()
        videos.add(video)
        logAll()
    }

    override fun update(video: VideoModel) {
        var foundVideo = findOne(video.id!!)
        if (foundVideo != null) {
            foundVideo.title = video.title
            foundVideo.description = video.description
        }
    }

    internal fun logAll() {
        videos.forEach { logger.info("${it}") }
    }
}