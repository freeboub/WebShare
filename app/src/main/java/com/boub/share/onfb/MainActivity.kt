/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <freeboub@gmail.org> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Olivier Bouillet
 * ----------------------------------------------------------------------------
 */

package com.boub.share.onfb

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat

class MainActivity : AppCompatActivity() {

    private val _tag = "MainActivity"

    class MySettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

        private val feedbackBtn = "feedback"
        private val licenceBtn = "licences"
        private val shareBtn = "share"

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)

            /// Register mail button
            val btnMail: Preference? = findPreference(feedbackBtn)
            btnMail?.onPreferenceClickListener = sendMail

            /// Register licence button
            val btnLicence: Preference? = findPreference(licenceBtn)
            btnLicence?.onPreferenceClickListener = showLicence

            /// Register share button
            val btnShare: Preference? = findPreference(shareBtn)
            btnShare?.onPreferenceClickListener = shareApp

            /// register generic enable buttons
            val prefs = PreferenceManager.getDefaultSharedPreferences(this.context)

            for ((strKey, _) in prefs.all ) {
                val btnClickMe: SwitchPreferenceCompat? = this.findPreference(strKey)
                btnClickMe?.onPreferenceChangeListener = this
            }
        }

        override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
            setActivityEnable(preference?.key, newValue as Boolean)
            return true
        }


        private fun setActivityEnable(activityName: String?, enable: Boolean) {
            fun getEnableValue(enable: Boolean): Int {
                return if (enable) PackageManager.COMPONENT_ENABLED_STATE_ENABLED else PackageManager.COMPONENT_ENABLED_STATE_DISABLED
            }

            val packagename = this.requireActivity().packageName

            val component =
                ComponentName(packagename, "$packagename.$activityName")

            this.activity?.packageManager?.setComponentEnabledSetting(
                component,
                getEnableValue(enable),
                PackageManager.DONT_KILL_APP
            )
        }

        private val sendMail = Preference.OnPreferenceClickListener()
        {
            val strMailTo = resources.getString(R.string.str_mail_to)

            // Create the Intent
            val emailIntent = Intent(
                Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", strMailTo, null)
            )

            // Fill it with Data
            emailIntent.putExtra(
                Intent.EXTRA_EMAIL,
                arrayOf(strMailTo)
            )
            emailIntent.putExtra(
                Intent.EXTRA_SUBJECT,
                resources.getString(R.string.mail_title)
            )
            emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // Send it off to the Activity-Chooser
            startActivity(
                Intent.createChooser(
                    emailIntent,
                    resources.getString(R.string.text_send_mail)
                )
            )
            true
        }

        private val showLicence = Preference.OnPreferenceClickListener()
        {
            val component = ComponentName("com.boub.share.onfb", "com.boub.share.onfb.LicenceActivity")
            val intent = Intent( ).setComponent( component )
            startActivity( intent )
            true
        }

        private val shareApp = Preference.OnPreferenceClickListener()
        {
            val appUri: String = "https://play.google.com/store/apps/details?id=" + context?.applicationInfo?.packageName
            val intent = Intent( )
            intent.action = Intent.ACTION_SEND
            intent.data = Uri.parse( appUri )
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, appUri)

            startActivity( Intent.createChooser( intent, null ) )
            true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView( R.layout.main )

        supportFragmentManager.beginTransaction()
            .replace(R.id.settings_container, MySettingsFragment())
            .commit()

    }

}
