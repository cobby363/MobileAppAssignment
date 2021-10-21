package org.wit.videoManagement.console.controllers

import VideoMemStore
import mu.KotlinLogging
import org.wit.videoManagement.console.models.VideoJSONStore
import org.wit.videoManagement.console.models.VideoModel
import org.wit.videoManagement.console.views.VideoView

class VideoController {

    //val videos = VideoMemStore()
    val videos = VideoJSONStore()
    val videoView = VideoView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching VideoManagement Console App" }
        println("VideoManagement Kotlin App")
    }
    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down VideoManagement Console App" }
    }


    fun menu() :Int { return videoView.menu() }

    fun add(){
        var aVideo = VideoModel()

        if (videoView.addVideoData(aVideo))
            videos.create(aVideo)
        else
            logger.info("Video Not Added")
    }



    fun list() {
        videoView.listVideos(videos)
    }

    fun update() {

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

    fun search() {
        val aVideo = search(videoView.getId())!!
        videoView.showVideo(aVideo)
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
}