package in.jigyasacodes.totask.bg;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import in.jigyasacodes.totask.data.redifined.Meta;
import in.jigyasacodes.totask.util.JSONUtils;

public class CoursesFetcherTaskLevel1 extends AsyncTask<String, Void, Meta> {

	private ProgressDialog progressDialog = null;

	private Context ctx = null;
	private OnCoursesFetcherTaskLevel1hCompleteListener onCoursesFetcherTaskLevel1hComplete;

	private final String PB_MSG_FETCHING_COURSES = "Fetching Courses for you from the COURSERA Server..";
	private final String PB_MSG_FETCHING_UNIVERSITIES = "Fetching respective Universities from the COURSERA Server for you..";
	private final String PB_MSG_FETCHING_INSTRUCTORS = "Fetching respective Instructors from the COURSERA Server for you..";

	private int intPaginationValue = 1;

	public CoursesFetcherTaskLevel1(
			OnCoursesFetcherTaskLevel1hCompleteListener thiss, Context ctx) {

		onCoursesFetcherTaskLevel1hComplete = thiss;
		this.ctx = ctx;
	}

	public CoursesFetcherTaskLevel1(
			OnCoursesFetcherTaskLevel1hCompleteListener thiss, Context ctx,
			final int INT_PAGINATION_VALUE) {

		onCoursesFetcherTaskLevel1hComplete = thiss;
		this.ctx = ctx;
		this.intPaginationValue = INT_PAGINATION_VALUE;
	}

	@Override
	protected void onPreExecute() {

		super.onPreExecute();

		progressDialog = new ProgressDialog(this.ctx);
		progressDialog.setMessage("Rukiye..");
		progressDialog.setTitle(PB_MSG_FETCHING_COURSES);
		progressDialog.setIndeterminate(true);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setCancelable(false);
		progressDialog.show();

		//onProgressUpdate();
	}

	@Override
	protected Meta doInBackground(final String... URLS) {

		// //Looper.prepare();

		// This fkn line wasted my 2.5 hrs :O :'( !!
		// onRESTCompleteListener = new MainActivity();

		/**
		 * :O Causes ->
		 * 
		 * java.lang.RuntimeException: Can't create handler inside thread that
		 * has not called Looper.prepare()
		 * 
		 **/
		// onProgressUpdate();

		final JSONObject JSON_OBJ_MAIN = fetchJSON(URLS[0]);

		if (!JSON_OBJ_MAIN.equals(null)) {

			Log.e("doInBackground() - if()",
					"RAW RESPONSE data Successfully Parsed to JSON ------------\n"
							+ JSON_OBJ_MAIN.toString() + "\n------------");

			return feedMetaPOJO(JSON_OBJ_MAIN);
		}

		return null;
	}

	private Meta feedMetaPOJO(final JSONObject JSON_OBJ_MAIN) {

		JSONUtils jsonUtils = new JSONUtils(JSON_OBJ_MAIN);

		return jsonUtils.processAndStoreRawJSONData();
	}

	private boolean isSCourseraResponseSuccessful(
			final JSONObject jsonObjectResponse) throws JSONException {

		// if (jsonObjectResponse.getInt(Consts.C_RESPONSE_CODE_cod_URL) ==
		// Consts.C_RESPONSE_CODE_404_ERROR) {

		// return false;
		// }

		return true;
	}

	private JSONObject fetchJSON(final String STR_URL) {

		// USING FOLLOWING CODE CONCEPT, SAVES MEMORY & TIME - RSD

		// InputStream isResponse = null;
		String strResp = null;
		// defaultHttpClient
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();

			// HttpPost httpPost = new HttpPost(URL);
			HttpGet httpget = new HttpGet(STR_URL);

			HttpResponse httpResponse = httpClient.execute(httpget);

			strResp = EntityUtils.toString(httpResponse.getEntity()).trim();

		} catch (UnsupportedEncodingException e) {

			Log.e(getClass().getSimpleName(), e.toString());

		} catch (ClientProtocolException e) {

			Log.e(getClass().getSimpleName(), e.toString());

		} catch (IOException e) {

			Log.e(getClass().getSimpleName(), e.toString());
		}

		Log.e("JSON_RESPONSE ->", "----------------\n" + strResp
				+ "\n----------------");

		// Toast.makeText(this, strResp, Toast.LENGTH_LONG).show();

		return JSONUtils.verifyJSON(strResp);
		// //////params/////////////////
		// JSONObject jsonObj = null;

		// Parse the string to a JSON object

	}

	@Override
	protected void onPostExecute(final Meta META) {

		progressDialog.cancel();

		if (!META.equals(null)) {

			Log.e("fkkkkkkkkkk--", "onPostExecute() - if () ->  1");
			onCoursesFetcherTaskLevel1hComplete
					.OnCoursesFetcherTaskLevel1hComplete(true, META);

		} else {

			Log.e("fkkkkkkkkkk--", "onPostExecute() - else () ->  1");
			onCoursesFetcherTaskLevel1hComplete
					.OnCoursesFetcherTaskLevel1hComplete(false, null);
		}
	}

	public interface OnCoursesFetcherTaskLevel1hCompleteListener {

		void OnCoursesFetcherTaskLevel1hComplete(
				final boolean isSHResponseSuccessful, final Meta META);
	}
}