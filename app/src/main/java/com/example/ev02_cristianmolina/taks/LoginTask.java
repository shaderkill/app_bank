package com.example.ev02_cristianmolina.taks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.ev02_cristianmolina.models.User;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The type Image load task.
 */
public class LoginTask extends AsyncTask<Void, Void, Boolean> {

    private User user;

    /**
     * Instantiates a new Image load task.
     *
     * @param user       the user
     */
    public LoginTask(User user) {
        this.user = user;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        boolean isValid = false;
        try {
            if(user.getUser().toLowerCase().equals("android") && user.getPassword().toLowerCase().equals("123")) {
                isValid = true;
            }
            return isValid;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
    }

}