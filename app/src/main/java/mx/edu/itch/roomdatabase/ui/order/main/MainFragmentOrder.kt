package mx.edu.itch.roomdatabase.ui.order.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.edu.itch.roomdatabase.R
import mx.edu.itch.roomdatabase.databinding.FragmentOrdenBinding
import mx.edu.itch.roomdatabase.ui.main.MainFragment

class MainFragmentOrder: Fragment() {

    companion object {
        fun newInstance() = MainFragmentOrder()
    }
    private var adapter:OrderListAdapter?=null
    //private lateinit var viewModel: MainViewModel
    private val viewModel:MainViewModelOrder by viewModels()
    private var _binding:FragmentOrdenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdenBinding.inflate(inflater,container,false)
        return  binding.root
        //return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenerSetup()
        observerSetup()
        recyclerSetup()
        
    }

    //Clear visual components on UI
    private fun clearFields(){
        binding.orderID.text=""
    }

    //Adding the Button Listeners
    private fun listenerSetup(){

        binding.findBtnOrder.setOnClickListener {
            viewModel.findOrder(Integer.parseInt(binding.orderID.text.toString()))
        }

        binding.deleteBtnOrder.setOnClickListener {
            viewModel.deleteOrder(Integer.parseInt(binding.orderID.text.toString()))
            clearFields()
        }



    }

    //Adding LiveData Observers
    private fun observerSetup(){
        viewModel.getAllOrder()?.observe(viewLifecycleOwner){
                orders->orders.let{adapter?.setProductList(it)}
        }

        viewModel.getSearchResults().observe(viewLifecycleOwner){
                products-> products.let {
            if(it.isNotEmpty()){
                binding.orderID.text = (it[0].orderProduct)

            }else{
                binding.orderID.text=""
                binding.orderID.hint="No Match"
            }
        }
        }


    }

    //Initializing the RecyclerView
    private fun recyclerSetup(){
        adapter = OrderListAdapter(R.layout.product_list_item)
        binding.orderRecycler.layoutManager = LinearLayoutManager(context)
        binding.orderRecycler.adapter = adapter
    }


}