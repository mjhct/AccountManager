package mjhct.accountmanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import java.util.Base64;

/**
 * 秘钥配置类
 */
@Component
@ConfigurationProperties(prefix = "am")
@Validated
public class AccountManagerConfig {

    /**
     * 加解密秘钥
     */
    @NotEmpty(message = "请配置密钥")
    private String securityKey;

    /**
     * 随机密码位数，默认10
     */
    private int passwordDigits = 10;

    /**
     * 秘钥二进制字节数组
     */
    private byte[] securityKeyByteArray;

    @PostConstruct
    private void initSecurityKeyByteArray() {
        securityKeyByteArray = Base64.getDecoder().decode(securityKey);
    }

    public byte[] getSecurityKeyByteArray() {
        return securityKeyByteArray;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public int getPasswordDigits() {
        return passwordDigits;
    }

    public void setPasswordDigits(int passwordDigits) {
        this.passwordDigits = passwordDigits;
    }
}
