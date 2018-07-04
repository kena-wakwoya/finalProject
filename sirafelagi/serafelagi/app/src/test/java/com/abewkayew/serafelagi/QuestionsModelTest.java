package com.abewkayew.serafelagi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class QuestionsModelTest {
    QuestionsModel qm = new QuestionsModel("what is ur name?","A,kena","B,milkias","C,abe","D,kal",3);

    @Before
    public void setup() throws  Exception{
        qm.setQuestion("what is ur favorite food?");
        qm.setOption1("A,shiro");
        qm.setOption2("B,rice");
        qm.setOption3("C,pasta");
        qm.setOption4("D,enjera");

    }

    @Test
    public void getQuestion() {
        String q  = qm.getQuestion();
        assertEquals("what is ur favorite food?",q);
    }

    @Test
    public void setQuestion() {
        qm.setQuestion("what is ur favorite game?");
        String getq = qm.getQuestion();
        assertEquals("what is ur favorite game?",getq);
    }

    @Test
    public void getOption1() {
        String geto  = qm.getOption1();
        assertEquals("A,shiro",geto);
    }

    @Test
    public void setOption1() {
        qm.setOption1("A,football");
        String getop1 = qm.getOption1();
        assertEquals("A,football",getop1);
    }

    @Test
    public void getOption2() {
        String getop2  = qm.getOption2();
        assertEquals("B,rice",getop2);

    }

    @Test
    public void setOption2() {
        qm.setOption2("B,volleyball");
        String getop2 = qm.getOption2();
        assertEquals("B,volleyball",getop2);
    }

    @Test
    public void getOption3() {
        String getop3  = qm.getOption3();
        assertEquals("C,pasta",getop3);

    }

    @Test
    public void setOption3() {
        qm.setOption3("C,riding");
        String getop3 = qm.getOption3();
        assertEquals("C,riding",getop3);
    }


    @After
    public void tearDown() throws  Exception{
        qm = null;

    }

}