package gp.com.appblocker.backend

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon


/**
 * This is just a wrapper for encapsulating the information of the application such as
 *      1 - Application Name
 *      2 - Application Icon
 *  So this is basically a data class for holding some data together.
 */
class ApplicationInfoWrapper {

    /*******************************************************************************************************************
                                                    VARIABLE DECLARATION
     ******************************************************************************************************************/
    private var applicationName: String
    private var applicationIcon: Drawable

    /*******************************************************************************************************************
                                                        CONSTRUCTORS
     ******************************************************************************************************************/
    public constructor(applicationName: String, applicationIcon: Drawable) {
        this.applicationIcon = applicationIcon
        this.applicationName = applicationName
    }

    /*******************************************************************************************************************
                                                        GETTERS AND SETTERS
     ******************************************************************************************************************/
    public fun getApplicationName() : String {
        return this.applicationName
    }
    public fun getApplicationIcon() : Drawable {
        return this.applicationIcon
    }
    public fun setApplicationName(applicationName: String) {
        this.applicationName = applicationName
    }
    public fun setApplicationIcon(applicationIcon: Drawable) {
        this.applicationIcon = applicationIcon
    }

}