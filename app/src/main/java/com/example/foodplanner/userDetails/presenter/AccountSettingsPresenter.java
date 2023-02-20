package com.example.foodplanner.userDetails.presenter;

import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.userDetails.view.AccountSettingsViewInterface;


public class AccountSettingsPresenter implements AccountSettingsPresenterInterface {

  AccountSettingsViewInterface accountSettingsViewInterface;
    RepositoryInterface repo;

    public AccountSettingsPresenter(AccountSettingsViewInterface accountSettingsViewInterface, RepositoryInterface repo) {
        this.accountSettingsViewInterface=accountSettingsViewInterface;
        this.repo=repo;
    }

    @Override
    public void backupUserData() {
        repo.backupUserData();
    }
}
