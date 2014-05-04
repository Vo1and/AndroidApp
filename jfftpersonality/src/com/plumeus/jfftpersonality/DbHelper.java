package com.plumeus.jfftpersonality;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
//import java.util.Date;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "jfftPersonality";
	// quizzes table name
	private static final String TABLE_QUIZZES = "quizzes";
	// questions table name
	private static final String TABLE_QUESTIONS = "questions";
	// answers table name
	private static final String TABLE_ANSWERS = "answers";
	// answers table name
	private static final String TABLE_REPORTTEXT = "reportText";
	// usersAnswers table name
//	private static final String TABLE_USERSANSWERS = "usersAnswers";
	// usersReports table name
	private static final String TABLE_USERSREPORTS = "usersReports";

	// quizzes Table Columns names
	private static final String QUIZZES_KEY_IDQUIZ = "idQuiz";
	private static final String QUIZZES_KEY_NAME = "name";
	private static final String QUIZZES_KEY_DESC = "desc";

	// questions Table Columns names
	private static final String QUESTIONS_KEY_IDQUESTION = "idQuestion";
	private static final String QUESTIONS_KEY_IDQUIZ = "idQuiz";
	private static final String QUESTIONS_KEY_QTEXT = "qText";

	// answers Table Columns names
	private static final String ANSWERS_KEY_IDANSWER = "idAnswer";
	private static final String ANSWERS_KEY_IDQUESTION = "idQuestion";
	private static final String ANSWERS_KEY_ATEXT = "aText";
	private static final String ANSWERS_KEY_VALUE = "value";

	// reportText Table Columns names
	private static final String REPORTTEXT_KEY_IDQUIZ = "idQuiz";
	private static final String REPORTTEXT_KEY_LEFT = "left";
	private static final String REPORTTEXT_KEY_RIGHT = "right";
	private static final String REPORTTEXT_KEY_TEXT = "text";

	// usersReports Table Columns names
	private static final String USERSREPORTS_KEY_IDQUIZ = "idQuiz";
	private static final String USERSREPORTS_KEY_IDHISTORY = "idHistory";
	private static final String USERSREPORTS_KEY_INDEXQUESTION = "indexQuestion";
	private static final String USERSREPORTS_KEY_FINISHED = "finished";
	private static final String USERSREPORTS_KEY_RESULT = "result";
	private static final String USERSREPORTS_KEY_DATETIME = "datetime";
	private static final String USERSREPORTS_KEY_REPORTNAME = "reportName";

	private static final int NUMBER_OF_REPORTS_TO_KEEP = 5;
	// usersAnswers Table Columns names
	//	private static final String USERSANSWERS_KEY_IDUSER = "idUser";
	//	private static final String USERSANSWERS_KEY_IDQUIZ = "idQuiz";
	//	private static final String USERSANSWERS_KEY_IDQUESTION = "idQuestion";
	//	private static final String USERSANSWERS_KEY_IDANSWER = "idAnswer";

	private SQLiteDatabase dbase;

	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase=db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUIZZES + " (" + QUIZZES_KEY_IDQUIZ + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUIZZES_KEY_NAME + " TEXT, " + QUIZZES_KEY_DESC + " TEXT)";
		db.execSQL(sql);
		sql = "CREATE TABLE IF NOT EXISTS questions (idQuestion INTEGER PRIMARY KEY, idQuiz INTEGER, qText TEXT)";
		db.execSQL(sql);
		sql = "CREATE TABLE IF NOT EXISTS answers (idAnswer INTEGER, idQuestion INTEGER, aText TEXT, value INTEGER, PRIMARY KEY (idAnswer, idQuestion))";
		db.execSQL(sql);
		sql = "CREATE TABLE IF NOT EXISTS reportText ( idQuiz INTEGER, left INTEGER, right INTEGER, text TEXT, PRIMARY KEY (idQuiz, left, right))";
		db.execSQL(sql);
		sql = "CREATE TABLE IF NOT EXISTS usersAnswers (idUser INTEGER, idQuiz INTEGER, idQuestion INTEGER, idAnswer INTEGER, PRIMARY KEY (idUser, idQuiz, idQuestion, idAnswer))";
		db.execSQL(sql);
		sql = "CREATE TABLE IF NOT EXISTS usersReports (idQuiz INTEGER, idHistory INTEGER, indexQuestion INTEGER, finished INTEGER, result INTEGER, datetime INTEGER, reportName TEXT, PRIMARY KEY (idQuiz, idHistory))";
		db.execSQL(sql);

		addQuiz();
		addQuestions();
		addAnswers();
		addReportText();

		//db.close();
	}

	public void addQuiz() {
		//SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values = new ContentValues();
		values.put(QUIZZES_KEY_IDQUIZ, 342);
		values.put(QUIZZES_KEY_NAME, "The Roommate Test");
		values.put(QUIZZES_KEY_DESC, "Are you a good roommate? Is living with you absolute heaven or total chaos? Are those \"roommate from hell\" stories talking about you? Assess your cohabitation potential with The Roommate Test.");
		dbase.insert(TABLE_QUIZZES, null, values);

		values = new ContentValues();
		values.put(QUIZZES_KEY_IDQUIZ, 356);
		values.put(QUIZZES_KEY_NAME, "The Good Employee Test");
		values.put(QUIZZES_KEY_DESC, "Are you a work slacker? Do you find yourself working hard or hardly working? Are you every boss' dream or nightmare? Find out if you deserve a raise or the boot with The Good Employee Test!");
		dbase.insert(TABLE_QUIZZES, null, values);

		values = new ContentValues();
		values.put(QUIZZES_KEY_IDQUIZ, 366);
		values.put(QUIZZES_KEY_NAME, "The Neighbor Test");
		values.put(QUIZZES_KEY_DESC, "Would you consider yourself a good neighbor? Are you more of a Ned Flanders or a Homer Simpson? Would Mr. Rogers insist on living next-door to you? Find out where you stand with The Neighbor Test - and see if your next door buddy agrees!");
		dbase.insert(TABLE_QUIZZES, null, values);

		values = new ContentValues();
		values.put(QUIZZES_KEY_IDQUIZ, 367);
		values.put(QUIZZES_KEY_NAME, "The Etiquette Test");
		values.put(QUIZZES_KEY_DESC, "Do your manners fit in perfectly at Buckingham Palace or Old McDonald's Farm? Is your social behavior cringe-worthy? Find out if your manners are honorable or horrible with The Etiquette Test.");
		dbase.insert(TABLE_QUIZZES, null, values);

		values = new ContentValues();
		values.put(QUIZZES_KEY_IDQUIZ, 404);
		values.put(QUIZZES_KEY_NAME, "The Friendship Test");
		values.put(QUIZZES_KEY_DESC, "Are you a good friend? Are you loyal to the death and willing to go through think and thin to be there for your best buds? Put your loyalty on the line with The Friendship Test.");
		dbase.insert(TABLE_QUIZZES, null, values);
	}

	public void addQuestions() {
		//SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values;

		// Questions for idTest 342 testName: The Roommate Test

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 342);
		values.put(QUESTIONS_KEY_IDQUESTION, 3470);
		values.put(QUESTIONS_KEY_QTEXT, "Your roommate has their very attractive younger sibling staying over for the weekend. It's obvious you could hook up with them if you wanted, but you know your roommate will be upset with you. Do you still go for it?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 342);
		values.put(QUESTIONS_KEY_IDQUESTION, 3471);
		values.put(QUESTIONS_KEY_QTEXT, "Your pet, which your roommate already can't stand, has just peed all over your roommate's pillow. You clean up the mess and there is no sign of any accident. Do you spill the beans about what happened?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 342);
		values.put(QUESTIONS_KEY_IDQUESTION, 3472);
		values.put(QUESTIONS_KEY_QTEXT, "You're battling a nasty cold and your roommate wants to have friends over for a small party. Do you let them?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 342);
		values.put(QUESTIONS_KEY_IDQUESTION, 3473);
		values.put(QUESTIONS_KEY_QTEXT, "It's Sunday night and you have a big meeting at work the next morning. Your roommate, who hasn't had sex in a year, is going at it in the next room…and not quietly! Do you...");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 342);
		values.put(QUESTIONS_KEY_IDQUESTION, 3474);
		values.put(QUESTIONS_KEY_QTEXT, "You've been intimate with your roommate in the past, but that part of your relationship is over and they are now dating someone. The two of you get drunk one night and your roommate makes a move on you. Do you...");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 342);
		values.put(QUESTIONS_KEY_IDQUESTION, 3475);
		values.put(QUESTIONS_KEY_QTEXT, "Have you ever tried to pick up your roommate's significant other?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 342);
		values.put(QUESTIONS_KEY_IDQUESTION, 3476);
		values.put(QUESTIONS_KEY_QTEXT, "Have you ever sneaked a peak at roommate when they were showering?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 342);
		values.put(QUESTIONS_KEY_IDQUESTION, 3477);
		values.put(QUESTIONS_KEY_QTEXT, "Have you ever stolen your roommate's food and never replaced it?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 342);
		values.put(QUESTIONS_KEY_IDQUESTION, 3478);
		values.put(QUESTIONS_KEY_QTEXT, "Have you ever intentionally not given your roommate a phone message?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 342);
		values.put(QUESTIONS_KEY_IDQUESTION, 3479);
		values.put(QUESTIONS_KEY_QTEXT, "Have you ever worn your roommate's underwear without their permission? ");
		dbase.insert(TABLE_QUESTIONS, null, values);

		// Questions for idTest 356 testName: The Good Employee Test

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 356);
		values.put(QUESTIONS_KEY_IDQUESTION, 3369);
		values.put(QUESTIONS_KEY_QTEXT, "Your friend at work steals an expensive laptop computer from the office. Do you turn them in?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 356);
		values.put(QUESTIONS_KEY_IDQUESTION, 3370);
		values.put(QUESTIONS_KEY_QTEXT, "You planned a vacation months ago but an important project at work isn't going as planned. It can't move forward without your expertise. Do you postpone your trip?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 356);
		values.put(QUESTIONS_KEY_IDQUESTION, 3371);
		values.put(QUESTIONS_KEY_QTEXT, "You found out someone who has the exact same job title as you makes significantly more money. Would you confront your boss and firmly demand a raise?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 356);
		values.put(QUESTIONS_KEY_IDQUESTION, 3372);
		values.put(QUESTIONS_KEY_QTEXT, "Your boss asks you to work on a Saturday, but you had plans to go out of town to visit friends. Do you:");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 356);
		values.put(QUESTIONS_KEY_IDQUESTION, 3373);
		values.put(QUESTIONS_KEY_QTEXT, "A company you've been with for over five years is going through tough times, and you are asked to take a temporary 20% pay cut. Do you:");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 356);
		values.put(QUESTIONS_KEY_IDQUESTION, 3374);
		values.put(QUESTIONS_KEY_QTEXT, "Your boss is out of town. Would you take extra long lunches?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 356);
		values.put(QUESTIONS_KEY_IDQUESTION, 3375);
		values.put(QUESTIONS_KEY_QTEXT, "Would you send your personal mail using the company postage machine?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 356);
		values.put(QUESTIONS_KEY_IDQUESTION, 3376);
		values.put(QUESTIONS_KEY_QTEXT, "Have you ever called in to work sick when you felt fine?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 356);
		values.put(QUESTIONS_KEY_IDQUESTION, 3377);
		values.put(QUESTIONS_KEY_QTEXT, "Have you ever gotten drunk at lunch on a workday?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 356);
		values.put(QUESTIONS_KEY_IDQUESTION, 3378);
		values.put(QUESTIONS_KEY_QTEXT, "Have you ever used your company computer to download porn?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		// Questions for idTest 366 testName: The Neighbor Test

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 366);
		values.put(QUESTIONS_KEY_IDQUESTION, 3450);
		values.put(QUESTIONS_KEY_QTEXT, "Your neighbors have no idea that you can see directly into their bedroom; do you tell them?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 366);
		values.put(QUESTIONS_KEY_IDQUESTION, 3451);
		values.put(QUESTIONS_KEY_QTEXT, "You're collecting your neighbor's mail while they're out of town and come across an unsealed envelope containing their paycheck. Do you have a peek?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 366);
		values.put(QUESTIONS_KEY_IDQUESTION, 3452);
		values.put(QUESTIONS_KEY_QTEXT, "Your plumbing is on the fritz for the day and you really need to go to the bathroom. Your neighbor is at work and you know how to slip in their back window. Would you sneak in to use the loo?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 366);
		values.put(QUESTIONS_KEY_IDQUESTION, 3453);
		values.put(QUESTIONS_KEY_QTEXT, "You borrow your neighbor's video camera and discover a tape of them having sex. Would you:");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 366);
		values.put(QUESTIONS_KEY_IDQUESTION, 3454);
		values.put(QUESTIONS_KEY_QTEXT, "Your neighbors, who are normally quiet, have a huge birthday bash. It is 4am and the music is still blaring. Do you…");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 366);
		values.put(QUESTIONS_KEY_IDQUESTION, 3455);
		values.put(QUESTIONS_KEY_QTEXT, "Have you ever had sex with a neighbor?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 366);
		values.put(QUESTIONS_KEY_IDQUESTION, 3456);
		values.put(QUESTIONS_KEY_QTEXT, "You're parking in the middle of the night and accidentally bump your neighbor's car. Would you mention it to them later?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 366);
		values.put(QUESTIONS_KEY_IDQUESTION, 3457);
		values.put(QUESTIONS_KEY_QTEXT, "Your neighbors have just moved into a house where a famous murder just took place. Do you tell them?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 366);
		values.put(QUESTIONS_KEY_IDQUESTION, 3458);
		values.put(QUESTIONS_KEY_QTEXT, "Have you ever sued a neighbor over an incident or problem you have had with them?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 366);
		values.put(QUESTIONS_KEY_IDQUESTION, 3459);
		values.put(QUESTIONS_KEY_QTEXT, "Have your neighbors ever called the police on you?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		// Questions for idTest 367 testName: The Etiquette Test

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 367);
		values.put(QUESTIONS_KEY_IDQUESTION, 3520);
		values.put(QUESTIONS_KEY_QTEXT, "You find out that you and a friend once dated the same person. They want to compare notes on the person's sexual habits. Do you kiss and tell?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 367);
		values.put(QUESTIONS_KEY_IDQUESTION, 3521);
		values.put(QUESTIONS_KEY_QTEXT, "Your friend invites you to dinner and you call ahead to see if you can bring something. They say no. Do you still bring something?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 367);
		values.put(QUESTIONS_KEY_IDQUESTION, 3522);
		values.put(QUESTIONS_KEY_QTEXT, "Have you ever snooped in the medicine cabinet at someone's house while visiting?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 367);
		values.put(QUESTIONS_KEY_IDQUESTION, 3523);
		values.put(QUESTIONS_KEY_QTEXT, "You are seated in the middle of a long row at the opera. You have to pee pretty badly. Do you:");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 367);
		values.put(QUESTIONS_KEY_IDQUESTION, 3524);
		values.put(QUESTIONS_KEY_QTEXT, "Your fiancé dumped you a year ago. You're still single when you get a wedding invitation to his or her marriage to someone else. Do you:");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 367);
		values.put(QUESTIONS_KEY_IDQUESTION, 3525);
		values.put(QUESTIONS_KEY_QTEXT, "The phone rings during sex. Do you answer it?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 367);
		values.put(QUESTIONS_KEY_IDQUESTION, 3526);
		values.put(QUESTIONS_KEY_QTEXT, "Do you ever get in to the express line at the supermarket with more than 10 items?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 367);
		values.put(QUESTIONS_KEY_IDQUESTION, 3527);
		values.put(QUESTIONS_KEY_QTEXT, "Would you let someone go in to a bathroom stall knowing there was no more toilet paper?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 367);
		values.put(QUESTIONS_KEY_IDQUESTION, 3528);
		values.put(QUESTIONS_KEY_QTEXT, "Have you ever hung up on a family member?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 367);
		values.put(QUESTIONS_KEY_IDQUESTION, 3529);
		values.put(QUESTIONS_KEY_QTEXT, "Have you ever broken wind and blamed it on someone else?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		// Questions for idTest 404 testName: The Friendship Test

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 404);
		values.put(QUESTIONS_KEY_IDQUESTION, 3430);
		values.put(QUESTIONS_KEY_QTEXT, "A couple that you're good friends with is having trouble conceiving. They ask you to donate your sperm or egg. Would you do it?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 404);
		values.put(QUESTIONS_KEY_IDQUESTION, 3431);
		values.put(QUESTIONS_KEY_QTEXT, "You are about to leave your house to see your favorite band in concert. Your friend calls you saying they've just been dumped and would like you to spend the evening with them. Do you go comfort your friend and skip the concert?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 404);
		values.put(QUESTIONS_KEY_IDQUESTION, 3432);
		values.put(QUESTIONS_KEY_QTEXT, "You've lent money to a friend in the past and he has never paid you back. The same friend gets in a jam and asks to borrow more money. Do you lend it to him?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 404);
		values.put(QUESTIONS_KEY_IDQUESTION, 3433);
		values.put(QUESTIONS_KEY_QTEXT, "A good friend is getting married in an exotic far away location. You do not have the money to go. Do you:");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 404);
		values.put(QUESTIONS_KEY_IDQUESTION, 3434);
		values.put(QUESTIONS_KEY_QTEXT, "A friend asks you to watch their pet when they go out of town for the weekend. On Saturday night, your partner surprises you with a trip to Las Vegas. Do you:");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 404);
		values.put(QUESTIONS_KEY_IDQUESTION, 3435);
		values.put(QUESTIONS_KEY_QTEXT, "A good friend needs a kidney. Would you donate one of yours?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 404);
		values.put(QUESTIONS_KEY_IDQUESTION, 3436);
		values.put(QUESTIONS_KEY_QTEXT, "Do you usually remember your best friend's birthday?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 404);
		values.put(QUESTIONS_KEY_IDQUESTION, 3437);
		values.put(QUESTIONS_KEY_QTEXT, "Would you lend your friend your favorite outfit without question?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 404);
		values.put(QUESTIONS_KEY_IDQUESTION, 3438);
		values.put(QUESTIONS_KEY_QTEXT, "Would you ever go on a date with someone you weren't attracted to in order to help your friend hook up with someone they were interested in?");
		dbase.insert(TABLE_QUESTIONS, null, values);

		values = new ContentValues();
		values.put(QUESTIONS_KEY_IDQUIZ, 404);
		values.put(QUESTIONS_KEY_IDQUESTION, 3439);
		values.put(QUESTIONS_KEY_QTEXT, "If your friend were stung by a jellyfish, would you pee on their leg to alleviate the pain?");
		dbase.insert(TABLE_QUESTIONS, null, values);

	}

	public void addAnswers() {
		//SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values;

		// Answers for idTest 342 testName: The Roommate Test

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3470);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3470);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3471);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3471);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3472);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3472);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3473);
		values.put(ANSWERS_KEY_ATEXT, "Knock on the door and politely ask them to keep it down.");
		values.put(ANSWERS_KEY_VALUE, "5");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3473);
		values.put(ANSWERS_KEY_ATEXT, "Pull a pillow over your head to drown out the noise.");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 3);
		values.put(ANSWERS_KEY_IDQUESTION, 3473);
		values.put(ANSWERS_KEY_ATEXT, "Storm into the room ranting about how inconsiderate they are.");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3474);
		values.put(ANSWERS_KEY_ATEXT, "Figure you did it once; why not get a little action.");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3474);
		values.put(ANSWERS_KEY_ATEXT, "Tell them 'No Way'.");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 3);
		values.put(ANSWERS_KEY_IDQUESTION, 3474);
		values.put(ANSWERS_KEY_ATEXT, "Fool around a little, but stop before it gets too serious.");
		values.put(ANSWERS_KEY_VALUE, "5");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3475);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3475);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3476);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3476);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3477);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3477);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3478);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3478);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3479);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3479);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);



		// Answers for idTest 356 testName: The Good Employee Test

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3369);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3369);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3370);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3370);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3371);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3371);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3372);
		values.put(ANSWERS_KEY_ATEXT, "Tell your boss you have plans and you'll work late on Monday.");
		values.put(ANSWERS_KEY_VALUE, "5");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3372);
		values.put(ANSWERS_KEY_ATEXT, "Cancel your plans and go into work.");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 3);
		values.put(ANSWERS_KEY_IDQUESTION, 3372);
		values.put(ANSWERS_KEY_ATEXT, "Tell your boss you had a family emergency and go out of town.");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3373);
		values.put(ANSWERS_KEY_ATEXT, "Take the pay cut and stick with your job.");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3373);
		values.put(ANSWERS_KEY_ATEXT, "Leave the company and look for another job.");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 3);
		values.put(ANSWERS_KEY_IDQUESTION, 3373);
		values.put(ANSWERS_KEY_ATEXT, "Attempt to negotiate a smaller pay cut.");
		values.put(ANSWERS_KEY_VALUE, "5");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3374);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3374);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3375);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3375);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3376);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3376);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3377);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3377);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3378);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3378);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);



		// Answers for idTest 366 testName: The Neighbor Test

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3450);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3450);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3451);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3451);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3452);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3452);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3453);
		values.put(ANSWERS_KEY_ATEXT, "Put it aside without watching it.");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3453);
		values.put(ANSWERS_KEY_ATEXT, "Watch it and later tell them you enjoyed their performance.");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 3);
		values.put(ANSWERS_KEY_IDQUESTION, 3453);
		values.put(ANSWERS_KEY_ATEXT, "Watch it but give it back to them without mentioning anything.");
		values.put(ANSWERS_KEY_VALUE, "5");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3454);
		values.put(ANSWERS_KEY_ATEXT, "Ask them politely to turn it down.");
		values.put(ANSWERS_KEY_VALUE, "5");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3454);
		values.put(ANSWERS_KEY_ATEXT, "Ignore it - it is a special occasion.");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 3);
		values.put(ANSWERS_KEY_IDQUESTION, 3454);
		values.put(ANSWERS_KEY_ATEXT, "Call the police and complain.");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3455);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3455);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3456);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3456);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3457);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3457);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3458);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3458);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3459);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3459);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);



		// Answers for idTest 367 testName: The Etiquette Test

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3520);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3520);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3521);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3521);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3522);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3522);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3523);
		values.put(ANSWERS_KEY_ATEXT, "Climb over everyone and go.");
		values.put(ANSWERS_KEY_VALUE, "5");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3523);
		values.put(ANSWERS_KEY_ATEXT, "Wait until intermission.");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 3);
		values.put(ANSWERS_KEY_IDQUESTION, 3523);
		values.put(ANSWERS_KEY_ATEXT, "Wet yourself.");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3524);
		values.put(ANSWERS_KEY_ATEXT, "Decline but send a gift.");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3524);
		values.put(ANSWERS_KEY_ATEXT, "Show up with the hottest date you can find.");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 3);
		values.put(ANSWERS_KEY_IDQUESTION, 3524);
		values.put(ANSWERS_KEY_ATEXT, "Send a sarcastic congratulations card.");
		values.put(ANSWERS_KEY_VALUE, "5");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3525);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3525);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3526);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3526);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3527);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3527);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3528);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3528);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3529);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3529);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);



		// Answers for idTest 404 testName: The Friendship Test

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3430);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3430);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3431);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3431);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3432);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3432);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3433);
		values.put(ANSWERS_KEY_ATEXT, "Charge the vacation on your credit card.");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3433);
		values.put(ANSWERS_KEY_ATEXT, "Explain to your friend why you can't go.");
		values.put(ANSWERS_KEY_VALUE, "5");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 3);
		values.put(ANSWERS_KEY_IDQUESTION, 3433);
		values.put(ANSWERS_KEY_ATEXT, "Make up an excuse for why you can't go.");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3434);
		values.put(ANSWERS_KEY_ATEXT, "Take it to a kennel and pick it up when you get back.");
		values.put(ANSWERS_KEY_VALUE, "5");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3434);
		values.put(ANSWERS_KEY_ATEXT, "Not go to Las Vegas and take care of the pet.");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 3);
		values.put(ANSWERS_KEY_IDQUESTION, 3434);
		values.put(ANSWERS_KEY_ATEXT, "Leave the pet and hope it doesn't die.");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3435);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3435);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3436);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3436);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3437);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3437);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3438);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3438);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 1);
		values.put(ANSWERS_KEY_IDQUESTION, 3439);
		values.put(ANSWERS_KEY_ATEXT, "Yes ");
		values.put(ANSWERS_KEY_VALUE, "10");
		dbase.insert(TABLE_ANSWERS, null, values);

		values = new ContentValues();
		values.put(ANSWERS_KEY_IDANSWER, 2);
		values.put(ANSWERS_KEY_IDQUESTION, 3439);
		values.put(ANSWERS_KEY_ATEXT, "No");
		values.put(ANSWERS_KEY_VALUE, "0");
		dbase.insert(TABLE_ANSWERS, null, values);

	}

	public void addReportText() {
		ContentValues values;
		// Results for idTest 342 testName: The Roommate Test
		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 342);
		values.put(REPORTTEXT_KEY_LEFT, 0);
		values.put(REPORTTEXT_KEY_RIGHT, 40);
		values.put(REPORTTEXT_KEY_TEXT, "Ever see Single White Female? While you're not that scary of a roommate, you certainly could improve your behavior…before it's too late and there isn't a potential flat-mate in sight. What do you do that gave such a low score? Well, according to your answers, you don't generally respect your roomies feelings or possessions. You're not always as honest as you should be, and sometimes cross the line between acceptable and just plain bad-mannered. This might be perfectly fine if your roommate is operating at the same level, and has the same standards of roomie behavior. If not, you could be driving them right out the front door. Take a look at the way you treat them - is that the way you would like to be treated? It might be a good idea to talk to your live-in pal and establish some \"rules\" for apartment etiquette. One person, for example, may not mind if you borrow their underwear while another would faint in disgust - you'll never know until you ask!");
		dbase.insert(TABLE_REPORTTEXT, null, values);

		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 342);
		values.put(REPORTTEXT_KEY_LEFT, 40);
		values.put(REPORTTEXT_KEY_RIGHT, 60);
		values.put(REPORTTEXT_KEY_TEXT, "You appear to be an average Joe on the good roommate scale. You try your best to be a decent, considerate roomie, and sometimes put your own desires aside out of respect for their feelings. On other occasions, however, you sway from the good path. Perhaps you've been overly tempted (by good food, attractive relatives, etc.) and temporarily lost perspective, or you might have a very flexible flat-mate who just doesn't mind when you cross the line. (I know some people, for example, who really don't mind if a friend borrows underwear!) It might be a good idea to talk to your live-in pal and establish some \"rules\" for apartment etiquette. You'll never know what bugs them until you ask!");
		dbase.insert(TABLE_REPORTTEXT, null, values);

		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 342);
		values.put(REPORTTEXT_KEY_LEFT, 60);
		values.put(REPORTTEXT_KEY_RIGHT, 101);
		values.put(REPORTTEXT_KEY_TEXT, "Congratulations, you appear to be a wonderful roommate! You realize that there is a certain level of decency that goes along with building strong resident relations, and generally respect your roomie's boundaries. In fact, you know that the way you treat your flat-mates will reflect back on you - what goes around, comes around. As a result, you consider their needs, try to gauge their reaction to certain behavior and act accordingly. They certainly appreciate this considerate attitude, and hopefully reciprocate. Either way, it's always a good idea to talk to those that share your humble abode to establish some house rules. Who knows, maybe they wouldn't mind if you borrowed their undies from time to time!");
		dbase.insert(TABLE_REPORTTEXT, null, values);

		// Results for idTest 356 testName: The Good Employee Test
		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 356);
		values.put(REPORTTEXT_KEY_LEFT, 0);
		values.put(REPORTTEXT_KEY_RIGHT, 25);
		values.put(REPORTTEXT_KEY_TEXT, "Remember The Shining? \"All work and no play makes Jack a dull boy\"? Well, you have something in common with Jack, and it's not being a raving lunatic. Work is just not the focus of your life, at least not in your present job. Your own pleasure rules over the good of the company, and you rarely hesitate to use less-than-honest tactics to get what you want - longer vacations, freebies from the office, and whatever else you can pilfer. This may be simply a symptom of extreme unhappiness in your present position, or a sort of \"acting out\" against what you see as unpleasant working conditions or an unfair boss. Whatever the root of your behavior, you may want to consider either changing companies or toning down the sneaky tricks…or you could find yourself without any job at all.");
		dbase.insert(TABLE_REPORTTEXT, null, values);

		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 356);
		values.put(REPORTTEXT_KEY_LEFT, 25);
		values.put(REPORTTEXT_KEY_RIGHT, 75);
		values.put(REPORTTEXT_KEY_TEXT, "You seem able to maintain a healthy balance between work and your personal life. Your job is fairly important to you, but it's not the focus of your existence. You know when you need to de-stress, and avoid pushing yourself to the brink of burnout. While your employer certainly values your well-intentioned work ethic, you may want to take a look at your answers to some of the situations on the test. Do you sometimes resort to sneaky tactics to get things you want, whether it's longer vacation time or free stamps? Have you, for example, downloaded inappropriate material at work or tied one on at a workday lunch? If so, you should give second thought to any behavior that could undermine your reputation or get you in serious trouble. It just isn't worth the risk.");
		dbase.insert(TABLE_REPORTTEXT, null, values);

		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 356);
		values.put(REPORTTEXT_KEY_LEFT, 75);
		values.put(REPORTTEXT_KEY_RIGHT, 101);
		values.put(REPORTTEXT_KEY_TEXT, "One thing that's for certain: no one will ever accuse you of being a slacker. When your boss snaps his fingers, you jump through hoops. Your job is a top priority in your life, and you give it your all. You may be trying to work your way to the top of the ladder, or simply love your job. Your sense of personal identity is likely strongly connected to your job, so when you perform well it adds to your sense of self-worth. But, before you get all high on your stellar work ethic, consider the possible negative side of such hard-core dedication. Do you give up things that are important to you, like special family occasions or time with close friends, for the sake of the company? Do you overwork yourself to the detriment of your own health and emotional well-being? While commitment to your job is part of the formula to success, so is balance. Sacrificing too much for your job can actually backfire, resulting in resentment or even burnout. The more fulfilled you are in your personal life, the more creative energy you'll bring to your work.");
		dbase.insert(TABLE_REPORTTEXT, null, values);

		// Results for idTest 366 testName: The Neighbor Test
		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 366);
		values.put(REPORTTEXT_KEY_LEFT, 0);
		values.put(REPORTTEXT_KEY_RIGHT, 40);
		values.put(REPORTTEXT_KEY_TEXT, "When it comes to neighborly relations, you're a bit out to lunch. You generally neglect to make any effort to develop a friendly rapport with your neighbors, and may actually even stoop to less-than-kind tactics to get your way (i.e. calling the police, \"borrowing\" things without asking, snooping into their private business).  While you're under no obligation to be best buddies with your neighbors, you should certainly treat them with the respect - after all, you have to live next door to them and suffer the consequences of strained relations! (Ask anyone who's rubbed a neighbor the wrong way and you'll surely uncover some horror stories…these things often escalate into outright wars.) Treat your neighbor as you'd like to be treated yourself and it should be smooth sailing.");
		dbase.insert(TABLE_REPORTTEXT, null, values);

		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 366);
		values.put(REPORTTEXT_KEY_LEFT, 40);
		values.put(REPORTTEXT_KEY_RIGHT, 60);
		values.put(REPORTTEXT_KEY_TEXT, "When it comes to your neighbors, you seem to be neither friend nor foe. You generally treat your neighbors with the same level of respect and consideration that you expect in return. While you try to maintain a cordial level of relations, you might be tempted from time to time to swerve off the good-neighbor path. Perhaps you occasionally peek into their private lives, keep certain awkward truths hidden or even seek revenge for their minor offenses. Whichever of these you divulge in, you should seriously consider the possible consequences before you take that leap. (Ask anyone who's rubbed a neighbor the wrong way and you'll surely uncover some horror stories... these things often escalate into outright wars.)");
		dbase.insert(TABLE_REPORTTEXT, null, values);

		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 366);
		values.put(REPORTTEXT_KEY_LEFT, 60);
		values.put(REPORTTEXT_KEY_RIGHT, 101);
		values.put(REPORTTEXT_KEY_TEXT, "Move over Ned Flanders... you're one amazing neighbor! According to your answers on the test, you're generally kind, decent and generous with those lucky enough to live in your vicinity. You go out of your way to build a rapport with your neighbors, and treat them with the utmost consideration. You respect their privacy, level with them even about awkward situations, and avoid getting entangled in their personal lives. Your neighbors surely appreciate your giving and thoughtful nature, and hopefully reciprocate!");
		dbase.insert(TABLE_REPORTTEXT, null, values);

		// Results for idTest 367 testName: The Etiquette Test
		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 367);
		values.put(REPORTTEXT_KEY_LEFT, 0);
		values.put(REPORTTEXT_KEY_RIGHT, 30);
		values.put(REPORTTEXT_KEY_TEXT, "Were you, by any chance, raised in a barn? You could certainly use a crash course in courtesy! Either you truly don't realize how rude your behavior is (and, in some cases, obnoxious), or you just don't give a rat's behind.  Whatever the case, it's likely that you are offending others with your ignorance - or blatant disregard - of basic social etiquette. While some of the situations on this test (peeing your pants at the opera) were a bit severe, others (like snooping in someone's bathroom cabinet) left more room for error. (We all give in to temptation from time to time, even if it's not so nice...) If you scored this low, however, you probably gave the wrong answer to at least a few of the \"unacceptable\" questions. Maybe you should read some Ann Landers before people realize how uncouth you are and run for the hills.");
		dbase.insert(TABLE_REPORTTEXT, null, values);

		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 367);
		values.put(REPORTTEXT_KEY_LEFT, 30);
		values.put(REPORTTEXT_KEY_RIGHT, 70);
		values.put(REPORTTEXT_KEY_TEXT, "While you're certainly not the Queen Mother, you do manage to follow society's rules of decorum - when it suits you.  You know that etiquette exists to provide guidelines, but that nothing is set in stone. The rules of decorum, after all, evolve with time (how many men you know would lay their jacket across a mud puddle for a lady friend?) and sometimes you have to simply throw them out the window. You bend - and sometimes break - the rules depending on the situation. You may, for example, give in to the occasional urge to snoop in a host's medicine cabinet or sneak into the express line with 15 items when you're in a hurry. While none of these social faux pas are too horribly offensive, be sure not to push the envelope too far.  Some rules are not meant to be broken!");
		dbase.insert(TABLE_REPORTTEXT, null, values);

		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 367);
		values.put(REPORTTEXT_KEY_LEFT, 70);
		values.put(REPORTTEXT_KEY_RIGHT, 101);
		values.put(REPORTTEXT_KEY_TEXT, "Are you, by any chance, a member of the Royal Family? You've got impeccable manners, and a standard of decorum high above that of the average Joe-blow. You wouldn't dream of behaving in a way that may offend or disgust others, and nearly always put decent social conduct above your own discomfort. This is a gift that certainly gives you the advantage in many situations - no one would hesitate to bring you home to meet the parents, for example. One warning though; the rules of decorum evolve with time (how many men you know would lay their jacket across a mud puddle for a lady friend?) and sometimes you can simply throw them out the window. No one expects you, for example, to buy your low-down ex a lavish wedding present. You have every right to be proud of your super-polite behavior, but don't get so hung up on etiquette that others take advantage of your good intentions.");
		dbase.insert(TABLE_REPORTTEXT, null, values);

		// Results for idTest 404 testName: The Friendship Test
		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 404);
		values.put(REPORTTEXT_KEY_LEFT, 0);
		values.put(REPORTTEXT_KEY_RIGHT, 30);
		values.put(REPORTTEXT_KEY_TEXT, "When it comes to friendship, you seem to have missed the boat. You are certainly not the most dedicated or giving of pals. In fact, others could easily accuse you of being a fair weather friend, the type who comes and goes on a whim. When amigos need your help, you are more likely to bolt for the door than pitch in, and the idea of <I>offering</I> to lend a hand is foreign to you. You generally have low expectations for camaraderie, which may stem from a variety of sources: bad experiences that have left you bitter, an untrusting nature, lack of what you consider \"true friends\", or simply a self-involved attitude. Whatever the root, your attitudes are preventing you from experiencing the value of real, lasting friendship. You don't have to suddenly dedicate all your time and attention to pals, but putting your own desires on hold from time to time will show them that they mean something to you. Things like remembering birthdays or helping someone move may seem minor, but they have can have a huge impact on the strength of your relationships.  Try to be the kind of friend you'd like to have!");
		dbase.insert(TABLE_REPORTTEXT, null, values);

		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 404);
		values.put(REPORTTEXT_KEY_LEFT, 30);
		values.put(REPORTTEXT_KEY_RIGHT, 70);
		values.put(REPORTTEXT_KEY_TEXT, "You rate a happy medium on the friendship scale. You are a regular good pal, someone friends can turn to turn to in times of need - whether emotional, financial, or otherwise. You are giving of your time and are generally thoughtful. While your heart is in the right place, however, you might sometimes slip up and forget special occasions or do slightly \"selfish\" things. This does not mean you are malicious or that you intend to hurt anyone - only that you are human. In fact, your approach to camaraderie is healthy and balanced; you realize that you don't always need to place your friends at the very top of your priority list, especially when it is to your own detriment. You know that true pals will respect, and even appreciate, your ability to say \"no\" or put yourself first from time to time. The most important thing is that you keep the lines of communication open and build the level of trust and respect necessary for a lasting bond.");
		dbase.insert(TABLE_REPORTTEXT, null, values);

		values = new ContentValues();
		values.put(REPORTTEXT_KEY_IDQUIZ, 404);
		values.put(REPORTTEXT_KEY_LEFT, 70);
		values.put(REPORTTEXT_KEY_RIGHT, 101);
		values.put(REPORTTEXT_KEY_TEXT, "Camaraderie is your specialty, and you tend to keep your pals at the very top of your priority list.   When a friend is in need, whether for emotional support, a financial boost, or a simple helping hand, they can count on you to come through. You give the very best of yourself, and will actually put your own desires (and sometimes needs) on hold for the sake of a pal's happiness.  Your friends surely appreciate your self-sacrificing nature and probably sing your praises. While it's wonderful to show such intense dedication, however, there could be some negative consequences to your high level of commitment; do you ever feel, for example, that you are giving much more than others give in return, or that you are being taken advantage of? While most people see the value of such a fab friend, there are also folks who will milk your generosity for all its worth - not always out of maliciousness, but sometimes because it's just too easy.  There is also the danger of developing bitter feelings when you give up your own wants for those of others - a true friend, in fact, will not expect you to drop everything for them in every situation, and will surely respect your decision to put yourself first from time to time.");
		dbase.insert(TABLE_REPORTTEXT, null, values);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
		// Create tables again
		onCreate(db);
	}

	public List<Question> getAllQuestions(int quizzId) {
		List<Question> questionsList = new ArrayList<Question>();
		String selectQuestionAnswers;
		Cursor cursor2;
		// Select All Query
		String selectQuizeQuestions = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE " + QUESTIONS_KEY_IDQUIZ + " = " + quizzId;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuizeQuestions, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(2));
				selectQuestionAnswers = "SELECT * FROM " + TABLE_ANSWERS + " WHERE " + ANSWERS_KEY_IDQUESTION + " = " + cursor.getInt(0);
				cursor2 = dbase.rawQuery(selectQuestionAnswers, null);
				if(cursor2.moveToFirst()){
					quest.setOPTA(cursor2.getString(2));
					quest.setOptValuesArray(0, cursor2.getInt(3));
					quest.setOptaValue(cursor2.getInt(3));
				}
				if(cursor2.moveToNext()){
					quest.setOPTB(cursor2.getString(2));
					quest.setOptbValue(cursor2.getInt(3));
				}
				if(cursor2.moveToNext()){
					quest.setOPTC(cursor2.getString(2));
					quest.setOptcValue(cursor2.getInt(3));
				}
				else{
					quest.setOPTC("NONE");
				}
				questionsList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return questionsList;
	}

	public int rowcount(int quizzId)
	{
		int rowsnum=0;
		String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE " + QUESTIONS_KEY_IDQUIZ + " = " + quizzId;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		rowsnum=cursor.getCount();
		return rowsnum;
	}

	public String getReportText(int quizzId, int score)
	{
		String reportText="";
		String selectQuery = "SELECT text FROM " + TABLE_REPORTTEXT + " WHERE " + REPORTTEXT_KEY_IDQUIZ + " = " + quizzId + " AND " + REPORTTEXT_KEY_LEFT + " <= " + score + " AND " + REPORTTEXT_KEY_RIGHT + " > " + score;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			reportText=cursor.getString(0);
		}
		return reportText;
	}

	public void insertReport(int quizzId, int historyId) {
		ContentValues values;
		SQLiteDatabase db = this.getWritableDatabase();
		values = new ContentValues();
		values.put(USERSREPORTS_KEY_IDQUIZ, quizzId);
		values.put(USERSREPORTS_KEY_IDHISTORY, historyId);
		db.insert(TABLE_USERSREPORTS, null, values);
	}

	public int getNextIdHistory(int quizzId)
	{
		int idHistory = 1;
		String selectQuery = "SELECT max("+USERSREPORTS_KEY_IDHISTORY+") as maxIdHistory FROM " + TABLE_USERSREPORTS + " WHERE " + USERSREPORTS_KEY_IDQUIZ + " = " + quizzId;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			idHistory=cursor.getInt(0);
			idHistory++;
		}
		return idHistory;
	}

	public int getUnfinishedIdHistory(int quizzId)
	{
		int idHistory = 0;
		String selectQuery = "SELECT "+USERSREPORTS_KEY_IDHISTORY+" FROM " + TABLE_USERSREPORTS + " WHERE " + USERSREPORTS_KEY_IDQUIZ + " = " + quizzId + " AND " + USERSREPORTS_KEY_FINISHED + " = 0";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			idHistory=cursor.getInt(0);
		}
		return idHistory;
	}

	public int getNextIndexQuestion(int quizzId, int historyId)
	{
		int indexQuestion = 0;
		String selectQuery = "SELECT "+USERSREPORTS_KEY_INDEXQUESTION+" FROM " + TABLE_USERSREPORTS + " WHERE " + USERSREPORTS_KEY_IDQUIZ + " = " + quizzId + " AND " + USERSREPORTS_KEY_IDHISTORY + " = " + historyId;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			indexQuestion=cursor.getInt(0);
		}
		return indexQuestion;
	}

	public int getResult(int quizzId, int historyId)
	{
		int score = 0;
		String selectQuery = "SELECT "+USERSREPORTS_KEY_RESULT+" FROM " + TABLE_USERSREPORTS + " WHERE " + USERSREPORTS_KEY_IDQUIZ + " = " + quizzId + " AND " + USERSREPORTS_KEY_IDHISTORY + " = " + historyId;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			score=cursor.getInt(0);
		}
		return score;
	}

	public String getQuizName(int quizzId)
	{
		String quizName="";
		String selectQuery = "SELECT "+QUIZZES_KEY_NAME+" FROM " + TABLE_QUIZZES + " WHERE " + QUIZZES_KEY_IDQUIZ + " = " + quizzId;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			quizName=cursor.getString(0);
		}
		return quizName;
	}

	public String getQuizDescription(int quizzId)
	{
		String quizDescription="";
		String selectQuery = "SELECT "+QUIZZES_KEY_DESC+" FROM " + TABLE_QUIZZES + " WHERE " + QUIZZES_KEY_IDQUIZ + " = " + quizzId;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			quizDescription=cursor.getString(0);
		}
		return quizDescription;
	}

	public void updateReport(int quizzId, int historyId, int indexQuestion, int finished, int score) {
		ContentValues values;
		SQLiteDatabase db = this.getWritableDatabase();
		values = new ContentValues();
		values.put(USERSREPORTS_KEY_INDEXQUESTION, indexQuestion);
		values.put(USERSREPORTS_KEY_FINISHED, finished);
		values.put(USERSREPORTS_KEY_RESULT, score);
		values.put(USERSREPORTS_KEY_DATETIME, java.lang.System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.US);
		Date resultdate = new Date(java.lang.System.currentTimeMillis());
		values.put(USERSREPORTS_KEY_REPORTNAME, sdf.format(resultdate));
		db.update(TABLE_USERSREPORTS, values, USERSREPORTS_KEY_IDQUIZ + " = " + quizzId + " AND " + USERSREPORTS_KEY_IDHISTORY + " = " + historyId, null);
	}

	public List<String> getQuizzHistory(int quizzId) {
		int numberOfReportToKeep = NUMBER_OF_REPORTS_TO_KEEP;
		List<String> list = new ArrayList<String>();
		String selectQuizeQuestions = "SELECT * FROM " + TABLE_USERSREPORTS + " WHERE " + USERSREPORTS_KEY_IDQUIZ + " = " + quizzId + " AND finished = 1 ORDER BY idHistory DESC LIMIT 0, " + numberOfReportToKeep;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuizeQuestions, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(6));
			} while (cursor.moveToNext());
		}
		else{
			list.add("No results saved");
		}
		return list;
	}

	public int getQuizIdHistoryByName(int quizzId, String reportName)
	{
		int idHistory = 0;
		String selectQuery = "SELECT "+USERSREPORTS_KEY_IDHISTORY+" FROM " + TABLE_USERSREPORTS + " WHERE " + QUIZZES_KEY_IDQUIZ + " = " + quizzId + " AND " + USERSREPORTS_KEY_REPORTNAME + " = '" + reportName + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			idHistory=cursor.getInt(0);
		}
		return idHistory;
	}

	public void cleanUpHistory(int quizzId) {
		int numberOfReportToKeep = NUMBER_OF_REPORTS_TO_KEEP;
		String selectQuizeHistoryToDelete = "SELECT " + USERSREPORTS_KEY_IDHISTORY + " FROM " + TABLE_USERSREPORTS + " WHERE " + USERSREPORTS_KEY_IDQUIZ + " = " + quizzId + " AND finished = 1 ORDER BY idHistory DESC LIMIT "+ numberOfReportToKeep +", 10";
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuizeHistoryToDelete, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				int idHistory=cursor.getInt(0);
				String deleteExtraQuizeHistory = "DELETE FROM " + TABLE_USERSREPORTS + " WHERE " + USERSREPORTS_KEY_IDQUIZ + " = " + quizzId + " AND finished = 1 AND " + USERSREPORTS_KEY_IDHISTORY + " = " + idHistory;
				db.execSQL(deleteExtraQuizeHistory);
			} while (cursor.moveToNext());
		}
	}


}
