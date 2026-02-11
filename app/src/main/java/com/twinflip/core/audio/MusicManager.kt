package com.twinflip.core.audio

import android.content.Context
import androidx.annotation.RawRes
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

class MusicManager(
    private val context: Context
) {

    private val player = ExoPlayer.Builder(context).build()

    fun play(@RawRes musicResId: Int, loop: Boolean = true, volume: Float = 1f) {
        if (player.isPlaying) return
        val uri = "android.resource://${context.packageName}/$musicResId"
        val mediaItem = MediaItem.Builder()
            .setUri(uri)
            .build()

        player.setMediaItem(mediaItem)
        player.volume = volume.coerceIn(0f,1f)
        player.prepare()
        player.playWhenReady = true
        player.repeatMode = if (loop) Player.REPEAT_MODE_ALL else Player.REPEAT_MODE_OFF
    }

    fun release() {
        player.release()
    }
    fun resume() {
        if (!player.isPlaying) player.playWhenReady = true
    }

    fun pauseMusic() {
        if (player.isPlaying) player.playWhenReady = false
    }

    fun stopMusic() {
        player.stop()
    }

}