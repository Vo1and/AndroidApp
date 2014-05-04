package com.plumeus.jfftpersonality;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class History2Activity extends Activity {
	int idHistory=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);

		Bundle b = getIntent().getExtras();
		final int idTest= b.getInt("idTest");
		final DbHelper db=new DbHelper(this);

		final ListView listview = (ListView) findViewById(R.id.listView);

		final List<String> list = db.getQuizzHistory(idTest);
		final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				idHistory = db.getQuizIdHistoryByName(idTest, item);
				Intent intent = new Intent(History2Activity.this, ResultActivity.class);
				Bundle b = new Bundle();
				b.putInt("idHistory", idHistory); //Your score
				b.putInt("idTest", idTest); //Your idTest
				intent.putExtras(b); //Put your score to your next Intent
				startActivity(intent);
				finish();
			}
		});

		Button closeButton = (Button)findViewById(R.id.bClose);
		closeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(History2Activity.this, InstructionActivity.class);
				Bundle b = new Bundle();
				b.putInt("idTest", idTest);
				intent.putExtras(b); //Put idTest to your next Intent
				startActivity(intent);
				finish();
			}
		});

	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}



}
