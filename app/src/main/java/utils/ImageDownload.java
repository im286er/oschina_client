package utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import myapplication.AppContext;

/**
 * Created by Administrator on 2016/6/12.
 */
public class ImageDownload
{

    public static void getImage (ImageView imageView, String url)
    {
        Picasso.with(AppContext.context).load(url).into(imageView);
    }


}
