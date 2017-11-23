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

package com.arvinsichuan.twentyseventeen.oldcarsaleserver.carsale.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Mobile
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 23-Nov-17
 * <p>
 * Package: com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.entities
 * @author ArvinSiChuan
 */
public class ChartList implements Serializable{
    private static final long serialVersionUID = 7780182080031956729L;
    private List<SellingCar> list = new ArrayList<>();

    public ChartList(){

    }

    public ChartList(List<SellingCar> list) {
        this.list = list;
    }

    public List<SellingCar> getList() {
        return list;
    }

    public void setList(List<SellingCar> list) {
        this.list = list;
    }

    public void add(SellingCar sellingCar){
        list.add(sellingCar);
    }

    public SellingCar get(int i){
        return list.get(i);
    }


    public boolean remove(SellingCar sellingCar){
        SellingCar targetCar=null;
        for (SellingCar car : list) {
            if (car.getUuid().equals(sellingCar.getUuid())) {
                targetCar=car;
                break;
            }
        }
        return list.remove(targetCar);

    }
}
