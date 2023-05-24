package org.nvware.core.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jakarta.persistence.*;

/**
 *
 * @author hamid SELECT * FROM telegrambotapi.word504 w
 */
@Entity
@Table(name = "word504")
public class FlashCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "left")
    private String left;

    @Column(name = "right")
    private String right;

    @Column(name = "userid")
    private long userId;

    @Column(name = "flashcardname")
    private String flashcardName;

    @Column(name = "lesson")
    private int lesson;
    @Column(name = "row")
    private int row;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFlashcardName() {
        return flashcardName;
    }

    public void setFlashcardName(String flashcardName) {
        this.flashcardName = flashcardName;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
