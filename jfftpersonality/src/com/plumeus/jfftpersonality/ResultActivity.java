package com.plumeus.jfftpersonality;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class ResultActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		DbHelper db=new DbHelper(this);
		//get history button
		Button historyButton = (Button)findViewById(R.id.bHistory);
		//get history button
		Button listButton = (Button)findViewById(R.id.bList);
		//get text view
		TextView t=(TextView)findViewById(R.id.textResult);
		//get text view
		TextView reportText=(TextView)findViewById(R.id.reportText);
		//get score
		final Bundle b = getIntent().getExtras();
		final int idTest= b.getInt("idTest");
		int idHistory= b.getInt("idHistory");
		int score= db.getResult(idTest, idHistory);
		String resultText = db.getReportText(idTest, score);

		//display score
		String your_score_is = getResources().getString(R.string.your_score_is);
		t.setText(your_score_is + ": " + score);
		reportText.setText(resultText);

		historyButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ResultActivity.this, History2Activity.class);
				b.putInt("idTest", idTest);
				intent.putExtras(b); //Put idTest to your next Intent
				startActivity(intent);
				finish();
			}
		});

		listButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ResultActivity.this, SelectTestActivity.class);
				startActivity(intent);
				finish();
			}
		});

	}

}
