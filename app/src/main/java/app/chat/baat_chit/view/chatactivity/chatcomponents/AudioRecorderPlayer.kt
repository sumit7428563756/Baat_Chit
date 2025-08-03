package app.chat.baat_chit.view.chatactivity.chatcomponents

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Looper
import android.os.Handler


class AudioRecorderPlayer {

    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var outputFile: String? = null
    private var startTime: Long = 0L
    private var handler: Handler? = null
    private var updateRunnable: Runnable? = null

    fun startRecording(context: Context) {
        startTime = System.currentTimeMillis()
        if (mediaRecorder != null) return
        outputFile = "${context.externalCacheDir?.absolutePath}/audiorecord.3gp"

        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(outputFile)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            prepare()
            start()
        }
    }

    fun getAmplitude(): Int {
        return mediaRecorder?.maxAmplitude ?: 0 // Returns 0–32767
    }


    fun stopRecording() {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
    }


    fun playRecording(path: String, onProgress: (Float) -> Unit, onComplete: () -> Unit) {
        stopPlaying()

        mediaPlayer = MediaPlayer().apply {
            setDataSource(path)
            prepare()
            start()

            val duration = duration
            handler = Handler(Looper.getMainLooper())
            updateRunnable = object : Runnable {
                override fun run() {
                    if (isPlaying()) {
                        val progress = currentPosition.toFloat() / duration
                        onProgress(progress)
                        handler?.postDelayed(this, 200)
                    } else {
                        // Playback finished
                        onProgress(1f) // Show full bar
                        onComplete()   // Notify Compose to update UI
                    }
                }
            }
            handler?.post(updateRunnable!!)
        }
    }

fun stopPlaying() {
    mediaPlayer?.stop()
    mediaPlayer?.release()
    mediaPlayer = null
    handler?.removeCallbacks(updateRunnable!!)
}



    fun getRecordingFilePath(): String {
        return outputFile ?: ""
    }


    fun isPlaying(): Boolean {
        return mediaPlayer?.isPlaying ?: false
    }

    fun getDurationInSec(): Int {
        // rough logic, improve if needed
        return ((System.currentTimeMillis() - startTime) / 1000).toInt()
    }
}


