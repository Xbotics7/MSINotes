package com.example.msinotes.Models;

import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.msinotes.NotesClass;
import com.example.msinotes.R;
import com.example.msinotes.SubjectsClass;
import com.example.msinotes.YoutubeClass;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class FirebaseHelper
{
    public static SubjectsClass getSubInfo(String subCode)
    {

        SubjectsClass mSubject = new SubjectsClass();
        mSubject.mSubjectCode = subCode;
        mSubject.mNotesList_url = new ArrayList<NotesClass>();
        mSubject.mYoutube_url = new ArrayList<YoutubeClass>();

        try
        {
            DataSnapshot mDataSnap = UtilityData.fireBaseDataSnapShot.child(subCode);
            DataSnapshot mYTDataSnap = mDataSnap.child("Youtube");
            if (!mYTDataSnap.exists())
                mYTDataSnap = mDataSnap.child("YOUTUBE");
            DataSnapshot mNotesDataSnap = mDataSnap.child("Notes");
            mSubject.mSubjectName = mDataSnap.child("SubjectName").getValue(String.class);
            mSubject.mNotes_url = "";
            mSubject.mAkash_url = mDataSnap.child("Akash_url").getValue(String.class);
            mSubject.mBook_url = mDataSnap.child("Book_url").getValue(String.class);
            mSubject.mPaper_analysis_url = mDataSnap.child("Paper_analysis_url").getValue(String.class);

            for (DataSnapshot postSnapshot : mNotesDataSnap.getChildren())
            {
                String mNotesTitle = postSnapshot.getKey();
                String mNotesLink = postSnapshot.getValue(String.class);
                if (!mNotesLink.isEmpty())
                {
                    if (mNotesTitle.equals("notes1"))
                        mNotesTitle = "Notes";
                    mSubject.mNotesList_url.add(new NotesClass(mNotesTitle, mNotesLink));
                }
            }

            for (DataSnapshot postSnapshot : mYTDataSnap.getChildren())
            {
                String mYtTitle = postSnapshot.getKey();
                String mYtLink = postSnapshot.getValue(String.class);
                if (!mYtLink.isEmpty())
                    mSubject.mYoutube_url.add(new YoutubeClass(mYtTitle, mYtLink));
            }
        } catch (Exception ex)
        {
            mSubject = UtilityClass.getSubInfo(subCode);
        }
        return mSubject;

    }

    public static void getAllSubjectsList(ArrayList<SubjectsClass> mSubject)
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

                break;
            case "semester 2":
                mSubject.add(getSubInfo("BCA 102"));
                mSubject.add(getSubInfo("BCA 104"));
                mSubject.add(getSubInfo("BCA 106"));
                mSubject.add(getSubInfo("BCA 108"));
                mSubject.add(getSubInfo("BCA 110"));
                break;


            case "semester 3":
                mSubject.add(getSubInfo("BCA 201"));
                mSubject.add(getSubInfo("BCA 203"));
                mSubject.add(getSubInfo("BCA 205"));
                mSubject.add(getSubInfo("BCA 207"));
                mSubject.add(getSubInfo("BCA 209"));
                break;
            case "semester 4":
                mSubject.add(getSubInfo("BCA 202"));
                mSubject.add(getSubInfo("BCA 204"));
                mSubject.add(getSubInfo("BCA 206"));
                mSubject.add(getSubInfo("BCA 208"));
                mSubject.add(getSubInfo("BCA 210"));
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
            case "semester 6":
                mSubject.add(getSubInfo("BCA 302"));
                mSubject.add(getSubInfo("BCA 304"));
                mSubject.add(getSubInfo("BCA 306"));
                mSubject.add(getSubInfo("BCA 308"));
                mSubject.add(getSubInfo("BCA 310"));
                mSubject.add(getSubInfo("BCA 312"));
                mSubject.add(getSubInfo("BCA 314"));
                mSubject.add(getSubInfo("BCA 316"));

                break;

        }
    }
}
