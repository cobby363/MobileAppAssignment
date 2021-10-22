package org.wit.videoManagement.console.main

import mu.KotlinLogging
import org.wit.videoManagement.console.controllers.VideoController
import org.wit.videoManagement.console.models.VideoJSONStore
//import org.wit.videoManagement.console.models.VideoMemStore
import org.wit.videoManagement.console.models.VideoModel
import org.wit.videoManagement.console.views.VideoView
import java.lang.NullPointerException

private val logger = KotlinLogging.logger {}

//val videos = VideoMemStore()
val videos = VideoJSONStore()
val controller = VideoController()
val videoView = VideoView()



fun main(args: Array<String>) {
    logger.info { "Launching Video Console App" }
    println("Video Kotlin App")

    var input: Int

    do {
        input = videoView.menu()
        when(input) {
            1 -> addVideo()
            2 -> updateVideo()
            3 -> videoView.listVideos(videos)
            4 -> searchVideo()
            5 -> controller.delete()
            6 -> videoView.searchByTags(videos)
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Video Console App" }
}

fun addVideo(){
    var aVideo = VideoModel()

    if (videoView.addVideoData(aVideo))
        videos.create(aVideo)
    else
        logger.info("Video Not Added")
}

fun updateVideo() {

    videoView.listVideos(videos)
    var searchId = videoView.getId()
    val aVideo = search(searchId)

    if(aVideo != null) {
        if(videoView.updateVideoData(aVideo)) {
            videos.update(aVideo)
            videoView.showVideo(aVideo)
            logger.info("Video Updated : [ $aVideo ]")
        }
        else
            logger.info("Video Not Updated")
    }
    else
        println("Video Not Updated...")
}

fun searchVideo() {
    videoView.listVideos(videos)
   try{ val aVideo = search(videoView.getId())!!
        videoView.showVideo(aVideo)}catch (e: NullPointerException) {

       println("Video not found.... Please try another id")
   }
}


fun search(id: Long) : VideoModel? {
    var foundVideo = videos.findOne(id)
    return foundVideo
}

fun dummyData() {
    videos.create(VideoModel(channel = "New York New York", videoTitle = "So Good They Named It Twice"))
    videos.create(VideoModel(channel= "Ring of Kerry", videoTitle = "Some place in the Kingdom"))
    videos.create(VideoModel(channel = "Waterford City", videoTitle = "You get great Blaas Here!!"))
}