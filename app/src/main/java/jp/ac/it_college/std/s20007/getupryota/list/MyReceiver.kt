package jp.ac.it_college.std.s20007.getupryota.list

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            MyNotification.sendNotification(context)
        }

            //ここであぷりみる
    }

}