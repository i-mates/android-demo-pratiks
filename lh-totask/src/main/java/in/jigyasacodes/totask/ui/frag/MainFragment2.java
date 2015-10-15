package in.jigyasacodes.totask.ui.frag;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.jigyasacodes.totask.R;
import in.jigyasacodes.totask.adap.CustomArrAdapCourses;
import in.jigyasacodes.totask.bg.CoursesFetcherTaskLevel1;
import in.jigyasacodes.totask.data.redifined.Linked;
import in.jigyasacodes.totask.data.redifined.Meta;
import in.jigyasacodes.totask.data.redifined.University;

public class MainFragment2 extends Fragment implements
		CoursesFetcherTaskLevel1.OnCoursesFetcherTaskLevel1hCompleteListener {

	private Context ctx;

	// private RecyclerView rvCourses;
	private ListView lvCourses;

	private ProgressBar progressBar;

	// ImageView ivCourseLogo;
	// TextView tvCourseName, tvUnivName, tvInstructorName;

	private ArrayAdapter<String> arrAdapStrCourses;
	// private RecyclerAdapterLevel1 recyclerAdapterLevel1;
	// private RecyclerView.Adapter rvAdapter;

	private OnCourseItemClickListenerr onCourseItemClickListenerr;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		View view = inflater.inflate(R.layout.fragment_main_new, container,
				false);

		if (initUI(view)) {
			;
		} else {
			Toast.makeText(ctx,
					"Failed to bind the UI elements..\n\nExiting..",
					Toast.LENGTH_LONG).show();
		}

		initREST();

		return view;
	}

	private boolean initUI(View view) {

		try {

			// ivCourseLogo = (ImageView) findViewById(R.id.ivCourseLogo);

			// tvCourseName = (TextView) findViewById(R.id.tvCourseName);
			// tvUnivName = (TextView) findViewById(R.id.tvUnivName);
			// tvInstructorName = (TextView)
			// findViewById(R.id.tvInstructorName);

			lvCourses = (ListView) view.findViewById(R.id.lvCourses);
			// rvCourses.setHasFixedSize(true);
			// LinearLayoutManager lLayoutManager = new
			// LinearLayoutManager(ctx);
			// lLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
			// lvCourses.setLayoutManager(lLayoutManager);

			// progressBar = (ProgressBar) findViewById(R.id.progressBar);
			// progressBar.setVisibility(View.VISIBLE);
			// progressBar.setTranslationY(20f);

		} catch (Exception e) {

			return false;
		}
		return true;
	}

	private void initREST() {

		// TRIAL URL
		// /////////////////////////////////////////////////////////////////////////////////
		// https://api.coursera.org/api/catalog.v1/courses?ids=2163,69,1322,2822,1024,1369,138,2889,1190
		// &fields=smallIcon,largeIcon,shortDescription,instructor,
		// universities.fields(name),
		// instructors.fields(photo150,department,firstName,middleName,lastName,fullName)
		// &includes=universities,instructors

		//final String COURSERA_COURSES_URL = "https://api.coursera.org/api/catalog.v1/courses?ids=2163,69,1322,2822,1024,1369,138,2889,1190&fields=smallIcon,largeIcon,shortDescription,instructor,universities.field(name),instructors.fields(photo150,department,firstName,middleName,lastName,fullName)&includes=universities,instructors";
		final String COURSERA_COURSES_URL = "https://api.coursera.org/api/catalog.v1/courses?fields=smallIcon,largeIcon,shortDescription,instructor,universities.fields(name),instructors.fields(photo150,department,firstName,middleName,lastName,fullName)&includes=universities,instructors";


		new CoursesFetcherTaskLevel1(this, ctx).execute(COURSERA_COURSES_URL);
	}

	@Override
	public void OnCoursesFetcherTaskLevel1hComplete(
			boolean isSHResponseSuccessful, Meta META) {

		if (!META.equals(null)) {

			floodCoursesRecyclerView(META);

		} else {

			Toast.makeText(ctx,
					"Oops..\n\nSomething went wrong !!\n\nExiting..",
					Toast.LENGTH_LONG).show();
		}
	}

	private boolean floodCoursesRecyclerView(final Meta META) {

		Log.e("fcrv() -> ", "1 -> " + META.getElements().get(0).getName());
		// progressBar.setVisibility(View.GONE);
		Linked l = META.getLinked();
		List<University> univList = META.getLinked().getUniversities();
		//Log.e("fcrv() -> ", "2: "
				//+ META.getLinked().getUniversities().get(0).getName());
		// recyclerAdapterLevel1 = new RecyclerAdapterLevel1(ctx, META,
		// META.getElements());

		List<String> listStr = new ArrayList<String>();	//new List<Element>();
		
		for(int i=0;i<META.getElements().size();++i)
		{
			listStr.add(META.getElements().get(i).getName().toString().trim());
		}
		
		arrAdapStrCourses = new CustomArrAdapCourses(this.ctx,
				R.layout.course_rv_item_new_2, R.id.tvCourseName,
				//(String[]) META.getElements().toArray(), META,
				listStr, META,
				META.getElements());

		Log.e("fcrv() -> ", "3 -> itemCount: " + arrAdapStrCourses.getCount());

		lvCourses.setAdapter(arrAdapStrCourses);
		Log.e("fcrv() -> ", "4");
		lvCourses.setVisibility(View.VISIBLE);
		Log.e("fcrv() -> ", "5");
		// RecyclerView.ItemDecoration itemDecoration = new
		// DividerItemDecoration(
		// this, LinearLayoutManager.VERTICAL);
		// rvCourses.addItemDecoration(itemDecoration);

		lvCourses.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapView, View view,
					final int POSITION, final long id) {

				if (onCourseItemClickListenerr != null) {

					onCourseItemClickListenerr.onCourseItemClickk(view, META,
							POSITION);
				}
			}
		});

		/*
		 * recyclerAdapterLevel1 .setOnCourseItemClickListener(new
		 * RecyclerAdapterLevel1.OnCourseItemClickListener() {
		 * 
		 * @Override public void onCourseItemClick(View view, int POSITION) {
		 * 
		 * if (onCourseItemClickListenerr != null) {
		 * 
		 * onCourseItemClickListenerr.onCourseItemClickk(view, META, POSITION);
		 * } } });
		 */

		return true;
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		this.ctx = activity;

		Log.e("onAttach() ->", "1");

		if (activity instanceof OnCourseItemClickListenerr) {

			Log.e("onAttach() -> if()", "2.1");

			this.onCourseItemClickListenerr = (OnCourseItemClickListenerr) activity;

			Log.e("onAttach() -> if()", "2.2");

		} else {

			Log.e("onAttach() -> else()", "3.1");

			//throw new ClassCastException(
					//"Class "
							//+ activity.toString().trim()
							//+ " must implement MainFragment2.OnCourseItemClickListenerr interface !!");
		}

		Log.e("onAttach() ->", "4");
	}

	public interface OnCourseItemClickListenerr {

		public void onCourseItemClickk(View view, final Meta META,
		                               final int POSITION);
	}
}