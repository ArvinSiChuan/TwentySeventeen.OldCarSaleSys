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

package com.arvinsichuan.twentyseventeen.oldcarsaleserver.carsale.repositories;

import com.arvinsichuan.twentyseventeen.oldcarsaleserver.carsale.entities.SellingCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project Server
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 22-Nov-17
 * <p>
 * Package: com.arvinsichuan.twentyseventeen.oldcarsaleserver.carsale.repositories
 * @author 75744
 */
@Repository("sellingCarsRepo")
public interface SellingCarPagingRepo extends PagingAndSortingRepository<SellingCar,String> {

    /**
     * Return all selling cars in List type
     * @return
     */
    @Override
    List<SellingCar> findAll();

    /**
     * Return all selling cars by Sort in List type
     * @param sort
     * @return
     */
    @Override
    List<SellingCar> findAll(Sort sort);

    /**
     * Return all selling cars by Iterable in List type
     * @param iterable
     * @return
     */
    @Override
    List<SellingCar> findAll(Iterable<String> iterable);

    /**
     * In sales cars
     * @param pageable
     * @return
     */
    Page<SellingCar> findAllByRelatedOrderIsNull(Pageable pageable);
}
