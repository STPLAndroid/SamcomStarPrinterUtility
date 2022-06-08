package com.sampatparas.starprinterwrapperutility
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sampatparas.starprinterutility.interfaces.PrinterListCallBack
import com.sampatparas.starprinterutility.model.SearchResultInfo
import com.sampatparas.starprinterutility.searchPrinter.SearchPrinterUtils
import com.sampatparas.starprinterutility.ui.TestingPrintActivity

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent=Intent(this,TestingPrintActivity::class.java)
        startActivity(intent)
        finish()
        /*SearchPrinterUtils(this, object : PrinterListCallBack {
            override fun onSuccessSearchResult(result: MutableList<SearchResultInfo>?) {
                Log.e(TAG, "searchListSize "+result?.size)
            }
            override fun onFlailedResult(message: String?) {
                Log.e(TAG, " onFlailedResult $message")
            }
        }).startSearchAll()*/
    }
}