package com.nenosoft.englishforkids;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nenosof.englishforkids.R;
import java.util.ArrayList;

/**
 * Created by USMAN BUTT on 3/19/2015.
 */
public class GridViewSecaAdapter extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public GridViewSecaAdapter(Context context, int layoutResourceId,
                           ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.image_1 = (ImageView) row.findViewById(R.id.image_f);
            holder.tv_name = (TextView) row.findViewById(R.id.tv_name);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Sec_Info item= (Sec_Info) data.get(position);
        holder.image_1.setImageBitmap(item.getIv_1());
        holder.tv_name.setText(item.getName());
        return row;
    }

    static class ViewHolder {
        ImageView image_1;
        TextView tv_name;
    }
}
