package mx.edu.itch.roomdatabase

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ProductRepository(application:Application) {
    val searchResults=MutableLiveData<List<Product>>()
    private var productDao:ProductDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allProducts:LiveData<List<Product>>?
    var productName: String=""


    init {
        val db:ProductRoomDataBase? = ProductRoomDataBase.getDataBase(application)
        productDao=db?.productDao()
        allProducts = productDao?.getAllProducts()

    }
    fun insertProduct(newProduct:Product){
        coroutineScope.launch(Dispatchers.IO){
            asyncInsert(newProduct)
        }
    }
    private fun asyncInsert(product: Product){
        productDao?.insertProduct(product)
    }

    fun deleteProduct(name:String){
        coroutineScope.launch(Dispatchers.IO){
            asyncDelete(name)
        }
    }
    fun findProductName(name: Int){
        productDao?.findProductName(name)
    }

    fun deleteProduct2(){
        coroutineScope.launch(Dispatchers.IO){
            asyncDelete2()
        }
    }

    private fun asyncDelete(name:String){
        productDao?.deleteProduct(name)
    }
    private fun asyncDelete2(){
        productDao?.deleteProduct2()
    }

    fun findProduct(name:String){
        coroutineScope.launch(Dispatchers.Main){
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name:String):Deferred<List<Product>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async productDao?.findProduct(name)
        }


}