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

class GoogleActivity : ShareActivity() {
    override fun getShareLink( originalRequest : String ): String {
        return "https://" + resources.getString( R.string.google_url ) + "/search?&q=" + Uri.encode(originalRequest)
    }
}
