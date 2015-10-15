package in.jigyasacodes.totask.ui.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import in.jigyasacodes.totask.R;
import in.jigyasacodes.totask.data.Consts;
import in.jigyasacodes.totask.data.redifined.Meta;
import in.jigyasacodes.totask.ui.dialog.Dialogs;
import in.jigyasacodes.totask.ui.dialog.Dialogs.OnNetIssueADYesClickListener;
import in.jigyasacodes.totask.ui.frag.InfoFragment;
import in.jigyasacodes.totask.ui.frag.MainFragment2;
import in.jigyasacodes.totask.util.Net;

public class ParentActivity extends AppCompatActivity implements
		OnNetIssueADYesClickListener, MainFragment2.OnCourseItemClickListenerr {

	private Button btnFetchCourseraCatlog;

	private Fragment fragMain, fragInfo;
	private FrameLayout frameLayoutParent;

	private FragmentTransaction ft;

	private Dialogs dialogs;
	private MainFragment2 mainFragment2;
	private InfoFragment infoFragment;

	private ParentActivity parentActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_parent);

		parentActivity = new ParentActivity();

		btnFetchCourseraCatlog = (Button) findViewById(R.id.btnFetchCourseraCatlog);
		btnFetchCourseraCatlog.setVisibility(View.VISIBLE);

		this.initUI(this);

		//this.isNetConnected(this);
	}

	private boolean isNetConnected(Context ctx) {

		if (!Net.haveNetworkConnection(ctx)) {

			dialogs = new Dialogs(this);
			dialogs.showNetworkIssueAD(Consts.NET_ISSUE_AD_TITLE,
					Consts.NET_ISSUE_AD_MESSAGE);

			return false;
		}

		//  if (initUI(this)) {
		//      ;
		//  } else {
		//      Toast.makeText(this,
		//      "Failed to bind the UI elements..\n\nExiting..",
		//      Toast.LENGTH_LONG).show();
		//  }

		//progressBar = (ProgressBar) findViewById(R.id.progressBar);
		//progressBar.setVisibility(View.VISIBLE);

		return this.addMainFragment();
	}

	private boolean initUI(final Context ctx) {

		//btnFetchCourseraCatlog = (Button) findViewById(R.id.btnFetchCourseraCatlog);
		//btnFetchCourseraCatlog.setVisibility(View.VISIBLE);

		btnFetchCourseraCatlog.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				isNetConnected(ctx);
			}
		});

		//  progressBar = (ProgressBar) findViewById(R.id.progressBar);
		//  progressBar.setVisibility(View.VISIBLE);

		//  frameLayoutParent = (FrameLayout) findViewById(R.id.frameLayoutParent);

		return false;
	}

	private boolean addMainFragment() {

		btnFetchCourseraCatlog.setVisibility(View.GONE);
		//  btnFetchCourseraCatlog.setVisibility(View.GONE);
		//frameLayoutParent = (FrameLayout) findViewById(R.id.frameLayoutParent);
		//frameLayoutParent.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
		//ViewGroup.LayoutParams.MATCH_PARENT));
		//frameLayoutParent.setVisibility(View.VISIBLE);

		this.ft = getSupportFragmentManager().beginTransaction();

		// ft.replace(R.id.frameLayoutParent, new MainFragment();

		mainFragment2 = new MainFragment2();
		ft.add(R.id.frameLayoutParent, mainFragment2,
				"FRAG_TAG_MAINFRAG1");

		ft.commit();

		return true;
	}

	private boolean addInfoFragment(final Meta META, final int POSITION) {

		this.ft = getSupportFragmentManager().beginTransaction();

		// ft.replace(R.id.frameLayoutParent, new InfoFragment();
		ft.add(R.id.frameLayoutParent, bundleArgsInInfoArgument(META, POSITION));
		ft.addToBackStack("FRAG_TAG_MAINFRAG1");
		ft.commit();

		return true;
	}

	private InfoFragment bundleArgsInInfoArgument(final Meta META,
	                                              final int POSITION) {

		InfoFragment infoFragmentTemp = new InfoFragment();
		infoFragment = infoFragmentTemp;

		Bundle bundleArgs = new Bundle();
		bundleArgs.putString("course_" + Consts.COURSES.NAME, META
				.getElements().get(POSITION).getName().trim());
		bundleArgs.putString(Consts.COURSES.LARGE_ICON,
				META.getElements().get(POSITION).getLargeIcon().trim());
		bundleArgs.putString(Consts.INSTRUCTORS.FIRST_NAME, META.getLinked()
				.getInstructors().get(POSITION).getFirstName().trim());
		bundleArgs.putString(Consts.INSTRUCTORS.PHOTO_150, META.getLinked()
				.getInstructors().get(POSITION).getPhoto150().trim());
		bundleArgs.putString(Consts.COURSES.SHORT_DESCRIPTION, META
				.getElements().get(POSITION).getShortDescription().trim());
		bundleArgs.putString(Consts.INSTRUCTORS.DEPARTMENT, META.getLinked()
				.getInstructors().get(POSITION).getDepartment().trim());
		bundleArgs.putString("univ_" + Consts.UNIVERSITIES.NAME, META
				.getLinked().getUniversities().get(POSITION).getName().trim());

		Log.e("bAIIA() 1", bundleArgs.get("course_" + Consts.COURSES.NAME)
				.toString());
		Log.e("bAIIA() 2", bundleArgs.get(Consts.COURSES.LARGE_ICON).toString());
		Log.e("bAIIA() 3", bundleArgs.get(Consts.INSTRUCTORS.FIRST_NAME)
				.toString());
		Log.e("bAIIA() 4", bundleArgs.get(Consts.INSTRUCTORS.PHOTO_150)
				.toString());
		Log.e("bAIIA() 5", bundleArgs.get(Consts.COURSES.SHORT_DESCRIPTION)
				.toString());
		Log.e("bAIIA() 6", bundleArgs.get(Consts.INSTRUCTORS.DEPARTMENT)
				.toString());
		Log.e("bAIIA() 6", bundleArgs.get("univ_" + Consts.UNIVERSITIES.NAME)
				.toString());

		infoFragmentTemp.setArguments(bundleArgs);

		return infoFragmentTemp;
	}

	@Override
	public void onCourseItemClickk(View view, final Meta META, int POSITION) {

		if (META != null && POSITION > -1) {

			this.addInfoFragment(META, POSITION);

		} else {

			Toast.makeText(
					this,
					"Failed to display Course's Info page..\n\nPlease try again..",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onStart() {

		super.onStart();

		//this.ifNetConnected(this);
	}

	@Override
	public void onBackPressed() {

		// super.onBackPressed();

		FragmentManager manager = getSupportFragmentManager();

		if (manager.getBackStackEntryCount() > 0) {

			manager.popBackStack();

		} else {

			if (ft != null) {

				ft.remove(mainFragment2);

				//moveTaskToBack(true);
			}
			finish();
		}
	}

	@Override
	public void onNetIssueADYesClickListener(String INTENT_SETTINGS_STRING) {

		if (INTENT_SETTINGS_STRING != null) {

			startActivity(new Intent(INTENT_SETTINGS_STRING));

		} else {

			Toast.makeText(this, "Alas.. Exiting..", Toast.LENGTH_LONG).show();
			this.finish();
		}
	}
}