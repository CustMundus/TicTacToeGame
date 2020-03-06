package com.example.tictactoegame

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MusicService: Service() {
    var player: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        player = MediaPlayer.create(this, R.raw.intro)
        player?.isLooping = true

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player?.start()
        return Service.START_NOT_STICKY
    }

    override fun onDestroy() {
        player?.start()
        player?.release()
        stopSelf()

        super.onDestroy()
    }
}