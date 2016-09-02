package com.example.peter_sumit.tally_data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {
    Button incomeButton;
    Button expenditureButton;
    Button cashButton;
    Button stockButton;
    Button loanButton;
    Button recievableButton;
    Button payableButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        incomeButton=(Button)findViewById(R.id.income_button);
        expenditureButton=(Button)findViewById(R.id.expenditure_button);
        cashButton=(Button)findViewById(R.id.cashandbank_button);
        stockButton=(Button)findViewById(R.id.stock_button);
        loanButton=(Button)findViewById(R.id.loan_button);
        recievableButton=(Button)findViewById(R.id.recievable_button);
        payableButton=(Button)findViewById(R.id.payable_button);

        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(HomeScreen.this, CardViewActivity.class));
                startActivity(new Intent(HomeScreen.this, Income.class));

            }
        });
        expenditureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, Expenditure.class));
            }
        });
        cashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, cashandbank.class));
            }
        });
        stockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, stock.class));
            }
        });
        loanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, loan.class));
            }
        });
        recievableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, Recievable.class));
            }
        });
        payableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, Payable.class));
            }
        });

        //Line chart
        LineChart lineChart = (LineChart)findViewById(R.id.chart); //getResources().getLayout(R.id.chart);//).findViewById(R.id.chart);

        double[] yData = {5, 2.7883608, 7.4684371,};
        String[] xData = {"Direct Incomes",
                "Indirect Incomes",
                "Sales Accounts"};
        int yl=yData.length;


        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < yl; i++) {
            entries.add(new Entry((float)yData[i], i));
        }

        LineDataSet dataset = new LineDataSet(entries, "Last recorded Data");

        ArrayList<String> labels = new ArrayList<String>();
        for (int i = 0; i < yl; i++) {
            labels.add(xData[i]);
        }

        LineData data = new LineData(labels, dataset);

        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);
        lineChart.setData(data);
        lineChart.animateY(3000);


    }//end of oncreate


}//End of Main_screen class
