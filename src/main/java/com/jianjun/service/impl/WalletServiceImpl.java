package com.jianjun.service.impl;

import com.jianjun.dao.IWalletMapper;
import com.jianjun.entity.wallet.Wallet;
import com.jianjun.service.IWalletService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WalletServiceImpl implements IWalletService {

    @Resource
    private IWalletMapper mWalletMapper;

    @Override
    public List<Wallet> queryWallets(String email) {
        return mWalletMapper.queryWallets(email);
    }

    @Override
    public boolean addWallet(Wallet wallet) {
        return mWalletMapper.addWallet(wallet) >= 0;
    }

    @Override
    public Wallet requestWallets(String walletId) {
        return mWalletMapper.requestWallets(walletId);
    }

    @Override
    public boolean updateWallet(Wallet wallet) {
        return mWalletMapper.updateWallet(wallet) >= 0;
    }
}
