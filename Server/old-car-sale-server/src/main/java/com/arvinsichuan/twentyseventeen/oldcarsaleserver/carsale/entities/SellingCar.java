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
import com.arvinsichuan.twentyseventeen.oldcarsaleserver.commet.entities.Comment;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "selling_cars")
public class SellingCar implements Serializable {

    private static final long serialVersionUID = 2878324667591229814L;

    /**
     * DD006
     */
    @Id
    @Column(name = "uuid", length = 36, nullable = false)
    private String uuid = UUID.randomUUID().toString();

    /**
     * DD007
     */
    @Column(name = "car_brand", length = 32, nullable = false)
    private String carBrand;

    /**
     * DD008
     */
    @Column(name = "cat_series", length = 32, nullable = false)
    private String carSeries;

    /**
     * DD009
     */
    @Column(name = "car_year", nullable = false)
    private int carYear = 0;

    /**
     * DD010 Default Measure Unit: Km.
     */
    @Column(name = "car_trip", nullable = false)
    private double carTrip = 0;

    /**
     * DD011
     */
    @Column(name = "car_description", length = 2048)
    private String carDescription;

    /**
     * DD012 Ignore Parent side indexes
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_seller", nullable = false,
            foreignKey = @ForeignKey(name = "fk_selling_car_users"))
    private User carSeller;

    /**
     * DD022 Measure Unit: RMB(¥)
     */
    @Column(name = "expected_price", nullable = false)
    private double expectedPrice;

    /**
     * DD023
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "related_order", foreignKey = @ForeignKey(name = "fk_selling_car_order"))
    private Order relatedOrder;

    /**
     * Cardinality Children to DD015
     */
    @OneToMany(mappedBy = "rootCar")
    private List<Comment> comments;


    public SellingCar() {
    }

    public SellingCar(String uuid, String carBrand, String carSeries, int carYear, double carTrip, String
            carDescription, User carSeller, double expectedPrice, Order relatedOrder, List<Comment> comments) {
        this.uuid = uuid;
        this.carBrand = carBrand;
        this.carSeries = carSeries;
        this.carYear = carYear;
        this.carTrip = carTrip;
        this.carDescription = carDescription;
        this.carSeller = carSeller;
        this.expectedPrice = expectedPrice;
        this.relatedOrder = relatedOrder;
        this.comments = comments;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public double getCarTrip() {
        return carTrip;
    }

    public void setCarTrip(double carTrip) {
        this.carTrip = carTrip;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

    public User getCarSeller() {
        return carSeller;
    }

    public void setCarSeller(User carSeller) {
        this.carSeller = carSeller;
    }

    public double getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(double expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    @JsonBackReference
    public Order getRelatedOrder() {
        return relatedOrder;
    }

    public String getRelatedOrderUuid() {
        return relatedOrder.getUuid();
    }

    public void setRelatedOrder(Order relatedOrder) {
        this.relatedOrder = relatedOrder;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


}
