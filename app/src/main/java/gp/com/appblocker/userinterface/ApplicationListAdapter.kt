package gp.com.appblocker.userinterface

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import gp.com.appblocker.R
import gp.com.appblocker.backend.ApplicationInfoWrapper


/**
 * This is the custom implementation of the ArrayAdapter that holds the ApplicationInfoWrapper.
 * Just overriding the class so that the custom elements can be added to the given list.
 */
class ApplicationListAdapter : ArrayAdapter<ApplicationInfoWrapper> {
    public constructor(context: Context,list: List<ApplicationInfoWrapper>) : super(context,0,list)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        // Accessing the layout inflator from the given context
        val layoutInflater = super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // Checking whehter the layout inflator is accessed or not
        if(layoutInflater == null)
            return convertView!!;

        // inflating and applying the layout in the given view
        val view = layoutInflater.inflate(R.layout.list_layout, parent, false)

        // Accessing the applicationInfoWrapper for the current position
        val applicationInfoWrapper = super.getItem(position)

        // Accessing the textView and setting the applicationName on it
        val textView = view.findViewById<TextView>(R.id.applicationName)
        textView.text = applicationInfoWrapper.getApplicationName()

        // Setting the appropriate wrapper as the tag
        view.setTag(ApplicationListAdapter.WRAPPER_TAG_KEY, applicationInfoWrapper)

        // Accessing the ImageView and setting the applicationIcon on it
        val imageView = view.findViewById<ImageView>(R.id.applicationIcon)
        imageView.setImageDrawable(applicationInfoWrapper.getApplicationIcon())

        return view
    }

    companion object {
        public val WRAPPER_TAG_KEY = "WRAPPER_TAG".hashCode()
    }
}