package com.sampatparas.starprinterutility.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.sampatparas.starprinterutility.R
import com.sampatparas.starprinterutility.Utils.Const
import com.sampatparas.starprinterutility.databinding.RowPrinterConnectionListBinding
import com.sampatparas.starprinterutility.model.SearchResultInfo
import com.sampatparas.starprinterutility.printerUtils.ModelCapability
import com.sampatparas.starprinterutility.printerUtils.ModelConfirmDialogFragmentSample
import com.sampatparas.starprinterutility.printerUtils.ModelSelectDialogFragment

class SearchResultAdapter(
    private val activity: AppCompatActivity,

    private val searchResultArray: List<SearchResultInfo>,
    private var fragmentManager: FragmentManager?
) : RecyclerView.Adapter<SearchResultAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_printer_connection_list, parent, false)
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
            searchResultInfo.confirmPrinter(activity);
            if (holder.binding.checkedIconImageView.visibility == View.VISIBLE) {
                holder.binding.checkedIconImageView.visibility = View.GONE

            } else {
                holder.binding.checkedIconImageView.visibility = View.VISIBLE
                holder.binding.checkedIconImageView.setImageResource(R.drawable.checked_icon)
            }
        }
    }

    override fun getItemCount(): Int {
        return searchResultArray.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = RowPrinterConnectionListBinding.bind(view)
    }
}