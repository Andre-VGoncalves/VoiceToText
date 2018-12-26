package com.andrevieira.voicetotext

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import kotlinx.android.synthetic.main.activity_voice_comand.*
import java.util.*

class VoiceComandActivity : Activity() {

    companion object {
        const val MSG_SPEAK = "oi Fale alguma coisa que vai ser transcrita"
        const val REQ_CODE_SPEECH_INPUT = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_comand)
        button.setOnClickListener { startSpeak() }
    }

    fun askSpeakInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, MSG_SPEAK)

        startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == Activity.RESULT_OK) {
                    val result = data
                            ?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    msg.setText(result!![0])
                }
            }
        }

    }

    fun startSpeak() {
        askSpeakInput()
    }
}
