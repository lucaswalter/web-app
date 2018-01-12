package com.cryptocaddy.core.exchanges;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.service.account.AccountService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jon Waggoner
 * Date: 1/11/2018
 */
public abstract class ExchangeController {

    protected String accountKey;
    protected String accountSecret;

    public ExchangeController(String key, String secret){
        accountKey = key;
        accountSecret = secret;
    }

    protected abstract Exchange getExchange();
    //public abstract AccountInfo getAccountInfo();
    public abstract ArrayList<Coin> getAllCoins();

    //nullable return type
    public AccountInfo getAccountInfo() {
        AccountService accountService = getExchange().getAccountService();
        AccountInfo accountInfo = null;

        try{
            accountInfo = accountService.getAccountInfo();
            //TODO: place in #if debug preprocessor or equivalent
            System.out.println(accountInfo.toString());
        }catch(IOException e){
            e.printStackTrace();
        }

        return accountInfo;
    }

}
