package com.example.daggermvpstudy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.daggermvpstudy.dagger.component.DaggerMainComponent;
import com.example.daggermvpstudy.dagger.model.MainModule;
import com.example.daggermvpstudy.presenter.LoginPresenterCompl;
import com.example.daggermvpstudy.view.ILoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_clear)
    Button btnClear;

    @Inject
    LoginPresenterCompl loginPresenterCompl;//注入控制层 这里需要注入我们的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //在module的构造函数带有参数且参数被使用的情况下，所产生的Component类就没有create()方法l
        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenterCompl.doLogin(etName.getText().toString().trim(),etPassword.getText().toString().trim());
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenterCompl.clear();
            }
        });

    }

    @Override
    public void onClearText() {
        etName.setText("");
        etPassword.setText("");
        Toast.makeText(this,"clear",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        btnLogin.setEnabled(true);
        btnClear.setEnabled(true);
        if(result){
            Toast.makeText(this,"登陆成功: "+code,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"登陆失败："+code,Toast.LENGTH_SHORT).show();
        }
    }
}
