package in.imates.arithmeticdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import static in.imates.arithmeticdemo.R.id.resultAdd;


public class MainActivity extends ActionBarActivity {

	EditText firstNumber;
	EditText secondNumber;
	TextView addition;
	Button btnAdd;

	private double number1,number2,sum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		firstNumber = (EditText)findViewById(R.id.txtNumber1);
		secondNumber = (EditText)findViewById(R.id.txtNumber2);
		addition = (TextView) findViewById(R.id.resultAdd);
		btnAdd = (Button)findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//number1 = Integer.parseInt(String.valueOf(firstNumber.getText().toString()));
				//number2 = Integer.parseInt(String.valueOf(secondNumber.getText().toString()));
				//sum = number1+number2;
				//String add =String.valueOf(sum);
				//addition.setText(add);


				number1 = Double.parseDouble(firstNumber.getText().toString());
				number2 = Double.parseDouble(secondNumber.getText().toString());
				sum = number1 + number2;
				addition.setText(Double.toString(sum));

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
