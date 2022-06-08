package com.STPL.samcomstarprinterutility

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samcom.starprinterutility.ui.TestingPrintActivity
class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent=Intent(this, TestingPrintActivity::class.java)
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