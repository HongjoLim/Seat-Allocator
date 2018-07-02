package guiAssignment_200385753;

import guiAssignment_200385753.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.*;

    /**
     * NAME: HONGJO LIM
     * DATE: June 30, 2018
     * PURPOSE: This class is the controller of this program
     */

public class Controller {

    /**
     * This class has ...
     *
     *      1) 9 Texts for student names
     *      2) 9 Rectangles indicating seats
     *      3) 1 Text to show error message
     *      4) 1 TextField to input a student name
     *      5) 1 ColorPicker to choose the color of the seat
     *      6) 1 Button to place students
     */

    @FXML
    private TextField studentNameText;

    @FXML
    private ColorPicker colorChoice;

    @FXML
    private Text name1, name2, name3, name4, name5, name6, name7, name8, name9;

    @FXML
    private Text messageText;

    @FXML
    private Rectangle seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9;

    @FXML
    private Button placeButton;

    private List<Text> names;

    private List<Rectangle> seats;

    private List<Student> students = new ArrayList<>();

    //constants to distinguish whether the process is done without any error or not
    private final int CASE_ERROR = 1;
    private final int CASE_SUCCESS = 2;

    private final String FAIL_MESSAGE_COLOR = "RED";
    private final String SUCCESS_MESSAGE_COLOR = "BLUE";

    @FXML
    public void initialize(){

        //call the method to construct the ArrayList for names
        constructNamesArrayList();

        //call the method to construct the ArrayList for seats
        constructSeatsArrayList();

    }

    private void constructNamesArrayList(){

        //initialize the ArrayList instance to store names
        names = new ArrayList<>();

        //add Text components to the ArrayList
        names.add(name1);
        names.add(name2);
        names.add(name3);
        names.add(name4);
        names.add(name5);
        names.add(name6);
        names.add(name7);
        names.add(name8);
        names.add(name9);

    }

    private void constructSeatsArrayList(){

        //initialize the ArrayList instance to store seats
        seats = new ArrayList<>();

        seats.add(seat1);
        seats.add(seat2);
        seats.add(seat3);
        seats.add(seat4);
        seats.add(seat5);
        seats.add(seat6);
        seats.add(seat7);
        seats.add(seat8);
        seats.add(seat9);

    }

    //a method to handle the ActionEvent set on the placeButton component
    @FXML
    private void onButtonAction(ActionEvent event){

        //if the errorMessage text is visible, make it invisible
        if(messageText.isVisible()){
            messageText.setVisible(false);
        }


        //check if all the 9 seats are taken
        if(students.size()>=9){

            //set the error message
            setMessage("All the seats are taken", CASE_ERROR);
            return;
        }

        //get student's name from the TextField
        String studentName = studentNameText.getText();

        //get the color from the ColorPicker component
        Color seatColor = colorChoice.getValue();

        //check whether the student's name is valid or not
        if(!validateStudentName(studentName)){

            //set the error message
            setMessage("Invalid Student Name", CASE_ERROR);

            //finish this method
            return;
        }

        //check whether the color is available or not
        if(!checkColorAvailability(seatColor)){

            //set the error message
            setMessage("This color has already been used", CASE_ERROR);

            //finish this method
            return;
        }

        //if all the validation checks are done, designate a seat
        designateSeat(studentName, seatColor);

    }

    //the method to set error message on the errorMessage text after receiving a string value for the error message
    public void setMessage(String message, int caseTag){

        //make errorMessage component invisible
        messageText.setVisible(true);

        if(caseTag==CASE_ERROR){
            messageText.setFill(Paint.valueOf(FAIL_MESSAGE_COLOR));
        }else{
            messageText.setFill(Paint.valueOf(SUCCESS_MESSAGE_COLOR));
        }

        //set the message
        messageText.setText(message);

    }

    //a method to check whether a student name is valid or not
    private boolean validateStudentName(String studentName){

        boolean availability = true;

        //if the name is empty, return false
        if(studentName.trim().isEmpty()){

            availability = false;

        }else{

            //loop through the ArrayList of Student to find the same name
            for(int i = 0; i<students.size(); i++){

                //if the same name exists, set the availability false
                if(students.get(i).getName().equals(studentName)){
                    availability = false;
                }
            }
        }

        return availability;
    }

    //a method to designate a seat for a student
    private void designateSeat(String studentName, Color seatColor){

        Random random = new Random();

        //get a random integer to randomly choose a seat by using random class
        int randomNumber;

        //continue the do-while loop until we find the available index for the seat
        do{

            randomNumber = random.nextInt(seats.size());

        }while(!checkSeatAvailability(randomNumber));


        //initialize an instance of the Student class passing values for seat, name
        Student student = new Student(studentName, randomNumber, seatColor);

        students.add(student);

        //fill the rectangle with the chosen color
        seats.get(student.getSeatIndex()).setFill(student.getSeatColor());

        //set the student's name for the text that has the same index as that of the seat
        names.get(student.getSeatIndex()).setVisible(true);
        names.get(student.getSeatIndex()).setText(student.getName());

        //set the message using the custom method, set the color blue(using the constant CASE_SUCCESS)
        setMessage(String.format("%s\'s seat is successfully assigned!", student.getName()), CASE_SUCCESS);

    }

    //the method to iterate ArrayList of students to find whether the seat index is already taken or not
    private boolean checkSeatAvailability(int randomNumber) {

        boolean availability = true;

        //loop through the ArrayList of students to check whether the seat number is taken or not
        for (int i = 0; i < students.size(); i++) {

            //if the same number is found, set availability to true
            if (students.get(i).getSeatIndex() == randomNumber) {
                availability = false;
            }
        }

        return availability;
    }

    //the method to iterate ArrayList of students to find whether the seat color is already taken or not
    private boolean checkColorAvailability(Color color){

        boolean availability= true;

        for(int i = 0; i<students.size(); i++){

            if(students.get(i).getSeatColor().equals(color)){
                availability = false;
            }
        }
        return availability;

    }
    
}
