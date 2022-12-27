package com.example.goodday

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.goodday.user.HealthTrack
import com.example.goodday.user.User
import com.example.goodday.user.profile
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList


class VisualizationDashboard : AppCompatActivity() {

    lateinit var line_chart: LineChart
    lateinit var linelist:ArrayList<Entry>
    lateinit var lineDataSet: LineDataSet
    lateinit var lineData: LineData

    lateinit var bar_chart: BarChart
    lateinit var barlist:ArrayList<BarEntry>
    lateinit var barDataSet: BarDataSet
    lateinit var barData: BarData
    lateinit var btn_back: ImageButton

    lateinit var reference: DatabaseReference
    lateinit var reference2: DatabaseReference
    lateinit var uid: String
    lateinit var user: FirebaseUser
    lateinit var arrayList_date: ArrayList<Float>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        setContentView(R.layout.activity_visualization_dashboard)

        line_chart = findViewById(R.id.line_chart)
        bar_chart = findViewById(R.id.bar_chart)
        btn_back = findViewById(R.id.btn_back)

        user = FirebaseAuth.getInstance().currentUser!!
        uid = user.uid
        arrayList_date = arrayListOf()
        reference = FirebaseDatabase.getInstance().getReference("Health_Track")
        reference2 = FirebaseDatabase.getInstance().getReference("Health_Track")

        val date = getdate()

        reference.child(uid).limitToLast(7).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for (dateSnapshot in snapshot.children){
                        val track  = dateSnapshot.getValue(HealthTrack::class.java) as HealthTrack
                        val healthScore = track.healthScore
                        if (healthScore != null) {
                            arrayList_date.add(healthScore)
                            }
                        }
                    }

                Log.d("arrayList_date", arrayList_date.toString())
                lineChart()
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        reference2.child(uid).child(date).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    val health = snapshot.getValue(HealthTrack::class.java) as HealthTrack
                    val fGlucose1: Float? = health.fGlucose
                    val tGlucose1: Float? = health.tGlucose
                    val hemoglobin1: Float? = health.hemoglobin
                    val urine1: Float? = health.urine

                    barchart1(fGlucose1!!,tGlucose1!!,hemoglobin1!!,urine1!!)
                }else{
                    Log.d("exist","not exist")
                }

            }
            override fun onCancelled(error: DatabaseError) {}
        })


        btn_back.setOnClickListener { finish() }

    }

    fun lineChart()
    {
        linelist = ArrayList()
        for(i in arrayList_date.indices){
            val num = (i+1).toFloat()
            linelist.add(Entry(num, arrayList_date[i]))
        }

        var xAxis_line: XAxis = line_chart.getXAxis()

        xAxis_line.setValueFormatter(object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase): String {
                var label = ""
                if (value == 1f)
                    label = "1"
                else if (value == 2f)
                    label = "2"
                else if (value == 3f)
                    label = "3"
                else if (value == 4f)
                    label = "4"
                else if (value == 5f)
                    label = "5"
                else if (value == 6f)
                    label = "6"
                else if (value == 7f)
                    label = "7"
                return label
            }
        })

        lineDataSet = LineDataSet(linelist, "Health Score")
        lineData = LineData(lineDataSet)
        line_chart.data = lineData
        lineDataSet.color = Color.rgb(242,143,59)
        lineDataSet.valueTextColor = Color.BLACK
        lineDataSet.valueTextSize = 8f
        lineDataSet.setDrawFilled(true)
        lineDataSet.fillColor = Color.rgb(242,143,59)
        lineDataSet.fillAlpha = 255
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER)

        // background color
        line_chart.setBackgroundColor(Color.WHITE)

        // disable description text
        line_chart.getDescription().setEnabled(false)

        // enable touch gestures
        line_chart.setTouchEnabled(true)

        // enable scaling and dragging
        line_chart.setDragEnabled(true)
        line_chart.setScaleEnabled(true)

        // force pinch zoom along both axis
        line_chart.setPinchZoom(true)

        var yAxis: YAxis = line_chart.getAxisLeft()

        // disable dual axis (only use LEFT axis)
        line_chart.getAxisRight().setEnabled(false)

        // horizontal grid lines
        xAxis_line.enableGridDashedLine(10f, 10f, 0f)
        yAxis.enableGridDashedLine(10f, 10f, 0f)

        // axis range
        yAxis.axisMaximum = 100f
        yAxis.axisMinimum = 0f

        //Create Limit Lines //

        val ll1 = LimitLine(60f, "standard")
        ll1.lineWidth = 1f
        ll1.enableDashedLine(2f, 0f, 0f)
        ll1.labelPosition = LimitLabelPosition.LEFT_TOP
        ll1.textColor = Color.BLACK
        ll1.lineColor = Color.GRAY
        ll1.textSize = 10f


        // draw limit lines behind data instead of on top
        yAxis.setDrawLimitLinesBehindData(false)

        // add limit lines
        yAxis.addLimitLine(ll1)
        //xAxis.addLimitLine(llXAxis);
    }

    fun barchart1(fGlucose:Float,tGlucose:Float,hemoglobin:Float,urine:Float){
        bar_chart.getDescription().setEnabled(false)

        // scaling can now only be done on x- and y-axis separately
        bar_chart.setPinchZoom(false)

        bar_chart.setDrawBarShadow(false)
        bar_chart.setDrawGridBackground(false)

        val xAxis_bar: XAxis = bar_chart.getXAxis()
        xAxis_bar.position = XAxisPosition.BOTTOM
        xAxis_bar.setDrawGridLines(false)

        xAxis_bar.setValueFormatter(object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase): String {
                var label = ""
                return label
            }
        })

        bar_chart.getAxisLeft().setDrawGridLines(false)
        val entries: ArrayList<BarEntry> = ArrayList()
        entries.add(BarEntry(1f, fGlucose))
        val entries2: ArrayList<BarEntry> = ArrayList()
        entries2.add(BarEntry(2f, tGlucose))
        val entries3: ArrayList<BarEntry> = ArrayList()
        entries3.add(BarEntry(3f, hemoglobin))
        val entries4: ArrayList<BarEntry> = ArrayList()
        entries4.add(BarEntry(4f, urine))
        val bars: MutableList<IBarDataSet> = ArrayList()
        val dataset = BarDataSet(entries, "Glucose(fast)")
        dataset.color = Color.rgb(88,139,139)
        bars.add(dataset)
        val dataset2 = BarDataSet(entries2, "Glucose(2 hours)")
        dataset2.color = Color.rgb(200,85,61)
        bars.add(dataset2)
        val dataset3 = BarDataSet(entries3, "Glycosylated")
        dataset3.color = Color.rgb(242,143,59)
        bars.add(dataset3)
        val dataset4 = BarDataSet(entries4, "Urine Ket Purity")
        dataset4.color = Color.rgb(200,85,61)
        bars.add(dataset4)
        val data = BarData(bars)
        bar_chart.setData(data)

        var yAxis_bar: YAxis = bar_chart.getAxisLeft()

        // disable dual axis (only use LEFT axis)
        bar_chart.getAxisRight().setEnabled(false)

        // horizontal grid lines
        xAxis_bar.enableGridDashedLine(10f, 10f, 0f)
        yAxis_bar.enableGridDashedLine(10f, 10f, 0f)

        // axis range
        yAxis_bar.axisMaximum = 10f
        yAxis_bar.axisMinimum = 0f
    }

    fun getdate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val date = "$year-${month + 1}-$day"

        return date
    }

}