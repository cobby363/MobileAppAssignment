package org.wit.videoManagement.console.views

import VideoMemStore
import org.wit.videoManagement.console.models.VideoJSONStore
//import org.wit.videoManagement.console.models.VideoMemStore
import org.wit.videoManagement.console.models.VideoModel

class VideoView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Video")
        println(" 2. Update Video")
        println(" 3. List All Videos")
        println(" 4. Search Videos")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listVideos(videos : VideoJSONStore) {
        println("List All Videos")
        println()
        videos.logAll()
        println()
    }

    fun showVideo(video : VideoModel) {
        if(video != null)
            println("Video Details [ $video ]")
        else
            println("Video Not Found...")
    }

    fun addVideoData(video : VideoModel) : Boolean {

        println()
        print("Enter a Title : ")
        video.title = readLine()!!
        print("Enter a Description : ")
        video.description = readLine()!!

        return video.title.isNotEmpty() && video.description.isNotEmpty()
    }

    fun updateVideoData(video : VideoModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?

        if (video != null) {
            print("Enter a new Title for [ " + video.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + video.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                video.title = tempTitle
                video.description = tempDescription
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}