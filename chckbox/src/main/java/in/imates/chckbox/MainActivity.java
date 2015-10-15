package in.imates.chckbox;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{

	CheckBox ch1,ch2,ch3,ch4;
	Button btn1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ch1 = (CheckBox)findViewById(R.id.checkBox1);
		ch2=(CheckBox)findViewById(R.id.checkBox2);
		ch3=(CheckBox)findViewById(R.id.checkBox3);
		ch4=(CheckBox)findViewById(R.id.checkBox3);

		btn1=(Button)findViewById(R.id.button);

		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				StringBuffer result=new StringBuffer();
				result.append("Passport Selected").append(ch1.isChecked());
				result.append("Driving Licence selected").append(ch2.isChecked());
				result.append("Voting selected").append(ch3.isChecked());
				result.append("Ration Card selected").append(ch4.isChecked());
				Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_LONG).show();
			}

		});

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

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
