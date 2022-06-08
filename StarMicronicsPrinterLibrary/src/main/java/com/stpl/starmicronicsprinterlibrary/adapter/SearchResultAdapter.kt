package com.stpl.starmicronicsprinterlibrary.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.stpl.starmicronicsprinterlibrary.R
import com.stpl.starmicronicsprinterlibrary.Utils.Const
import com.stpl.starmicronicsprinterlibrary.model.SearchResultInfo
import com.stpl.starmicronicsprinterlibrary.printerUtils.ModelCapability
import com.stpl.starmicronicsprinterlibrary.printerUtils.ModelConfirmDialogFragmentSample
import com.stpl.starmicronicsprinterlibrary.printerUtils.ModelSelectDialogFragment
import kotlinx.android.synthetic.main.row_printer_connection_list.view.*

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
            holder.itemView.modelNameTextView.text = modelName
        }
        holder.itemView.listPrinterInfoRow.setOnClickListener {
            val searchResultInfo = searchResultArray[position]
            searchResultInfo.confirmPrinter(activity)
            if (holder.itemView.checkedIconImageView.visibility == View.VISIBLE) {
                holder.itemView.checkedIconImageView.visibility = View.GONE
            } else {
                holder.itemView.checkedIconImageView.visibility = View.VISIBLE
                holder.itemView.checkedIconImageView.setImageResource(R.drawable.checked_icon)
            }
        }
    }

    override fun getItemCount(): Int {
        return searchResultArray.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}