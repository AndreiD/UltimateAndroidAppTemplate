package com.andrei.template.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import com.andrei.template.R;

public final class DialogFactory {

  public static Dialog createSimpleOkDialog(Context context, String title, String message) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).setNeutralButton(R.string.dialog_action_ok, null);
    return alertDialog.create();
  }

  public static Dialog createGenericErrorDialog(Context context, String message) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogErrorStyle).setTitle(context.getString(R.string.generic_error_title)).setMessage(message).setNeutralButton(R.string.dialog_action_ok, null);
    return alertDialog.create();
  }

  public static ProgressDialog createProgressDialog(Context context, String message) {
    ProgressDialog progressDialog = new ProgressDialog(context);
    progressDialog.setMessage(message);
    return progressDialog;
  }

  public static ProgressDialog createProgressDialog(Context context, @StringRes int messageResource) {
    return createProgressDialog(context, context.getString(messageResource));
  }

  public static Snackbar showErrorSnackBar(Activity mContext, View rootView, Throwable throwable) {
    String message = mContext.getString(R.string.dialog_general_error_message);
    if (throwable != null) {
      message = throwable.getLocalizedMessage();

    }
    Snackbar snack_error = Snackbar.make(rootView, throwable.getLocalizedMessage(), Snackbar.LENGTH_LONG);
    View view = snack_error.getView();
    TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
    tv.setTextColor(ContextCompat.getColor(mContext, R.color.material_red));
    return snack_error;
  }
}
