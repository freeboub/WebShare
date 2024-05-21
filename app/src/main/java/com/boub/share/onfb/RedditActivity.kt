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

class RedditActivity : ShareActivity() {

    ///             "reddit"     -> "https://reddit.com/submit?url=" + encodedUrl + "&title=" + args["title"]

    override fun getShareLink( originalRequest : String ): String {
        var result = "https://reddit.com/submit?"
        val urlExtracted = getUrlFromText(originalRequest)
        result += if (urlExtracted == originalRequest) {
            // This sharing is only an url
            "url=" + Uri.encode(originalRequest)
        } else {
            // This sharing is text
            "title=" + Uri.encode(originalRequest)
        }
        return result
    }
}
