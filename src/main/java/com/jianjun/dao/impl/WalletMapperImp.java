package com.jianjun.dao.impl;

import com.jianjun.dao.IWalletMapper;
import com.jianjun.entity.wallet.Wallet;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class WalletMapperImp implements IWalletMapper {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<Wallet> queryWallets(String email) {

        return sqlSessionTemplate.selectList("queryWallets", email);
    }

    @Override
    public int addWallet(Wallet wallet) {
        return sqlSessionTemplate.insert("addWallet", wallet);
    }

    @Override
    public Wallet requestWallets(String walletId) {
        return sqlSessionTemplate.selectOne("requestWallets", walletId);
    }

    @Override
    public int updateWallet(Wallet wallet) {
        return sqlSessionTemplate.update("updateWallet", wallet);
    }

    @Override
    public int deleteWallet(String walletId) {
        return sqlSessionTemplate.update("deleteWallet", walletId);
    }
}
