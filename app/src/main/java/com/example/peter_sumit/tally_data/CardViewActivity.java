package com.example.peter_sumit.tally_data;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;


public class CardViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    LedgersAndGroups[] dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        dat = LoginActivity.data;
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
        getSupportActionBar().setTitle("Income");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorMaterialGreen_Income)));

    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
//                Intent i= new Intent(CardViewActivity.this,SecondPageActivity.class);
//                i.putExtra("position",position);
//                startActivity(i);
            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
        dat= LoginActivity.data;
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < dat.length; index++) {
            DataObject obj = new DataObject(dat[index].name,"OpBal :"+dat[index].opBal ,"CpBal :"+dat[index].clBal);
            results.add(index, obj);
        }
        return results;
    }
}
