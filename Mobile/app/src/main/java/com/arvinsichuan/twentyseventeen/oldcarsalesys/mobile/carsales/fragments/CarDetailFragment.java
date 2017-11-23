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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.R;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.entities.SellingCar;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.services.AddToChartRequest;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.general.activities.MainContentActivity;

/**
 * @author ArvinSiChuan
 */
public class CarDetailFragment extends Fragment {
    private TextView carTitleTextView;
    private TextView carPriceTextView;
    private TextView carYearTextView;
    private TextView carDescTextView;
    private TextView carTripTextView;
    private ImageButton addToChart;
    private ImageButton goBack;
    private SellingCar car;

    private MainContentActivity mainContentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_detail, container, false);
        bindView(view);
        updateView();
        bindEvent();
        return view;
    }

    public void setMainContentActivity(MainContentActivity mainContentActivity) {
        this.mainContentActivity = mainContentActivity;
    }

    private void bindView(View view){
        carTitleTextView = view.findViewById(R.id.textView_car_detail_title);
        carPriceTextView = view.findViewById(R.id.textView_car_detail_price);
        carTripTextView = view.findViewById(R.id.textView_car_detail_trip);
        carYearTextView = view.findViewById(R.id.textView_car_detail_year);
        carDescTextView = view.findViewById(R.id.textView_car_detail_desc);
        addToChart = view.findViewById(R.id.imageButton_add_to_chart);
        goBack = view.findViewById(R.id.imageButton_back);
    }

    private void updateView(){
        car= (SellingCar) getArguments().getSerializable("car");
        carTitleTextView.setText(car.getCarBrand() + "/" + car.getCarSeries());
        carPriceTextView.setText(String.format(getActivity().getString(R.string.money_RMB) + "%.2f", car.getExpectedPrice()));
        carTripTextView.setText(String.format(getActivity().getString(R.string.car_trip) + ": %.2f", car.getCarTrip()));
        carYearTextView.setText(String.format(getActivity().getString(R.string.car_year)+": %d",car.getCarYear()));
        carDescTextView.setText(car.getCarDescription());
    }

    private void bindEvent(){
        goBack.setOnClickListener(mainContentActivity);

        addToChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddToChartRequest(getActivity()).execute(car);
            }
        });
    }


}
