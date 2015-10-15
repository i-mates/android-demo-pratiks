package in.imates.student;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;

/**
 * Created by root on 26/9/15.
 */
public class Activity2 extends Activity {

    Spinner spinner;
    private  Button button;

    ArrayAdapter<String> adapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        spinner=(Spinner)findViewById(R.id.spinner);

        adapter= new ArrayAdapter<String>(Activity2.this,android.R.layout.simple_spinner_dropdown_item,getResources()
        .getStringArray(R.array.what_to_study));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner= (Spinner) findViewById(R.id.spinner);


    }
}
