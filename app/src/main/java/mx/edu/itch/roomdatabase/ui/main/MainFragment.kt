package mx.edu.itch.roomdatabase.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.edu.itch.roomdatabase.Product
import mx.edu.itch.roomdatabase.R
import mx.edu.itch.roomdatabase.databinding.FragmentMainBinding
import mx.edu.itch.roomdatabase.ui.order.Order
import mx.edu.itch.roomdatabase.ui.order.main.MainFragmentOrder
import mx.edu.itch.roomdatabase.ui.order.main.MainViewModelOrder
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var adapter:ProductListAdapter?=null
    //private lateinit var viewModel: MainViewModel
    private val viewModel:MainViewModel by viewModels()
    private var _binding:FragmentMainBinding? = null
    private val binding get() = _binding!!
    val lista:ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return  binding.root
        //return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenerSetup()
        observerSetup()
        recyclerSetup()
        listenerSetupOrder()
    }

    //Clear visual components on UI
    private fun clearFields(){
        binding.productMsg.text=""
        binding.productName.setText("")
        binding.productQuantity.setText("")
    }

    //Adding the Button Listeners
    private fun listenerSetupOrder(){

        binding.saveOrder.setOnClickListener {
            val recycler = binding.productRecycler
            var cadena=""


            if (recycler.isNotEmpty()){
                val recycler2=viewModel.getAllProducts()
                val order= Order(cadena)
                val viewModelOrder:MainViewModelOrder by viewModels()
                viewModelOrder.insertOrder(order)
                clearFields2()
                viewModel.deleteProduct2()


            }else{
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.container, MainFragmentOrder())
                transaction?.commit()

            }


        }
    }

    private fun clearFields2() {

    }

    private fun listenerSetup(){
        binding.addBtn.setOnClickListener {
            val name = binding.productName.text.toString()
            val quantity = binding.productQuantity.text.toString()
            if(name != "" && quantity != ""){
                val product= Product(name,Integer.parseInt(quantity))
                viewModel.insertProduct(product)

                clearFields()
            }else{
                binding.productMsg.text ="Incompleteinformation"
            }
        }

        binding.findBtn.setOnClickListener {
            viewModel.findProduct(binding.productName.text.toString())
        }

        binding.deleteBtn.setOnClickListener {
            viewModel.deleteProduct(binding.productName.text.toString())
            clearFields()
        }



    }

    //Adding LiveData Observers
    private fun observerSetup(){
        viewModel.getAllProducts()?.observe(viewLifecycleOwner){
                products->products.let{adapter?.setProductList(it)}
        }

        viewModel.getSearchResults().observe(viewLifecycleOwner){
                products-> products.let {
            if(it.isNotEmpty()){
                binding.productMsg.text= String.format(Locale.US,"%d",it[0].id)
                binding.productName.setText(it[0].productName)
                binding.productQuantity.setText(String.format(Locale.US,"%d",it[0].quantity))

            }else{
                binding.productMsg.text="No Match"
            }
        }
        }


    }

    //Initializing the RecyclerView
    private fun recyclerSetup(){
        adapter = ProductListAdapter(R.layout.product_list_item)
        binding.productRecycler.layoutManager = LinearLayoutManager(context)
        binding.productRecycler.adapter = adapter
    }

}