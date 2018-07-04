package com.abewkayew.serafelagi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.abewkayew.serafelagi.QuestionConstants.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abebe Kayimo on 5/2/2018.
 */

public class ExamDbHelper extends SQLiteOpenHelper {
//   constant values initialization...
    private static final String DATABASE_NAME = "exams.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public ExamDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        final String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + QuestionsTable.TABLE_NAME
                + " ( " + QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NUMBER + " INTEGER" + ")";
//
        sqLiteDatabase.execSQL(CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        This will update the database...
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void fillQuestionsTable() {
        for(int i = 1; i < 100; i++ ){
            String question = "What is the question " + i + " is about?";
            String option1 = "It is about " + i;
            String option2 = i + "may be about of bound";
            String option3 = "I have no idea what " + i + " is about.";
            String option4 = i + " is not the correct answer.";
            int correct_answer = 2;
            QuestionsModel questionDatas = new QuestionsModel(question, option1, option2, option3,
                    option4, correct_answer);
            addQuestions(questionDatas);
        }
//        QuestionsModel question1 = new QuestionsModel("A is correct", "A", "B",
//                "C", "D", 1);
//        addQuestions(question1);
//        QuestionsModel question2 = new QuestionsModel("B is correct", "A", "B",
//                "C", "D", 1);
//        addQuestions(question2);
//        QuestionsModel question3 = new QuestionsModel("C is correct", "A", "B",
//                "C", "D", 1);
//        addQuestions(question3);
//        QuestionsModel question4 = new QuestionsModel("D is correct", "A", "B",
//                "C", "D", 1);
//        addQuestions(question4);
//        QuestionsModel question5 = new QuestionsModel("A is correct again..", "A", "B",
//                "C", "D", 1);
//        addQuestions(question5);
//        QuestionsModel question6 = new QuestionsModel("B is correct again..", "A", "B",
//                "C", "D", 1);
//        addQuestions(question6);

    }
    private void addQuestions(QuestionsModel questionsModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuestionsTable.COLUMN_QUESTION, questionsModel.getQuestion());
        contentValues.put(QuestionsTable.COLUMN_OPTION1, questionsModel.getOption1());
        contentValues.put(QuestionsTable.COLUMN_OPTION2, questionsModel.getOption2());
        contentValues.put(QuestionsTable.COLUMN_OPTION3, questionsModel.getOption3());
        contentValues.put(QuestionsTable.COLUMN_OPTION4, questionsModel.getOption4());
        contentValues.put(QuestionsTable.COLUMN_ANSWER_NUMBER, questionsModel.getAnswerNumber());
//        Now insert all the datas to the local database...
        db.insert(QuestionsTable.TABLE_NAME, null, contentValues);

    }
//    retrieve SQLITE data...
    public List<QuestionsModel> getAllQuestions(){
        List<QuestionsModel> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME,null);
        if(cr.moveToFirst()){
            do{
                QuestionsModel question = new QuestionsModel();
                question.setQuestion(cr.getString(cr.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(cr.getString(cr.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(cr.getString(cr.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(cr.getString(cr.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(cr.getString(cr.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));

                question.setAnswerNumber(cr.getInt(cr.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUMBER)));
                questionList.add(question);

            }while(cr.moveToNext());
        }
        return questionList;
    }

}