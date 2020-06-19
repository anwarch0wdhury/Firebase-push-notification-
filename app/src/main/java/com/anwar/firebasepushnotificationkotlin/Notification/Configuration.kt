package com.anwar.firebasepushnotificationkotlin.Notification

class Configuration {
//companion objects have over static members is that they can inherit from other classes or implement interfaces and generally behave like any other singleton.
    companion object {
        // global topic to receive app wide push notifications
        val TOPIC_GLOBAL = "global"



        // id to handle the notification in the notification tray
        const val NOTIFICATION_ID = 100
        const val NOTIFICATION_ID_BIG_IMAGE = 101


    }

}