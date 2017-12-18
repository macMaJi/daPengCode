package com.example.free.mymvpdemo.view.glide;

import com.bumptech.glide.request.RequestOptions;

/**
 * Created by free on 2017/12/16.
 */

public class GlideOptions extends RequestOptions {

    /**
     * @see CustomGlideExtension#miniThumb(RequestOptions)
     */
    public GlideOptions miniThumb() {
        CustomGlideExtension.miniThumb(this);
        return this;
    }

    /**
     * @see CustomGlideExtension#miniThumb(RequestOptions)
     */
    public static GlideOptions miniThumbOf() {
        return new GlideOptions().miniThumb();
    }

}
