package ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import base.BaseActivity;
import fragment.MyDetailToolBar;
import fragment.MyInputBar;
import fragment.NewsDetailFragment;
import inter.InputListener;
import inter.KeyBoardListener;
import list.FragmentList;
import myapplication.AppContext;
import test.oschina_client.R;

/**
 * Created by Administrator on 2016/6/23.
 */
public class DetailActivity extends BaseActivity implements KeyBoardListener, InputListener {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment mContainFragment;
    private Fragment mToolbar;
    private Fragment mInput;
    public Object detail;

    @Override
    public int getRootView() {
        return R.layout.activity_detail;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        detail = intent.getSerializableExtra("bean");
        Class clazz = FragmentList.getFragmentClazz(detail);
        if (clazz != null) {
            mContainFragment = Fragment.instantiate(AppContext.context, clazz.getName());
        }
    }

    @Override
    public void initAction() {
        mToolbar = new MyDetailToolBar(this);
        mInput = new MyInputBar(this);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.detail_fragment, mContainFragment);
        mFragmentTransaction.replace(R.id.toolbar_fragment, mToolbar);
        mFragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void keyBoardChanger() {
        fragmentChange (mInput);
    }

    @Override
    public void inputListener() {
        fragmentChange (mToolbar);
    }


    public void fragmentChange(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.footer_menu_slide_in,
                R.anim.footer_menu_slide_out).replace(R.id.toolbar_fragment, fragment).commit();
    }
}
