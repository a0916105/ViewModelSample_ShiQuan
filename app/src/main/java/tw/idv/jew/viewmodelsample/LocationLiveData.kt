package tw.idv.jew.viewmodelsample

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.lifecycle.LiveData

class LocationLiveData(context: Context) : LiveData<Location>() {
    private val locationManager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val listener = LocationListener{
        value = it
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, listener)
    }

    override fun onInactive() {
        locationManager.removeUpdates(listener)
    }
}