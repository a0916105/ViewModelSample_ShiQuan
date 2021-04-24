package tw.idv.jew.viewmodelsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    //No factory
    val viewModel by viewModels<UserListViewModel>()

    //With factory provided
    lateinit var factory: UserListViewModel.Factory
    val viewModelWithFactory by viewModels<UserListViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        //No factory
        /*val viewModel = ViewModelProvider(this)
            .get(UserListViewModel::class.java)
        println(viewModel.userList)*/

        //Factory
        /*val viewModelWithFactory = ViewModelProvider(this, factory)
            .get(UserListViewModel::class.java)
        println(viewModelWithFactory.userList)*/
    }
}