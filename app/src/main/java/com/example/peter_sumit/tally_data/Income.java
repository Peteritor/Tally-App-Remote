package com.example.peter_sumit.tally_data;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

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
    int c = 0;
    //    LinearLayout hor[];
    int len;
    LedgersAndGroups[] dat;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
        final ArrayList results = new ArrayList<DataObject>();
        mAdapter = new MyRecyclerViewAdapter(results);
        mRecyclerView.setAdapter(mAdapter);

        Log.i("Check", "Its at Inc 1");
        cont = (LinearLayout) findViewById(R.id.income_cont);
        len = groups.length;
        Log.i("Check", "Its at Inc 2");
        dat = LoginActivity.data;
        Log.i("Check", "Its at Inc 3");


        for (int i = 0; i < dat.length; i++) {
            for (int j = 0; j < len; j++) {
                if (dat[i].name.equals(groups[j]) && !(dat[i].opBal == 0f && dat[i].clBal == 0f)) {
                    try {
                        Log.i("Check", "Its at Inc 6 i=" + i + "  group =" + groups[j]);
//                        cont.addView(hor[j]);
                        Log.i("Check", "Its at Inc 7 i=" + i);
                        DataObject obj = new DataObject(dat[i].name, "OpBal :" + dat[i].opBal, "CpBal :" + dat[i].clBal);
                        results.add(c++, obj);
                        mAdapter.notifyDataSetChanged();
                        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                                .MyClickListener() {
                            @Override
                            public void onItemClick(int position, View v) {
                                DataObject dataObject = (DataObject) results.get(position);
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

    void drillDown() {
        startActivity(new Intent(this, DisplayLedgerGroups.class));
    }

}
