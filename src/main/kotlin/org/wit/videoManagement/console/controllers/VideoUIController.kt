package org.wit.videoManagement.console.controllers

import mu.KotlinLogging
import org.wit.videoManagement.console.models.VideoJSONStore
import org.wit.videoManagement.console.models.VideoModel
import org.wit.videoManagement.console.views.AddVideoScreen
import org.wit.videoManagement.console.views.ListVideoScreen
import org.wit.videoManagement.console.views.MenuScreen
import tornadofx.*

class VideoUIController : Controller() {

    val videos = VideoJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Video TornadoFX UI App" }
    }
    fun add(_channel : String, _videoTitle : String){

        var aVideo = VideoModel(channel = _channel, videoTitle = _videoTitle)
        videos.create(aVideo)
        logger.info("Video Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListVideoScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        videos.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddVideoScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddVideoScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListVideoScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}