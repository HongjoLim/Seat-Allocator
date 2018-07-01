package guiAssignment.model;

import javafx.scene.paint.Color;

    /**
     * NAME: HONGJO LIM
     * DATE: July 1, 2018
     * PURPOSE: This class is to store the information of a student
     */


public class Student {

    //a variable to store student name
    private String name;

    //a variable to store the number of the seat (to store the index of Rectangle ArrayList)
    private int seatIndex;

    //a variable to store the string value for the color of the seat
    private Color seatColor;

    //the constructor that 3 arguments for name, seatNumber, and color
    public Student(String name, int seatIndex, Color seatColor){

        this.name = name;
        this.seatIndex = seatIndex;
        this.seatColor = seatColor;

    }

        public String getName() {
            return name;
        }

        public int getSeatIndex() {
            return seatIndex;
        }

        public Color getSeatColor() {
            return seatColor;
        }
    }
