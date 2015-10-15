package in.jigyasacodes.totask.ui.act.deprecated;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import in.jigyasacodes.totask.R;

public class Trial extends AppCompatActivity{

	Button btnTrial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_trial);

		btnTrial = (Button) findViewById(R.id.btnTrial);

		btnTrial.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				btnTrial.setText("Hahahaaa");
			}
		});
	}
}