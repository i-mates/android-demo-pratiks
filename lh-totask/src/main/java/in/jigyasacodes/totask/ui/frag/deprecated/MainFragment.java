package in.jigyasacodes.totask.ui.frag.deprecated;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import in.jigyasacodes.totask.R;
import in.jigyasacodes.totask.adap.deprecated.RecyclerAdapterLevel1;
import in.jigyasacodes.totask.bg.CoursesFetcherTaskLevel1;
import in.jigyasacodes.totask.data.redifined.Linked;
import in.jigyasacodes.totask.data.redifined.Meta;
import in.jigyasacodes.totask.data.redifined.University;

public class MainFragment extends Fragment implements
        CoursesFetcherTaskLevel1.OnCoursesFetcherTaskLevel1hCompleteListener {

    private Context ctx;

    private RecyclerView rvCourses;

    private ProgressBar progressBar;

    // ImageView ivCourseLogo;
    // TextView tvCourseName, tvUnivName, tvInstructorName;
    private RecyclerAdapterLevel1 recyclerAdapterLevel1;
    private RecyclerView.Adapter rvAdapter;

    private OnCourseItemClickListenerr OnCourseItemClickListenerr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_main, container, false);

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

            rvCourses = (RecyclerView) view.findViewById(R.id.rvCourses);
            rvCourses.setHasFixedSize(true);
            LinearLayoutManager lLayoutManager = new LinearLayoutManager(ctx);
            lLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvCourses.setLayoutManager(lLayoutManager);

            //progressBar = (ProgressBar) findViewById(R.id.progressBar);
            //progressBar.setVisibility(View.VISIBLE);
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

        final String COURSERA_COURSES_URL = "https://api.coursera.org/api/catalog.v1/courses?ids=2163,69,1322,2822,1024,1369,138,2889,1190&fields=smallIcon,largeIcon,shortDescription,instructor,universities.fields%28name%29,instructors.fields%28photo150,department,firstName,middleName,lastName,fullName%29&includes=universities,instructors";

        new CoursesFetcherTaskLevel1(this, ctx)
                .execute(COURSERA_COURSES_URL);
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

        Log.e("fcrv() -> ", "1 -> " + META.getElements().get(1).getName());
        //progressBar.setVisibility(View.GONE);
        Linked l = META.getLinked();
        List<University> univList = META.getLinked().getUniversities();
        Log.e("fcrv() -> ", "2: " + META.getLinked().getUniversities().get(1).getName());
        recyclerAdapterLevel1 = new RecyclerAdapterLevel1(ctx, META, META.getElements());

        Log.e("fcrv() -> ", "3 -> itemCount: " + recyclerAdapterLevel1.getItemCount());

        rvCourses.setAdapter(recyclerAdapterLevel1);
        Log.e("fcrv() -> ", "4");
        rvCourses.setVisibility(View.VISIBLE);
        Log.e("fcrv() -> ", "5");
        // RecyclerView.ItemDecoration itemDecoration = new
        // DividerItemDecoration(
        // this, LinearLayoutManager.VERTICAL);
        // rvCourses.addItemDecoration(itemDecoration);

        recyclerAdapterLevel1.setOnCourseItemClickListener(new RecyclerAdapterLevel1.OnCourseItemClickListener() {
            @Override
            public void onCourseItemClick(View view, int POSITION) {

                if (OnCourseItemClickListenerr != null) {

                    OnCourseItemClickListenerr.onCourseItemClickk(view, META, POSITION);
                }
            }
        });

        return true;
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        this.ctx = activity.getApplicationContext();

        Log.e("onAttach() ->", "1");

        if (activity instanceof OnCourseItemClickListenerr) {

            Log.e("onAttach() -> if()", "2.1");

            this.OnCourseItemClickListenerr = (OnCourseItemClickListenerr) activity;

            Log.e("onAttach() -> if()", "2.2");

        } else {

            Log.e("onAttach() -> else()", "3");

            throw new ClassCastException("Class " + activity.toString().trim()
                    + " must implement MainFragment.OnCourseItemClickListenerr interface !!");
        }

        Log.e("onAttach() ->", "4");
    }

    public interface OnCourseItemClickListenerr {

        public void onCourseItemClickk(View view, final Meta META, final int POSITION);
    }
}