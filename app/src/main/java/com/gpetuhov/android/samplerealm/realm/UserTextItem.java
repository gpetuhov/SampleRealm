package com.gpetuhov.android.samplerealm.realm;


import io.realm.RealmObject;

public class UserTextItem extends RealmObject {

    private String mText;

    public UserTextItem() {
    }

    public String getText() {
        if (mText != null) {
            return mText;
        } else {
            return "";
        }
    }

    public void setText(String text) {
        if (text != null) {
            mText = text;
        } else {
            mText = "No value";
        }
    }
}
