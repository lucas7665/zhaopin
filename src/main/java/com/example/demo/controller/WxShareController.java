package com.example.demo.controller;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/wx")
@Slf4j
public class WxShareController {

    @Autowired
    private WxMpService wxMpService;

    @Value("${share.title}")
    private String shareTitle;
    
    @Value("${share.description}")
    private String shareDescription;
    
    @Value("${share.image}")
    private String shareImage;

    @GetMapping("/share-config")
    public Map<String, String> getShareConfig(@RequestParam String url) {
        try {
            log.info("获取分享配置, URL: {}", url);
            
            // 确保编码正确
            String encodedTitle = new String(shareTitle.getBytes("ISO-8859-1"), "UTF-8");
            String encodedDesc = new String(shareDescription.getBytes("ISO-8859-1"), "UTF-8");
            
            log.info("编码后的配置 - 标题: {}, 描述: {}, 图片: {}", encodedTitle, encodedDesc, shareImage);
            
            WxJsapiSignature jsapiSignature = wxMpService.createJsapiSignature(url);
            
            Map<String, String> config = new HashMap<>();
            config.put("appId", jsapiSignature.getAppId());
            config.put("timestamp", String.valueOf(jsapiSignature.getTimestamp()));
            config.put("nonceStr", jsapiSignature.getNonceStr());
            config.put("signature", jsapiSignature.getSignature());
            config.put("shareTitle", encodedTitle);
            config.put("shareDescription", encodedDesc);
            config.put("shareImage", shareImage);
            
            log.info("完整的分享配置: {}", config);
            return config;
        } catch (Exception e) {
            log.error("生成分享配置失败", e);
            return new HashMap<>();
        }
    }
} 