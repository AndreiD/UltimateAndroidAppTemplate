package com.ultimatetemplateandroidapp.app;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class DUtils extends Activity {

	public static final String TAG = "*** UTILS ***";

	// ----------- EXAMPLES ------------
	/*
	 * YES NO DIALOG
	 * 
	 * DialogInterface.OnClickListener dialogClickListener = new
	 * DialogInterface.OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) { switch
	 * (which){ case DialogInterface.BUTTON_POSITIVE: //Yes button clicked
	 * break;
	 * 
	 * case DialogInterface.BUTTON_NEGATIVE: //No button clicked break; } } };
	 * 
	 * AlertDialog.Builder builder = new AlertDialog.Builder(this);
	 * builder.setMessage("Are you sure?").setPositiveButton("Yes",
	 * dialogClickListener) .setNegativeButton("No",
	 * dialogClickListener).show();
	 */

	public static boolean isAppInstalled(Context ctx,String uri) {
		PackageManager pm = ctx.getPackageManager();
		boolean installed = false;
		try {
			pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
			installed = true;
		} catch (PackageManager.NameNotFoundException e) {
			installed = false;
		}
		return installed;
	}
	
	
	
	

	public static String getMyPhoneNumber(Context ctx) {

		try {
			TelephonyManager mTelephonyMgr;
			mTelephonyMgr = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
			return mTelephonyMgr.getLine1Number();
		} catch (Exception e) {
			return "";
		}

	}

	public static String getMy10DigitPhoneNumber(Context ctx) {
		try {
			String s = getMyPhoneNumber(ctx);
			return s.substring(2);
		} catch (Exception e) {
			return "";
		}

	}

	// ------- GET USER EMAIL ADDRESS

	public static class UserEmailFetcher {

		public static String getEmail(Context context) {
			AccountManager accountManager = AccountManager.get(context);
			Account account = getAccount(accountManager);

			if (account == null) {
				return null;
			} else {
				return account.name;
			}
		}

		private static Account getAccount(AccountManager accountManager) {
			Account[] accounts = accountManager.getAccountsByType("com.google");
			Account account;
			if (accounts.length > 0) {
				account = accounts[0];
			} else {
				account = null;
			}
			return account;
		}
	}

	@TargetApi(9)
	public static String getMonth(int month, Locale locale) {
		return DateFormatSymbols.getInstance(locale).getMonths()[month - 1];
	}

	public static void checkExternalMedia() {

		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// Can read and write the media
			mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			// Can only read the media
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		} else {
			// Can't read or write
			mExternalStorageAvailable = mExternalStorageWriteable = false;
		}

	}

	public static final String md5(final String s) {
		try {
			// Create MD5 Hash
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String h = Integer.toHexString(0xFF & messageDigest[i]);
				while (h.length() < 2)
					h = "0" + h;
				hexString.append(h);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String get_device_id(Context ctx) {

		final TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);

		final String tmDevice, tmSerial, tmPhone, androidId;

		try {
			tmDevice = "" + tm.getDeviceId();
			tmSerial = "" + tm.getSimSerialNumber();
			androidId = "" + android.provider.Settings.Secure.getString(ctx.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
			UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
			String deviceId = deviceUuid.toString();

			return deviceId;
		} catch (Exception ex) {
			return "nodeviceid";
		}
	}

	/**
	 * @param directory
	 *            example: util_dir
	 * @param file_name
	 *            example: log.txt (include extension!)
	 * @param text
	 *            - text to write
	 * @return true/false Method to write ascii text characters to file on SD
	 *         card. Note that you must add a WRITE_EXTERNAL_STORAGE permission
	 *         to the manifest file or this method will throw a FileNotFound
	 *         Exception because you won't have write permission.
	 */

	public static Boolean writeToSDFile(String directory, String file_name, String text) {

		// Find the root of the external storage.
		// See
		// http://developer.android.com/guide/topics/data/data-storage.html#filesExternal

		File root = Environment.getExternalStorageDirectory();

		// See
		// http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder

		File dir = new File(root.getAbsolutePath() + "/" + directory);
		dir.mkdirs();
		File file = new File(dir, file_name);

		try {
			FileOutputStream f = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(f);
			pw.println(text);
			pw.flush();
			pw.close();
			f.close();
			// Log.v(TAG, "file written to sd card");
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// Log.i(TAG, "******* File not found. Did you" +
			// " add a WRITE_EXTERNAL_STORAGE permission to the manifest?");
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Method to read in a text file placed in the res/raw directory of the
	 * application. The method reads in all lines of the file sequentially.
	 */

	public static void readRaw(Context ctx, int res_id) {

		InputStream is = ctx.getResources().openRawResource(res_id);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr, 8192); // 2nd arg is buffer
															// size

		// More efficient (less readable) implementation of above is the
		// composite expression
		/*
		 * BufferedReader br = new BufferedReader(new InputStreamReader(
		 * this.getResources().openRawResource(R.raw.textfile)), 8192);
		 */

		try {
			String test;
			while (true) {
				test = br.readLine();
				// readLine() returns null if no more lines in the file
				if (test == null)
					break;
			}
			isr.close();
			is.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Read a file from assets
	 * 
	 * @return the string from assets
	 */

	public static String getQuestions(Context ctx, String file_name) {

		AssetManager assetManager = ctx.getAssets();
		ByteArrayOutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			inputStream = assetManager.open(file_name);
			outputStream = new ByteArrayOutputStream();
			byte buf[] = new byte[1024];
			int len;
			try {
				while ((len = inputStream.read(buf)) != -1) {
					outputStream.write(buf, 0, len);
				}
				outputStream.close();
				inputStream.close();
			} catch (IOException e) {
			}
		} catch (IOException e) {
		}
		return outputStream.toString();

	}

	/**
	 * 
	 * @author Admin usage new DownloadImageTask((ImageView)
	 *         findViewById(R.id.imageView1)) .execute(
	 *         "http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png"
	 *         );
	 */
	public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", "exception in downloading image");

			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			try {

				int picture_height = result.getHeight();
				int picture_width = result.getWidth();

				float scaleHeight = ((float) 480) / picture_height;

				Matrix matrix = new Matrix();

				matrix.postScale(scaleHeight, scaleHeight);

				// recreate the new Bitmap
				Bitmap resizedBitmap = Bitmap.createBitmap(result, 0, 0, picture_width, picture_height, matrix, true);

				bmImage.setImageBitmap(resizedBitmap);

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	/**
	 * Checks if there's an active internet connection
	 * 
	 */

	public static boolean isOnline(Context ctx) {

		ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(DUtils.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	/**
	 * Get the source string from an URL
	 * 
	 * @author Dan
	 * 
	 */

	public static class Get_Webpage {

		public static String parsing_url = "";

		public Get_Webpage(String url_2_get) {

			parsing_url = url_2_get;
		}

		public static String get_webpage_source() {

			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(parsing_url);
			// request.setHeader("Content-Type",
			// "text/plain; charset=ISO-8859-1");
			HttpResponse response = null;
			try {
				response = client.execute(request);
			} catch (ClientProtocolException e) {

			} catch (IOException e) {

			}

			String html = "";

			try {
				html = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return html;
		}

	}

	/**
	 * Get the source string from an URL
	 * 
	 * @author Dan
	 * 
	 */

	public static class Get_Webpage2 {

		public static String parsing_url = "";

		public Get_Webpage2(String url_2_get) {

			parsing_url = url_2_get;
		}

		public static String get_webpage_source() {

			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(parsing_url);
			// request.setHeader("Content-Type",
			// "text/plain; charset=ISO-8859-1");
			HttpResponse response = null;
			try {
				response = client.execute(request);
			} catch (ClientProtocolException e) {

			} catch (IOException e) {

			}

			String html = "";

			try {
				html = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return html;
		}

	}

	/*************************************************************************************************
	 * Returns size in MegaBytes.
	 * 
	 * @author Dan
	 * 
	 *         If you need calculate external memory, change this: StatFs statFs
	 *         = new StatFs(Environment.getRootDirectory().getAbsolutePath());
	 *         to this: StatFs statFs = new
	 *         StatFs(Environment.getExternalStorageDirectory
	 *         ().getAbsolutePath());
	 **************************************************************************************************/
	public static int TotalMemory() {
		StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
		int Total = (statFs.getBlockCount() * statFs.getBlockSize()) / 1048576;
		return Total;
	}

	public static int FreeMemory() {
		StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
		int Free = (statFs.getAvailableBlocks() * statFs.getBlockSize()) / 1048576;
		return Free;
	}

	public static int BusyMemory() {
		StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
		int Total = (statFs.getBlockCount() * statFs.getBlockSize()) / 1048576;
		int Free = (statFs.getAvailableBlocks() * statFs.getBlockSize()) / 1048576;
		int Busy = Total - Free;
		return Busy;
	}

	/*****************************************************************************************
	 * SETS THE ENTIRE FONT FOR THE ACTIVITY!
	 * 
	 * mContext = AddSpellActivity.this;
	 * 
	 * Typeface tf1 = Typeface.createFromAsset(mContext.getAssets(),
	 * "fonts/KaushanScript-Regular.otf"); final ViewGroup mContainer =
	 * (ViewGroup) findViewById(android.R.id.content).getRootView();
	 * setAppFont(mContainer, tf2);
	 * 
	 * @author Dan
	 * @param mContainer
	 * @param mFont
	 */

	public static final void setAppFont(ViewGroup mContainer, Typeface mFont) {
		if (mContainer == null || mFont == null)
			return;

		final int mCount = mContainer.getChildCount();

		// Loop through all of the children.
		for (int i = 0; i < mCount; ++i) {
			final View mChild = mContainer.getChildAt(i);
			if (mChild instanceof TextView) {
				// Set the font if it is a TextView.
				((TextView) mChild).setTypeface(mFont);
			} else if (mChild instanceof ViewGroup) {
				// Recursively attempt another ViewGroup.
				setAppFont((ViewGroup) mChild, mFont);
			}
		}
	}

	public static Bitmap decodeUri(Context ctx, Uri selectedImage) throws FileNotFoundException {

		// Decode image size
		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(selectedImage), null, o);

		// The new size we want to scale to
		final int REQUIRED_SIZE = 240;

		// Find the correct scale value. It should be the power of 2.
		int width_tmp = o.outWidth, height_tmp = o.outHeight;
		int scale = 1;
		while (true) {
			if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
				break;
			}
			width_tmp /= 2;
			height_tmp /= 2;
			scale *= 2;
		}

		// Decode with inSampleSize
		BitmapFactory.Options o2 = new BitmapFactory.Options();
		o2.inSampleSize = scale;
		return BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(selectedImage), null, o2);

	}

	public static File createTemporaryFile(String part, String ext) throws Exception {
		File tempDir = Environment.getExternalStorageDirectory();
		tempDir = new File(tempDir.getAbsolutePath() + "/.temp/");
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		return File.createTempFile(part, ext, tempDir);
	}

}
