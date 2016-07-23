package custom;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import inter.RefreshListListener;
import test.oschina_client.R;

/**
 * Created by Administrator on 2016/6/3.
 */
public class RefreshListView extends ListView implements AbsListView.OnScrollListener {

    private int mFirstTouchY;
    private View mHeadView;
    private View mFootView;
    private Handler mHandler;
    private RefreshListListener mRefreshListener;
    private ImageView mArrow;
    private TextView mTextView;
    private ImageView mLoading;
    private ObjectAnimator mRotate;
    private boolean isScroll = false;
    private boolean isRotate = true;
    public static boolean refreshing = false;
    private int height;
    private boolean loadMore = true;
    private int count = 0;

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    private void initView(Context context) {
        mHeadView = View.inflate(context, R.layout.head_view, null);
        mArrow = (ImageView) mHeadView.findViewById(R.id.refresh_arrow);
        mTextView = (TextView) mHeadView.findViewById(R.id.refresh_text);
        mLoading = (ImageView) mHeadView.findViewById(R.id.loading);
        addHeaderView(mHeadView);
        mHeadView.measure(0, 0);
        height = mHeadView.getMeasuredHeight();
        mHeadView.setPadding(0, -height, 0, 0);
        mFootView = View.inflate(context, R.layout.footer_view, null);
        addFooterView(mFootView);
        mHandler = new Handler();
        mRotate = new ObjectAnimator();
        setOnScrollListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFirstTouchY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                if (getFirstVisiblePosition() == 0) {
                    int scrollY = (int) ev.getY() - mFirstTouchY;

                    if (scrollY >= height) {
                        scrollY = height;
                        mTextView.setText("释放刷新");
                    }

                    mHeadView.setPadding(0, scrollY - height, 0, 0);

                    if (scrollY > 0 && isRotate) {
                        mRotate.ofFloat(mArrow, "rotation", 0, -180).setDuration(500).start();
                        isRotate = false;
                        isScroll = true;
                    }

                }

                break;
            case MotionEvent.ACTION_UP:

                mArrow.setVisibility(View.GONE);
                mLoading.setVisibility(View.VISIBLE);
                mTextView.setText("刷新中...");

                if (isScroll) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshing = true;
                            mRefreshListener.refreshData();
                            mHeadView.setPadding(0, -height, 0, 0);
                            mArrow.setVisibility(View.VISIBLE);
                            mLoading.setVisibility(View.GONE);
                        }
                    }, 2000);

                    isScroll = false;
                    isRotate = true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }


    public void setRefreshListener(RefreshListListener refreshListener) {
        mRefreshListener = refreshListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (!loadMore) {
            System.out.println("没有执行啊");
            return;
        }

        if (getLastVisiblePosition() == getCount() - 1 && loadMore) {
            mRefreshListener.loadMoreData();
            count++;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    public void footViewDisappear() {
        loadMore = true;
        mFootView.setVisibility(View.GONE);
    }

    public void footViewAppear() {
        mFootView.setVisibility(View.VISIBLE);
    }

    public void setLoadMore() {
        loadMore= false;
    }
}
