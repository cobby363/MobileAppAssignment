package org.wit.videoManagement.console.views

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.wit.videoManagement.console.controllers.VideoUIController
import org.wit.videoManagement.console.models.VideoModel
import tornadofx.*

class ListVideoScreen : View("List Videos") {

    val videoUIController: VideoUIController by inject()
    val tableContent = videoUIController.videos.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", VideoModel::id)
            readonlyColumn("TITLE", VideoModel::channel)
            readonlyColumn("DESCRIPTION", VideoModel::videoTitle)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    videoUIController.closeList()
                }
            }
        }
    }
}