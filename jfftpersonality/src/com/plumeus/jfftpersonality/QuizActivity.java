package com.plumeus.jfftpersonality;

import java.util.List;


import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
//import android.widget.Toast;
import com.google.ads.*;

public class QuizActivity extends Activity {

	List<Question> quesList;
	int questNum=0;
	int resultValue=0;
	int score=0;
	int qid=0;
	int idTest=0;
	int idHistory=0;
	int unfinishedIdHistory=0;
	int checkedRadioButtonId;
	int answerValue=0;
	ProgressBar progressBar;
	Question currentQ;
	TextView txtQuestion;
	TextView txtProgressStatus;
	View rdaV,rdbV,rdcV;
	RadioButton rda, rdb, rdc;
	private AdView adView;
	private ViewGroup aniView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		idTest= b.getInt("idTest");

		setContentView(R.layout.activity_quiz);
		final DbHelper db=new DbHelper(this);

		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		unfinishedIdHistory = db.getUnfinishedIdHistory(idTest);
		if( unfinishedIdHistory != 0){
			idHistory = unfinishedIdHistory;
			qid = db.getNextIndexQuestion(idTest, idHistory);
			resultValue = db.getResult(idTest, idHistory);
		}
		else{
			idHistory = db.getNextIdHistory(idTest);
			db.insertReport(idTest, idHistory);
		}
		questNum = db.rowcount(idTest);
		progressBar.setMax(questNum);
		progressBar.setProgress(qid + 1);
		quesList=db.getAllQuestions(idTest);
		currentQ=quesList.get(qid);
		txtProgressStatus=(TextView)findViewById(R.id.progrStatus);
		txtQuestion=(TextView)findViewById(R.id.textView1);
		rdaV=(View)findViewById(R.id.lineRadio0);
		rdbV=(View)findViewById(R.id.lineRadio1);
		rdcV=(View)findViewById(R.id.lineRadio2);
		rda=(RadioButton)findViewById(R.id.radio0);
		rdb=(RadioButton)findViewById(R.id.radio1);
		rdc=(RadioButton)findViewById(R.id.radio2);
		setQuestionView();

		rda.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				answerValue = currentQ.getOptaValue();
				rdaV.setBackgroundDrawable(getResources().getDrawable(R.drawable.pressedselect));
				resultValue += answerValue;
				if(qid < questNum){
					progressBar.setProgress(qid + 1);
					currentQ=quesList.get(qid);
					db.updateReport(idTest, idHistory, qid, 0, resultValue);
					doAnimation();
				}else{
					Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
					Bundle b = new Bundle();
					b.putInt("score", resultValue); //Your score
					b.putInt("idHistory", idHistory); //Your score
					b.putInt("idTest", idTest); //Your idTest
					db.updateReport(idTest, idHistory, qid, 1, resultValue);
					db.cleanUpHistory(idTest);
					intent.putExtras(b); //Put your score to your next Intent
					startActivity(intent);
					finish();
				}
			}
		});
		rdb.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				answerValue = currentQ.getOptbValue();
				rdbV.setBackgroundDrawable(getResources().getDrawable(R.drawable.pressedselect));
				resultValue += answerValue;
				if(qid < questNum){
					progressBar.setProgress(qid + 1);
					currentQ=quesList.get(qid);
					db.updateReport(idTest, idHistory, qid, 0, resultValue);
					doAnimation();
				}else{
					Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
					Bundle b = new Bundle();
					b.putInt("score", resultValue); //Your score
					b.putInt("idHistory", idHistory); //Your score
					b.putInt("idTest", idTest); //Your idTest
					db.updateReport(idTest, idHistory, qid, 1, resultValue);
					db.cleanUpHistory(idTest);
					intent.putExtras(b); //Put your score to your next Intent
					startActivity(intent);
					finish();
				}
			}
		});

		rdc.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				answerValue = currentQ.getOptcValue();
				rdcV.setBackgroundDrawable(getResources().getDrawable(R.drawable.pressedselect));
				resultValue += answerValue;
				if(qid < questNum){
					progressBar.setProgress(qid + 1);
					currentQ=quesList.get(qid);
					db.updateReport(idTest, idHistory, qid, 0, resultValue);
					doAnimation();
				}else{
					Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
					Bundle b = new Bundle();
					b.putInt("score", resultValue); //Your score
					b.putInt("idHistory", idHistory); //Your score
					b.putInt("idTest", idTest); //Your idTest
					db.updateReport(idTest, idHistory, qid, 1, resultValue);
					db.cleanUpHistory(idTest);
					intent.putExtras(b); //Put your score to your next Intent
					startActivity(intent);
					finish();
				}
			}
		});
	}

	private void setQuestionView()
	{
		int questCurrent = qid+1;
		txtProgressStatus.setText(questCurrent + " of " + questNum);
		txtQuestion.setText(currentQ.getQUESTION());
		rdaV.setBackgroundDrawable(getResources().getDrawable(R.drawable.normalselect));
		rdbV.setBackgroundDrawable(getResources().getDrawable(R.drawable.normalselect));
		rdcV.setBackgroundDrawable(getResources().getDrawable(R.drawable.normalselect));
		rda.setText(currentQ.getOPTA());
		rda.setChecked(false);
		rdb.setText(currentQ.getOPTB());
		rdb.setChecked(false);
		rdc.setChecked(false);
		if(currentQ.getOPTC() == "NONE"){
			rdc.setVisibility(View.GONE);
			rdcV.setVisibility(View.GONE);
		}
		else{
			rdc.setVisibility(View.VISIBLE);
			rdcV.setVisibility(View.VISIBLE);
			rdc.setText(currentQ.getOPTC());
		}
		qid++;
	}

	private void doAnimation()
	{
		aniView = (ViewGroup) findViewById(R.id.mainLinearLayout);
		ObjectAnimator fadeOut = ObjectAnimator.ofFloat(aniView, "alpha", 0f);
		fadeOut.setDuration(1000);
		ObjectAnimator mover = ObjectAnimator.ofFloat(aniView,"translationX", 500f, 0f);
		mover.setDuration(500);
		ObjectAnimator fadeIn = ObjectAnimator.ofFloat(aniView, "alpha", 0f, 1f);
		fadeIn.setDuration(500);
		AnimatorSet animatorSet = new AnimatorSet();
		final AnimatorSet animatorSet2 = new AnimatorSet();
		animatorSet.play(fadeOut);
		animatorSet2.play(mover).with(fadeIn);
		animatorSet.addListener(new Animator.AnimatorListener(){
			@Override
			public void onAnimationCancel(Animator animatorSet) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onAnimationEnd(Animator animatorSet) {
				setQuestionView();
				animatorSet2.start();
			}
			@Override
			public void onAnimationRepeat(Animator animatorSet) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onAnimationStart(Animator animatorSet) {
				// TODO Auto-generated method stub
//				setQuestionView();
			}
		});
		animatorSet.start();
	}

	@Override
	public void onDestroy() {
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent intent = new Intent(QuizActivity.this, SelectTestActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}

}
