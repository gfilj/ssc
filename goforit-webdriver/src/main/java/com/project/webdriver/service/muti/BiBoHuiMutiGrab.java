package com.project.webdriver.service.muti;

import com.project.webdriver.model.BiBoHuiMitiBean;
import com.project.webdriver.service.BiBoHuiGrab;
import com.project.webdriver.util.ExecutorPoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by goforit on 2017/9/4.
 */
@Service
public class BiBoHuiMutiGrab {

    @Autowired
    private BiBoHuiGrab biBoHuiGrab;

    private ThreadPoolExecutor threadPool = ExecutorPoll.creatNamedFixedPools("BiBoHuiMutiGrab", 500);
    private Map<String, BiBoHuiMitiBean> biBoHuiMitiBeanMap = new HashMap<>();

    public void start(BiBoHuiMitiBean biBoHuiMitiBean) {
        if(biBoHuiMitiBeanMap.containsKey(biBoHuiMitiBean.getUserName())) {
            stop(biBoHuiMitiBean);
        }
        biBoHuiMitiBeanMap.put(biBoHuiMitiBean.getUserName(), biBoHuiMitiBean);
        threadPool.execute(() -> biBoHuiGrab.execute(biBoHuiMitiBean));
    }

    public void stop(BiBoHuiMitiBean biBoHuiMitiBean) {
        biBoHuiMitiBeanMap.get(biBoHuiMitiBean.getUserName()).setShutdown(true);
    }


}
