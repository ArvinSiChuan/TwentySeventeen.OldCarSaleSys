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

package com.arvinsichuan.general.users.controller;


import com.arvinsichuan.general.WebInfoEntity;
import com.arvinsichuan.general.exceptions.DuplicatedDataException;
import com.arvinsichuan.general.users.entity.AuthoritiesEnum;
import com.arvinsichuan.general.users.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Project theWhiteSail
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 2017/9/28
 * <p>
 * Package: com.arvinsichuan.users.controller
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(path = "/signUp", method = RequestMethod.POST)
    public Serializable signUp(@RequestParam(value = "username") String name, @RequestParam(value =
            "password") String password, @RequestParam(value = "role", required = false) AuthoritiesEnum role) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        try {
            webInfoEntity = userService.userSignUp(name, password, role);
        } catch (DuplicatedDataException e) {
            webInfoEntity.exception(e);
        }
        return webInfoEntity;
    }

}
