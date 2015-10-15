package in.jigyasacodes.totask.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Net {

	static public boolean haveNetworkConnection(Context ctx) {

		boolean isWifiConn = false;
		boolean isMobileConn = false;

		ConnectivityManager cm = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo[] netInfo = cm.getAllNetworkInfo();

		for (NetworkInfo ni : netInfo) {

			if (ni.getTypeName().equalsIgnoreCase("WIFI")) {
				
				if (ni.isConnected()) {
					
					isWifiConn = true;
				}
			}
			if (ni.getTypeName().equalsIgnoreCase("MOBILE")) {
				
				if (ni.isConnected()) {
					
					isMobileConn = true;
				}
			}
		}
		return isWifiConn || isMobileConn;
	}
}