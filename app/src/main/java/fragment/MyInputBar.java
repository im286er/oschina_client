package fragment;

import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import base.BaseFragment;
import inter.InputListener;
import test.oschina_client.R;

/**
 * Created by Administrator on 2016/6/29.
 */
public class MyInputBar extends BaseFragment implements View.OnClickListener {

    private InputListener mInputListener;
    private CheckBox mCheckBox;
    private AppCompatEditText mAppCompatEditText;
    private CheckBox mEmoji;
    private ImageView mSendComment;

    public MyInputBar(InputListener inputListener) {
        mInputListener = inputListener;
    }


    @Override
    public View getRootView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.detail_input, null);
    }

    @Override
    public void initView(View view) {
        mCheckBox = (CheckBox) view.findViewById(R.id.emoji_title_flag);
        mCheckBox.setOnClickListener(this);
        mAppCompatEditText = (AppCompatEditText) view.findViewById(R.id.emoji_titile_input);
        mEmoji = (CheckBox) view.findViewById(R.id.emoji_title_menu);
        mEmoji.setOnClickListener(this);
        mSendComment = (ImageView) view.findViewById(R.id.emoji_title_send);
        mSendComment.setOnClickListener(this);
    }


    @Override
    public void setView(byte[] bytes) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.emoji_title_flag:
                mInputListener.inputListener();
                break;
            case R.id.emoji_title_menu:
                break;
            case R.id.emoji_title_send:
                break;
        }
    }
}
