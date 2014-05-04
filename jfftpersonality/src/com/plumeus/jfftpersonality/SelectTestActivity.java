package com.plumeus.jfftpersonality;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class SelectTestActivity extends Activity {
	int idTest=0;
	Button startQuiz1;
	Button startQuiz2;
	Button startQuiz3;
	Button startQuiz4;
	Button startQuiz5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_test);
		startQuiz1=(Button)findViewById(R.id.button1);
		startQuiz2=(Button)findViewById(R.id.button2);
		startQuiz3=(Button)findViewById(R.id.button3);
		startQuiz4=(Button)findViewById(R.id.button4);
		startQuiz5=(Button)findViewById(R.id.button5);

		startQuiz1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelectTestActivity.this, InstructionActivity.class);
				idTest = 342;
				Bundle b = new Bundle();
				b.putInt("idTest", idTest);
				intent.putExtras(b); //Put idTest to your next Intent
				startActivity(intent);
				finish();
			}
		});

		startQuiz2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelectTestActivity.this, InstructionActivity.class);
				idTest = 356;
				Bundle b = new Bundle();
				b.putInt("idTest", idTest);
				intent.putExtras(b); //Put idTest to your next Intent
				startActivity(intent);
				finish();
			}
		});

		startQuiz3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelectTestActivity.this, InstructionActivity.class);
				idTest = 366;
				Bundle b = new Bundle();
				b.putInt("idTest", idTest);
				intent.putExtras(b); //Put idTest to your next Intent
				startActivity(intent);
				finish();
			}
		});

		startQuiz4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelectTestActivity.this, InstructionActivity.class);
				idTest = 367;
				Bundle b = new Bundle();
				b.putInt("idTest", idTest);
				intent.putExtras(b); //Put idTest to your next Intent
				startActivity(intent);
				finish();
			}
		});

		startQuiz5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelectTestActivity.this, InstructionActivity.class);
				idTest = 404;
				Bundle b = new Bundle();
				b.putInt("idTest", idTest);
				intent.putExtras(b); //Put idTest to your next Intent
				startActivity(intent);
				finish();
			}
		});

	}
}
