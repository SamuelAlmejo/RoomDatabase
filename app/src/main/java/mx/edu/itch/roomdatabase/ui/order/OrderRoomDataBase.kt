package mx.edu.itch.roomdatabase.ui.order

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mx.edu.itch.roomdatabase.ProductRoomDataBase

@Database(entities = [(Order::class)], version =1)
abstract class OrderRoomDataBase: RoomDatabase() {
    abstract fun orderDao():OrderDao
    companion object{
        private var INSTANCE: OrderRoomDataBase?=null
        internal fun getDataBase(context: Context): OrderRoomDataBase?{
            if(INSTANCE == null){
                synchronized((OrderRoomDataBase::class.java)){
                    if(INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            OrderRoomDataBase::class.java,
                            "order_database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}