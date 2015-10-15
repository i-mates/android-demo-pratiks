package in.imates.i_mates;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by root on 26/9/15.
 */
public class Activity3 extends Activity {

    private Button btn;
    private TextView tv,info1,info2,info3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);

        tv = (TextView) findViewById(R.id.rsuccess);
        info1 = (TextView) findViewById(R.id.info1);
        info2 = (TextView) findViewById(R.id.info2);
        info3 = (TextView) findViewById(R.id.info3);
        btn = (Button) findViewById(R.id.button3);

        Bundle b = getIntent().getExtras();

        String uname = b.getString("uname");
        String pass = b.getString("pass");
        String cpass = b.getString("cpass");

        info1.setText(uname);
        info2.setText(pass);
        info3.setText(cpass);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startActivity(new Intent(Activity3.this, Activity4.class));
            }
        });

    }
}


