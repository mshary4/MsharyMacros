package com.msharytech.msharymacros;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.msharytech.msharymacros.Adapter.BaseViewHolder;
import com.msharytech.msharymacros.Adapter.DefaultRecyclerViewItemDecorator;
import com.msharytech.msharymacros.Adapter.ResultsAdapter;

import java.io.Serializable;
import java.util.List;


public class HistoryActivity extends AppCompatActivity implements BaseViewHolder.RecyclerClickListener {
    List<Result> resultList;
    ResultsAdapter resultsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_history);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvResults);
        resultList=Result.getAllResults();
        recyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
         resultsAdapter = new ResultsAdapter(resultList, HistoryActivity.this);
        recyclerView.setAdapter(resultsAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DefaultRecyclerViewItemDecorator(getResources().getDimension(R.dimen.dimen_10dp)));
        resultsAdapter.notifyDataSetChanged();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(HistoryActivity.this, ChooserActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_history, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        switch (item.getItemId()) {
            case R.id.action_delete:
                new AlertDialog.Builder(HistoryActivity.this)
                        .setTitle("Alert")
                        .setMessage("do you want to delete all results")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                              RealmManager.getInstance().delete(resultList);
                                resultsAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton(android.R.string.cancel,new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            }).setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                // User chose the "Settings" item, show the app settings UI...
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onClick(RecyclerView.ViewHolder vh, int position) {
        Result result=resultList.get(position); //
        Intent i = new Intent(HistoryActivity.this,ResultFromHistory.class);
        i.putExtra("result", result.getId());
        startActivity(i);
    }

    @Override
    public void onLongClick(RecyclerView.ViewHolder vh, int position) {

    }
}
