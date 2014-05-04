package com.plumeus.jfftpersonality;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class InstructionActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instruction);
		DbHelper db=new DbHelper(this);
		//get history button
		Button historyButton = (Button)findViewById(R.id.bHistory);
		//get start button
		Button startButton = (Button)findViewById(R.id.bStart);
		//get list button
		Button listButton = (Button)findViewById(R.id.bList);
		//get quiz name
		TextView quizName=(TextView)findViewById(R.id.quizName);
		//get text view
		TextView instructionText=(TextView)findViewById(R.id.instructionText);
		//get score
		final Bundle b = getIntent().getExtras();
		final int idTest= b.getInt("idTest");
		//display name
		String nameText = db.getQuizName(idTest);
		quizName.setText(nameText);
		//display instruction
		String descText = db.getQuizDescription(idTest);
		instructionText.setText(descText);

		historyButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(InstructionActivity.this, History2Activity.class);
				b.putInt("idTest", idTest);
				intent.putExtras(b); //Put idTest to your next Intent
				startActivity(intent);
				finish();
			}
		});

		startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(InstructionActivity.this, QuizActivity.class);
				b.putInt("idTest", idTest);
				intent.putExtras(b); //Put idTest to your next Intent
				startActivity(intent);
				finish();
			}
		});

		listButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(InstructionActivity.this, SelectTestActivity.class);
				startActivity(intent);
				finish();
			}
		});

	}

}
