package com.abewkayew.serafelagi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
//@RunWith(MockitoJUnitRunner.class)
public class sera_datasTest {
     sera_datas sera_datas = new sera_datas(1,"sisco","Instructor","this is to announce u ...","mon","firstDe","mas");

    @Before
    public void setUp() throws Exception {
        sera_datas.setCompany_name("AmboUniv");
        sera_datas.setImage_resource(2);
        sera_datas.setJob_date("mon");
        sera_datas.setJob_degree_level("masters");
        sera_datas.setJob_desc("instructor");
        sera_datas.setJob_specs("teaching");
        sera_datas.setJob_title("lecturor");
    }

    @Test
    public void testgetJob_specs() {
        String jobspecs = sera_datas.getJob_specs();
        assertEquals("teaching",jobspecs);
    }

    @Test
    public void testsetJob_specs() {
        sera_datas.setJob_specs("janitor");
        String seted = sera_datas.getJob_specs();
        assertEquals("janitor",seted);
    }

    @Test
    public void testgetJob_degree_level() {
        String degree = sera_datas.getJob_degree_level();
        assertEquals("masters",degree);
    }

    @Test
    public void testsetJob_degree_level() {
        sera_datas.setJob_degree_level("phd");
        String comparewith = sera_datas.getJob_degree_level();
        assertEquals("phd",comparewith);

    }

    @Test
    public void testgetJob_desc() {
        String desc = sera_datas.getJob_desc();
        assertEquals("instructor",desc);
    }

    @Test
    public void testgetImage_resource() {
        int image = sera_datas.getImage_resource();
        assertEquals(2,image);
    }

    @Test
    public void testgetCompany_name() {
        String compName = sera_datas.getCompany_name();
        assertEquals("AmboUniv",compName);
    }

    @Test
    public void testgetJob_title() {
        String title = sera_datas.getJob_title();
        assertEquals("lecturor",title);
    }
}