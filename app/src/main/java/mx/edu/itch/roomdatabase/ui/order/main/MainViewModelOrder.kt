package mx.edu.itch.roomdatabase.ui.order.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.edu.itch.roomdatabase.ui.order.Order
import mx.edu.itch.roomdatabase.ui.order.OrderRepository

class MainViewModelOrder(application: Application) : AndroidViewModel(application) {
    private val repository:OrderRepository= OrderRepository(application)
    private val allOrder: LiveData<List<Order>>?
    private val searchResult: MutableLiveData<List<Order>>

    init {
        allOrder = repository.allOrder
        searchResult = repository.searchResults
    }
    fun insertOrder(order: Order) {
        repository.insertOrder(order)
    }

    fun findOrder(id: Int) {
        repository.findOrder(id)
    }

    fun deleteOrder(id: Int) {
        repository.deleteOrder(id)
    }

    fun getSearchResults(): MutableLiveData<List<Order>> {
        return searchResult
    }

    fun getAllOrder(): LiveData<List<Order>>? {
        return allOrder
    }

}