package mjhct.accountmanager.service;

import mjhct.accountmanager.bo.MyAccountAddBO;
import mjhct.accountmanager.dao.MyAccountRepository;
import mjhct.accountmanager.entity.MyAccount;
import mjhct.accountmanager.service.crypto.CryptoService;
import mjhct.accountmanager.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyAccountService {

    private static final Logger logger = LoggerFactory.getLogger(MyAccountService.class);

    @Resource(name = "aesService")
    private CryptoService cryptoService;

    @Resource
    private MyAccountRepository myAccountRepository;

    public List<MyAccount> getMyAccountByIdOrAppName(Integer id, String appName) {
        // id优先
        if (id != null) {

            Optional<MyAccount> byId = myAccountRepository.findById(id);
            if (byId.isPresent()) {
                List<MyAccount> rst = new ArrayList<>(2);
                rst.add(byId.get());
                return rst;
            }
            return null;
        }

        // 根据应用名称查
        return myAccountRepository.findByAppName(appName);
    }

    public MyAccount addMyAccount(MyAccountAddBO myAccountAddBO) {
        MyAccount addAccount = new MyAccount();
        BeanUtils.copyProperties(myAccountAddBO, addAccount);
        addAccount.setMyUsername(cryptoService.encrypt(addAccount.getMyUsername()));
        addAccount.setMyPassword(cryptoService.encrypt(addAccount.getMyPassword()));
        OffsetDateTime nowOffsetDateTime = DateTimeUtil.nowChinaOffsetDateTime();
        addAccount.setCreateTime(nowOffsetDateTime);
        addAccount.setUpdateTime(nowOffsetDateTime);
        logger.debug("添加到数据库的数据是:{}", addAccount);
        myAccountRepository.save(addAccount);
        logger.debug("添加到数据库的结果是{}", addAccount);
        return addAccount;
    }

    public Iterable<MyAccount> listMyAccount() {
        return myAccountRepository.findAll();
    }

}
