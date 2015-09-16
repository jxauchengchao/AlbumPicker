package com.csdn.icoder.albumpicker;

/**
 * Created by icoder on 15/9/15.
 */
public class Album {
    String uri;
    boolean checked;
    int type;
    public Album() {
    }
    public Album(int type) {
        this.type = type;
    }

    public Album(String uri, boolean checked, int type) {
        this.uri = uri;
        this.checked = checked;
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
