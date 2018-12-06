package com.appdev.toolkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.appdev.common.view.base.BaseActivity;
import com.appdev.toolkit.activity.DialogDemoActivity;
import com.appdev.toolkit.adapter.MenuAdapter;
import com.appdev.toolkit.entity.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    public static final String MAIN_EXTRA_TITLE = "extra_title";
    private RecyclerView mRecyclerView;
    private MenuAdapter mMenuAdapter;
    private List<MenuItem> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.rv_recyclerView);
        mItems = getData();
        mMenuAdapter = new MenuAdapter(R.layout.item_menu_view,mItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMenuAdapter);
        mMenuAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(MainActivity.this,((MenuAdapter)adapter).getData().get(position).getActivity());
            intent.putExtra(MAIN_EXTRA_TITLE,((MenuAdapter)adapter).getData().get(position).getTitle());
            startActivity(intent);
        });
    }

    private List<MenuItem> getData() {
        mItems.add(new MenuItem(DialogDemoActivity.class,"DialogFragment案例"));
        return mItems;
    }
}
