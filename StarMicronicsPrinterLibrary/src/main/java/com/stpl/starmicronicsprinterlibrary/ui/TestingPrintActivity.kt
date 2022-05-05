package com.stpl.starmicronicsprinterlibrary.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.stpl.starmicronicsprinterlibrary.R
import com.stpl.starmicronicsprinterlibrary.adapter.SearchResultAdapter
import com.stpl.starmicronicsprinterlibrary.customView.TestReceiptView
import com.stpl.starmicronicsprinterlibrary.interfaces.PrinterListCallBack
import com.stpl.starmicronicsprinterlibrary.model.SearchResultInfo
import com.stpl.starmicronicsprinterlibrary.printerUtils.StarPrinterUtils
import com.stpl.starmicronicsprinterlibrary.searchPrinter.PrinterConnectionTypesDialogFragment
import com.stpl.starmicronicsprinterlibrary.searchPrinter.SearchPrinterUtils

import kotlinx.android.synthetic.main.activity_testing_print.*

class TestingPrintActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "TestingPrintActivity"
    var searchResultArray: ArrayList<SearchResultInfo> = ArrayList()
    var adapter: SearchResultAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing_print)
        setOnClickListener()
    }


    private fun setOnClickListener() {
        printerName.setOnClickListener(this);
        buttonTestPrint.setOnClickListener(this);
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
                        textViewConnectionName.text = selectedType
                        searchResultArray.clear()
                        SearchPrinterUtils(this, object : PrinterListCallBack {
                            @SuppressLint("NotifyDataSetChanged")
                            override fun onSuccessSearchResult(result: MutableList<SearchResultInfo>?) {
                                recyclerViewListPrinter.visibility = View.VISIBLE
                                TextViewDataNotFound.visibility = View.GONE
                                if (adapter != null) {
                                    adapter?.notifyDataSetChanged()
                                } else {
                                    setAdapter()
                                }
                            }

                            override fun onFailureResult(message: String?) {
                                TextViewDataNotFound.text = message
                                TextViewDataNotFound.visibility = View.VISIBLE
                                recyclerViewListPrinter.visibility = View.GONE
                            }

                        }).searchPrinter(selectedType)
                    }
                dialog.show(supportFragmentManager, "")
            }
        }
    }

    private fun setAdapter() {
        adapter = SearchResultAdapter(searchResultArray, supportFragmentManager)
        recyclerViewListPrinter.adapter = adapter
    }
}