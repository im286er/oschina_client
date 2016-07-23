package ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bean.LoginUserBean;
import bean.User;
import inter.PresenterLogin;
import inter.PresenterLoginView;
import presenter.PresenterLoginImple;
import runteset.ObjectToDiskTest;
import test.oschina_client.R;
import threadpool.ThreadPool;
import utils.CloseEz;
import utils.ToastUitls;
import utils.XmlUtils;

/**
 * Created by Administrator on 2016/6/7.
 */
public class LoginActivity extends Activity implements View.OnClickListener, PresenterLoginView {

    private EditText mEdt_account;
    private EditText mEdt_password;
    private Button mBtn_login;
    private PresenterLogin mPresenterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mEdt_account = (EditText) findViewById(R.id.et_username);
        mEdt_password = (EditText) findViewById(R.id.et_password);
        mBtn_login = (Button) findViewById(R.id.btn_login);
        mBtn_login.setOnClickListener(this);
        mPresenterLogin = new PresenterLoginImple(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String username = mEdt_account.getText().toString();
                String password = mEdt_password.getText().toString();
                if (username != null && password != null) {
                    String url = "http://www.oschina.net/action/api/login_validate?";
                    mPresenterLogin.visitNet(url, username, password);
                } else {
                    ToastUitls.showToastLong("请输入用户名或者密码");
                }
                break;
        }
    }

    @Override
    public void setView(byte[] bytes) {
        LoginUserBean loginResult = XmlUtils.toBean(LoginUserBean.class, new ByteArrayInputStream(bytes));
        if (loginResult.getResult().OK()) {
            final User user = loginResult.getUser();
            ThreadPool threadPool = new ThreadPool();
            Runnable runnable = new ObjectToDiskTest(user);
            threadPool.execute(runnable);
        } else {
            ToastUitls.showToastLong("用户名或密码错误");
        }
    }
}
