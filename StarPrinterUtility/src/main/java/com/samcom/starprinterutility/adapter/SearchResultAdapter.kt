package com.samcom.starprinterutility.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.samcom.starprinterutility.R
import com.samcom.starprinterutility.databinding.RowPrinterConnectionListBinding
import com.samcom.starprinterutility.interfaces.SelectPrinterCallBack
import com.samcom.starprinterutility.model.SearchResultInfo

class SearchResultAdapter(
    private val activity: AppCompatActivity,
    private val searchResultArray: List<SearchResultInfo>,
    private var fragmentManager: FragmentManager?
) : RecyclerView.Adapter<SearchResultAdapter.MyViewHolder>() {
    var index = -1;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_printer_connection_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val searchResultInfo = searchResultArray[position]
        val modelName = searchResultInfo.modelName
        if (modelName != null) {
            holder.binding.modelNameTextView.text = modelName
        }
        holder.binding.listPrinterInfoRow.setOnClickListener {
            val searchResultInfo = searchResultArray[position]
            searchResultInfo.confirmPrinter(activity, object : SelectPrinterCallBack {
                override fun printerSelected() {
                    index = position
                    notifyDataSetChanged()
                }
            });
        }
        if (index == position) {
            holder.binding.checkedIconImageView.visibility = View.VISIBLE
        } else {
            holder.binding.checkedIconImageView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return searchResultArray.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = RowPrinterConnectionListBinding.bind(view)
    }
}