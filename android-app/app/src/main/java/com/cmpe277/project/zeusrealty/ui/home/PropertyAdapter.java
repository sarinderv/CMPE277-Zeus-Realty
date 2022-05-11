package com.cmpe277.project.zeusrealty.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmpe277.project.zeusrealty.MainActivity;
import com.cmpe277.project.zeusrealty.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class PropertyAdapter extends ArrayAdapter<StackProperties> {
    /**
     * fields
     */
    ImageLoader imageLoader;
    DisplayImageOptions options;
    List<StackProperties> productList;
    SparseBooleanArray mSelectedItemsIds;
    Context context;


    /**
     * Constructor.
     *
     * @param ctx
     * @param textViewResourceId
     * @param sites
     */
    public PropertyAdapter(Context ctx, int textViewResourceId, List<StackProperties> sites) {
        super(ctx, textViewResourceId, sites);

        /**Setup the ImageLoader, we'll use this to display our images*/
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(ctx).build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

        mSelectedItemsIds = new SparseBooleanArray();

        /**Setup options for ImageLoader so it will handle caching for us.*/
        options = new DisplayImageOptions.Builder()
                .cacheInMemory()
                .cacheOnDisc()
                .build();

        this.context = ctx;
    }

    /**
     * This method is responsible for creating row views out of a StackProduits object that can be put
     * into our ListView.
     * <p/>
     * (non-Javadoc)
     *
     * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        RelativeLayout row = (RelativeLayout) convertView;
        //Log.i("StackSites", "getView pos = " + pos);
        if (null == row) {   //No recycled View, we have to inflate one.
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = (RelativeLayout) inflater.inflate(R.layout.item_row, null);
        }

        //Get our View References from item_row.xml
        final ImageView iconImg = (ImageView) row.findViewById(R.id.iconImg);
        TextView nameText = (TextView) row.findViewById(R.id.nameTxt);
        TextView txtAbout = (TextView) row.findViewById(R.id.aboutTxt);
        TextView txtPrice = (TextView) row.findViewById(R.id.priceTxt);
        TextView txtTotalArea = (TextView) row.findViewById(R.id.areaTxt);
        final ProgressBar indicator = (ProgressBar) row.findViewById(R.id.progress);

        StackProperties p=getItem(pos);
        if(p!=null) {
            // txtAbout.setText(p.getAbout());

            //Initially we want the progress indicator visible, and the image invisible
           /* indicator.setVisibility(View.VISIBLE); //show progress indicator
            iconImg.setVisibility(View.INVISIBLE); //make image invisible

            //Setup a listener we can use to switch from the loading indicator to the Image once it's ready
            //changed ImageLoadingListener with SimpleImageLoadingListener
            SimpleImageLoadingListener listener = new SimpleImageLoadingListener() {
                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
                    indicator.setVisibility(View.INVISIBLE);
                    iconImg.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                }
            };*/

            //Load the image and use our options so caching is handled.
            //imageLoader.displayImage(getItem(pos), iconImg, options, listener);

            //  Set the relevant text in our TextViews (ListView)
            nameText.setText(p.getName());

            txtAbout.setText(p.getAbout());
            txtPrice.setText("$"+p.getPrice());
            txtTotalArea.setText(p.getTotal_area()+"sq.ft");


            //return view that represents the full row


        }
        return row;
    }


    /***********************************************************************************************
     * START OF MULTIPLE ITEM DELETION METHODS                                                     *
     ***********************************************************************************************/
    @Override
    public void remove(StackProperties object) {
        super.remove(object);
        notifyDataSetChanged();
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
    /***********************************************************************************************
     * END OF MULTIPLE ITEM DELETION METHODS                                                       *
     ***********************************************************************************************/
}
