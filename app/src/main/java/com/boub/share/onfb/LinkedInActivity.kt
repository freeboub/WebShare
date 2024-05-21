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

class LinkedInActivity : ShareActivity() {
    override fun getShareLink( originalRequest : String ): String {
        var result = "https://www.linkedin.com/shareArticle?mini=true&"
        val urlExtracted = getUrlFromText(originalRequest)
        result += if (urlExtracted == originalRequest) {
            // This sharing is only an url
            "url=" + Uri.encode(originalRequest)
        } else {
            // This sharing is text
            "summary=" + Uri.encode(originalRequest)
        }
        return result
    }
}
