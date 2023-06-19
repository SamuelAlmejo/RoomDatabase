package mx.edu.itch.roomdatabase.ui.order.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itch.roomdatabase.R
import mx.edu.itch.roomdatabase.ui.order.Order

class OrderListAdapter(private val productItemLayout:Int): RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {
    private var orderList: List<Order>? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item: TextView = itemView.findViewById(R.id.product_row)
    }
    override fun onBindViewHolder(holder: OrderListAdapter.ViewHolder, position: Int) {
        val item= holder.item
        orderList.let {
            item.text = it!![position].orderProduct
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(productItemLayout,parent,false)
        return OrderListAdapter.ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(orders: List<Order>){
        orderList=orders
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (orderList== null) 0 else orderList!!.size
    }

}