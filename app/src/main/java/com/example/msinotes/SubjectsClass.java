package com.example.msinotes;

import java.util.ArrayList;

public class SubjectsClass
{
    public String mSubjectName;
    public String mSubjectCode;
    public String mNotes_url;
    public String mAkash_url;
    public String mBook_url;
    public String mPaper_analysis_url;
    public String mSyllabus_url;
    public ArrayList<NotesClass> mNotesList_url;
    public ArrayList<YoutubeClass> mYoutube_url;

    public SubjectsClass()
    {
    }

    public SubjectsClass(String mSubjectName, String mSubjectCode)
    {

        this.mSubjectName = mSubjectName;
        this.mSubjectCode = mSubjectCode;
    }

    public SubjectsClass(String mSubjectName, String mSubjectCode, String mNotesUrl)
    {

        this.mSubjectName = mSubjectName;
        this.mSubjectCode = mSubjectCode;
        this.mNotes_url = mNotesUrl;
    }

    //public String getSubjectName() {
    //return mSubjectName;
    //}
    // public String getSubjectCode() {
    // return mSubjectCode;
    //}
}
