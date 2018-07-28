package com.project.service.weixin.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.common.exception.BusinessException;
import com.project.common.util.SwitchUtil;
import com.project.model.sql.User;
import com.project.model.vo.Page;
import com.project.model.vo.UserSearchVO;
import com.project.service.user.impl.UserService;
import com.project.webmagic.model.OrderDetailDB;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.project.common.util.LogUtil.getLogger;
import static com.project.common.util.LogUtil.logstr;

/**
 * 微信用户服务
 * Created by goforit on 2017/12/3.
 */
@Service
public class WechatUserService {

    @Autowired
    private UserService userService;
    private Log logger = getLogger(getClass());

    public PageInfo<User> list(Page page) throws BusinessException {
        String funcname = "微信用户列表";
        PageHelper.offsetPage(page.getOffset(), page.getSize());
        logger.info(logstr(funcname, "偏移量", page.getOffset(), "页面大小", page.getSize()));
        List<User> users = userService.selectList();
        int i = 1;
        for (User user : users) {
            user.setId(page.getOffset() + i);
            i++;
        }
        return new PageInfo<User>(users);
    }

    public User get(String openid) throws BusinessException {
        String funcname = "搜索用户";
        logger.info(logstr(funcname, "openId", openid));
        User user = userService.selectOne(openid);
        user.setId(1);
        return user;
    }

    public int update(User user) throws BusinessException {
        String funcname = "更新用户";
        logger.info(logstr(funcname, "user", user.getNickname()));
        return userService.update(user);

    }

    public PageInfo<User> search(UserSearchVO searchVO) throws BusinessException {
        String funcname = "微信用户模糊查询";
        Map<String, Object> map = SwitchUtil.objectToMap(searchVO);
        map.forEach((k, v) -> {
            map.put(k, "%" + v + "%");
        });

        logger.info(logstr(funcname, "map", map));
        List<User> users = userService.search(map);
        int i = 1;
        for (User user : users) {
            user.setId(i);
            i++;
        }
        return new PageInfo<User>(users);
    }


    public String markDelete(List<User> userList) throws BusinessException {
        String funcname = "标记删除";
        final List<String> nameList = new ArrayList<>();

        userList.forEach(item -> {
            nameList.add(item.getNickname());
        });
        return nameList + "已经被合并!";
    }

    public PageInfo<OrderDetailDB> getOrderByOpenid(String openid) throws BusinessException {
        String funcname = "更新用户";
        logger.info(logstr(funcname, "openid", openid));
        List<OrderDetailDB>orders = userService.getOrderByOpenid(openid);
        return new PageInfo<OrderDetailDB>(orders);
    }

}

