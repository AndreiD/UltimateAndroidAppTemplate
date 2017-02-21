package com.andrei.template.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import com.andrei.template.R;

public final class DialogFactory {

  public static Dialog createSimpleOkDialog(Context context, String title, String message) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context).setTitle(title)
        .setMessage(message)
        .setNeutralButton(R.string.dialog_action_ok, null);
    return alertDialog.create();
  }

  public static Dialog createGenericErrorDialog(Context context, String message) {
    AlertDialog.Builder alertDialog =
        new AlertDialog.Builder(context, R.style.AppCompatAlertDialogErrorStyle).setTitle(
            context.getString(R.string.generic_error_title))
            .setMessage(message)
            .setNeutralButton(R.string.dialog_action_ok, null);
    return alertDialog.create();
  }

  public static ProgressDialog createProgressDialog(Context context, String message) {
    ProgressDialog progressDialog = new ProgressDialog(context);
    progressDialog.setMessage(message);
    return progressDialog;
  }

  public static ProgressDialog createProgressDialog(Context context,
      @StringRes int messageResource) {
    return createProgressDialog(context, context.getString(messageResource));
  }

}
