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

package com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.entities;


import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.account.entities.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Project Server
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 22-Nov-17
 * <p>
 * Package: com.arvinsichuan.twentyseventeen.oldcarsaleserver.carsale.entities
 *
 * @author ArvinSiChuan
 */
public class Order implements Serializable {

    private static final long serialVersionUID = -4565192938924957644L;
    /**
     * DD019
     */
    private String uuid = UUID.randomUUID().toString();

    /**
     * DD020
     */
    private double sumAmount = 0;

    /**
     * DD021
     * Ignore Cardinality Parent Mapping.
     */
    private User buyer;

    /**
     * DD025
     */
    private OrderStatus status = OrderStatus.CREATED;

    @JsonManagedReference
    private List<SellingCar> sellingCars;


    public Order() {
    }

    public Order(String uuid, double sumAmount, User buyer, OrderStatus status, List<SellingCar> sellingCars) {
        this.uuid = uuid;
        this.sumAmount = sumAmount;
        this.buyer = buyer;
        this.status = status;
        this.sellingCars = sellingCars;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public double getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(double sumAmount) {
        this.sumAmount = sumAmount;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<SellingCar> getSellingCars() {
        return sellingCars;
    }

    public void setSellingCars(List<SellingCar> sellingCars) {
        this.sellingCars = sellingCars;
    }
}
