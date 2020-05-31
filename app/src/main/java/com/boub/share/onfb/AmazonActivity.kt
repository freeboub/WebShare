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

class AmazonActivity : ShareActivity() {
    override fun getShareLink( originalRequest : String ): String {
        /// ie, amazon.com doesn't redirect to .fr
        return "https://" + resources.getString( R.string.amazon_url ) + "/s?k=" + Uri.encode(originalRequest)
    }
}
