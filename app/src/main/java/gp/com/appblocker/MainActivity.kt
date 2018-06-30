package gp.com.appblocker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import gp.com.appblocker.backend.utils.ApplicationFetcher
import gp.com.appblocker.userinterface.ApplicationListAdapter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.applicationList)

        // UNIT TESTING
        val listView = findViewById<ListView>(R.id.applicationList)
        if(listView != null) {
            val installedApplicationList = ApplicationFetcher.getInstalledApplications(this)
            Log.d("ActivityManager","Just Checking the returned list : " + installedApplicationList.size)
            val applicationListAdapter = ApplicationListAdapter(this,installedApplicationList)
            listView.adapter = applicationListAdapter
            Log.d("ActivityManager","Adapter config successfully")
        } else {
            Log.d("ActivityManager", "The listView could not be accessed")
        }
    }
}
