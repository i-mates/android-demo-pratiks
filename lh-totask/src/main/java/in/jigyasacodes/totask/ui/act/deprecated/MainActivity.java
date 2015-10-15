package in.jigyasacodes.totask.ui.act.deprecated;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import in.jigyasacodes.totask.R;
import in.jigyasacodes.totask.adap.deprecated.RecyclerAdapterLevel1;
import in.jigyasacodes.totask.bg.CoursesFetcherTaskLevel1;
import in.jigyasacodes.totask.data.redifined.Linked;
import in.jigyasacodes.totask.data.redifined.Meta;
import in.jigyasacodes.totask.data.redifined.University;

public class MainActivity extends AppCompatActivity implements
        CoursesFetcherTaskLevel1.OnCoursesFetcherTaskLevel1hCompleteListener {

    private RecyclerView rvCourses;

    private ProgressBar progressBar;

    // ImageView ivCourseLogo;
    // TextView tvCourseName, tvUnivName, tvInstructorName;
    private RecyclerAdapterLevel1 recyclerAdapterLevel1;
    private RecyclerView.Adapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (initUI()) {
            ;
        } else {
            Toast.makeText(this,
                    "Failed to bind the UI elements..\n\nExiting..",
                    Toast.LENGTH_LONG).show();
        }

        //getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        //supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        initREST();
    }

    private boolean initUI() {

        try {

            // ivCourseLogo = (ImageView) findViewById(R.id.ivCourseLogo);

            // tvCourseName = (TextView) findViewById(R.id.tvCourseName);
            // tvUnivName = (TextView) findViewById(R.id.tvUnivName);
            // tvInstructorName = (TextView)
            // findViewById(R.id.tvInstructorName);

            rvCourses = (RecyclerView) findViewById(R.id.rvCourses);
            rvCourses.setHasFixedSize(true);
            LinearLayoutManager lLayoutManager = new LinearLayoutManager(this);
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

        new CoursesFetcherTaskLevel1(this, this.getApplicationContext())
                .execute(COURSERA_COURSES_URL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnCoursesFetcherTaskLevel1hComplete(
            boolean isSHResponseSuccessful, Meta META) {

        if (!META.equals(null)) {

            floodCoursesRecyclerView(META);

        } else {

            Toast.makeText(this,
                    "Oops..\n\nSomething went wrong !!\n\nExiting..",
                    Toast.LENGTH_LONG).show();
        }
    }

    private boolean floodCoursesRecyclerView(final Meta META) {

        Log.e("fcrv() -> ", "1 -> " + META.getElements().get(1).getName());
        //progressBar.setVisibility(View.GONE);
        Linked l = META.getLinked();
        List<University> univList = META.getLinked().getUniversities();
        Log.e("fcrv() -> ", "2: "+ META.getLinked().getUniversities().get(1).getName());
        rvAdapter = new RecyclerAdapterLevel1(MainActivity.this, META, META.getElements());

        Log.e("fcrv() -> ", "3 -> itemCount: " + rvAdapter.getItemCount());

        rvCourses.setAdapter(rvAdapter);
        Log.e("fcrv() -> ", "4");
        rvCourses.setVisibility(View.VISIBLE);
        Log.e("fcrv() -> ", "5");
        // RecyclerView.ItemDecoration itemDecoration = new
        // DividerItemDecoration(
        // this, LinearLayoutManager.VERTICAL);
        // rvCourses.addItemDecoration(itemDecoration);

        return true;
    }
}