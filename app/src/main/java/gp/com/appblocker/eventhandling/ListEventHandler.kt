package gp.com.appblocker.eventhandling

import android.content.Context
import android.view.View
import gp.com.appblocker.R
import gp.com.appblocker.backend.ApplicationInfoWrapper
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * This is class is used to handle the event handling of the ApplicationList and the floationNextActionButton in the
 * MainActivity.
 * So whenever a application is selected it toggles the selection of that application and adds it to the Map of the
 * selected applications.
 * Also if no application is selected then the nextActionButton is disabled.When the first application is selected then
 * shows the nextActionButton.
 */
class ListEventHandler : View.OnClickListener {

    val reference: Context
    val applicationsMap: Map<String, ApplicationInfoWrapper>

    constructor(reference: Context) {
        this.reference = reference
        this.applicationsMap = HashMap()
    }

    override fun onClick(view: View?) {
        val id = view?.id
        if(id == R.id.nextActionButton) {
            // Perform the tasks that needs to be performed when the floating action button is clicked....
        } else {
            // Perform the tasks that needs to be performed when any of the item in the list is clicked....
        }
    }
}