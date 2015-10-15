package in.imates.dateandtimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

	DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
	TextView dateAndTimeLabel;
	Calendar dateAndTime = Calendar.getInstance();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dateAndTimeLabel = (TextView) findViewById(R.id.dateAndTime);
		updateLabel();
	}

	public void chooseDate(View v) {
		new DatePickerDialog(MainActivity.this, d, dateAndTime.get(Calendar.YEAR),
				dateAndTime.get(Calendar.MONTH), dateAndTime.get(Calendar.DAY_OF_MONTH)).show();

	}

	public void chooseTime(View v) {
		new TimePickerDialog(MainActivity.this, t, dateAndTime.get(Calendar.HOUR_OF_DAY),
				dateAndTime.get(Calendar.MINUTE), true).show();
	}

	private void updateLabel() {
		dateAndTimeLabel.setText(fmtDateAndTime.format(dateAndTime.getTime()));
	}
	DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {

			dateAndTime.set(Calendar.YEAR, year);
			dateAndTime.set(Calendar.MONTH, monthOfYear);
			dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateLabel();
		}
	};

	TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker v, int hourOfDay, int minute) {
			dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
			dateAndTime.set(Calendar.MINUTE, minute);
			updateLabel();

		}
	};
}