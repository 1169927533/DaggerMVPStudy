package com.example.daggermvpstudy.presenter;

import com.example.daggermvpstudy.model.User;
import com.example.daggermvpstudy.view.ILoginView;

import javax.inject.Inject;

public class LoginPresenterCompl implements ILoginPresenter{
    private ILoginView iLoginView;
    private User user;

    @Inject
    public LoginPresenterCompl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        user = new User("z","1");
    }

    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @Override
    public void doLogin(String name, String password) {
        boolean result = false;
        int code = 0;
        if(name.equals(user.getName())&& password.equals(user.getPassword())){
            result = true;
            code = 1;
        }else{
            result = false;
            code = 0;
        }
        iLoginView.onLoginResult(result,code);
    }
}
