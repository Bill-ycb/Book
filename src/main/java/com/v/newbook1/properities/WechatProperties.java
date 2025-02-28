package com.v.newbook1.properities;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "vbook.wechat")
@Data
public class WechatProperties {
    private String appId;
    private String secret;
    private String code;//用户凭证
    //微信支付相关配置，本项目不做支付功能，不予配置
    /**private String mchid;
    private String privateKeyFilePath; //商户私钥文件
    private String apiV3Key; //证书解密的密钥
    private String weChatPayCertFilePath; //平台证书
    private String notifyUrl; //支付成功的回调地址
    private String refundNotifyUrl; //退款成功的回调地址
    private String mchSerialNo; //商户API证书的证书序列号
**/
}
