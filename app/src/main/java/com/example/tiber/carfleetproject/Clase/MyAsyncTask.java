package com.example.tiber.carfleetproject.Clase;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by tiber on 4/19/2016.
 * Clients of this class must treat the response data in onPostExecute
 * if null - problem with server
 */
public abstract class MyAsyncTask extends AsyncTask<Void,Void,Void> {
    protected Context context;
    ProgressDialog mDialog;
    public MyAsyncTask(Context context) {
        super();
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDialog  = new ProgressDialog(context);
        mDialog.setMessage("Contacting server...");
        mDialog.setCancelable(false);
        mDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mDialog.dismiss();
    }
}
