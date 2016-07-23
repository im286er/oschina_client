package fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import base.BaseFragment;
import inter.KeyBoardListener;
import test.oschina_client.R;


/**
 * Created by Administrator on 2016/6/26.
 */
public class MyDetailToolBar extends BaseFragment implements View.OnClickListener {

    private ImageButton mInputKeyBoard;
    private ImageView mComment;
    private ImageView mWrite;
    private ImageView mCollection;
    private ImageView mShare;
    private KeyBoardListener mKeyBoardListener;


    public MyDetailToolBar(KeyBoardListener keyBoardListener) {
        mKeyBoardListener = keyBoardListener;
    }


    @Override
    public View getRootView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.detail_toolbar, null);
    }

    @Override
    public void initView(View view) {
        mInputKeyBoard = (ImageButton) view.findViewById(R.id.btn_change);
        mInputKeyBoard.setOnClickListener(this);
        mComment = (ImageView) view.findViewById(R.id.action_view_comment);
        mComment.setOnClickListener(this);
        mWrite = (ImageView) view.findViewById(R.id.action_write_comment);
        mWrite.setOnClickListener(this);
        mCollection = (ImageView) view.findViewById(R.id.action_favor);
        mCollection.setOnClickListener(this);
        mShare = (ImageView) view.findViewById(R.id.action_repost);
        mShare.setOnClickListener(this);
    }

    @Override
    public void setView(byte[] bytes) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_change:
                mKeyBoardListener.keyBoardChanger();
                break;
            case R.id.action_view_comment:
                break;
            case R.id.action_write_comment:
                break;
            case R.id.action_favor:
                break;
            case R.id.action_repost:
                break;
        }
    }

}
