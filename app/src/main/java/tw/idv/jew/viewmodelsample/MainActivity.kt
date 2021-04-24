package tw.idv.jew.viewmodelsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var factory: UserListViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //No factory
        val viewModel = ViewModelProvider(this)
            .get(UserListViewModel::class.java)
        println(viewModel.userList)

        //Factory
        val viewModelWithFactory = ViewModelProvider(this, factory)
            .get(UserListViewModel::class.java)
        println(viewModelWithFactory.userList)
    }
}