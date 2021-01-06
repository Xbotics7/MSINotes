package com.example.msinotes;

public class NotesClass
{
    public String mName;
    public String mNotes_url;

    public NotesClass(String mName, String mNotes_url)
    {
        this.mName = mName;
        this.mNotes_url = mNotes_url;
    }

    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object instanceof NotesClass)
        {
            sameSame = this.mNotes_url.equals( ((NotesClass) object).mNotes_url);
        }

        return sameSame;
    }
}
