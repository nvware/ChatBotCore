package org.nvware.core.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hamid SELECT * FROM telegrambotapi.word504 w
 */
public class Word504 {

    private int id;

    private int lessons;
    private int rows;
    private String Left;
    private String Right;

    public Word504() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLessons() {
        return lessons;
    }

    public void setLessons(int lessons) {
        this.lessons = lessons;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getLeft() {
        return Left;
    }

    public void setLeft(String Left) {
        this.Left = Left;
    }

    public String getRight() {
        return Right;
    }

    public void setRight(String Right) {
        this.Right = Right;
    }
    
}
