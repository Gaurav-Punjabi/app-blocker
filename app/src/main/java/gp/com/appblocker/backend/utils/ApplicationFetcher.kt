package gp.com.appblocker.backend.utils

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
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

        private const val HOUR_RANGE : Int = 1000 * 3600 * 24

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
            // Querying the PackageManager to get all the InstalledApplications by passing 0 flags
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
         * @param context The context from which the active app needs to be identified.
         * @return The name of the application currently active,
         *         if no active application is found it returns null.
         *
         * This method is used to return the current active application on the given context by using the Usage Stats
         * Manager.
         */
        public fun getForegroundApplication(context: Context) : String? {
            val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
            val currentTime = System.currentTimeMillis()
            val usageStats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,currentTime - HOUR_RANGE, currentTime)
            if(usageStats != null) {
                val sortedMap = TreeMap<Long, UsageStats>()
                for(usageStat in usageStats) {
                    val time : Long = usageStat.lastTimeUsed
                    sortedMap.put(time, usageStat)
                }
                if(sortedMap.isNotEmpty()) {
                    val lastKey = sortedMap.lastKey()
                    val foregroundAppUsageStats = sortedMap.get(lastKey)
                    if(foregroundAppUsageStats != null) {
                        val applicationName = context.packageManager.getApplicationLabel(context.packageManager.getApplicationInfo(foregroundAppUsageStats.packageName, 0)).toString()
                        if(applicationName != null) {
                            return applicationName
                        }
                    }
                }
            }
            return null
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