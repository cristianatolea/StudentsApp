package com.example.cris.studentsapp.screen.main.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.main.model.entity.DrawerItem;

import java.util.List;


/**
 * NavDrawerListAdapter class is a custom adapter for the ListView used in navigation drawer
 */
public class NavDrawerListAdapter extends ArrayAdapter<DrawerItem> {


    private int mResource;
    private Context mContext;
    private OnMenuItemClickListener mOnMenuItemClickListener;

    public NavDrawerListAdapter(@NonNull Context context,
                                List<DrawerItem> items,
                                OnMenuItemClickListener onMenuItemClickListener,
                                int resource) {
        super(context, resource, items);
        this.mResource = resource;
        mContext = context;
        mOnMenuItemClickListener = onMenuItemClickListener;
    }

    /**
     * method used to bind data to the corsponding view
     *
     * @param position    used to know know the position of the item
     * @param convertView the view
     * @param parent
     * @return the view with its content
     */
    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if (rowView == null) {

            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            rowView = layoutInflater.inflate(mResource, null, true);
            viewHolder = new ViewHolder(rowView, mContext);
            rowView.setTag(viewHolder);
            rowView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnMenuItemClickListener.onDrawerItemClick(position);
            }
        });

        DrawerItem item = getItem(position);

        viewHolder.bindData(item);
        return rowView;
    }

    /**
     * method used to change focus of items when clicked on one of them
     *
     * @param position position of the item clicked
     */
    public void onFocus(int position) {
        if (position != -1)
            for (int i = 0; i < getCount(); i++) {
                if (i != position)
                    getItem(i).setOnFocus(false);
                else
                    getItem(i).setOnFocus(true);
            }
        else
            for (int i = 0; i < getCount(); i++) {
                getItem(i).setOnFocus(false);
            }

    }

    /**
     * interface for item click listener
     */
    public interface OnMenuItemClickListener {
        void onDrawerItemClick(int position);
    }

    /**
     * Custom view holder for the item
     */
    static class ViewHolder {
        private TextView mTitle;
        private ImageView mIcon;
        private View mEndDivider;
        private Context mContext;

        ViewHolder(View itemView, Context context) {
            mContext = context;

            mTitle = itemView.findViewById(R.id.text_item_nav_drawer);
            mIcon = itemView.findViewById(R.id.image_item_nav_drawer);
            mEndDivider = itemView.findViewById(R.id.end_divider_nav_drawer);
        }

        /**
         * method to populate the view
         *
         * @param item used to get data to populate the views
         */
        void bindData(DrawerItem item) {
            mTitle.setText(item.getTitle());

            if (item.isOnFocus()) {
                mTitle.setTextColor(mContext.getResources().getColor(R.color.blue_lighter_two));
                mIcon.setImageDrawable(mContext.getResources().getDrawable(item.getIconOnFocused()));
                mEndDivider.setBackgroundColor(mContext.getResources().getColor(R.color.blue_lighter_two));
            } else {
                mTitle.setTextColor(mContext.getResources().getColor(R.color.divider_grey));
                mIcon.setImageDrawable(mContext.getResources().getDrawable(item.getIconOutOfFocused()));
                mEndDivider.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
            }
        }
    }

}

