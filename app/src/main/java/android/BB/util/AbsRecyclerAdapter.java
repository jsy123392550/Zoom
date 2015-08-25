package android.BB.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;


public abstract class AbsRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    public static final int VIEWTYPE_ITEM = 0;
    public static final int VIEWTYPE_FOOT = 1;
    public static final int VIEWTYPE_DESC_SECOND=2;
    protected Context mContext;
    protected LayoutInflater layoutInflater;
    public ItemClickListener itemClickListener;


    public AbsRecyclerAdapter(Context context) {
        this.mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {
        if (viewHolder instanceof AbsRecyclerAdapter.ItemViewHolder) {
            ((ItemViewHolder) viewHolder).bind(pos);
        }
    }

        @Override
        public int getItemViewType(int position) {
            int viewType;
            if(position+1==getItemCount())
                viewType=VIEWTYPE_FOOT;
            else if(position+2==getItemCount())
                viewType=VIEWTYPE_DESC_SECOND;
            else
                viewType=VIEWTYPE_ITEM;
            return viewType;
    }

    public abstract void loadMore();
    public abstract void refresh();
    public interface ItemClickListener{
        public void click(int info_id, int pos);
    }
    public void setClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    protected abstract class ItemViewHolder extends ViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
        public abstract void bind(int pos);
    }
    protected class FootViewHolder extends ViewHolder {
        public FootViewHolder(View view) {
            super(view);
        }
    }
}

