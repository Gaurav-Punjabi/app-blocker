package gp.com.appblocker.backend.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import gp.com.appblocker.backend.ApplicationInfoWrapper
import java.util.*
import kotlin.collections.ArrayList

/**
 * This is class is used to provide the following functionalities
 *      -   A List of Installed User Applications
 *      -   Recently Opened Application (Can be used to detect the current foreground application
 */

class ApplicationFetcher {

    /*******************************************************************************************************************
     *                                          STATIC METHODS
     ******************************************************************************************************************/
    companion object {
        /**
         * @param context The current context of the application.
         * @return A list of User Installed Applications in the form of a custom wrapper.
         *
         * This method is used to return a list of User Installed Applications by accessing the Package manager of the
         * current context.It also excludes the pre-installed System-Applications by checking the flag of the
         * particular ApplicationInfo.
         */
        public fun getInstalledApplications(context: Context) : List<ApplicationInfoWrapper> {
            val packageManager = context.packageManager
            if(packageManager == null)
                return Collections.emptyList<ApplicationInfoWrapper>()
            val applications = packageManager.getInstalledApplications(0)
            if(applications == null)
                return Collections.emptyList<ApplicationInfoWrapper>()
            val installedApplications = ArrayList<ApplicationInfoWrapper>()
            for(applicationInfo in applications) {
                if(!isSystemApplication(applicationInfo)) {
                    val applicationName = packageManager.getApplicationLabel(applicationInfo).toString()
                    val applicationIcon = packageManager.getApplicationIcon(applicationInfo)
                    installedApplications.add(ApplicationInfoWrapper(applicationName,applicationIcon))
                }
            }
            return installedApplications
        }

        /**
         * @param applicationInfo The ApplicationInfo of the respective application.
         * @return If the application is SystemApplication then true, else it returns false.
         *
         * This is just a service method that is used to indicate whether the given application is an System Installed
         * Application or a User Installed Application.
         */
        private fun isSystemApplication(applicationInfo: ApplicationInfo) :Boolean {
            if((applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0)
                return true
            return false
        }
    }
}