package com.project;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by goforit on 2017/11/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootStrap.class)
@WebAppConfiguration
public class BaseTest {

}
