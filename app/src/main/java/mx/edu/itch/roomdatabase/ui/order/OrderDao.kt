package mx.edu.itch.roomdatabase.ui.order

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import mx.edu.itch.roomdatabase.Product

@Dao
interface OrderDao {
    @Insert
    fun insertOrder(order: Order)

    @Query("SELECT * FROM orders WHERE orderId= :id")
    fun findOrder(id: Int):List<Order>?

    @Query("DELETE FROM orders WHERE orderId= :id")
    fun deleteOrder(id: Int)

    @Query("SELECT * FROM orders")
    fun getAllProducts(): LiveData<List<Order>>?


}