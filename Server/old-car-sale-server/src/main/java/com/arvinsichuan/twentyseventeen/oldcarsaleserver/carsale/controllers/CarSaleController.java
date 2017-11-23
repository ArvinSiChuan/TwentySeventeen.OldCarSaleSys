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

package com.arvinsichuan.twentyseventeen.oldcarsaleserver.carsale.controllers;

import com.arvinsichuan.general.WebInfoEntity;
import com.arvinsichuan.general.exceptions.EmptyDataException;
import com.arvinsichuan.twentyseventeen.oldcarsaleserver.carsale.entities.ChartList;
import com.arvinsichuan.twentyseventeen.oldcarsaleserver.carsale.entities.SellingCar;
import com.arvinsichuan.twentyseventeen.oldcarsaleserver.carsale.services.CarSaleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project Server
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 23-Nov-17
 * <p>
 * Package: com.arvinsichuan.twentyseventeen.oldcarsaleserver.carsale.controllers
 *
 * @author ArvinSiChuan
 */
@RestController
@RequestMapping("/carSale")
@SessionAttributes(value = {CarSaleController.myChartPara}, types = {ChartList.class})
public class CarSaleController {
    public static final String myChartPara = "myChart";

    @Resource(name = "carSaleService")
    private CarSaleService carSaleService;

    @GetMapping("/inSales")
    @PreAuthorize("hasRole('ROLE_USER')")
    public WebInfoEntity getSellingCarByPage(@RequestParam("startPage") int startPage,
                                             @RequestParam("pageSize") int pageSize) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        List<SellingCar> data = carSaleService.getSellingCars(startPage, pageSize);
        if (data != null && !data.isEmpty()) {
            webInfoEntity.ok();
            webInfoEntity.addInfoAndData("carList", data);
        } else {
            webInfoEntity.exception(new EmptyDataException("CAR_LIST"));
        }
        return webInfoEntity;
    }

    @PostMapping("/addToChart")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public WebInfoEntity addToChart(@RequestBody SellingCar sellingCar, Model model,
                                    ChartList chartList) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        if (chartList == null) {
            chartList = new ChartList();
        }
        chartList.add(sellingCar);
        model.addAttribute(myChartPara, chartList);
        webInfoEntity.ok();
        webInfoEntity.addInfoAndData(myChartPara, chartList.getList());
        return webInfoEntity;
    }

    @GetMapping("/myChart")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public WebInfoEntity getShoppingChart(ChartList chartList) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        if (chartList != null && !chartList.getList().isEmpty()) {
            webInfoEntity.addInfoAndData(myChartPara, chartList.getList());
            webInfoEntity.ok();
        } else {
            webInfoEntity.exception(new EmptyDataException("CHART_EMPTY"));
        }
        return webInfoEntity;
    }

    @PostMapping("/removeFromChart")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public WebInfoEntity removeFromShoppingChart(@RequestBody SellingCar sellingCar, Model model,ChartList chartList) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        if (chartList != null && !chartList.getList().isEmpty()) {
            System.out.println(sellingCar.getCarSeries());
            chartList.remove(sellingCar);
            webInfoEntity.addInfoAndData(myChartPara, chartList.getList());
            chartList.getList().forEach((sellingCar1) -> {System.out.println(sellingCar.getCarSeries());});
            webInfoEntity.ok();
        } else {
            webInfoEntity.exception(new EmptyDataException("CHART_EMPTY"));
        }
        return webInfoEntity;
    }


}
