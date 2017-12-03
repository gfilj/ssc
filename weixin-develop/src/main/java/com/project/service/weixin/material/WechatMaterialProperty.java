package com.project.service.weixin.material;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by goforit on 2017/11/27.
 */
@Component
@ConfigurationProperties(prefix = "WechatMaterialProperty")
public class WechatMaterialProperty {

    private String batchget_material;

    public String getBatchget_material() {
        return batchget_material;
    }

    public void setBatchget_material(String batchget_material) {
        this.batchget_material = batchget_material;
    }
}
