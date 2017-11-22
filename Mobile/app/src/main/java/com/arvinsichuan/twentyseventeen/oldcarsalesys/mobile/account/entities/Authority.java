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

package com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.account.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;

/**
 * Project Mobile
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 22-Nov-17
 * <p>
 * Package: com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.account.entities
 * @author ArvinSiChuan
 */
public class Authority implements Serializable{


    private static final long serialVersionUID = 9055330315894110112L;
    private AuthoritiesEnum authority;

    private User userByUsername;

    public Authority() {
    }

    public Authority(AuthoritiesEnum authority, User userByUsername) {
        this.authority = authority;
        this.userByUsername = userByUsername;
    }



    public AuthoritiesEnum getAuthority() {
        return authority;
    }

    public void setAuthority(AuthoritiesEnum authority) {
        this.authority = authority;
    }

    public User getUserByUsername() {
        return userByUsername;
    }

    public void setUserByUsername(User userByUsername) {
        this.userByUsername = userByUsername;
    }

}
