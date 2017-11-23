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

package com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.R;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.entities.SellingCar;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.fragments.ShoppingChartFragment;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.services.OnDeleteCarFromChartListener;

import java.util.List;

/**
 * Project Mobile
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 23-Nov-17
 * <p>
 * Package: com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.adapters
 * @author ArvinSiChuan
 */
public class ChartAdapter extends SellingCarAdapter {
    private ShoppingChartFragment shoppingChartFragment;

    public ChartAdapter(List<SellingCar> mData, Context mContext, ShoppingChartFragment shoppingChartFragment) {
        super(mData, mContext);
        this.shoppingChartFragment=shoppingChartFragment;
    }



    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(getmContext()).inflate(R.layout.layout_viewlist_selling_car, viewGroup, false);
        }
        ImageView imageView = view.findViewById(R.id.imageView_car_backup);
        TextView carTitle = view.findViewById(R.id.textView_car_detail_title);
        TextView carPrice = view.findViewById(R.id.textView_car_price);
        TextView carTrip = view.findViewById(R.id.textView_car_trip);
        TextView carYear = view.findViewById(R.id.textView_car_year);
        SellingCar car = getMData().get(position);
        carTitle.setText(car.getCarBrand() + "/" + car.getCarSeries());
        carPrice.setText(String.format(getmContext().getString(R.string.money_RMB) + "%.2f", car.getExpectedPrice()));
        carTrip.setText(String.format(getmContext().getString(R.string.car_trip) + ": %.2f", car.getCarTrip()));
        carYear.setText(String.format(getmContext().getString(R.string.car_year)+": %d",car.getCarYear()));
        view.setOnClickListener(new OnDeleteCarFromChartListener(car,getMainContentActivity(),shoppingChartFragment));
        return view;
    }
}
