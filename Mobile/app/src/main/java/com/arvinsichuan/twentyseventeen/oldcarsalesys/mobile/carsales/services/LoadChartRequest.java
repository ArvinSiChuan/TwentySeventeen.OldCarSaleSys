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

package com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.services;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import com.arvinsichuan.resttool.JsonObjectParseTool;
import com.arvinsichuan.resttool.RestTemplateWithCookie;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.R;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.adapters.ChartAdapter;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.entities.SellingCar;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.fragments.ShoppingChartFragment;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.Configurations;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.activities.MainContentActivity;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.entities.WebInfoEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Mobile
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 23-Nov-17
 * <p>
 * Package: com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.services
 *
 * @author ArvinSiChuan
 */
public class LoadChartRequest extends AsyncTask<String, WebInfoEntity, WebInfoEntity> {

    private ShoppingChartFragment shoppingChartFragment;

    public static final String TAG = "LOAD_CHART_REQ";

    private Context mContext;

    private MainContentActivity mainContentActivity;

    private ListView listView;


    public LoadChartRequest(Context mContext, MainContentActivity mainContentActivity, ListView listView,
                            ShoppingChartFragment shoppingChartFragment) {
        this.mContext = mContext;
        this.shoppingChartFragment = shoppingChartFragment;
        this.mainContentActivity = mainContentActivity;
        this.listView = listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public void setMainContentActivity(MainContentActivity mainContentActivity) {
        this.mainContentActivity = mainContentActivity;
    }

    @Override
    protected WebInfoEntity doInBackground(String... strings) {
        RestTemplateWithCookie template = new RestTemplateWithCookie();
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        String url = Configurations.HOST_ROOT + "/carSale/myChart";
        try {
            webInfoEntity = template.getForObject(url, WebInfoEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            webInfoEntity.exception(e);
        }
        return webInfoEntity;
    }

    private void setEmptyCondition(){
        if (listView!=null){
            ChartAdapter chartAdapter=new ChartAdapter(new ArrayList<SellingCar>(), mContext, shoppingChartFragment);
            chartAdapter.setMainContentActivity(mainContentActivity);
            listView.setAdapter(chartAdapter);
        }
        Toast.makeText(mainContentActivity, R.string.no_data, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onPostExecute(WebInfoEntity webInfoEntity) {
        super.onPostExecute(webInfoEntity);
        ChartAdapter chartAdapter = null;

        Log.d(TAG, "onPostExecute: " + webInfoEntity);
        if (webInfoEntity.isOk() && webInfoEntity.get("myChart") != null) {
            List<String> sellingCarsJson = null;
            List<SellingCar> sellingCars = new ArrayList<>();
            try {
                sellingCarsJson = JsonObjectParseTool.parse(webInfoEntity.get("myChart"), List.class);
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
                chartAdapter = new ChartAdapter(sellingCars, mContext, shoppingChartFragment);
                chartAdapter.setMainContentActivity(mainContentActivity);
                listView.setAdapter(chartAdapter);
            } else {

            }
        } else if (webInfoEntity.haveException()) {
            if (webInfoEntity.get("message") != null) {
                if (webInfoEntity.get("message").toString().contains("CHART_EMPTY")) {
                    setEmptyCondition();
                }
            }
        }
    }
}

