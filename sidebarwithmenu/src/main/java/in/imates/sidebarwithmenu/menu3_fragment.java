package in.imates.sidebarwithmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by root on 7/10/15.
 */
public class menu3_fragment extends android.support.v4.app.Fragment {
	View rootview;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootview = inflater.inflate(R.layout.menu3_layout,container,false);
		return rootview;
	}
}
