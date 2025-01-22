package com.example.demo.controller;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/wx")
public class WxShareController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/share-config")
    public Map<String, String> getShareConfig(@RequestParam String url) {
        try {
            WxJsapiSignature signature = wxMpService.createJsapiSignature(url);
            Map<String, String> config = new HashMap<>();
            config.put("appId", signature.getAppId());
            config.put("timestamp", String.valueOf(signature.getTimestamp()));
            config.put("nonceStr", signature.getNonceStr());
            config.put("signature", signature.getSignature());
            return config;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
} 