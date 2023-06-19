package mx.edu.itch.roomdatabase.ui.order

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import mx.edu.itch.roomdatabase.ProductRoomDataBase

class OrderRepository(application: Application) {
    val searchResults= MutableLiveData<List<Order>>()
    private var orderDao:OrderDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allOrder: LiveData<List<Order>>?

    init {
        val db: OrderRoomDataBase? = OrderRoomDataBase.getDataBase(application)
        orderDao=db?.orderDao()
        allOrder = orderDao?.getAllProducts()

    }
    fun insertOrder(order:Order){
        coroutineScope.launch(Dispatchers.IO){
            asyncInsertOrder(order)
        }
    }
    private fun asyncInsertOrder(order: Order){
        orderDao?.insertOrder(order)
    }

    fun deleteOrder(id: Int){
        coroutineScope.launch(Dispatchers.IO){
            asyncDeleteOrder(id)
        }
    }
    private fun asyncDeleteOrder(id: Int){
        orderDao?.deleteOrder(id)
    }

    fun findOrder(id: Int){
        coroutineScope.launch(Dispatchers.Main){
            searchResults.value = asyncFind(id).await()
        }
    }

    private fun asyncFind(id: Int): Deferred<List<Order>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async orderDao?.findOrder(id)
        }
}