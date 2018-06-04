package com.mobile.app.maxmoney.Common;
import android.app.ProgressDialog;
import android.content.Context;
public class StandardProgressDialog extends ProgressDialog {

    public StandardProgressDialog(Context context) {
        super(context);

        setMessage("Loading");
        setProgressStyle(ProgressDialog.STYLE_SPINNER);
        setCancelable(true);

    }
}
