package com.example.goodday

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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


class VisualizationDashboard : AppCompatActivity() {

    lateinit var line_chart: LineChart
    lateinit var linelist:ArrayList<Entry>
    lateinit var lineDataSet: LineDataSet
    lateinit var lineData: LineData

    lateinit var bar_chart: BarChart
    lateinit var barlist:ArrayList<BarEntry>
    lateinit var barDataSet: BarDataSet
    lateinit var barData: BarData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualization)

        line_chart = findViewById(R.id.line_chart)
        bar_chart = findViewById(R.id.bar_chart)

        lineChart()
        barchart1()

    }

    fun lineChart()
    {
        linelist = ArrayList()
        linelist.add(Entry(1f,70f))
        linelist.add(Entry(2f,50f))
        linelist.add(Entry(3f,40f))
        linelist.add(Entry(4f,50f))
        linelist.add(Entry(5f,80f))
        linelist.add(Entry(6f,100f))
        linelist.add(Entry(7f,70f))


        var xAxis_line: XAxis = line_chart.getXAxis()

        xAxis_line.setValueFormatter(object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase): String {
                var label = ""
                if (value == 1f)
                    label = "12.1"
                else if (value == 2f)
                    label = "12.2"
                else if (value == 3f)
                    label = "12.3"
                else if (value == 4f)
                    label = "12.4"
                else if (value == 5f)
                    label = "12.5"
                else if (value == 6f)
                    label = "12.6"
                else if (value == 7f)
                    label = "12.7"
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

    fun barchart(){
        bar_chart.getDescription().setEnabled(false)

        // scaling can now only be done on x- and y-axis separately
        bar_chart.setPinchZoom(false)

        bar_chart.setDrawBarShadow(false)
        bar_chart.setDrawGridBackground(false)

        val xAxis: XAxis = bar_chart.getXAxis()
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)

        bar_chart.getAxisLeft().setDrawGridLines(false)

        // setting data
        barlist = ArrayList()
        barlist.add(BarEntry(1f,70f))
        barlist.add(BarEntry(2f,50f))
        barlist.add(BarEntry(3f,40f))
        barlist.add(BarEntry(4f,50f))
        barlist.add(BarEntry(5f,80f))

        barDataSet = BarDataSet(barlist,"")
        barData = BarData(barDataSet)
        bar_chart.data = barData

        var xAxis_bar: XAxis = bar_chart.getXAxis()

        xAxis_bar.setValueFormatter(object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase): String {
                var label2 = "hi"
                when (value) {
                    10f -> label2 = "Temperature"
                    20f -> label2 = "Pressure"
                    30f -> label2 = "Glucose"
                    40f -> label2 = "Oxygen Saturation"
                    50f -> label2 = "Pulse Rate"
                }
                return label2
            }
        })


        // add a nice and smooth animation
        bar_chart.animateY(1500)
        //lineDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS)

        bar_chart.getLegend().setEnabled(false)
    }

    fun barchart1(){
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
        entries.add(BarEntry(1f, 900f))
        val entries2: ArrayList<BarEntry> = ArrayList()
        entries2.add(BarEntry(2f, 950f))
        val entries3: ArrayList<BarEntry> = ArrayList()
        entries3.add(BarEntry(3f, 500f))
        val entries4: ArrayList<BarEntry> = ArrayList()
        entries4.add(BarEntry(4f, 600f))
        val entries5: ArrayList<BarEntry> = ArrayList()
        entries5.add(BarEntry(5f, 880f))
        val bars: MutableList<IBarDataSet> = ArrayList()
        val dataset = BarDataSet(entries, "Temperature")
        dataset.color = Color.rgb(88,139,139)
        bars.add(dataset)
        val dataset2 = BarDataSet(entries2, "Blood Pressure")
        dataset2.color = Color.rgb(200,85,61)
        bars.add(dataset2)
        val dataset3 = BarDataSet(entries3, "Glucose")
        dataset3.color = Color.rgb(242,143,59)
        bars.add(dataset3)
        val dataset4 = BarDataSet(entries4, "Oxygen Saturation")
        dataset4.color = Color.rgb(200,85,61)
        bars.add(dataset4)
        val dataset5 = BarDataSet(entries5, "Pulse Rate")
        dataset5.color = Color.rgb(88,139,139)
        bars.add(dataset5)
        val data = BarData(bars)
        bar_chart.setData(data)

        var yAxis_bar: YAxis = bar_chart.getAxisLeft()

        // disable dual axis (only use LEFT axis)
        bar_chart.getAxisRight().setEnabled(false)

        // horizontal grid lines
        xAxis_bar.enableGridDashedLine(10f, 10f, 0f)
        yAxis_bar.enableGridDashedLine(10f, 10f, 0f)

        // axis range
        yAxis_bar.axisMaximum = 1000f
        yAxis_bar.axisMinimum = 0f
    }

}