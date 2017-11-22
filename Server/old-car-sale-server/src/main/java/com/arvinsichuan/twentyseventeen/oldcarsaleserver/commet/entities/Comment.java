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

package com.arvinsichuan.twentyseventeen.oldcarsaleserver.commet.entities;

import com.arvinsichuan.general.users.entity.User;
import com.arvinsichuan.twentyseventeen.oldcarsaleserver.carsale.entities.SellingCar;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
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
@Entity
@Table(name = "comments")
public class Comment implements Serializable {
    private static final long serialVersionUID = 646415339434993653L;
    /**
     * DD013
     */
    @Id
    @Column(name = "uuid", length = 36, nullable = false)
    private String uuid = UUID.randomUUID().toString();

    /**
     * DD014
     */
    @Column(name = "comment_content", length = 2048)
    private String commentContent;

    /**
     * DD015
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "root_car", nullable = false,
            foreignKey = @ForeignKey(name = "fk_comment_selling_cars"))
    private SellingCar rootCar;

    /**
     * DD016
     */
    @Column(name = "parent_comment", length = 36)
    private String parentComment = null;

    /**
     * DD017
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "comment_type", length = 24, nullable = false)
    private CommentType commentType = CommentType.COMMENT;

    /**
     * DD018 Ignore Cardinality Parent Mapping.
     */
    //TODO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher", nullable = false,
            foreignKey = @ForeignKey(name = "fk_comment_users"))
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
