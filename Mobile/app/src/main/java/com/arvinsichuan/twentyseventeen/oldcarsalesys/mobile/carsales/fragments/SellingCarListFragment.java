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
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.arvinsichuan.resttool.JsonObjectParseTool;
import com.arvinsichuan.resttool.RestTemplateWithCookie;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.R;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.adapters.SellingCarAdapter;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.entities.SellingCar;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.Configurations;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.activities.MainContentActivity;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.entities.WebInfoEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * A simple {@link Fragment} subclass.
 *
 * @author ArvinSiChuan
 */
public class SellingCarListFragment extends Fragment{

    public static final String TAG = "SELLING_FRAGMENT";

  private MainContentActivity mainContentActivity;
    private Context mContext;
    private int page = 0;
    private int pageSize = 5;
    private List<SellingCar> currentPageDataList;
    private SellingCarAdapter sellingCarAdapter;
    private ListView listView;

    private SellingCarListFragment thisFragment;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selling_car_list, container, false);
        listView = view.findViewById(R.id.listview_selling_cars_list);
        mContext = this.getActivity();
        return view;
    }

    public void setMainContentActivity(MainContentActivity mainContentActivity) {
        this.mainContentActivity = mainContentActivity;
    }

    @Override
    public void onResume() {
        executeUpdate();
        super.onResume();
    }


    public void executeUpdate() {
        new RequestForSellingCarList().execute();
    }


    private class RequestForSellingCarList extends AsyncTask<String, WebInfoEntity, WebInfoEntity> {

        @Override
        protected WebInfoEntity doInBackground(String... strings) {
            RestTemplateWithCookie template = new RestTemplateWithCookie();
            String url = Configurations.HOST_ROOT + "/carSale/inSales?startPage={page}&pageSize={size}";
            WebInfoEntity webInfoEntity = new WebInfoEntity();
            Map<String, Integer> parameters = new TreeMap<>();
            parameters.put("page", page);
            parameters.put("size", pageSize);
            try {
                webInfoEntity = template.getForObject(url, WebInfoEntity.class, parameters);
            } catch (Exception e) {
                e.printStackTrace();
                webInfoEntity.exception(e);
            }
            return webInfoEntity;
        }

        @Override
        protected void onPostExecute(WebInfoEntity webInfoEntity) {
            super.onPostExecute(webInfoEntity);
            if (webInfoEntity.isOk() && webInfoEntity.get("carList") != null) {
                List<String> sellingCarsJson = null;
                List<SellingCar> sellingCars = new ArrayList<>();
                try {
                    sellingCarsJson = JsonObjectParseTool.parse(webInfoEntity.get("carList"), List.class);
                    Log.d(TAG, "onPostExecute: " + sellingCarsJson.size());
                    for (Object sellingCar : sellingCarsJson) {
                        SellingCar car = JsonObjectParseTool.parse(sellingCar, SellingCar.class);
                        Log.d(TAG, "onPostExecute: " + car.getCarBrand());
                        sellingCars.add(car);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!sellingCars.isEmpty()) {
                    sellingCarAdapter = new SellingCarAdapter(sellingCars, mContext);
                    sellingCarAdapter.setMainContentActivity(mainContentActivity);
                    listView.setAdapter(sellingCarAdapter);
                } else {
                    Toast.makeText(mContext, R.string.no_data, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}


