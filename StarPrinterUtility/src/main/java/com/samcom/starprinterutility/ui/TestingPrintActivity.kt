package com.samcom.starprinterutility.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.samcom.starprinterutility.R
import com.samcom.starprinterutility.adapter.SearchResultAdapter
import com.samcom.starprinterutility.customView.TestReceiptView
import com.samcom.starprinterutility.databinding.ActivityTestingPrintBinding
import com.samcom.starprinterutility.interfaces.PrinterListCallBack
import com.samcom.starprinterutility.model.SearchResultInfo
import com.samcom.starprinterutility.printerUtils.StarPrinterUtils
import com.samcom.starprinterutility.searchPrinter.PrinterConnectionTypesDialogFragment
import com.samcom.starprinterutility.searchPrinter.SearchPrinterUtils
import java.util.*

class TestingPrintActivity : AppCompatActivity(), View.OnClickListener {
    private var binding: ActivityTestingPrintBinding? = null
    var searchResultArray: ArrayList<SearchResultInfo> = ArrayList()
    var adapter: SearchResultAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestingPrintBinding.inflate(layoutInflater);
        setContentView(binding?.root)
        setOnClickListener()
    }
    private fun setOnClickListener() {
        binding?.printerName?.setOnClickListener(this)
        binding?.buttonTestPrint?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonTestPrint -> {
                val test = TestReceiptView(this)
                test.setData("1234")
                StarPrinterUtils.printReceipts(
                    this, test
                ) { status, message ->
                    Log.e("TestingPrintActivity", "printer :: $message")
                }
            }
            R.id.printerName -> {
                val dialog: PrinterConnectionTypesDialogFragment =
                    PrinterConnectionTypesDialogFragment.newInstance { selectedType ->
                        binding?.textViewConnectionName?.text = selectedType
                        searchResultArray.clear()
                        SearchPrinterUtils(this, object : PrinterListCallBack {
                            @SuppressLint("NotifyDataSetChanged")
                            override fun onSuccessSearchResult(result: MutableList<SearchResultInfo>?) {
                                binding?.recyclerViewListPrinter?.visibility = View.VISIBLE
                                binding?.TextViewDataNotFound?.visibility = View.GONE
                                searchResultArray.clear()
                                searchResultArray.addAll(result!!)
                                if (adapter != null) {
                                    adapter?.notifyDataSetChanged()
                                } else {
                                    setAdapter()
                                }
                            }
                            override fun onFlailedResult(message: String?) {
                                binding?.TextViewDataNotFound?.text = message
                                binding?.TextViewDataNotFound?.visibility = View.VISIBLE
                                binding?.recyclerViewListPrinter?.visibility = View.GONE
                            }
                        }).searchPrinter(selectedType)
                    }
                dialog.show(supportFragmentManager, "")
            }
        }
    }
    private fun setAdapter() {
        adapter = SearchResultAdapter(this,searchResultArray, supportFragmentManager)
        binding?.recyclerViewListPrinter?.adapter = adapter
    }
}