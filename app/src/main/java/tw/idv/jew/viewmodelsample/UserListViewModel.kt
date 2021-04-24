package tw.idv.jew.viewmodelsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserListViewModel() : ViewModel() {
//class UserListViewModel(private val userRepository: UserRepository) : ViewModel() {

//    val userList = listOf("John", "Marry")
//    val userList = userRepository.getUserInfo()
//    val userListLiveData = MutableLiveData(listOf("John", "Marry"))
//    val userListLiveData = MutableLiveData<List<String>>()
    private val _userListLiveData = MutableLiveData<List<String>>()

    /*init {
        userListLiveData.value = listOf("John", "Marry")
    }*/
    val userListLiveData: LiveData<List<String>>
        get() = _userListLiveData

    /*class Factory(private val userRepository: UserRepository) : ViewModelProvider.Factory{
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserListViewModel(userRepository) as T
        }
    }*/
}

// User 倉庫
class UserRepository {

    fun getUserInfo(): MutableLiveData<List<String>> {
        val user = MutableLiveData<List<String>>()
        user.value = listOf("John", "Marry")
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