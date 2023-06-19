package mx.edu.itch.roomdatabase.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.edu.itch.roomdatabase.Product
import mx.edu.itch.roomdatabase.ProductRepository

class MainViewModel(application: Application) :AndroidViewModel(application) {
    private val repository:ProductRepository= ProductRepository(application)
    private val allProducts:LiveData<List<Product>>?
    private val searchResult:MutableLiveData<List<Product>>

    init {
        allProducts = repository.allProducts
        searchResult = repository.searchResults
    }
    fun getProductName(name: Int){
        repository.findProductName(name)
    }
    fun insertProduct(product: Product) {
        repository.insertProduct(product)
    }

    fun findProduct(name: String) {
        repository.findProduct(name)
    }

    fun deleteProduct(name: String) {
        repository.deleteProduct(name)
    }
    fun deleteProduct2() {
        repository.deleteProduct2()
    }

    fun getSearchResults(): MutableLiveData<List<Product>> {
        return searchResult
    }

    fun getAllProducts(): LiveData<List<Product>>? {
        return allProducts
    }

}