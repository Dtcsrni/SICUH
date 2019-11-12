module SICUH{
        requires javafx.fxml;
        requires javafx.controls;
        requires java.sql;


        opens dao;
        opens main;
        opens modelo;
        opens recursos;
        opens vista;

}