package com.example.peter_sumit.tally_data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class DisplayLedgerGroups extends AppCompatActivity {

    public static String groupName;
    LinearLayout cont;
    int len;
    LedgersAndGroups[] dat;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<MyRecyclerViewAdapter.DataObjectHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<DataObject> results;
    int c;


    @Override
    public void onBackPressed() {
        finish();
//        startActivity(new Intent(getApplicationContext(),Income.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("groupname: ", groupName);
        for (int i = 0; i < dat.length; i++) {
            if (dat[i].parent.equals(groupName) && !(dat[i].opBal == 0f && dat[i].clBal == 0f)) {
                DataObject obj = new DataObject(dat[i].name, "OpBal :" + dat[i].opBal, "CpBal :" + dat[i].clBal);
//                results.addAll(store_results);
                results.add(c++, obj);
                mAdapter.notifyDataSetChanged();

                if (dat[i].isLedger) {
                    ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                            .MyClickListener() {
                        @Override
                        public void onItemClick(int position, View v) {
                            DataObject dataObject = results.get(position);
                            DisplayLedgerGroups.groupName = dataObject.getmHeading();
                            drillDown();
                        }
                    });

                } else {
                    ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                            .MyClickListener() {
                        @Override
                        public void onItemClick(int position, View v) {
                            LedgerAnalysis.ind = position;
                            openLedger();
                        }
                    });


                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ledger_groups);
        c = 0;
        cont = (LinearLayout) findViewById(R.id.lng_cont);
        dat = LoginActivity.data;
        len = dat.length;

        getSupportActionBar().setTitle(groupName);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_display_ledger_groups);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        results = new ArrayList<DataObject>();
        mAdapter = new MyRecyclerViewAdapter(results);
        mRecyclerView.setAdapter(mAdapter);


    }

    void drillDown() {
        startActivity(new Intent(this, DisplayLedgerGroups.class));
    }

    void openLedger() {
        startActivity(new Intent(this, LedgerAnalysis.class));
    }

}
