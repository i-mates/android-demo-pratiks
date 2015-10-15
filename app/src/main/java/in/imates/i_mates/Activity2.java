package in.imates.i_mates;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by root on 26/9/15.
 */
public class Activity2 extends Activity{

        private Button btn;
        private EditText uname,pass,cpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty2);

        btn = (Button) findViewById(R.id.button2);
        uname=(EditText) findViewById(R.id.uname);
        pass=(EditText) findViewById(R.id.passwrd);
        cpass=(EditText) findViewById(R.id.cpasswrd);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(pass.equals(cpass)) {
                        Intent intent = new Intent();
                        intent.putExtra("uname", uname.getText().toString().trim());
                        intent.putExtra("pass", pass.getText().toString().trim());
                        intent.putExtra("cpass", cpass.getText().toString().trim());

                        intent.setClass(Activity2.this, Activity3.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Password does not match",
                                Toast.LENGTH_SHORT).show();

            }
        });




    }
}

