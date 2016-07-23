package custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import emoji.DisplayRules;
import emoji.Emojicon;

/**
 * Created by Administrator on 2016/6/14.
 */
public class TweetTextView extends TextView {

    public TweetTextView(Context context) {
        super(context);
    }

    public TweetTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TweetTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int first = 0;
        int last = 0;
        int first1 = 0;
        int last1 = 0;
        int first2 = 0;
        int last2 = 0;
        boolean firstMeet = true;
        boolean firstMeet1 = true;
        boolean firstMeet2 = true;
        String str = getText().toString();
        Spanned spanned = Html.fromHtml(str);
        SpannableStringBuilder mSpannableStringBuilder = new SpannableStringBuilder(spanned);
        mSpannableStringBuilder = new SpannableStringBuilder(spanned);
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            if (chars[i] == '@') {
                first2 = i;
                firstMeet2 = false;
            } else if (chars[i] == ' ' && !firstMeet2) {
                last2 = i;
                firstMeet2 = true;
                mSpannableStringBuilder.setSpan(new MyOnClick(), first2, last2 + 1, 0);
            }

            if (chars[i] == '#' && firstMeet1) {
                first1 = i;
                firstMeet1 = false;
            } else if (chars[i] == '#' && !firstMeet1) {
                last1 = i;
                firstMeet1 = true;
                mSpannableStringBuilder.setSpan(new MyOnClick(), first1, last1 + 1, 0);
            }


            if (chars[i] == ':' && firstMeet) {
                first = i;
                firstMeet = false;
            } else if (chars[i] == ':' && !firstMeet) {
                last = i;
                firstMeet = true;
                String temp = str.substring(first, last + 1);
                Emojicon emojicon = DisplayRules.getEmojiFromName(temp);
                if (emojicon == null) {
                    first = i;
                    firstMeet = false;
                    continue;
                }
                Drawable drawable = getResources().getDrawable(emojicon.getResId());
                int bound = 20;
                drawable.setBounds(0, 20, bound, 20 + bound);
                ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
//                ((Spannable) spanned).setSpan(span, first, last + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                mSpannableStringBuilder.setSpan(span, first, last + 1, 0);
            }
        }
        setText(mSpannableStringBuilder);
    }

    class MyOnClick extends ClickableSpan {
        private int default_color = 0xff517fae;

        @Override
        public void onClick(View widget) {

        }

        @Override
        public CharacterStyle getUnderlying() {
            return super.getUnderlying();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(default_color);
            ds.setUnderlineText(false);
        }
    }


}
