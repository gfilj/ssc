package com.project.service.message;

import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.service.message.util.MessageUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by goforit on 2017/10/29.
 */
@Service
public class MessageService {

    public Map<String,String> parseXml(HttpServletRequest request) throws BusinessException{
        try {
            return MessageUtil.parseXml(request);
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.XML_PARSE_CAUSE,e);
        }

    }
}
