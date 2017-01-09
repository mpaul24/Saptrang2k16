package delhi.android.nit.com.saptrang2k16;

import android.graphics.Bitmap;

/**
 * Created by Manojit Paul on 11/5/2016.
 */
public class Images {

    private String thumnailLink;
    private String fullImageLink;

    public Images(String thumnailLink, String fullImageLink) {
        this.thumnailLink = thumnailLink;
        this.fullImageLink = fullImageLink;
    }

    public String getFullImageLink() {
        return fullImageLink;
    }

    public void setFullImageLink(String fullImageLink) {
        this.fullImageLink = fullImageLink;
    }

    public String getThumnailLink() {
        return thumnailLink;
    }

    public void setThumnailLink(String thumnailLink) {
        this.thumnailLink = thumnailLink;
    }
}