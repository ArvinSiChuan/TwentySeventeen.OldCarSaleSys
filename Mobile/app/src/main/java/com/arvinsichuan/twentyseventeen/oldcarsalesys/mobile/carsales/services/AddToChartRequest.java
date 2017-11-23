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
import android.widget.Toast;
import com.arvinsichuan.resttool.RestTemplateWithCookie;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.R;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.entities.SellingCar;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.Configurations;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.entities.WebInfoEntity;

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
public class AddToChartRequest extends AsyncTask<SellingCar, WebInfoEntity, WebInfoEntity> {
    private Context context;

    public AddToChartRequest() {

    }

    public AddToChartRequest(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected WebInfoEntity doInBackground(SellingCar... sellingCars) {
        RestTemplateWithCookie template = new RestTemplateWithCookie();
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        String url = Configurations.HOST_ROOT + "/carSale/addToChart";
        try {
            webInfoEntity = template.postForObject(url, sellingCars[0], WebInfoEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            webInfoEntity.exception(e);
        }
        return webInfoEntity;
    }

    @Override
    protected void onPostExecute(WebInfoEntity webInfoEntity) {
        super.onPostExecute(webInfoEntity);
        if (webInfoEntity.isOk()) {
            Toast.makeText(context, R.string.add_successful, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, R.string.add_failed, Toast.LENGTH_LONG).show();
        }
    }
}
