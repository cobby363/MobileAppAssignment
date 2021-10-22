package org.wit.videoManagement.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.videoManagement.console.controllers.VideoUIController
import tornadofx.*

class MenuScreen : View("Video Main Menu") {

    val videoUIController: VideoUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Video") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        videoUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Videos") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        videoUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}