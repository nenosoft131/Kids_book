package com.nenosoft.englishforkids;

import android.graphics.Bitmap;

/**
 * Created by USMAN BUTT on 3/19/2015.
 */
public class Sec_Info {
    Bitmap iv_1;
    String name;


    public Bitmap getIv_1() {
        return iv_1;
    }

    public void setIv_1(Bitmap iv_1) {
        this.iv_1 = iv_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sec_Info(Bitmap b_1, String name) {
        this.iv_1 = b_1;
        this.name = name;

    }


}
