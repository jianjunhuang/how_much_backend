package com.jianjun.service;

import com.jianjun.entity.wallet.Wallet;

import java.util.List;

public interface IWalletService {
    List<Wallet> queryWallets(String email);

    boolean addWallet(Wallet wallet);

    Wallet requestWallets(String walletId);

    boolean updateWallet(Wallet wallet);
}
