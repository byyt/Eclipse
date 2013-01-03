package com.example.greenteamp3.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.example.greenteamp3.R;

public class HomeActivity extends SherlockFragmentActivity  {

	private static final String TAG = "HomeActivity";

	private static final String TAB_STR = "tab";
	private static final String TAB_1 = "tab1";
	private static final String TAB_2 = "tab2";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.main_layout);

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		initializeTabs(savedInstanceState);
	}

	private void initializeTabs(Bundle savedInstanceState) {
		ActionBar actionBar = getSupportActionBar();

		ActionBar.Tab tab = actionBar.newTab();
		tab.setText(getString(R.string.tab1_label));
		tab.setTabListener(new TabListener<FoldersFragment>(this, TAB_1, FoldersFragment.class));
		actionBar.addTab(tab);

		tab = actionBar.newTab();
		tab.setText(getString(R.string.tab2_label));
		tab.setTabListener(new TabListener<FoldersFragment>(this, TAB_2, FoldersFragment.class));
		actionBar.addTab(tab);

		if (savedInstanceState != null) {
			actionBar.setSelectedNavigationItem(savedInstanceState.getInt(TAB_STR, 0));
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(TAB_STR, getSupportActionBar().getSelectedNavigationIndex());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getSupportMenuInflater();
	    inflater.inflate(R.menu.activity_main, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_settings:
			Log.i(TAG, "clicked the settings menu");

			return true;
		}
		
		return false;
	}
	
	public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
		private Fragment mFragment;
		private final SherlockFragmentActivity mActivity;
		private final String mTag;
		private final Class<T> mClass;

		/** Constructor used each time a new tab is created.
		 * @param activity  The host Activity, used to instantiate the fragment
		 * @param tag  The identifier tag for the fragment
		 * @param clz  The fragment's Class, used to instantiate the fragment
		 */
		public TabListener(SherlockFragmentActivity activity, String tag, Class<T> clz) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
		}

		/* The following are each of the ActionBar.TabListener callbacks */
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			Fragment preInitializedFragment = (Fragment) mActivity.getSupportFragmentManager().findFragmentByTag(mTag);

			// Check if the fragment is already initialized
			if (preInitializedFragment == null) {
				// If not, instantiate and add it to the activity
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				ft.add(android.R.id.content, mFragment, mTag);
			} 
			else {
				// If it exists, simply attach it in order to show it
				ft.attach(preInitializedFragment);
			} 
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			Fragment preInitializedFragment = (Fragment) mActivity.getSupportFragmentManager().findFragmentByTag(mTag);

			if (preInitializedFragment != null) {
				ft.detach(preInitializedFragment);
			} 
			else if (mFragment != null) {
				ft.detach(mFragment);
			}
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// User selected the already selected tab. Usually do nothing.
		}
	}
}
