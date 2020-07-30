package com.example.msinotes.Models;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.msinotes.SubjectsClass;

import java.io.BufferedReader;
import java.util.ArrayList;

public class UtilityClass
{
        public static void showToast(String message, Context context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }
    public  static void getSubjectsList(ArrayList<SubjectsClass> mSubject, String sem, AppCompatActivity activity){
        ActionBar bar = activity.getSupportActionBar();

        switch (sem) {

            case "sem 1":
                bar.setTitle("YEAR 1");
                mSubject.add(getSubInfo("BCA 101"));
                mSubject.add(getSubInfo("BCA 103"));
                mSubject.add(getSubInfo("BCA 105"));
                mSubject.add(getSubInfo("BCA 107"));
                mSubject.add(getSubInfo("BCA 109"));
//                mSubject.add(new SubjectsClass("Mathematics – I", "BCA 101"));
//                mSubject.add(new SubjectsClass("Technical Communication", "BCA 103"));
//                mSubject.add(new SubjectsClass("Programming with C", "BCA 105"));
//                mSubject.add(new SubjectsClass("Introduction to Computers & IT", "BCA 107"));
//                mSubject.add(new SubjectsClass("Physics", "BCA 109"));
            break;
            case "sem 2":
                bar.setTitle("YEAR 1");
                mSubject.add(getSubInfo("BCA 102"));
                mSubject.add(getSubInfo("BCA 104"));
                mSubject.add(getSubInfo("BCA 106"));
                mSubject.add(getSubInfo("BCA 108"));
                mSubject.add(getSubInfo("BCA 110"));
//                mSubject.add(new SubjectsClass("Mathematics – II", "BCA 102"));
//                mSubject.add(new SubjectsClass("Principles of Management", "BCA 104"));
//                mSubject.add(new SubjectsClass("Digital Electronics", "BCA 106"));
//                mSubject.add(new SubjectsClass("Data Structure using C", "BCA 108"));
//                mSubject.add(new SubjectsClass("Database Management System", "BCA 110"));
                break;


            case "sem 3":
                bar.setTitle("YEAR 2");
                mSubject.add(getSubInfo("BCA 201"));
                mSubject.add(getSubInfo("BCA 203"));
                mSubject.add(getSubInfo("BCA 205"));
                mSubject.add(getSubInfo("BCA 207"));
                mSubject.add(getSubInfo("BCA 209"));
//                mSubject.add(new SubjectsClass("Mathematics – III", "BCA 201"));
//                mSubject.add(new SubjectsClass("Computer Architecture", "BCA 203"));
//                mSubject.add(new SubjectsClass("Front End Design Tool VB.Net", "BCA 205"));
//                mSubject.add(new SubjectsClass("Principles of Accounting", "BCA 207"));
//                mSubject.add(new SubjectsClass("Object Oriented Programming using C++.", "BCA 209"));
                break;
            case "sem 4":
                bar.setTitle("YEAR 2");
                mSubject.add(getSubInfo("BCA 202"));
                mSubject.add(getSubInfo("BCA 204"));
                mSubject.add(getSubInfo("BCA 206"));
                mSubject.add(getSubInfo("BCA 208"));
                mSubject.add(getSubInfo("BCA 210"));
//                mSubject.add(new SubjectsClass("Mathematics – IV", "BCA 202"));
//                mSubject.add(new SubjectsClass("Web Technologies", "BCA 204"));
//                mSubject.add(new SubjectsClass("Java Programming", "BCA 206"));
//                mSubject.add(new SubjectsClass("Software Engineering", "BCA 208"));
//                mSubject.add(new SubjectsClass("Computer Networks", "BCA 210"));
                break;

            case "sem 5":
                bar.setTitle("YEAR 3");
                mSubject.add(getSubInfo("BCA 301"));
                mSubject.add(getSubInfo("BCA 303"));
                mSubject.add(getSubInfo("BCA 305"));
                mSubject.add(getSubInfo("BCA 307"));
                mSubject.add(getSubInfo("BCA 309"));
                mSubject.add(getSubInfo("BCA 311"));
                mSubject.add(getSubInfo("BCA 313"));
                mSubject.add(getSubInfo("BCA 315"));
//                mSubject.add(new SubjectsClass("Operating System", "BCA 301"));
//                mSubject.add(new SubjectsClass("Computer Graphics", "BCA 303"));
//                mSubject.add(new SubjectsClass("E- Commerce", "BCA 305"));
//                mSubject.add(new SubjectsClass("Software Testing", "BCA 307"));
//                mSubject.add(new SubjectsClass("Microprocessor", "BCA 309"));
//                mSubject.add(new SubjectsClass("Advance Computer Networks", "BCA 311"));
//                mSubject.add(new SubjectsClass("Web Based Programming", "BCA 313"));
//                mSubject.add(new SubjectsClass("Business Economics", "BCA 315"));
            case "sem 6":
                bar.setTitle("YEAR 3");
                mSubject.add(getSubInfo("BCA 302"));
                mSubject.add(getSubInfo("BCA 304"));
                mSubject.add(getSubInfo("BCA 306"));
                mSubject.add(getSubInfo("BCA 308"));
                mSubject.add(getSubInfo("BCA 310"));
                mSubject.add(getSubInfo("BCA 312"));
                mSubject.add(getSubInfo("BCA 314"));
                mSubject.add(getSubInfo("BCA 316"));
//                mSubject.add(new SubjectsClass("Data Ware Housing & Data Mining", "BCA 302"));
//                mSubject.add(new SubjectsClass("Mobile Computing", "BCA 304"));
//                mSubject.add(new SubjectsClass("Linux Environment", "BCA 306"));
//                mSubject.add(new SubjectsClass("Multimedia & Its Applications", "BCA 308"));
//                mSubject.add(new SubjectsClass("Bio Informatics", "BCA 310"));
//                mSubject.add(new SubjectsClass("Artificial Intelligence", "BCA 312"));
//                mSubject.add(new SubjectsClass("Network Security", "BCA 314"));
//                mSubject.add(new SubjectsClass("Network Programming", "BCA 316"));

                break;

        }
    }
    public static SubjectsClass getSubInfo(String subCode){
            SubjectsClass mSubject = new SubjectsClass();
            mSubject.mSubjectCode = subCode;
            switch (subCode){

                //Semester 1
                case "BCA 101":
                    mSubject.mSubjectName = "Mathematics – I";
                    mSubject.mNotes_url = "http://bit.ly/37F75Ou";
                    mSubject.mAkash_url = "http://bit.ly/2JLqQcV";
                    mSubject.mBook_url = "http://bit.ly/2YJBwNW";
                    mSubject.mPaper_analysis_url = "http://bit.ly/2qtebF7";
                    break;
                case "BCA 103":
                    mSubject.mSubjectName = "Technical Communication";
                    break;
                case "BCA 105":
                    mSubject.mSubjectName = "Programming with C";
                    break;
                case "BCA 107":
                    mSubject.mSubjectName = "Introduction to Computers & IT";
                    break;
                case "BCA 109":
                    mSubject.mSubjectName = "Physics";
                    break;

                //Semester 2
                case "BCA 102":
                    mSubject.mSubjectName = "Mathematics – II";
                    break;
                case "BCA 104":
                    mSubject.mSubjectName = "Principles of Management";
                    break;
                case "BCA 106":
                    mSubject.mSubjectName = "Digital Electronics";
                    break;
                case "BCA 108":
                    mSubject.mSubjectName = "Data Structure using C";
                    break;
                case "BCA 110":
                    mSubject.mSubjectName = "Database Management System";
                    break;

                //Semester 3
                case "BCA 201":
                    mSubject.mSubjectName = "Mathematics – III";
                    break;
                case "BCA 203":
                    mSubject.mSubjectName = "Computer Architecture";
                    break;
                case "BCA 205":
                    mSubject.mSubjectName = "Front End Design Tool VB.Net";
                    break;
                case "BCA 207":
                    mSubject.mSubjectName = "Principles of Accounting";
                    break;
                case "BCA 209":
                    mSubject.mSubjectName = "Object Oriented Programming using C++.";
                    break;

                //Semester 4
                case "BCA 202":
                    mSubject.mSubjectName = "Mathematics – IV";
                    break;
                case "BCA 204":
                    mSubject.mSubjectName = "Web Technologies";
                    break;
                case "BCA 206":
                    mSubject.mSubjectName = "Java Programming";
                    break;
                case "BCA 208":
                    mSubject.mSubjectName = "Software Engineering";
                    break;
                case "BCA 210":
                    mSubject.mSubjectName = "Computer Networks";
                    break;

                //Semester 5
                case "BCA 301":
                    mSubject.mSubjectName = "Operating System";
                    break;
                case "BCA 303":
                    mSubject.mSubjectName = "Computer Graphics";
                    break;
                case "BCA 305":
                    mSubject.mSubjectName = "E- Commerce";
                    break;
                case "BCA 307":
                    mSubject.mSubjectName = "Software Testing";
                    break;
                case "BCA 309":
                    mSubject.mSubjectName = "Microprocessor";
                    break;
                case "BCA 311":
                    mSubject.mSubjectName = "Advance Computer Networks";
                    break;
                case "BCA 313":
                    mSubject.mSubjectName = "Web Based Programming";
                    break;
                case "BCA 315":
                    mSubject.mSubjectName = "Business Economics";
                    break;

                //Semester 6
                case "BCA 302":
                    mSubject.mSubjectName = "Data Ware Housing & Data Mining";
                    break;
                case "BCA 304":
                    mSubject.mSubjectName = "Mobile Computing";
                    break;
                case "BCA 306":
                    mSubject.mSubjectName = "Linux Environment";
                    break;
                case "BCA 308":
                    mSubject.mSubjectName = "Multimedia & Its Applications";
                    break;
                case "BCA 310":
                    mSubject.mSubjectName = "Bio Informatics";
                    break;
                case "BCA 312":
                    mSubject.mSubjectName = "Artificial Intelligence";
                    break;
                case "BCA 314":
                    mSubject.mSubjectName = "Network Security";
                    break;
                case "BCA 316":
                    mSubject.mSubjectName = "Network Programming";
                    break;
            }
            return  mSubject;
    }
}
