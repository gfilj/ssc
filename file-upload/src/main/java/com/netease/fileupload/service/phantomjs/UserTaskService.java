package com.netease.fileupload.service.phantomjs;

import com.netease.fileupload.model.User;
import com.netease.fileupload.util.Constant;
import com.netease.fileupload.util.LogUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by goforit on 2017/9/6.
 */
@Service
public class UserTaskService {

    private Logger logger = LogUtil.getLogger(getClass());

    private BlockingQueue<User> blockingQueue = new LinkedBlockingDeque<>();

    public User waitNewUser() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            logger.error(Constant.ERRORStr("waitNewUser - interrupted, error"), e);
            return null;
        }
    }

    public void signalNewUser(User user) {

        try {
            blockingQueue.put(user);
        } catch (InterruptedException e) {
            logger.error(Constant.ERRORStr("signalNewUser - interrupted, error"), e);
        }
    }

}
