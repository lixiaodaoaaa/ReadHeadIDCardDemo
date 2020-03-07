package com.brc.idauth.bean;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-12
       Time     :  17:34
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class Square {

    private String squareName;
    private String squareId;


    public Square() {
    }

    public Square(String squareName, String squareId) {
        this.squareName = squareName;
        this.squareId = squareId;
    }

    public String getSquareName() {
        return squareName;
    }

    public void setSquareName(String squareName) {
        this.squareName = squareName;
    }

    public String getSquareId() {
        return squareId;
    }

    public void setSquareId(String squareId) {
        this.squareId = squareId;
    }

    @Override
    public String toString() {
        return "Square{" +
                "squareName='" + squareName + '\'' +
                ", squareId='" + squareId + '\'' +
                '}';
    }
}
