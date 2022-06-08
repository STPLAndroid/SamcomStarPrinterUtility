package com.samcom.starprinterutility.customView
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.samcom.starprinterutility.R
import com.samcom.starprinterutility.databinding.TestReceiptViewBinding
class TestReceiptView : LinearLayoutCompat {
    private var binding: TestReceiptViewBinding? = null
    constructor(context: Context) : super(context) {
        setup()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setup()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setup()
    }
    private fun setup() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding= TestReceiptViewBinding.inflate(inflater)
        inflater.inflate(R.layout.test_receipt_view, this, true)
        overrideFonts(context, binding!!.llBase)
    }
    private fun overrideFonts(context: Context, v: View) {
        try {
            if (v is ViewGroup) {
                val vg = v as ViewGroup
                for (i in 0 until vg.childCount) {
                    val child: View = vg.getChildAt(i)
                    overrideFonts(context, child)
                }
            } else if (v is TextView) {
                v.typeface = Typeface.createFromAsset(context.assets, "bold.ttf")
                v.setTextColor(Color.BLACK)
            }
        } catch (e: Exception) {
        }
    }
    fun setData(orderID:String) {
        binding?.tvOrderId?.text = "ORDER ID $orderID"
    }
}