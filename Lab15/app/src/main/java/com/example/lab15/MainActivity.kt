package com.example.lab15

// В файле MainActivity.kt

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button
    private lateinit var seekBar: SeekBar
    private lateinit var elapsedTime: TextView
    private lateinit var remainingTime: TextView

    private var totalTime: Int = 0
    private var handler = Handler()
    private var isUserSeeking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация элементов управления
        playButton = findViewById(R.id.playButton)
        pauseButton = findViewById(R.id.pauseButton)
        stopButton = findViewById(R.id.stopButton)
        seekBar = findViewById(R.id.seekBar)
        elapsedTime = findViewById(R.id.elapsedTime)
        remainingTime = findViewById(R.id.remainingTime)

        // Инициализация MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.gta)
        mediaPlayer.isLooping = true
        mediaPlayer.seekTo(0)
        totalTime = mediaPlayer.duration

        seekBar.max = totalTime

        // Обработчик кнопки Play
        playButton.setOnClickListener {
            mediaPlayer.start()
            updateSeekBar()
        }

        // Обработчик кнопки Pause
        pauseButton.setOnClickListener {
            mediaPlayer.pause()
        }

        // Обработчик кнопки Stop
        stopButton.setOnClickListener {
            mediaPlayer.pause()
            mediaPlayer.seekTo(0)
            seekBar.progress = 0
        }

        // Обработчик изменения положения ползунка
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    isUserSeeking = true
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Начало перемещения ползунка
                isUserSeeking = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Пользователь закончил перемещение ползунка
                isUserSeeking = false
                mediaPlayer.seekTo(seekBar?.progress ?: 0)
                updateSeekBar()
            }
        })
    }

    // Обновление позиции ползунка и времени
    private fun updateSeekBar() {
        if (!isUserSeeking && mediaPlayer.isPlaying) {
            seekBar.progress = mediaPlayer.currentPosition
            val elapsedTimeText = createTimeLabel(mediaPlayer.currentPosition)
            elapsedTime.text = elapsedTimeText
            val remainingTimeText = createTimeLabel(totalTime - mediaPlayer.currentPosition)
            remainingTime.text = "-$remainingTimeText"
        }

        // Повторяем обновление каждые 100 миллисекунд
        handler.postDelayed({ updateSeekBar() }, 100)
    }

    // Форматирование времени в формат "минуты:секунды"
    private fun createTimeLabel(time: Int): String {
        val minutes = time / 1000 / 60
        val seconds = time / 1000 % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        handler.removeCallbacksAndMessages(null)
    }
}
