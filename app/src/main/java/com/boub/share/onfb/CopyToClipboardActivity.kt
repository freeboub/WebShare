/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <freeboub@gmail.org> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Olivier Bouillet
 * ----------------------------------------------------------------------------
 */

package com.boub.share.onfb

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.widget.Toast


class CopyToClipboardActivity : ShareActivity() {
    override fun getShareLink( originalRequest : String ): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val clipboard: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(originalRequest, originalRequest)
            clipboard.setPrimaryClip(clip)
            globalError = ShareResult.Success
            Toast.makeText(this, R.string.copy_to_clipboard_text_copied, Toast.LENGTH_SHORT).show()
        }
        // FIXME We can a specific error message not supported on platform
        return "" // no need to return anything as no web link to open
    }
}
