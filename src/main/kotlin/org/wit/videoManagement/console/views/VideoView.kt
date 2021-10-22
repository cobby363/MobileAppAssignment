package org.wit.videoManagement.console.views

import org.wit.videoManagement.console.models.VideoJSONStore
//import org.wit.videoManagement.console.models.VideoMemStore
import org.wit.videoManagement.console.models.VideoModel
import java.lang.NumberFormatException

class VideoView {

    val blue = "\u001b[34m"
    val cyan =  "\u001b[36m"
    val yellow = " \u001b[33m"
    val reset = "\u001b[0m"

    fun menu() : Int {

        var option : Int
        var input: String?

        println(reset + blue+"MAIN MENU")
        println(" 1. Add Video")
        println(" 2. Update Video")
        println(" 3. List All Videos")
        println(" 4. Search Videos")
        println(" 5. Delete Video")
        println(" 6. Search by Tag")
        println("-1. Exit $reset")
        println()
        print(cyan+"Enter Option :  $reset")
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

    fun searchByTags(videos : VideoJSONStore){
        if(videos.videos.size>0){
            print("What tag would you like to search for?: ")
            var tagToSearch = readLine()!!
            var foundOne = false
            for(i in 0 until videos.videos.size){
                if(videos.videos[i].tags.size>0) {
                    for (x in 0 until videos.videos[i].tags.size){
                        if(tagToSearch == videos.videos[i].tags[x]) {
                            foundOne = true
                            println(videos.videos[i])
                        }
                    }
                }
            }
            if(!foundOne)
                print("Could not find any videos with this tag... Please try again!")
        }else
            print("Sorry there are no videos printed yet :(")
    }

    fun showVideo(video : VideoModel) {
        if(video != null)
            println("Video Details [ $video ]")
        else
            println("Video Not Found...")
    }

    fun addVideoData(video : VideoModel) : Boolean {

        println()
        print(yellow + "Enter a Channel Name : ")
        video.channel = readLine()!!
        print("Enter a Video Title : ")
        video.videoTitle = readLine()!!
        print("What star rating would you give this video (1-5 whole numbers)")
        var tempStarRatingFloat: Float
        try {tempStarRatingFloat = readLine()!!.toFloat()} catch (e: NumberFormatException){
            tempStarRatingFloat = 3f
            print("You didn't enter a number.... Defaulting to rating of 3")
        }
        var tempStarRating = tempStarRatingFloat.toInt()
        var moveOn = false
        while(!moveOn) {
            if (tempStarRating < 1 || tempStarRating > 5) {
                print("Please enter a whole number between 1 and 5")
                try {tempStarRatingFloat = readLine()!!.toFloat()} catch (e: NumberFormatException){
                    tempStarRatingFloat = 3f
                    print("You didn't enter a number.... Defaulting to rating of 3")
                }
                tempStarRating = tempStarRatingFloat.toInt()
            }else
                moveOn = true
        }
        video.starRating = tempStarRating
        val toPrint = "Which of these tags would suit your opinion of this video?: $reset"
        var tempTagFloat: Float
        var tempTag: Int
        moveOn = false
        if(video.starRating==1 || video.starRating==2) {

            while (!moveOn) {
                print("$toPrint\n1: Misleading Title \n2: Too long \n3: Too Short \n4: different than usual content \n5: Bad morals shown \n6: Not funny \n7: None of the above \n8: Add my own tag \n9: Finish Step \nPlease enter your choice of number")
                try {
                    tempTagFloat = readLine()!!.toFloat()
                } catch (e: NumberFormatException) {
                    tempTagFloat = 9f
                }
                tempTag = tempTagFloat.toInt()

                if (tempTag == 9) {
                    moveOn = true
                } else if (tempTag < 1 || tempTag > 9) {
                    print("Please re enter a correct number: ")
                    try {
                        tempTagFloat = readLine()!!.toFloat()
                    } catch (e: NumberFormatException) {
                        tempTagFloat = 9f
                    }
                    tempTag = tempTagFloat.toInt()
                } else if (tempTag == 1)
                    video.tags.add("Misleading Title")
                else if (tempTag == 2)
                    video.tags.add("Too long")
                else if (tempTag == 3)
                    video.tags.add("Too short")
                else if (tempTag == 4)
                    video.tags.add("different than usual content")
                else if (tempTag == 5)
                    video.tags.add("Bad morals shown")
                else if (tempTag == 6)
                    video.tags.add("Not funny")
                else if (tempTag == 7)
                    video.tags.add("None of the above")
                else if (tempTag == 8) {
                    print("Please enter tag: ")
                    video.tags.add(readLine()!!)
                }
            }
        }else if(video.starRating==3){

            while (!moveOn) {
                print("$toPrint\n1: Nothing special \n2: Average video \n3: Didn't make me feel anything \n4: Add my own tag \n5: Finish Step \nPlease enter your choice of number")
                try {tempTagFloat = readLine()!!.toFloat()}catch (e: NumberFormatException){
                    tempTagFloat = 5f
                }
                tempTag = tempTagFloat.toInt()

                if (tempTag == 5) {
                    moveOn = true
                } else if (tempTag < 1 || tempTag > 5) {
                    print("Please re enter a correct number: ")
                    try {tempTagFloat = readLine()!!.toFloat()}catch (e: NumberFormatException){
                        tempTagFloat = 5f
                    }
                    tempTag = tempTagFloat.toInt()
                } else if (tempTag == 1)
                    video.tags.add("Nothing special")
                else if (tempTag == 2)
                    video.tags.add("Average video")
                else if (tempTag == 3)
                    video.tags.add("Didn't make me feel anything")
                else if (tempTag == 4){
                    print("Please enter tag: ")
                    video.tags.add(readLine()!!)
                }
            }
        }else{

            while (!moveOn) {
                print("$toPrint\n1: Relateable \n2: Funny \n3: Positive Content \n4: Inspirational \n5: Kind \n6: Informative \n7: Memorable \n8: Add my own tag \n9: Finish Step \nPlease enter your choice of number")
                try {tempTagFloat = readLine()!!.toFloat()}catch (e: NumberFormatException){
                    tempTagFloat = 9f
                }
                tempTag = tempTagFloat.toInt()
                if (tempTag == 9) {
                    moveOn = true
                } else if (tempTag < 1 || tempTag > 9) {
                    print("Please re enter a correct number: ")
                    try {tempTagFloat = readLine()!!.toFloat()}catch (e: NumberFormatException){
                        tempTagFloat = 9f
                    }
                    tempTag = tempTagFloat.toInt()
                } else if (tempTag == 1)
                    video.tags.add("Relateable")
                else if (tempTag == 2)
                    video.tags.add("Funny")
                else if (tempTag == 3)
                    video.tags.add("Positive Content")
                else if (tempTag == 4)
                    video.tags.add("Inspirational")
                else if (tempTag == 5)
                    video.tags.add("Kind")
                else if (tempTag == 6)
                    video.tags.add("Informative")
                else if (tempTag == 7)
                    video.tags.add("Memorable")
                else if (tempTag == 8){
                    print("Please enter tag: ")
                    video.tags.add(readLine()!!)
                }
            }
        }

        return video.channel.isNotEmpty() && video.videoTitle.isNotEmpty()
    }

    fun updateVideoData(video : VideoModel) : Boolean {

        var tempChannel: String?
        var tempVideoTitle: String?
        var tempStarRating: Int
        var tempTags: ArrayList<String> = arrayListOf()

        if (video != null) {
            print(yellow + "Enter a new Channel for [ " + video.channel + " ] : ")
            tempChannel = readLine()!!
            print("Enter a new Video Title for [ " + video.videoTitle + " ] : ")
            tempVideoTitle = readLine()!!
            print("What new Star rating would you like to give for [ " +video.videoTitle+ "  - Previous rating: " + video.starRating + " ] : ")
            var tempStarRatingFloat: Float
            try {tempStarRatingFloat = readLine()!!.toFloat()} catch (e: NumberFormatException){
                tempStarRatingFloat = 3f
                print("You didn't enter a number.... Defaulting to rating of 3")
            }
            tempStarRating = tempStarRatingFloat.toInt()
            var moveOn = false
            while(!moveOn) {
                if (tempStarRating < 1 || tempStarRating > 5) {
                    print("Please enter a whole number between 1 and 5")
                    try {tempStarRatingFloat = readLine()!!.toFloat()} catch (e: NumberFormatException){
                        tempStarRatingFloat = 3f
                        print("You didn't enter a number.... Defaulting to rating of 3")
                    }
                    tempStarRating = tempStarRatingFloat.toInt()
                }else
                    moveOn = true
            }

            val toPrint = "Which of these tags would suit your opinion of this video?: $reset"
            var tempTagFloat: Float
            var tempTag: Int
            moveOn = false
            if(video.starRating==1 || video.starRating==2) {

                while (!moveOn) {
                    print("$toPrint\n1: Misleading Title \n2: Too long \n3: Too Short \n4: different than usual content \n5: Bad morals shown \n6: Not funny \n7: None of the above \n8: Add my own tag \n9: Finish Step \nPlease enter your choice of number")
                    try {
                        tempTagFloat = readLine()!!.toFloat()
                    } catch (e: NumberFormatException) {
                        tempTagFloat = 9f
                    }
                    tempTag = tempTagFloat.toInt()

                    if (tempTag == 9) {
                        moveOn = true
                    } else if (tempTag < 1 || tempTag > 9) {
                        print("Please re enter a correct number: ")
                        try {
                            tempTagFloat = readLine()!!.toFloat()
                        } catch (e: NumberFormatException) {
                            tempTagFloat = 9f
                        }
                        tempTag = tempTagFloat.toInt()
                    } else if (tempTag == 1)
                        tempTags.add("Misleading Title")
                    else if (tempTag == 2)
                        tempTags.add("Too long")
                    else if (tempTag == 3)
                        tempTags.add("Too short")
                    else if (tempTag == 4)
                        tempTags.add("different than usual content")
                    else if (tempTag == 5)
                        tempTags.add("Bad morals shown")
                    else if (tempTag == 6)
                        tempTags.add("Not funny")
                    else if (tempTag == 7)
                        tempTags.add("None of the above")
                    else if (tempTag == 8) {
                        print("Please enter tag: ")
                        tempTags.add(readLine()!!)
                    }
                }
            }else if(video.starRating==3){

                while (!moveOn) {
                    print("$toPrint\n1: Nothing special \n2: Average video \n3: Didn't make me feel anything \n4: Add my own tag \n5: Finish Step \nPlease enter your choice of number")
                    try {tempTagFloat = readLine()!!.toFloat()}catch (e: NumberFormatException){
                        tempTagFloat = 5f
                    }
                    tempTag = tempTagFloat.toInt()

                    if (tempTag == 5) {
                        moveOn = true
                    } else if (tempTag < 1 || tempTag > 5) {
                        print("Please re enter a correct number: ")
                        try {tempTagFloat = readLine()!!.toFloat()}catch (e: NumberFormatException){
                            tempTagFloat = 5f
                        }
                        tempTag = tempTagFloat.toInt()
                    } else if (tempTag == 1)
                        tempTags.add("Nothing special")
                    else if (tempTag == 2)
                        tempTags.add("Average video")
                    else if (tempTag == 3)
                        tempTags.add("Didn't make me feel anything")
                    else if (tempTag == 4){
                        print("Please enter tag: ")
                        tempTags.add(readLine()!!)
                    }
                }
            }else{

                while (!moveOn) {
                    print("$toPrint\n1: Relateable \n2: Funny \n3: Positive Content \n4: Inspirational \n5: Kind \n6: Informative \n7: Memorable \n8: Add my own tag \n9: Finish Step \nPlease enter your choice of number")
                    try {tempTagFloat = readLine()!!.toFloat()}catch (e: NumberFormatException){
                        tempTagFloat = 9f
                    }
                    tempTag = tempTagFloat.toInt()
                    if (tempTag == 9) {
                        moveOn = true
                    } else if (tempTag < 1 || tempTag > 9) {
                        print("Please re enter a correct number: ")
                        try {tempTagFloat = readLine()!!.toFloat()}catch (e: NumberFormatException){
                            tempTagFloat = 9f
                        }
                        tempTag = tempTagFloat.toInt()
                    } else if (tempTag == 1)
                        tempTags.add("Relateable")
                    else if (tempTag == 2)
                        tempTags.add("Funny")
                    else if (tempTag == 3)
                        tempTags.add("Positive Content")
                    else if (tempTag == 4)
                        tempTags.add("Inspirational")
                    else if (tempTag == 5)
                        tempTags.add("Kind")
                    else if (tempTag == 6)
                        tempTags.add("Informative")
                    else if (tempTag == 7)
                        tempTags.add("Memorable")
                    else if (tempTag == 8){
                        print("Please enter tag: ")
                        tempTags.add(readLine()!!)
                    }
                }
            }

            if (!tempChannel.isNullOrEmpty() && !tempVideoTitle.isNullOrEmpty()) {
                video.channel = tempChannel
                video.videoTitle = tempVideoTitle
                video.starRating = tempStarRating
                video.tags = tempTags
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print(yellow + "Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}