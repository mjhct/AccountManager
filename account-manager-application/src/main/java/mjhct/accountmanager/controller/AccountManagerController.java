package mjhct.accountmanager.controller;

import mjhct.accountmanager.bo.MyAccountAddBO;
import mjhct.accountmanager.commons.CommonCode;
import mjhct.accountmanager.commons.CommonResult;
import mjhct.accountmanager.dto.MyAccountAddReqDTO;
import mjhct.accountmanager.entity.MyAccount;
import mjhct.accountmanager.service.MyAccountService;
import mjhct.accountmanager.service.crypto.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;

@RestController
@RequestMapping("/account")
public class AccountManagerController {

    public static final Logger logger = LoggerFactory.getLogger(AccountManagerController.class);

    @Resource(name = "aesService")
    private CryptoService cryptoService;

    @Resource
    private MyAccountService myAccountService;

    @GetMapping("/testQ")
    public CommonResult<MyAccount> testQ(@RequestParam("id") Integer id) throws SQLException {
        logger.debug("测试成功");

        MyAccount myAccountById = myAccountService.getMyAccountById(id);

        return new CommonResult<>(CommonCode.SUCCESS, myAccountById);
    }

    @PostMapping("/add")
    public CommonResult<MyAccount> add(@RequestBody @Validated MyAccountAddReqDTO myAccountAddReqDTO) {
        MyAccountAddBO myAccountAddBO = new MyAccountAddBO();
        BeanUtils.copyProperties(myAccountAddReqDTO, myAccountAddBO);
        logger.info("要添加的账号是{}", myAccountAddBO);
        MyAccount myAccount = myAccountService.addMyAccount(myAccountAddBO);
        return new CommonResult<>(CommonCode.SUCCESS, myAccount);
    }

    @GetMapping("/testL")
    public CommonResult<Iterable<MyAccount>> testL() {
        logger.debug("测试成功");

        Iterable<MyAccount> myAccounts = myAccountService.listMyAccount();

        return new CommonResult<>(CommonCode.SUCCESS, myAccounts);
    }

}
