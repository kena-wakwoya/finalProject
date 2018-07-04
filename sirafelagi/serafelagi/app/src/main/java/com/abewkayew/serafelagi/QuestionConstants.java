package com.abewkayew.serafelagi;

import android.provider.BaseColumns;

/**
 * Created by Abebe kayimo on 5/2/2018.
 */

public final class QuestionConstants {

//  empty constructor...
    private QuestionConstants(){}
    public static class QuestionsTable implements BaseColumns{
        public static final String TABLE_NAME = "questions_table";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NUMBER = "answernumber";

    }

}
