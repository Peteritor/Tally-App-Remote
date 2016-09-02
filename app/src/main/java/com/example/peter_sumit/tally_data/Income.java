package com.example.peter_sumit.tally_data;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Income extends AppCompatActivity {

    private static String groups[] = {
            "Direct Incomes",
            "Indirect Incomes",
            "Sales Accounts"
            //"Branch / Divisions",
            //"Capital Account",
            //"Reserves & Surplus",
            //"Current Assets",
            //"Bank Accounts",
            //"Cash-in-hand",
            //"Cash",
            //"Deposits (Asset)",
            //"Loans & Advances (Asset)",
            //"Stock-in-hand",
            //"Sundry Debtors",
            //"Current Liabilities",
            //"Duties & Taxes",
            //"Provisions",
            //"Sundry Creditors",
            //"Fixed Assets",
            //"Investments",
            //"Loans (Liability)",
            //"Bank OD A/c",
            //"Secured Loans",
            //"Unsecured Loans",
            //"Misc. Expenses (ASSET)",
            //"Suspense A/c",
            //"Profit & Loss A/c"
    };

    LinearLayout cont;
    int results_index = 0;
    //    LinearLayout hor[];
    int len;
    LedgersAndGroups[] dat;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<MyRecyclerViewAdapter.DataObjectHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    final ArrayList<DataObject> results = new ArrayList<DataObject>();

    //pie chart

    private FrameLayout mainLayout;
    private PieChart mchart;
    //CREATING PIE CHART
    private double[] yData = {5, 2.7883608, 2.4684371};
    private String[] xData = {"Direct Incomes",
            "Indirect Incomes",
            "Sales Accounts"};
    int yl=yData.length;

    //pie chart

    @Override
    protected void onRestart() {
        super.onRestart();
        results.clear();
        mAdapter.notifyDataSetChanged();
        results_index = 0;
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (int i = 0; i < dat.length; i++) {
            for (int j = 0; j < len; j++) {
                if (dat[i].name.equals(groups[j]) && !(dat[i].opBal == 0f && dat[i].clBal == 0f)) {
                    try {
                        Log.i("Check", "Its at Inc 6 i=" + i + "  group =" + groups[j]);
//                        cont.addView(hor[j]);
                        Log.i("Check", "Its at Inc 7 i=" + i);
                        //NOT SURE
                        DataObject obj = new DataObject(dat[i].name, "Opening Balance : " + dat[i].opBal, "Closing Balance : " + dat[i].clBal*(-1));
                        results.add(results_index++, obj);
                        mAdapter.notifyDataSetChanged();
                        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                                .MyClickListener() {
                            @Override
                            public void onItemClick(int position, View v) {
                                DataObject dataObject = results.get(position);
                                DisplayLedgerGroups.groupName = dataObject.getmHeading();
                                drillDown();
                            }
                        });
                    } catch (Exception ex) {
                        Log.i("Check", "Group = " + groups[j] + "   " + ex.getMessage());
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        getSupportActionBar().setTitle(getLocalClassName());
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorMaterialGreen_Income)));

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_income);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyRecyclerViewAdapter(results);
        mRecyclerView.setAdapter(mAdapter);

        Log.i("Check", "Its at Inc 1");
        cont = (LinearLayout) findViewById(R.id.income_cont);
        len = groups.length;
        Log.i("Check", "Its at Inc 2");
        dat = LoginActivity.data;
        Log.i("Check", "Its at Inc 3");

//Chart
        double s;
        float f_i;
        for(int i=0;i<dat.length;i++){
            for(int j=0;j<len;j++){
                if(dat[i].name.equals(groups[j]) && !(dat[i].opBal==0f && dat[i].clBal==0f)){
                    try {
                        s=dat[i].clBal;
                        f_i = (float)s;
                    }catch (Exception ex){
                        Log.i("Check","Group = "+groups[j]+"   "+ex.getMessage());
                    }
                }
            }
        }
        mainLayout = (FrameLayout)findViewById(R.id.linear);
        mchart = new PieChart(this);
        mainLayout.addView(mchart);
        mchart.setUsePercentValues(true);
        mchart.setDescription("Income");
        mchart.setDrawHoleEnabled(true);
        mchart.setHoleRadius(0);
        mchart.setTransparentCircleRadius(0);
        mchart.setRotationAngle(0);
        mchart.setRotationEnabled(true);
        mchart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {

                if (entry == null)
                    return;

//                Toast.makeText(getApplicationContext(), xData[entry.getXIndex()] + " = " + entry.getVal() + "%", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {


            }
        });
        addData();
//        customize legends;
        Legend l = mchart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setXEntrySpace(7);
        l.setYEntrySpace(10);

    }//On Create

    //For chart
    private void addData()
    {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i=0;i<yl;i++){
            yVals1.add(new Entry((float)yData[i],i));
        }

        ArrayList<String> xVals = new ArrayList<String>();
        for(int i=0;i<yl;i++) {
            xVals.add(xData[i]);
        }
        //create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1,"");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        //ADD COLORS
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c: ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for(int c: ColorTemplate.JOYFUL_COLORS)
            colors.add(c);


        for(int c:ColorTemplate.COLORFUL_COLORS)
            colors.add(c);


        for(int c:ColorTemplate.LIBERTY_COLORS)
            colors.add(c);


        for(int c:ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(xVals,dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.GRAY);
        mchart.setData(data);
        mchart.highlightValue(null);
        mchart.invalidate();


    }
    //Chart end


    void drillDown() {
        startActivity(new Intent(this, DisplayLedgerGroups.class));
    }

}//end of class