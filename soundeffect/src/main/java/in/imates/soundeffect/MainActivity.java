package in.imates.soundeffect;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button btn9 = (Button) findViewById(R.id.button9);
		final Button btn1 = (Button) findViewById(R.id.button1);
		final Button btn2 = (Button) findViewById(R.id.button2);
		final Button btn3 = (Button) findViewById(R.id.button3);
		final Button btn4 = (Button) findViewById(R.id.button4);
		final Button btn5 = (Button) findViewById(R.id.imageButton2);

		final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.lion);
		final MediaPlayer mp1 = MediaPlayer.create(MainActivity.this, R.raw.catsound);
		final MediaPlayer mp2 = MediaPlayer.create(MainActivity.this,R.raw.dogbark);
		final MediaPlayer mp3 = MediaPlayer.create(MainActivity.this, R.raw.fbeby);
		final MediaPlayer mp4 = MediaPlayer.create(MainActivity.this.R.raw.ambulance);

		btn4.setOnClickListener(new View.OnClickListener()){
			pub
		}

					btn9.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						finish();
					}
				});

				btn1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {

							if (mp1.isPlaying()||mp2.isPlaying()||mp3.isPlaying()) {

								mp1.stop();
								mp2.stop();
								mp3.stop();
							}
						mp.start();

					}
				});

				btn2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {

						if(mp.isPlaying()||mp2.isPlaying()||mp3.isPlaying()){
							mp.stop();
							mp2.stop();
							mp3.stop();
						}
						mp1.start();

					}
				});
				btn3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						if(mp.isPlaying()||mp1.isPlaying()||mp3.isPlaying()){
							mp.stop();
							mp1.stop();
							mp3.stop();
						}
						mp2.start();

					}
				});
				btn4.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {

						if(mp.isPlaying()||mp2.isPlaying()||mp1.isPlaying()){
							mp.stop();
							mp2.stop();
							mp1.stop();
						}
						mp3.start();
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
