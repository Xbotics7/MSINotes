package com.example.msinotes;

public class YoutubeClass
{
    public String yt_name;
    public String yt_playlist_url;

    public YoutubeClass(String yt_name, String yt_playlist_url)
    {
        this.yt_name = yt_name;
        this.yt_playlist_url = yt_playlist_url;
    }

    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object instanceof YoutubeClass)
        {
            sameSame = this.yt_playlist_url.equals(((YoutubeClass) object).yt_playlist_url);
        }

        return sameSame;
    }
}
