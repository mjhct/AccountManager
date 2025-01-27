package drintau.accountmanager.webserver.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyAccountUpdateReqVO {

    @JsonProperty("id")
    @NotNull(message = "修改记录的id不能为空")
    private Integer id;

    @JsonProperty("name")
    @NotBlank(message = "应用名称不能为空")
    @Length(min = 1, max = 20, message = "应用名称长度为1-20")
    private String appName;

    @JsonProperty("url")
    @Length(max = 100, message = "应用网址最大长度为100")
    private String appUrl;

    @JsonProperty("username")
    @NotBlank(message = "账号不能为空")
    @Length(min = 1, max = 20, message = "账号长度为1-20")
    private String myUsername;

    @JsonProperty("password")
    @NotBlank(message = "密码不能为空")
//    @Pattern(regexp = "^(?![a-z0-9]{6,12})(?![0-9A-Z]{6,12})(?![a-zA-Z]{6,12})[a-zA-Z0-9].{5,11}$",
//            message = "密码长度6-12位，不能以特殊符号开头，且前6位不能仅有：小写字母+数字、大写字母+数字、大小写字母")
    @Length(min = 1, max = 20, message = "密码长度为1-20")
    private String myPassword;

    @JsonProperty("remark")
    @Length(max = 200, message = "备注最大长度为200")
    private String remark;
}
