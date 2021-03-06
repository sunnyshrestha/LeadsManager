package dev.suncha.leadsmanager;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class summaryView extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private CoordinatorLayout coordinatorLayout;
    private TextView nolead;
    private DatabaseHelper dbHelper;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_view);

        dbHelper = new DatabaseHelper(summaryView.this);
        Toast.makeText(getApplicationContext(),String.valueOf(dbHelper.getLeadsCount()),Toast.LENGTH_SHORT).show();
        nolead = (TextView)findViewById(R.id.noLead);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setupToolbar();

        fab = (FloatingActionButton)findViewById(R.id.buttonFloat);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinatorlayout);
        snackbarDecider();

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        setRecyclerView();
        fab.setOnClickListener(this);
        viewDecider();
    }

    public void setRecyclerView(){
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(dbHelper.getAllLeads());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(summaryView.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(summaryView.this,null));
        recyclerView.setItemAnimator( new DefaultItemAnimator());

        myRecyclerViewAdapter.SetOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(),DisplayLeadDetails.class);
                intent.putExtra("key",position);
                Toast.makeText(getApplicationContext(),String.valueOf((int)myRecyclerViewAdapter.getItemId(position)),Toast.LENGTH_SHORT).show();
                Toast.makeText(summaryView.this,"Position is "+String.valueOf(position),Toast.LENGTH_SHORT).show();
                Log.v("Should start activity","Did?");
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private void setupToolbar() {
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
    }

    private void snackbarDecider() {
        switch (getIntent().getIntExtra("snackbar", -1)) {
            case 1:
                Snackbar
                        .make(coordinatorLayout, R.string.saved, Snackbar.LENGTH_LONG)
                        .show();
                break;
            case 2:
                Snackbar
                        .make(coordinatorLayout, R.string.changessaved, Snackbar.LENGTH_LONG)
                        .show();
                break;
            default:
                break;
        }
    }

    public void viewDecider(){                          //decides whether to show the database summary or No Leads text
        if(dbHelper.getLeadsCount()>0){
            recyclerView.setVisibility(View.VISIBLE);
            nolead.setVisibility(View.GONE);
        }else{
            nolead.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonFloat:
                Intent addLeadIntent = new Intent(this,AddLead.class);
                startActivity(addLeadIntent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //viewDecider();
        setRecyclerView();
    }



}
