/*
 * Copyright (c) 2014. Ye Lin Aung
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.yelinaung.karrency.app.ui;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.yelinaung.myancur.app.R;

public class HomeActivity extends FragmentActivity implements ActionBar.TabListener {

  @InjectView(R.id.pager) ViewPager mPager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    ButterKnife.inject(this);

    final ActionBar mActionBar = getActionBar();
    assert mActionBar != null;
    mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

    mPager.setAdapter(pagerAdapter);

    mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override public void onPageSelected(int position) {
        mActionBar.setSelectedNavigationItem(position);
      }
    });

    mActionBar.addTab(mActionBar.newTab().setText(R.string.rate).setTabListener(this));
    mActionBar.addTab(mActionBar.newTab().setText(R.string.calc).setTabListener(this));
  }

  @Override public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
    mPager.setCurrentItem(tab.getPosition());
  }

  @Override public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
  }

  @Override public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
  }

  public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override public Fragment getItem(int position) {
      switch (position) {
        case 0:
          return ExchangeRateFragment.newInstance();
        case 1:
          return CalculatorFragment.newInstance();
        default:
          return null;
      }
    }

    @Override public int getCount() {
      return 2;
    }
  }
}