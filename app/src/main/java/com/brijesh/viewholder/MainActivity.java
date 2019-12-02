package com.brijesh.viewholder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRootref;
    private ArrayList <DataObject> list;
    private ValueEventListener DataSnap;
    private String type="PRESENT";
    private static String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        type= getIntent().getStringExtra("str");
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.setAdapter(mAdapter);
       // mAdapter=new MyRecyclerViewAdapter(getDataSet());
        firebaseDatabase=FirebaseDatabase.getInstance();
        if (type != null) {
            mRootref = firebaseDatabase.getReference().child(type);
        }
        else {
            mRootref = firebaseDatabase.getReference().child("PRESENT");
        }
        DataSnap=mRootref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list=new ArrayList<DataObject>();
                int i=0;
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren())
                {
                   /* String str=Integer.toString(i);
                  DataObject p = dataSnapshot.child("str").getValue(DataObject.class);
                  list.add(p);
                DataObject q = dataSnapshot.child("2").getValue(DataObject.class);
                DataObject r = dataSnapshot.child("3").getValue(DataObject.class);
                list.add(p);
                list.add(q);
                DataObject s = dataSnapshot.child("2").getValue(DataObject.class);
                DataObject t = dataSnapshot.child("3").getValue(DataObject.class);
                list.add(s);
                list.add(t);
                list.add(r);*/
                   DataObject  p =postSnapshot.getValue(DataObject.class);
                   list.add(p);

                }
                mAdapter = new MyRecyclerViewAdapter(MainActivity.this,list);
                mRecyclerView.setAdapter(mAdapter);

                //String desc=dataSnapshot.child("1").child("CODE").getValue().toString();
                //String end_time=dataSnapshot.child("1").child("END_TIME").getValue().toString();
                //String name=dataSnapshot.child("1").child("NAME").getValue().toString();
                //String start_time=dataSnapshot.child("1").child("START_TIME").getValue().toString();
                //String link=dataSnapshot.child("1").child("URL").getValue().toString();
                //DataObject obj = new DataObject(desc,end_time,name,start_time,link);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Oopsss.... Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Toast.makeText(getApplicationContext(),"Reloading",Toast.LENGTH_SHORT).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRootref.removeEventListener(DataSnap);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            type="PRESENT";
            mRootref = firebaseDatabase.getReference().child(type);
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("str","PRESENT");
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {
            type="PAST";
            mRootref = firebaseDatabase.getReference().child(type);
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("str","PAST");
            startActivity(intent);


        } else if (id == R.id.nav_slideshow) {
            type="FUTURE";
            mRootref = firebaseDatabase.getReference().child(type);
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("str","FUTURE");
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
