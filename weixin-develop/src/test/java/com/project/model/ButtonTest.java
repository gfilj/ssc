package com.project.model;

import com.project.BaseTest;
import com.project.model.menu.Button;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by goforit on 2017/11/25.
 */
public class ButtonTest extends BaseTest {
    @Autowired
    private Button button;

    @Test
    public void initButtonTest(){
        System.out.println(button.getButton());
    }
}

