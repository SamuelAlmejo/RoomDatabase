package mx.edu.itch.roomdatabase.ui.order

import android.widget.ListAdapter

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
class Order {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "orderId")
    var id:Int =0
    @ColumnInfo(name = "orderProduct")
    var orderProduct: String=""
    constructor()

    constructor(id: Int, orderProduct:String) {
        this.id = id
        this.orderProduct = orderProduct
    }

    constructor(orderProduct: String) {
        this.orderProduct = orderProduct
    }

}