package drintau.accountmanager.gui;

import lombok.Data;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 应用GUI容器：单例
 */
@Data
public class AMUIContext {

    private AMUIContext(){}
    private static final AMUIContext instance = new AMUIContext();
    public static AMUIContext getInstance(){
        return instance;
    }

    // 启动参数
    private String[] args;

    // webserver实例
    private ConfigurableApplicationContext webServerContext;

}