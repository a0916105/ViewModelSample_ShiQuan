package tw.idv.jew.viewmodelsample

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private var userAgreePermissionCode = 1

    //No factory
    private val viewModel by viewModels<UserListViewModel>()

    //With factory provided
    /*lateinit var factory: UserListViewModel.Factory
    val viewModelWithFactory by viewModels<UserListViewModel> { factory }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(LocationObserver())

        //No factory
        /*val viewModel = ViewModelProvider(this)
            .get(UserListViewModel::class.java)
        println(viewModel.userList)*/

        //Factory
        /*val viewModelWithFactory = ViewModelProvider(this, factory)
            .get(UserListViewModel::class.java)
        println(viewModelWithFactory.userList)*/

        viewModel.userListLiveData.observe(this){   list ->
            println(list)
        }

//        viewModel.userListLiveData.value = listOf("Peter", "Jane")

        //Transformations
        // map
        val textLiveData = MutableLiveData<String>()
        textLiveData.value = "Peter"
        val lengthLiveData: LiveData<Int> = Transformations.map(textLiveData){ it.length }
        //switchMap
        val listLiveData: LiveData<List<String>> =
            Transformations.switchMap(textLiveData) { fetch(it) }

        val locationLiveData = LocationLiveData(this)
        //check permission before observe
        val somePermission = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION)
        for (p in somePermission ){
            if(ActivityCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(this, somePermission , userAgreePermissionCode)
            break
        }

        locationLiveData.observe(this){ location ->
            updateLocation(location)
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            userAgreePermissionCode -> {
                for( i in 0..(grantResults.size-1) ){
                    if ((grantResults.isNotEmpty() && grantResults[i] == PackageManager.PERMISSION_GRANTED))
                        Log.i("Status:", "Agree a permission")
                    else
                        finish()
                }
                return
            }
        }
    }

    private fun updateLocation(location: Location?) {
        findViewById<TextView>(R.id.location).text = location.toString()
    }

    val nameList = MutableLiveData<List<String>>()
    val stringList = ArrayList<String>()
    fun fetch(query: String): LiveData<List<String>>{
        stringList.add("$query Wu")
        nameList.value = stringList
        return nameList
    }
}