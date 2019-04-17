package com.jianjun.dao;

import com.jianjun.entity.wallet.Wallet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IWalletMapper {

    List<Wallet> queryWallets(@Param("email") String email);

    int addWallet(Wallet wallet);

    Wallet requestWallets(@Param("walletId") String walletId);

    int updateWallet(Wallet wallet);
}
