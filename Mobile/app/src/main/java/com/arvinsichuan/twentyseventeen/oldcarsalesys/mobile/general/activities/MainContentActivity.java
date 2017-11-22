/*
 *     This project is one of projects of ArvinSiChuan.com.
 *     Copyright (C) 2017, ArvinSiChuan.com <https://arvinsichuan.com>.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.R;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.account.userprofile.UserProfileFragment;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.fragments.CreatingOrderFragment;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.fragments.SellingCarListFragment;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.fragments.ShoppingChartFragment;

/**
 * @author ArvinSiChuan
 */
public class MainContentActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainContentActivity";
    private BottomNavigationView navigation;

    private FrameLayout contentContainer;
    private SellingCarListFragment sellingCarListFragment;
    private CreatingOrderFragment creatingOrderFragment;
    private ShoppingChartFragment shoppingChartFragment;
    private UserProfileFragment userProfileFragment;
    private Fragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);
        contentContainer = (FrameLayout) findViewById(R.id.content_container);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        currentFragment = sellingCarListFragment = new SellingCarListFragment();
        shoppingChartFragment = new ShoppingChartFragment();
        userProfileFragment = new UserProfileFragment();
        creatingOrderFragment = new CreatingOrderFragment();

        getFragmentManager()
                .beginTransaction()
                .add(R.id.content_container, currentFragment, currentFragment.getTag())
                .show(currentFragment)
                .commit();
        //switchTo(getTicketsQueryFragment());

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shopping_chart:
                    switchTo(shoppingChartFragment);
                    return true;
                case R.id.navigation_selling_car_lsit:
                    switchTo(sellingCarListFragment);
                    return true;
                case R.id.navigation_userself:
                    switchTo(userProfileFragment);
                    return true;
                default:
                    break;
            }
            return false;
        }
    };


    private void switchTo(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (currentFragment != fragment) {
            if (!fragment.isAdded()) {
                transaction.add(R.id.content_container, fragment, fragment.getTag());
            }
            transaction.hide(currentFragment).show(fragment).commit();
            currentFragment = fragment;
        }

    }


    public void regButtonListener(Button button) {
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            default:
                break;
        }
    }

}
