package com.example.msinotes.Models;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import com.example.msinotes.NotesClass;
import com.example.msinotes.R;
import com.example.msinotes.SubjectsClass;
import com.example.msinotes.YoutubeClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UtilityClass
{
    public static boolean isBookmark = false;

    public static void showToast(String message, Context context)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static ArrayList<NotesClass> getNotesBookMarks(Context context)
    {
        ArrayList<NotesClass> savedNotesList = new ArrayList<>();
        Gson gson = new Gson();

        SharedPreferences sharedPrefBookMark = PreferenceManager.getDefaultSharedPreferences(context);

        String json = sharedPrefBookMark.getString("BookMark List", "");
        Type type = new TypeToken<ArrayList<NotesClass>>()
        {
        }.getType();

        savedNotesList = gson.fromJson(json, type);

        if (savedNotesList == null)
        {
            savedNotesList = new ArrayList<>();
        }
        return savedNotesList;
    }


    public static void saveToBookmark(NotesClass subNotes, ImageButton btnBookMark, ImageButton btnUnBookMark, Context context)
    {
        Gson gson = new Gson();

        SharedPreferences sharedPrefBookMark = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefBookMark.edit();

        ArrayList<NotesClass> notesList = new ArrayList<>();

        String json = sharedPrefBookMark.getString("BookMark List", "");
        Type type = new TypeToken<ArrayList<NotesClass>>()
        {
        }.getType();

        notesList = gson.fromJson(json, type);

        if (notesList == null)
        {
            notesList = new ArrayList<>();
        }

        notesList.add(subNotes);

        String json2 = gson.toJson(notesList);

        editor.putString("BookMark List", json2);
        editor.apply();

        btnBookMark.setVisibility(View.GONE);
        btnUnBookMark.setVisibility(View.VISIBLE);

        UtilityClass.showToast("Bookmarked", context);

    }

    public static void removeFromBookMark(NotesClass currentNotesItem, ImageButton btnBookMark, ImageButton btnUnBookMark, Context context)
    {
        Gson gson = new Gson();
        ArrayList<NotesClass> savedNotesList = UtilityClass.getNotesBookMarks(context);

        SharedPreferences sharedPrefBookMark = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefBookMark.edit();

        savedNotesList.remove(currentNotesItem);
        String json2 = gson.toJson(savedNotesList);

        editor.putString("BookMark List", json2);
        editor.apply();

        btnBookMark.setVisibility(View.VISIBLE);
        btnUnBookMark.setVisibility(View.GONE);


        UtilityClass.showToast("UnBookmarked", context);
    }

    public static ArrayList<YoutubeClass> getYTBookMarks(Context context)
    {
        ArrayList<YoutubeClass> savedYTList = new ArrayList<>();
        Gson gson = new Gson();

        SharedPreferences sharedPrefBookMark = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = sharedPrefBookMark.edit();
//        editor.putString("YT BookMark List", "");
//        editor.apply();
        String json = sharedPrefBookMark.getString("YT BookMark List", "");
        Type type = new TypeToken<ArrayList<YoutubeClass>>()
        {
        }.getType();

        savedYTList = gson.fromJson(json, type);

        if (savedYTList == null)
        {
            savedYTList = new ArrayList<>();
        }
        return savedYTList;
    }

    public static void saveToYTBookmark(YoutubeClass ytNotes, ImageButton btnBookMark, ImageButton btnUnBookMark, Context context)
    {
        Gson gson = new Gson();

        SharedPreferences sharedPrefBookMark = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefBookMark.edit();

        ArrayList<YoutubeClass> ytList = new ArrayList<>();

        String json = sharedPrefBookMark.getString("YT BookMark List", "");
        Type type = new TypeToken<ArrayList<YoutubeClass>>()
        {
        }.getType();

        ytList = gson.fromJson(json, type);

        if (ytList == null)
        {
            ytList = new ArrayList<>();
        }

        ytList.add(ytNotes);

        String json2 = gson.toJson(ytList);

        editor.putString("YT BookMark List", json2);
        editor.apply();

        btnBookMark.setVisibility(View.GONE);
        btnUnBookMark.setVisibility(View.VISIBLE);

        UtilityClass.showToast("Bookmarked", context);

    }

    public static void removeFromYTBookMark(YoutubeClass currentYTItem, ImageButton btnBookMark, ImageButton btnUnBookMark, Context context)
    {
        Gson gson = new Gson();
        ArrayList<YoutubeClass> savedYTList = UtilityClass.getYTBookMarks(context);

        SharedPreferences sharedPrefBookMark = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefBookMark.edit();

        savedYTList.remove(currentYTItem);
        String json2 = gson.toJson(savedYTList);

        editor.putString("YT BookMark List", json2);
        editor.apply();

        btnBookMark.setVisibility(View.VISIBLE);
        btnUnBookMark.setVisibility(View.GONE);


        UtilityClass.showToast("UnBookmarked", context);
    }

    public static void getSubjectsList(ArrayList<SubjectsClass> mSubject)
    {
        //Gets all subjects from every sem
        getSubjectsList(mSubject, "semester 1", null);
        getSubjectsList(mSubject, "semester 2", null);
        getSubjectsList(mSubject, "semester 3", null);
        getSubjectsList(mSubject, "semester 4", null);
        getSubjectsList(mSubject, "semester 5", null);
        getSubjectsList(mSubject, "semester 6", null);
    }

    public static void getSubjectsList(ArrayList<SubjectsClass> mSubject, String sem, Toolbar actionBar)
    {
        //Returns Subjects list according to sem
        if (actionBar != null)
        {
            TextView toolbar_text = actionBar.findViewById(R.id.toolbar_title);
            toolbar_text.setText(sem.toUpperCase());
        }
        switch (sem)
        {

            case "semester 1":
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
            case "semester 2":
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


            case "semester 3":
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
            case "semester 4":
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

            case "semester 5":
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
            case "semester 6":
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
        mSubject.mNotesList_url = new ArrayList<NotesClass>();
        mSubject.mYoutube_url = new ArrayList<YoutubeClass>();
        switch (subCode)
        {
            //Semester 1
            case "BCA 101":
                mSubject.mSubjectName = "Mathematics – I";
                mSubject.mNotes_url = "http://bit.ly/37F75Ou";
                mSubject.mAkash_url = "http://bit.ly/2JLqQcV";
                mSubject.mBook_url = "http://bit.ly/2YJBwNW";
                mSubject.mPaper_analysis_url = "http://bit.ly/2qtebF7";
                mSubject.mYoutube_url.add(new YoutubeClass("Determinant", "https://www.youtube.com/playlist?list=PLSUTYSVj7etzxi3o_dnEab50DFYIOgsI3"));
                mSubject.mYoutube_url.add(new YoutubeClass("Differential Calculas", "https://www.youtube.com/playlist?list=PLSUTYSVj7etzYljLRSVKrfnNBlGCvyp6E"));
                mSubject.mYoutube_url.add(new YoutubeClass("Differentiation", "https://www.youtube.com/playlist?list=PLHz3_2lRlEzpSkcheaJizPNyldHCiFBkC"));
                mSubject.mYoutube_url.add(new YoutubeClass("Integrations", "https://www.youtube.com/playlist?list=PLkW3zC7NnNHYIBBZeXCex_YETRDCtre4u"));
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
                mSubject.mYoutube_url.add(new YoutubeClass("Videos List 1", "https://www.youtube.com/playlist?list=PLLioQ130_xjVgW5MVfLS1s7zfLFAbGIVs"));
                mSubject.mYoutube_url.add(new YoutubeClass("Videos List 2", "https://www.youtube.com/playlist?list=PLVlQHNRLflP8IGz6OXwlV_lgHgc72aXlh"));
                break;
            case "BCA 107":
                mSubject.mSubjectName = "Introduction to Computers & IT";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/36jH8DA";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2KDmC7P";
                mSubject.mNotesList_url.add(new NotesClass("Unit 1 & 3", "http://bit.ly/2Ktv5to"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 2 & 4", "http://bit.ly/2yC7mBC"));
                mSubject.mYoutube_url.add(new YoutubeClass("Computer Fundamentals", "https://www.youtube.com/playlist?list=PLWPirh4EWFpF_2T13UeEgZWZHc8nHBuXp"));
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
                mSubject.mYoutube_url.add(new YoutubeClass("Sets", "https://www.youtube.com/playlist?list=PLHz3_2lRlEzqT6B1h-AhgtvIbdbK9RRb-"));
                mSubject.mYoutube_url.add(new YoutubeClass("Relations", "https://www.youtube.com/playlist?list=PLHz3_2lRlEzr9pmT_Qpk3eJGdUuSr6FON"));
                mSubject.mYoutube_url.add(new YoutubeClass("Graph Theory", "https://www.youtube.com/playlist?list=PLmXKhU9FNesS7GpOddHDX3ZCl86_cwcIn"));
                mSubject.mYoutube_url.add(new YoutubeClass("Whole Unit", "https://www.youtube.com/playlist?list=PLDDGPdw7e6Ag1EIznZ-m-qXu4XX3A0cIz"));
                break;
            case "BCA 104":
                mSubject.mSubjectName = "Principles of Management";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/2GVfQHC";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2WQVRk3";
                mSubject.mYoutube_url.add(new YoutubeClass("POM", "https://www.youtube.com/playlist?list=PLeWDeRMAMaffBCwESfmFmo0grMpwO8z1J"));
                break;
            case "BCA 106":
                mSubject.mSubjectName = "Digital Electronics";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/2Lmhpnw";
                mSubject.mBook_url = "http://bit.ly/2tq221A";
                mSubject.mPaper_analysis_url = "http://bit.ly/2M8wrNZ";
                mSubject.mYoutube_url.add(new YoutubeClass("DE", "https://www.youtube.com/playlist?list=PLBlnK6fEyqRjMH3mWf6kwqiTbT798eAOm"));
                break;
            case "BCA 108":
                mSubject.mSubjectName = "Data Structure using C";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/2PLg1cz";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2Lw940y";
                mSubject.mYoutube_url.add(new YoutubeClass("DS", "https://www.youtube.com/playlist?list=PLdo5W4Nhv31bbKJzrsKfMpo_grxuLl8LU"));
                break;
            case "BCA 110":
                mSubject.mSubjectName = "Database Management System";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/2W3aa4R";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2J4Nxdt";
                mSubject.mYoutube_url.add(new YoutubeClass("DBMS", "https://www.youtube.com/playlist?list=PLdo5W4Nhv31b33kF46f9aFjoJPOkdlsRc"));
                break;

            //Semester 3
            case "BCA 201":
                mSubject.mSubjectName = "Mathematics – III";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/2PvCWdJ";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2OAet6b";
                mSubject.mNotesList_url.add(new NotesClass("Unit 1: Measure Of Central Tendency and Dispersion", "http://bit.ly/33kFYWG"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 2: Correlation/Regression", "http://bit.ly/2lNFIhI"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 3 + 4", "http://bit.ly/2BDFCxW"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 1 & Unit 2 formulae", "http://bit.ly/2kJRXf9"));

                mSubject.mYoutube_url.add(new YoutubeClass("Unit I and II", "https://www.youtube.com/playlist?list=PLkW3zC7NnNHYhpN0e7LJW9MMDKyIT_dpV"));
                mSubject.mYoutube_url.add(new YoutubeClass("Unit III and IV", "https://www.youtube.com/playlist?list=PLabr9RWfBcnoLyXr4Y7MzmHSu3bDjLvhu"));
                break;
            case "BCA 203":
                mSubject.mSubjectName = "Computer Architecture";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/34fTN8E";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2CgyLe6";
                mSubject.mNotesList_url.add(new NotesClass("Unit 1", "http://bit.ly/2jZUFwO"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 2", "http://bit.ly/2qAufV8"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 3", "http://bit.ly/32HIiXm"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 4", "http://bit.ly/2s23UjH"));
                mSubject.mNotesList_url.add(new NotesClass("Notes by Mam", "http://bit.ly/2rkb9TK"));

                mSubject.mYoutube_url.add(new YoutubeClass("CA", "https://www.youtube.com/playlist?list=PLftJ4X48yC1nRU3-6h0P30rWoT-hznf74"));
                break;
            case "BCA 205":
                mSubject.mSubjectName = "Front End Design Tool VB.Net";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/36qRVvV";
                mSubject.mBook_url = "http://bit.ly/35j7Zy7";
                mSubject.mPaper_analysis_url = "http://bit.ly/36kdFZr";
                mSubject.mNotesList_url.add(new NotesClass("Unit 1 & 2", "http://bit.ly/2lHp8A9"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 3 & 4", "http://bit.ly/34OVt9v"));

                mSubject.mYoutube_url.add(new YoutubeClass("VB NET 1", "https://www.youtube.com/playlist?list=PLS1QulWo1RIYLpgVN_CpXbkOQoYJTItzg"));
                mSubject.mYoutube_url.add(new YoutubeClass("VB NET 2", "https://www.youtube.com/playlist?list=PL6n9fhu94yhX5dzHunAI2t4kE0kOuv4D7"));
                break;
            case "BCA 207":
                mSubject.mSubjectName = "Principles of Accounting";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/33qemz8";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2LJo9tx";
                mSubject.mNotesList_url.add(new NotesClass("Unit 1 & 2", "http://bit.ly/2kuzhQF"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 2 & 3", "http://bit.ly/2JkyTx1"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 4", "http://bit.ly/33aeyBW"));

                mSubject.mYoutube_url.add(new YoutubeClass("POA", "https://www.youtube.com/playlist?list=PLEJOTGLf455Dlox6VG_2ODwrG3jA0MJb5"));
                break;
            case "BCA 209":
                mSubject.mSubjectName = "Object Oriented Programming using C++.";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "http://bit.ly/2PKl6SU";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "http://bit.ly/2LXoSr0";
                mSubject.mNotesList_url.add(new NotesClass("Unit 1: Introduction & C++ Environment", "http://bit.ly/2ZH0oHc"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 2: Classes & Objects", "http://bit.ly/2ks7Sid"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 3: Inheritance & Polymorphism", "http://bit.ly/335gMlY"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 4: Generic Programming", "http://bit.ly/2rdmnJq"));

                mSubject.mYoutube_url.add(new YoutubeClass("C++", "https://www.youtube.com/playlist?list=PLfVsf4Bjg79DLA5K3GLbIwf3baNVFO2Lq"));
                break;

            //Semester 4
            case "BCA 202":
                mSubject.mSubjectName = "Mathematics – IV";
                mSubject.mNotes_url = "http://bit.ly/3bJufoU";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";

                mSubject.mYoutube_url.add(new YoutubeClass("Combinatorics/Discrete Math II", "https://www.youtube.com/playlist?list=PLl-gb0E4MII1_QX6h6TzMW3rF_7Taapyd"));
                mSubject.mYoutube_url.add(new YoutubeClass("Probability Distributions", "https://www.youtube.com/playlist?list=PLaFfQroTgZnzbfK-Rie19FdV6diehETQy"));
                mSubject.mYoutube_url.add(new YoutubeClass("Numerical Analysis", "https://www.youtube.com/playlist?list=PLU6SqdYcYsfLrTna7UuaVfGZYkNo0cpVC"));
                break;
            case "BCA 204":
                mSubject.mSubjectName = "Web Technologies";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "https://bit.ly/3g5d9nN";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";
                mSubject.mNotesList_url.add(new NotesClass("Unit 1", "http://bit.ly/37vYnRm"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 2 - CSS", "http://bit.ly/2I9YjfR"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 2 - JS", "http://bit.ly/2vnizIm"));
                mSubject.mNotesList_url.add(new NotesClass("Notes by Mam", "http://bit.ly/2PG9alX"));

                mSubject.mYoutube_url.add(new YoutubeClass("HTML Tutorials", "https://www.youtube.com/playlist?list=PL9OpiZLUnhZAfnqwbKuP_WdCsgaOC24qV"));
                mSubject.mYoutube_url.add(new YoutubeClass("JavaScript Tutorial for Beginners", "https://www.youtube.com/playlist?list=PLsyeobzWxl7qtP8Lo9TReqUMkiOp446cV"));
                mSubject.mYoutube_url.add(new YoutubeClass("CSS Tutorial for Beginners", "https://www.youtube.com/playlist?list=PLr6-GrHUlVf8JIgLcu3sHigvQjTw_aC9C"));
                mSubject.mYoutube_url.add(new YoutubeClass("dHTML", "https://www.youtube.com/playlist?list=PLWWqE3Nx9YpvDDohYNl9HhU_pgh0YwnVk"));
                mSubject.mYoutube_url.add(new YoutubeClass("WT 4 Unit 4", "https://www.youtube.com/playlist?list=PLkW3zC7NnNHa16dJjawlr6L_ckqUUs_ln"));
                mSubject.mYoutube_url.add(new YoutubeClass("XML Tutorial", "https://www.youtube.com/playlist?list=PLfX2IHFUV0cEEIMZYXX6N6z4u691LvsiI"));
                break;
            case "BCA 206":
                mSubject.mSubjectName = "Java Programming";
                mSubject.mNotes_url = "http://bit.ly/2Vp4swP";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";

                mSubject.mYoutube_url.add(new YoutubeClass("Java Tutorial For Beginners (Step by Step tutorial)", "https://www.youtube.com/playlist?list=PLS1QulWo1RIbfTjQvTdj8Y6yyq4R7g-Al"));
                mSubject.mYoutube_url.add(new YoutubeClass("Exception Handling in Java", "https://www.youtube.com/playlist?list=PLsyeobzWxl7rS9B2K1l--VDpCn41gijnV"));
                mSubject.mYoutube_url.add(new YoutubeClass("MultiThreading in Java with Lambda Expression", "https://www.youtube.com/playlist?list=PLsyeobzWxl7rmuFYRpkqLanwoG4pQQ7oW"));
                mSubject.mYoutube_url.add(new YoutubeClass("Library Management System in Java(Netbeans) Complete Project (Step by Step )", "https://www.youtube.com/playlist?list=PL_Ke9hJMFeR_TWfBOug40-2uSANxa8dtE"));
                mSubject.mYoutube_url.add(new YoutubeClass("Java Applet Programming (Hindi)", "https://www.youtube.com/playlist?list=PLbGui_ZYuhignGrO7DPr3kpZZ91yw4IEk"));
                mSubject.mYoutube_url.add(new YoutubeClass("Java Swing GUI", "https://www.youtube.com/playlist?list=PLsyeobzWxl7pVZdyDXj0arOdTzo4MYekh"));
                mSubject.mYoutube_url.add(new YoutubeClass("Network Fundamentals", "https://www.youtube.com/playlist?list=PLDQaRcbiSnqF5U8ffMgZzS7fq1rHUI3Q8"));
                mSubject.mYoutube_url.add(new YoutubeClass("Java Database Connectivity (JDBC) by Navin Reddy", "https://www.youtube.com/playlist?list=PLsyeobzWxl7rU7Jz3zDRpqB-EODzBbHOI"));
                mSubject.mYoutube_url.add(new YoutubeClass("Servlet and JSP Tutorial for Beginners 2018", "https://www.youtube.com/playlist?list=PLsyeobzWxl7pUPF2xjjJiG4BKC9x_GY46"));
                break;
            case "BCA 208":
                mSubject.mSubjectName = "Software Engineering";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";

                mSubject.mYoutube_url.add(new YoutubeClass("SE", "https://www.youtube.com/playlist?list=PL3oWeJK3GDfyHhD9JOBe-C3UO4o0OLFsk"));
                break;
            case "BCA 210":
                mSubject.mSubjectName = "Computer Networks";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "http://bit.ly/2TLs1gS";
                mSubject.mPaper_analysis_url = "";
                mSubject.mNotesList_url.add(new NotesClass("Unit 1", "http://bit.ly/381S886"));
                mSubject.mNotesList_url.add(new NotesClass("Unit 2", "http://bit.ly/2IicIGW"));

                mSubject.mYoutube_url.add(new YoutubeClass("CN", "https://www.youtube.com/playlist?list=PLxCzCOWd7aiGFBD2-2joCpWOLUrDLvVV_"));
                break;

            //Semester 5
            case "BCA 301":
                mSubject.mSubjectName = "Operating System";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";

                mSubject.mYoutube_url.add(new YoutubeClass("OS", "https://www.youtube.com/playlist?list=PLxCzCOWd7aiGz9donHRrE9I3Mwn6XdP8p"));
                break;
            case "BCA 303":
                mSubject.mSubjectName = "Computer Graphics";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";

                mSubject.mYoutube_url.add(new YoutubeClass("1. Computer Graphics (CG)", "https://www.youtube.com/playlist?list=PLrjkTql3jnm9cY0ijEyr2fPdwnH-0t8EY"));
                mSubject.mYoutube_url.add(new YoutubeClass("2. Computer Graphics", "https://www.youtube.com/playlist?list=PLWPirh4EWFpHukXICQrDcmjZUa2WlLMAb"));
                break;
            case "BCA 305":
                mSubject.mSubjectName = "E- Commerce";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";

                mSubject.mYoutube_url.add(new YoutubeClass("1. E-Commerce Course", "https://www.youtube.com/playlist?list=PL3N0yGp9djBkGcJIn6fbGRThUSmCnz5Qx"));
                mSubject.mYoutube_url.add(new YoutubeClass("2. E-Commerce and M-Commerce Lectures", "https://www.youtube.com/playlist?list=PLV8vIYTIdSnbh5dOzzOGYABSZLdguEbUG"));
                mSubject.mYoutube_url.add(new YoutubeClass("3. Complete E-Commerce Course with Java and MySQL", "https://www.youtube.com/playlist?list=PL7saoWAQ-9B5T8HmKTl4fRNPaEhdqOmgf"));
                mSubject.mYoutube_url.add(new YoutubeClass("4. E- Commerce Tutorial 2019", "https://www.youtube.com/playlist?list=PLbWX42QoZL5vhNjqqfyzLgbvUezv72ocC"));
                mSubject.mYoutube_url.add(new YoutubeClass("5. ELECTRONIC PAYMENT SYSTEM", "https://www.youtube.com/playlist?list=PLb8zN-8LXfQAkHpezdSvGXFyxiRD5fRPE"));
                mSubject.mYoutube_url.add(new YoutubeClass("6. E-commerce", "https://www.youtube.com/playlist?list=PLhf9MNcJXxbc8ZDS6qcX_pavRi9vxeCv4"));
                mSubject.mYoutube_url.add(new YoutubeClass("7. Business Process Management Course", "https://www.youtube.com/playlist?list=PL2LDY1TRcen1SCPmL1BY_yA1DijztcmIh"));
                mSubject.mYoutube_url.add(new YoutubeClass("8. Trends in Technology and E-Commerce in India: Legal and Tax Issues", "https://www.youtube.com/playlist?list=PLPhnxNKBMAoiKju0PQaOmzGo1uWVYnqxj"));
                mSubject.mYoutube_url.add(new YoutubeClass("9. DIGITAL MARKETING - CAMPAIGNS CASE STUDIES", "https://www.youtube.com/playlist?list=PL6mNfaiUSHTdIGAMXsaMYCZEWmHeHhyKM"));

                break;
            case "BCA 307":
                mSubject.mSubjectName = "Software Testing";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";

                mSubject.mYoutube_url.add(new YoutubeClass("Software Testing Training Videos | Edureka", "https://www.youtube.com/playlist?list=PL9ooVrP1hQOHYamtWSSlt5jF52WvP0cHl"));
                break;
            case "BCA 309":
                mSubject.mSubjectName = "Microprocessor";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";

                mSubject.mYoutube_url.add(new YoutubeClass("1. Microprocessor 8085", "https://www.youtube.com/playlist?list=PLWPirh4EWFpFDi8bggPYOiMLlD1D_bBPM"));
                mSubject.mYoutube_url.add(new YoutubeClass("2. MICROPROCESSOR GATE 2020 (EE/ECE/IN)", "https://www.youtube.com/playlist?list=PL1XaeVNXKsvylDOYoZzxiWPd5CNvc3RYQ"));
                break;
            case "BCA 311":
                mSubject.mSubjectName = "Advance Computer Networks";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";

                mSubject.mYoutube_url.add(new YoutubeClass("1. ADVANCED COMPUTER NETWORK", "https://www.youtube.com/playlist?list=PLG9aCp4uE-s3Mmbn4q5J87OriIN3CuFDS"));
                mSubject.mYoutube_url.add(new YoutubeClass("2. Advanced Computer Networks", "https://www.youtube.com/playlist?list=PL3EA3BED31F20DE92"));
                mSubject.mYoutube_url.add(new YoutubeClass("3. Full Series | 200-301 CCNA | Networking Inc.", "https://www.youtube.com/playlist?list=PLh94XVT4dq02frQRRZBHzvj2hwuhzSByN"));
                mSubject.mYoutube_url.add(new YoutubeClass("4. NETWORK SECURITY / INFORMATION SECURITY", "https://www.youtube.com/playlist?list=PLLOxZwkBK52Ch0y2lLtfepy4Lt_SVkwo3"));
                mSubject.mYoutube_url.add(new YoutubeClass("5. Cryptography and network security", "https://www.youtube.com/playlist?list=PL9FuOtXibFjV77w2eyil4Xzp8eooqsPp8"));
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

                mSubject.mYoutube_url.add(new YoutubeClass("1. UGC - Bcom, bba, ba, bca, honours ( Business economics)", "https://www.youtube.com/playlist?list=PLgC10_Xv-BGirAqOr-hU8e-N_Nz0UpgJ-"));
                mSubject.mYoutube_url.add(new YoutubeClass("2. CA Foundation - Business Economics", "https://www.youtube.com/playlist?list=PLBiVUADzZpd_hh5lFerym1qSQ9TQfNYuW"));
                break;

            //Semester 6
            case "BCA 302":
                mSubject.mSubjectName = "Data Ware Housing & Data Mining";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";

                mSubject.mYoutube_url.add(new YoutubeClass("1. Data Warehousing and Data Mining in Hindi", "https://www.youtube.com/playlist?list=PL-JvKqQx2AteuF5RnKVD0ZuW1_Tuk8H60"));
                mSubject.mYoutube_url.add(new YoutubeClass("2. Datawarehouse and Data Mining Lectures in Hindi", "https://www.youtube.com/playlist?list=PLV8vIYTIdSnb4H0JvSTt3PyCNFGGlO78u"));
                mSubject.mYoutube_url.add(new YoutubeClass("3. Data Mining and Warehouse", "https://www.youtube.com/playlist?list=PLYwpaL_SFmcChP0xiW3KK9elNuhfCLVVi"));
                mSubject.mYoutube_url.add(new YoutubeClass("4. Data warehouse and data mining", "https://www.youtube.com/playlist?list=PL0s3O6GgLL5fuVR545mzIuCkgGLi02fkN"));
                break;
            case "BCA 304":
                mSubject.mSubjectName = "Mobile Computing";
                mSubject.mNotes_url = "";
                mSubject.mAkash_url = "";
                mSubject.mBook_url = "";
                mSubject.mPaper_analysis_url = "";

                mSubject.mYoutube_url.add(new YoutubeClass("1. Mobile Computing (MC)", "https://www.youtube.com/playlist?list=PLrjkTql3jnm-kLRBgIt8kvuwbTScoI2IJ"));
                mSubject.mYoutube_url.add(new YoutubeClass("2. Mobile Communication", "https://www.youtube.com/playlist?list=PLYwpaL_SFmcAjqrKO-b9UMa2AaAlzZY7D"));
                mSubject.mYoutube_url.add(new YoutubeClass("3. Mobile Computing IPU", "https://www.youtube.com/playlist?list=PLPEhGb71u9EhlpzSedPmCBIocOTflCD5A"));
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

    public static String getSyllabusUrl(String BCACode)
    {
        String url = "";
        switch (BCACode)
        {
            case "semester 1":
                url = "https://drive.google.com/file/d/1NHEBDX6abXhIr_LQ9_7bxFPUZaANIRQV/view";
                break;
            case "semester 2":
                url = "https://drive.google.com/file/d/1z_806K8prZ1XkiHzHJDN1h78YwIflwHt/view";
                break;
            case "semester 3":
                url = "https://drive.google.com/file/d/18Ztc2JiHaPN08dQOLFaCEjebiKSLEorw/view";
                break;
            case "semester 4":
                url = "https://drive.google.com/file/d/13z2ldJHVl6OgvuBfNmJ4qEUcJaYVuLzF/view";
                break;
            case "semester 5":
                url = "https://drive.google.com/file/d/1eTM8sC2TeZkhZbpgdGfqUtVokICH3yeA/view";
                break;
            case "semester 6":
                url = "https://drive.google.com/file/d/1ncmOwCpqmfWrRL2UTg_C5gN7cWB2HJOv/view";
                break;
        }
        return url;
    }

    public static void updateWebViewDefaults(WebView webView)
    {

        WebSettings settings = webView.getSettings();
        // Enable Javascript
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        // Configure the client to use when opening URLs

        // Use WideViewport and Zoom out if there is no viewport defined
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        // Enable pinch to zoom without the zoom buttons
        settings.setBuiltInZoomControls(true);

        settings.setDisplayZoomControls(false);

    }

    public static void injectJavascript(WebView web, String style)
    {
        try
        {
            style = style.replace("\r", "");
            style = "javascript:function addStyleString(str) { var node = document.createElement('style'); node.innerHTML = " +
                    "str; document.body.appendChild(node); } addStyleString('" + style + "');";

            web.loadUrl(style);
        } catch (Exception e)
        {

        }
    }

    public static String getCustomHtml(String src)
    {
        String _html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "<iframe class=\"ytframe\" width=\"100%\" height=\"600\" src=\"https://www.youtube.com/embed/" +
                src +
                "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>\n" +
                "</body>\n" +
                "</html>";
        return _html;
    }

    public static String getCSS()
    {
        String css = ".mobile-topbar-header, ytm-pivot-bar-renderer, .compact-media-item-menu, .playlist-header-actions {display: none;} " +
                "ytm-app.sticky-player {padding-top: 0;}";
        return css;
    }
}
