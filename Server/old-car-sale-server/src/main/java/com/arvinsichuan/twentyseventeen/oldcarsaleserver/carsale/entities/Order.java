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

import com.arvinsichuan.general.users.entity.User;

import javax.persistence.*;
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
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = -4565192938924957644L;
    /**
     * DD019
     */
    @Id
    @Column(name = "uuid", length = 36, nullable = false)
    private String uuid = UUID.randomUUID().toString();

    /**
     * DD020
     */
    @Column(name = "sum_amount", nullable = false)
    private double sumAmount = 0;

    /**
     * DD021
     * Ignore Cardinality Parent Mapping.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer", nullable = false,
            foreignKey = @ForeignKey(name = "fk_order_user"))
    private User buyer;

    /**
     * DD025
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 24, nullable = false)
    private OrderStatus status = OrderStatus.CREATED;

    @OneToMany(mappedBy = "relatedOrder")
    private List<SellingCar> sellingCarList;

    public Order() {
    }

    public Order(String uuid, double sumAmount, User buyer, OrderStatus status, List<SellingCar> sellingCarList) {
        this.uuid = uuid;
        this.sumAmount = sumAmount;
        this.buyer = buyer;
        this.status = status;
        this.sellingCarList = sellingCarList;
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

    public List<SellingCar> getSellingCarList() {
        return sellingCarList;
    }

    public void setSellingCarList(List<SellingCar> sellingCarList) {
        this.sellingCarList = sellingCarList;
    }
}
