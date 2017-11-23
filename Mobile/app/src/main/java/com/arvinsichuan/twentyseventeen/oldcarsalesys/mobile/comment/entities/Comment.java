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

package com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.comment.entities;


import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.account.entities.User;
import com.arvinsichuan.twentyseventeen.oldcarsalesys.mobile.carsales.entities.SellingCar;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.UUID;

/**
 * Project Server
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 22-Nov-17
 * <p>
 * Package: com.arvinsichuan.twentyseventeen.oldcarsaleserver.commet.entities
 *
 * @author ArvinSiChuan
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 646415339434993653L;
    /**
     * DD013
     */
    private String uuid = UUID.randomUUID().toString();

    /**
     * DD014
     */
    private String commentContent;

    /**
     * DD015
     */
    private SellingCar rootCar;

    /**
     * DD016
     */
    private String parentComment = null;

    /**
     * DD017
     */
    private CommentType commentType = CommentType.COMMENT;

    /**
     * DD018 Ignore Cardinality Parent Mapping.
     */
    private User publisher;

    public Comment() {

    }

    public Comment(String uuid, String commentContent, SellingCar rootCar, String parentComment, CommentType
            commentType, User publisher) {
        this.uuid = uuid;
        this.commentContent = commentContent;
        this.rootCar = rootCar;
        this.parentComment = parentComment;
        this.commentType = commentType;
        this.publisher = publisher;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @JsonBackReference
    public SellingCar getRootCar() {
        return rootCar;
    }

    public String getRootCatUuid() {
        return rootCar.getUuid();
    }

    public void setRootCar(SellingCar rootCar) {
        this.rootCar = rootCar;
    }

    public String getParentComment() {
        return parentComment;
    }

    public void setParentComment(String parentComment) {
        this.parentComment = parentComment;
    }

    public CommentType getCommentType() {
        return commentType;
    }

    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

}
