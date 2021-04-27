package tw.idv.jew.viewmodelsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    //No factory
    private val viewModel by viewModels<UserListViewModel>()

    //With factory provided
    /*lateinit var factory: UserListViewModel.Factory
    val viewModelWithFactory by viewModels<UserListViewModel> { factory }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

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
    }

    val nameList = MutableLiveData<List<String>>()
    val stringList = ArrayList<String>()
    fun fetch(query: String): LiveData<List<String>>{
        stringList.add("$query Wu")
        nameList.value = stringList
        return nameList
    }
}