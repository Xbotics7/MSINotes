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
    public static void showToast(String message, Context context)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }

    public static void getSubjectsList(ArrayList<SubjectsClass> mSubject)
    {
        getSubjectsList(mSubject, "sem 1", null);
        getSubjectsList(mSubject, "sem 2", null);
        getSubjectsList(mSubject, "sem 3", null);
        getSubjectsList(mSubject, "sem 4", null);
        getSubjectsList(mSubject, "sem 5", null);
        getSubjectsList(mSubject, "sem 6", null);
    }

    public static void getSubjectsList(ArrayList<SubjectsClass> mSubject, String sem, ActionBar actionBar)
    {


        switch (sem)
        {

            case "sem 1":
                if (actionBar != null)
                    actionBar.setTitle("YEAR 1");
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
                if (actionBar != null)
                    actionBar.setTitle("YEAR 1");
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
                if (actionBar != null)
                    actionBar.setTitle("YEAR 2");
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
                if (actionBar != null)
                    actionBar.setTitle("YEAR 2");
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
                if (actionBar != null)
                    actionBar.setTitle("YEAR 3");
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
                if (actionBar != null)
                    actionBar.setTitle("YEAR 3");
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

    public static SubjectsClass getSubInfo(String subCode)
    {
        SubjectsClass mSubject = new SubjectsClass();
        mSubject.mSubjectCode = subCode;
        switch (subCode)
        {

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
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/34rHRk6";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2r52vIL";
                break;
            case "BCA 105":
                mSubject.mSubjectName = "Programming with C";
                mSubject.mNotes_url = "http://bit.ly/2KSc8BF";
                mSubject.mAkash_url = "http://bit.ly/36sGzaq";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2KD4r26";
                break;
            case "BCA 107":
                mSubject.mSubjectName = "Introduction to Computers & IT";
                mSubject.mNotes_url = "http://bit.ly/2KSc8BF";
                mSubject.mAkash_url = "http://bit.ly/36jH8DA";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2KDmC7P";
                break;
            case "BCA 109":
                mSubject.mSubjectName = "Physics";
                mSubject.mNotes_url = "http://bit.ly/2YHU5pF";
                mSubject.mAkash_url = "http://bit.ly/2N4qqAF";
                mSubject.mBook_url = "http://bit.ly/2Zg21Pj";
                mSubject.mPaper_analysis_url = "http://bit.ly/2O0LlF4";
                break;

            //Semester 2
            case "BCA 102":
                mSubject.mSubjectName = "Mathematics – II";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/2VMFqZ6";
                mSubject.mBook_url = "http://bit.ly/2IeMGXT";
                mSubject.mPaper_analysis_url = "http://bit.ly/2VlqvAx";
                break;
            case "BCA 104":
                mSubject.mSubjectName = "Principles of Management";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/2GVfQHC";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2WQVRk3";
                break;
            case "BCA 106":
                mSubject.mSubjectName = "Digital Electronics";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/2Lmhpnw";
                mSubject.mBook_url = "http://bit.ly/2tq221A";
                mSubject.mPaper_analysis_url = "http://bit.ly/2M8wrNZ";
                break;
            case "BCA 108":
                mSubject.mSubjectName = "Data Structure using C";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/2PLg1cz";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2Lw940y";
                break;
            case "BCA 110":
                mSubject.mSubjectName = "Database Management System";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/2W3aa4R";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2J4Nxdt";
                break;

            //Semester 3
            case "BCA 201":
                mSubject.mSubjectName = "Mathematics – III";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/2PvCWdJ";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2OAet6b";
                break;
            case "BCA 203":
                mSubject.mSubjectName = "Computer Architecture";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/34fTN8E";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2CgyLe6";
                break;
            case "BCA 205":
                mSubject.mSubjectName = "Front End Design Tool VB.Net";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/36qRVvV";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/36kdFZr";
                break;
            case "BCA 207":
                mSubject.mSubjectName = "Principles of Accounting";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/33qemz8";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2LJo9tx";
                break;
            case "BCA 209":
                mSubject.mSubjectName = "Object Oriented Programming using C++.";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/2PKl6SU";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2LXoSr0";
                break;

            //Semester 4
            case "BCA 202":
                mSubject.mSubjectName = "Mathematics – IV";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 204":
                mSubject.mSubjectName = "Web Technologies";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 206":
                mSubject.mSubjectName = "Java Programming";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 208":
                mSubject.mSubjectName = "Software Engineering";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 210":
                mSubject.mSubjectName = "Computer Networks";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "http://bit.ly/2TLs1gS";
                mSubject.mPaper_analysis_url = "";
                break;

            //Semester 5
            case "BCA 301":
                mSubject.mSubjectName = "Operating System";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 303":
                mSubject.mSubjectName = "Computer Graphics";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 305":
                mSubject.mSubjectName = "E- Commerce";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";

                break;
            case "BCA 307":
                mSubject.mSubjectName = "Software Testing";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 309":
                mSubject.mSubjectName = "Microprocessor";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 311":
                mSubject.mSubjectName = "Advance Computer Networks";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 313":
                mSubject.mSubjectName = "Web Based Programming";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 315":
                mSubject.mSubjectName = "Business Economics";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;

            //Semester 6
            case "BCA 302":
                mSubject.mSubjectName = "Data Ware Housing & Data Mining";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 304":
                mSubject.mSubjectName = "Mobile Computing";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 306":
                mSubject.mSubjectName = "Linux Environment";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 308":
                mSubject.mSubjectName = "Multimedia & Its Applications";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 310":
                mSubject.mSubjectName = "Bio Informatics";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 312":
                mSubject.mSubjectName = "Artificial Intelligence";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 314":
                mSubject.mSubjectName = "Network Security";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
            case "BCA 316":
                mSubject.mSubjectName = "Network Programming";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                break;
        }
        return mSubject;
    }
}
