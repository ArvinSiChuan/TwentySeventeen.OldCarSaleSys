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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.R;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.entities.SellingCar;

import java.util.List;

/**
 * Project Mobile
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 23-Nov-17
 * <p>
 * Package: com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.adapters
 *
 * @author ArvinSiChaun
 */
public class SellingCarAdapter extends BaseAdapter {

    private List<SellingCar> mData;
    private Context mContext;

    public SellingCarAdapter(List<SellingCar> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.fragment_selling_car_list, viewGroup, false);
        ImageView imageView = view.findViewById(R.id.imageView_car_backup);
        TextView carTitle = view.findViewById(R.id.textView_car_title);
        TextView carPrice = view.findViewById(R.id.textView_car_price);
        TextView carTrip = view.findViewById(R.id.textView_car_trip);
        SellingCar car = mData.get(position);
        carTitle.setText(car.getCarBrand() + "/" + car.getCarSeries());
        carPrice.setText(R.string.car_price + R.string.colon + R.string.money_RMB
                + String.valueOf(car.getExpectedPrice()));
        carTrip.setText(R.string.car_trip + R.string.colon + String.valueOf(car.getCarTrip()));
        return view;
    }
}
