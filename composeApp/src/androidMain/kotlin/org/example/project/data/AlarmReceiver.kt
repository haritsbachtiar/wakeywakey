package org.example.project.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.example.project.AlarmActivity
import org.example.project.MainActivity

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        /** Navigate to the AlarmTriggerScreen */
        val notificationHandler = NotificationHandler(context!!)
        notificationHandler.start(MainActivity::class.java, "TEST ALARM")

        val intent = Intent(context, AlarmActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        context.startActivity(intent)

        println("ALARM RECEIVE")
//        val alarmIntent = Intent(context, MainActivity::class.java).apply {
////            flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)// Required to start an Activity from a Receiver
//        }
//
//        val hour = intent?.getIntExtra("hour", 0)
//        val minute = intent?.getIntExtra("minute", 0)
//
//        alarmIntent.putExtra("alarm", true)
//        alarmIntent.putExtra("hour", hour)
//        alarmIntent.putExtra("minute", minute)
//
//        println("ALARM RECEIVE END")
//        context?.startActivity(alarmIntent)
    }
}