package com.example.msinotes.Models;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.msinotes.SubjectsClass;

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
                mSubject.add(new SubjectsClass("Mathematics – I", "BCA 101"));
                mSubject.add(new SubjectsClass("Technical Communication", "BCA 103"));
                mSubject.add(new SubjectsClass("Programming with C", "BCA 105"));
                mSubject.add(new SubjectsClass("Introduction to Computers & IT", "BCA 107"));
                mSubject.add(new SubjectsClass("Physics", "BCA 109"));
            break;
            case "sem 2":
                bar.setTitle("YEAR 1");
                mSubject.add(new SubjectsClass("Mathematics – II", "BCA 102"));
                mSubject.add(new SubjectsClass("Principles of Management", "BCA 104"));
                mSubject.add(new SubjectsClass("Digital Electronics", "BCA 106"));
                mSubject.add(new SubjectsClass("Data Structure using C", "BCA 108"));
                mSubject.add(new SubjectsClass("Database Management System", "BCA 110"));
                break;


            case "sem 3":
                bar.setTitle("YEAR 2");
                mSubject.add(new SubjectsClass("Mathematics – III", "BCA 201"));
                mSubject.add(new SubjectsClass("Computer Architecture", "BCA 203"));
                mSubject.add(new SubjectsClass("Front End Design Tool VB.Net", "BCA 205"));
                mSubject.add(new SubjectsClass("Principles of Accounting", "BCA 207"));
                mSubject.add(new SubjectsClass("Object Oriented Programming using C++.", "BCA 209"));
                break;
            case "sem 4":
                bar.setTitle("YEAR 2");
                mSubject.add(new SubjectsClass("Mathematics – IV", "BCA 202"));
                mSubject.add(new SubjectsClass("Web Technologies", "BCA 204"));
                mSubject.add(new SubjectsClass("Java Programming", "BCA 206"));
                mSubject.add(new SubjectsClass("Software Engineering", "BCA 208"));
                mSubject.add(new SubjectsClass("Computer Networks", "BCA 210"));
                break;

            case "sem 5":
                bar.setTitle("YEAR 3");
                mSubject.add(new SubjectsClass("Operating System", "BCA 301"));
                mSubject.add(new SubjectsClass("Computer Graphics", "BCA 303"));
                mSubject.add(new SubjectsClass("E- Commerce", "BCA 305"));
                mSubject.add(new SubjectsClass("Software Testing", "BCA 307"));
                mSubject.add(new SubjectsClass("Microprocessor", "BCA 309"));
                mSubject.add(new SubjectsClass("Advance Computer Networks", "BCA 311"));
                mSubject.add(new SubjectsClass("Web Based Programming", "BCA 313"));
                mSubject.add(new SubjectsClass("Business Economics", "BCA 315"));
            case "sem 6":
                bar.setTitle("YEAR 3");
                mSubject.add(new SubjectsClass("Data Ware Housing & Data Mining", "BCA 302"));
                mSubject.add(new SubjectsClass("Mobile Computing", "BCA 304"));
                mSubject.add(new SubjectsClass("Linux Environment", "BCA 306"));
                mSubject.add(new SubjectsClass("Multimedia & Its Applications", "BCA 308"));
                mSubject.add(new SubjectsClass("Bio Informatics", "BCA 310"));
                mSubject.add(new SubjectsClass("Artificial Intelligence", "BCA 312"));
                mSubject.add(new SubjectsClass("Network Security", "BCA 314"));
                mSubject.add(new SubjectsClass("Network Programming", "BCA 316"));

                break;

        }
    }
}
