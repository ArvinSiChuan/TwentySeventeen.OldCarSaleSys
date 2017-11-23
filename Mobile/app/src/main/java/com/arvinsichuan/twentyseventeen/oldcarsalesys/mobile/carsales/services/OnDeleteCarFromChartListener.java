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
import android.view.View;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.entities.SellingCar;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.fragments.ShoppingChartFragment;

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
public class OnDeleteCarFromChartListener implements View.OnClickListener {

    private SellingCar car;

    private ShoppingChartFragment shoppingChartFragment;

    private Context mContext;

    public OnDeleteCarFromChartListener(SellingCar car, Context mContext,ShoppingChartFragment shoppingChartFragment) {
        this.car = car;
        this.mContext = mContext;
        this.shoppingChartFragment = shoppingChartFragment;
    }

    public void setShoppingChartFragment(ShoppingChartFragment shoppingChartFragment) {
        this.shoppingChartFragment = shoppingChartFragment;
    }

    public void setCar(SellingCar car) {
        this.car = car;
    }

    @Override
    public void onClick(View view) {
        new DeleteFromChartRequest(mContext,shoppingChartFragment).execute(car);
    }
}
