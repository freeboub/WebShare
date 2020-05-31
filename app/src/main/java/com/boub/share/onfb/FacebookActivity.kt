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

class FacebookActivity : ShareActivity() {

     //// Sample link url
    // https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fwww.lemonde.fr%2Fclimat%2Farticle%2F2020%2F01%2F10%2Femmanuel-macron-souhaite-un-referendum-sur-des-propositions-de-la-convention-sur-le-climat_6025478_1652612.html

    override fun getShareLink( originalRequest : String ): String {
        val url2 = getUrlFromText(originalRequest)
        if (url2 == "") {
            globalError = ShareResult.ErrorFacebookUrlInvalid
            return ""
        }
        return "http://www.facebook.com/sharer.php?u=" + Uri.encode(url2)
    }
}
