package com.example.free.mymvpdemo.ui;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.adapter.StaggeredHomeAdapter;
import com.example.free.mymvpdemo.manager.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridLayoutActivity extends BaseActivity
{

	private RecyclerView mRecyclerView;
	private List<String> mDatas;
	private StaggeredHomeAdapter mStaggeredHomeAdapter;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_single_recyclerview;
	}

	@Override
	protected void initView() {
		mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);

		initEvent();
	}

	@Override
	protected void initListener() {

	}

	private void initEvent() {
		mStaggeredHomeAdapter.setOnItemClickLitener(new StaggeredHomeAdapter.OnItemClickLitener() {
			@Override
			public void onItemClick(View view, int position)
			{
				Toast.makeText(StaggeredGridLayoutActivity.this,
						position + " click", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onItemLongClick(View view, int position)
			{
				Toast.makeText(StaggeredGridLayoutActivity.this,
						position + " long click", Toast.LENGTH_SHORT).show();
			}
		});
	}

	protected void initData() {
		mDatas = new ArrayList<>();
		for (int i = 'A'; i < 'z'; i++) {
			mDatas.add("" + (char) i);
		}
		mStaggeredHomeAdapter = new StaggeredHomeAdapter(this, mDatas);
		mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
		mRecyclerView.setAdapter(mStaggeredHomeAdapter);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());  // 设置item动画
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_staggered, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.id_action_add:
			mStaggeredHomeAdapter.addData(1);
			break;
		case R.id.id_action_delete:
			mStaggeredHomeAdapter.removeData(1);
			break;
		}
		return true;
	}
}
