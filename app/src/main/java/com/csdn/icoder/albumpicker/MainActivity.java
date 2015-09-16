package com.csdn.icoder.albumpicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;

    private AlbumAdapter mAlbumAdapter;

    Button sumbit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        new Utils().getAlbums(MainActivity.this, new Utils.OnLoadAlbum() {
            @Override
            public void onResult(ArrayList<Album> mAlbums) {
                setAdapter(mAlbums);
            }

        });
    }
    private void setAdapter(ArrayList<Album> mAlbums) {
        mAlbumAdapter =new AlbumAdapter(MainActivity.this,mRecyclerView,mAlbums);
        mRecyclerView.setAdapter(mAlbumAdapter);

    }
    private void initView() {
        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(" ");
        }
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
        sumbit_btn = (Button) this.findViewById(R.id.submit_btn);
        sumbit_btn.setOnClickListener(this);
        mLinearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mGridLayoutManager=new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_btn:
                Toast.makeText(MainActivity.this, "选择了"+mAlbumAdapter.getSelectedAlbum().size()+"张图片",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
