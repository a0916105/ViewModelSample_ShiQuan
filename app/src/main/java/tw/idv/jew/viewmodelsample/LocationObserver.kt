package tw.idv.jew.viewmodelsample

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LocationObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connect(){
        //listen to location update
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnect(){
        // stop location listener
    }
}