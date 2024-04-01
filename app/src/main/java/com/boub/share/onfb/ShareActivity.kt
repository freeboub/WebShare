/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <freeboub@gmail.org> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Olivier Bouillet
 * ----------------------------------------------------------------------------
 */

package com.boub.share.onfb

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.TextView
import java.util.regex.Matcher

abstract class ShareActivity : Activity() {

    private val _tag = "ShareActivity"

    enum class ShareResult {
        // SUCCESS
        Success,

        // Generic error
        ErrorUnknown,
        ErrorNoBrowserInstalled,

        // facebook errors
        ErrorFacebookUrlInvalid

    }

    protected var globalError : ShareResult = ShareResult.ErrorUnknown

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /// Parse intent
        if (intent.action == Intent.ACTION_SEND) {
            val strData = intent.getStringExtra(Intent.EXTRA_TEXT)
            if (strData != null && strData.isNotEmpty()) {
                /// Build new intent
                val intentToSend = Intent(Intent.ACTION_VIEW)
                val strLink = getShareLink(strData)
                Log.i(_tag, "opening $strLink")

                if (strLink.isNotEmpty()) {

                    intentToSend.data = Uri.parse(strLink)
                    globalError = try {
                        startActivity(intentToSend)
                        ShareResult.Success
                    } catch (e: Exception) {
                        ShareResult.ErrorNoBrowserInstalled
                    }
                } else {
                    // Should never happen
                    Log.e(_tag, "empty link")
                }
            } else {
                // Should never happen
                Log.e(_tag, "nothing to share")
            }
        } else {
            // Should never happen
            Log.e(_tag, "unknown action $intent.action")
        }

        fun displayError( text : Int )
        {
            setContentView(R.layout.failed_layout)
            val errorMessage : TextView = findViewById(R.id.error_message)
            errorMessage.setText( text )
        }

        /// handle result
        when ( globalError )
        {
            ShareResult.ErrorFacebookUrlInvalid -> displayError( R.string.error_facebook_shortcut_invalid )
            ShareResult.ErrorNoBrowserInstalled -> displayError( R.string.error_no_browser_installed )
            ShareResult.ErrorUnknown -> displayError( R.string.error_unknown ) /// Should not happen
            ShareResult.Success -> finish()
        }
    }

    /// Helper to extract the first http url in string
    fun getUrlFromText(text : String)  : String
    {
        /// We must extract url from content
        val m : Matcher = Patterns.WEB_URL.matcher( text )
        return if ( m.find()) // Can be a loop
        {
            val urlTmp : String = m.group()
            urlTmp
        } else {
            ""
        }
    }

    abstract fun getShareLink(originalRequest: String): String

    /// Handler to exit application in case of error
    fun finish(view: View) { finish() }
}
