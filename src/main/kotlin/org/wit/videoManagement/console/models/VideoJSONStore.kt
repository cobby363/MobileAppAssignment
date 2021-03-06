package org.wit.videoManagement.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.wit.videoManagement.console.helpers.exists
import org.wit.videoManagement.console.helpers.read
import org.wit.videoManagement.console.helpers.write
import mu.KotlinLogging

import org.wit.videoManagement.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "videos.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<VideoModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class VideoJSONStore : VideoStore {

    val yellow = " \u001b[33m"

    var videos = mutableListOf<VideoModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<VideoModel> {
        return videos
    }

    override fun findOne(id: Long) : VideoModel? {
        var foundVideo: VideoModel? = videos.find { p -> p.id == id }
        return foundVideo
    }

    override fun create(video: VideoModel) {
        video.id = generateRandomId()
        videos.add(video)
        serialize()
    }

    override fun update(video: VideoModel) {
        var foundVideo = findOne(video.id!!)
        if (foundVideo != null) {
            foundVideo.channel = video.channel
            foundVideo.videoTitle = video.videoTitle
            foundVideo.starRating = video.starRating
            foundVideo.tags = video.tags
        }
        serialize()
    }

    internal fun logAll() {
        videos.forEach { logger.info(yellow + "${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(videos, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        videos = Gson().fromJson(jsonString, listType)
    }

    override fun delete(video: VideoModel) {
        videos.remove(video)
        serialize()
    }

//    override fun searchByTag(video: VideoModel) {
//        TODO("Not yet implemented")
//    }
}