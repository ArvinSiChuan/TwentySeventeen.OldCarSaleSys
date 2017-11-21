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

package com.arvinsichuan.twentyseventeen.oldcarsaleserver.sessionTest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Project Server
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 11/20/2017
 * <p>
 * Package: com.arvinsichuan.twentyseventeen.oldcarsaleserver.sessionTest
 *
 * @author ArvinSiChuan
 */
@RestController
@RequestMapping("/testSession")
public class SessionTestController {

    @PostMapping(path = "/post")
    public String testSession(@RequestParam("info") String info, HttpServletRequest request) {
        System.out.println("###########################################");
        System.out.println("###########################################");
        System.out.println("###########################################");
        System.out.println("###########################################");
        System.out.println(request.getHeaderNames());
        return "{\""+info + "\":\"Received\"}";
    }


    @GetMapping(path = "/csrf")
    public CsrfToken getCSRF(String info, CsrfToken csrfToken) {
        return csrfToken;
    }
}
