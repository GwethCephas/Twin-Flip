package com.twinflip.core.audio

import android.content.Context
import android.media.SoundPool
import com.twinflip.R

class SoundManager(
     context: Context
) {
    private val soundPool = SoundPool.Builder()
        .setMaxStreams(4)
        .build()

    private val sounds = mapOf(
        GameSound.MATCH_SUCCESS to soundPool.load(context, R.raw.sfx_matching_cards, 1),
        GameSound.LEVEL_COMPLETE to soundPool.load(context, R.raw.sfx_levelcomplete, 1),
        GameSound.BUTTON_TAP to soundPool.load(context, R.raw.sfx_button, 1)
    )
    var isMuted = false

    fun playSound(sound: GameSound) {
        if (isMuted) return
        sounds[sound]?.let { soundId ->
            soundPool.play(soundId, 1f, 1f, 1, 0, 1f)
        }
    }

    fun releaseSound() {
        soundPool.release()
    }

}