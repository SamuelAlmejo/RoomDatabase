package mx.edu.itch.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ProductDao {
    @Insert
    fun insertProduct(product: Product)

    @Query("SELECT * FROM products WHERE productName= :name")
    fun findProduct(name:String):List<Product>

    @Query("SELECT productName FROM products WHERE productId= :name")
    fun findProductName(name:Int):String

    @Query("DELETE FROM products WHERE productName= :name")
    fun deleteProduct(name: String)

    @Query("DELETE FROM products")
    fun deleteProduct2()

    @Query("SELECT * FROM products")
    fun getAllProducts():LiveData<List<Product>>
}