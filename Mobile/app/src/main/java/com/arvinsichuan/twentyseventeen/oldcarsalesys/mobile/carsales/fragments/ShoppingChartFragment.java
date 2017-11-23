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

package com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.R;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.services.LoadChartRequest;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.activities.MainContentActivity;

/**
 * @author ArvinSiChuan
 */
public class ShoppingChartFragment extends Fragment {

    private static final String TAG = "CHART_FRAG";
    private MainContentActivity mainContentActivity;

    private ListView listView;
    private Button submitButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_chart, container, false);
        Log.d(TAG, "onCreateView: " + view);
        bindView(view);
        bindEvent();
        updateChart();
        return view;
    }

    public void setMainContentActivity(MainContentActivity mainContentActivity) {
        this.mainContentActivity = mainContentActivity;
    }

    private void bindView(View view) {
        listView = view.findViewById(R.id.listView_chart);
        submitButton = view.findViewById(R.id.button_submit_order);
    }


    private void bindEvent() {
        submitButton.setOnClickListener(mainContentActivity);
    }

    public void updateChart() {
        new LoadChartRequest(mainContentActivity, mainContentActivity, listView, this).execute();
    }


}
