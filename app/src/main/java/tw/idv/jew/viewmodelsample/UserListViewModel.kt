package tw.idv.jew.viewmodelsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserListViewModel(private val userRepository: UserRepository) : ViewModel() {
    val userList = userRepository.getUserInfo()

    class Factory(private val userRepository: UserRepository) : ViewModelProvider.Factory{
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserListViewModel(userRepository) as T
        }

    }
}

// User 倉庫
class UserRepository {

    fun getUserInfo(): MutableLiveData<User> {
        val user = MutableLiveData<User>()
        user.value = User("張三","18")
        return user
    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: UserRepository().also { instance = it }
            }
    }
}

// User 實體
data class User(val name:String,val age:String)