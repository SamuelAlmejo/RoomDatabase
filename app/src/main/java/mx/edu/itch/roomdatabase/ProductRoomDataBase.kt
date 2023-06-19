package mx.edu.itch.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mx.edu.itch.roomdatabase.ui.order.Order
import mx.edu.itch.roomdatabase.ui.order.OrderDao

@Database(entities = [(Product::class)], version =1)
abstract class ProductRoomDataBase: RoomDatabase() {
    abstract fun productDao():ProductDao

    companion object{
        private var INSTANCE:ProductRoomDataBase?=null
        internal fun getDataBase(context:Context):ProductRoomDataBase?{
         if(INSTANCE == null){
             synchronized((ProductRoomDataBase::class.java)){
                 if(INSTANCE == null) {
                     INSTANCE = Room.databaseBuilder(
                         context.applicationContext,
                         ProductRoomDataBase::class.java,
                         "product_database"
                     ).build()
                 }
             }
         }
         return INSTANCE
        }
    }
}