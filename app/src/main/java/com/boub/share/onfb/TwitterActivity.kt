/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <freeboub@gmail.org> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Olivier Bouillet
 * ----------------------------------------------------------------------------
 */

package com.boub.share.onfb

import android.net.Uri

class TwitterActivity : ShareActivity() {

    // Logo:
    // https://search.creativecommons.org/photos/ac2687ae-6efb-4521-b2c7-e673835447f0
    // https://upload.wikimedia.org/wikipedia/commons/0/06/Cib-twitter_%28CoreUI_Icons_v1.0.0%29.svg
    // Licence: CC BY 4.0

    override fun getShareLink( originalRequest : String ): String {
        var result = "https://twitter.com/intent/tweet?"
        val urlExtracted = getUrlFromText(originalRequest)
        result += if (urlExtracted == originalRequest) {
            // This sharing is only an url
            "url=" + Uri.encode(originalRequest)
        } else {
            // This sharing is text
            "text=" + Uri.encode(originalRequest)
        }
        return result
//            return "https://twitter.com/intent/tweet?url=" + encodedUrl + "&text=" + args["text"] + "&via=" + args["via"] + "&hashtags=" + args["hash_tags"]
    }
}
