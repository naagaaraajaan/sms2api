package com.nagi.sms2api.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class SMSReceiver : BroadcastReceiver() {
    companion object {
        private val TAG by lazy { SMSReceiver::class.java.simpleName }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (!intent?.action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) return
        val extractMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        extractMessages.forEach { smsMessage ->
            run {
                Log.v(TAG, smsMessage.messageBody)
                val messageBody = smsMessage.messageBody
                val client = OkHttpClient().newBuilder().build()
                val mediaType = MediaType.parse("application/json")
                val body = RequestBody.create(mediaType, JSONObject(mapOf("text" to messageBody.toString())).toString())
                val request = Request.Builder()
                    .url("http://192.168.1.4:5000/login") // Replace the Actual URL to which SMS need to be forwarded
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build()
                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Log.v(TAG, e.toString())
                    }

                    override fun onResponse(call: Call, response: Response) {
                        Log.v(TAG, response.toString())
                    }
                })

            }

        }

    }
}