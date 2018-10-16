package com.dev.android.lastf.view.Adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.dev.android.lastf.R;
import com.dev.android.lastf.databinding.ItemAlbumBinding;
import com.dev.android.lastf.model.Album;
import com.dev.android.lastf.viewmodel.Items.ItemAlbumViewModel;
import java.util.Collections;
import java.util.List;

/**
 * Created by macbookpro on 13/10/18.
 */

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.AlbumAdapterViewHolder> {

    private List<Album> albumsList;

    public AlbumListAdapter() {
        this.albumsList = Collections.emptyList();
    }

    @Override
    public AlbumAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAlbumBinding itemAlbumBinding =  DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_album, parent, false);
        return new AlbumAdapterViewHolder(itemAlbumBinding);
    }

    @Override
    public void onBindViewHolder( AlbumAdapterViewHolder holder, int position) {
        holder.bindTrack(albumsList.get(position));
    }

    @Override
    public int getItemCount() {
        return albumsList.size();
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumsList = albumList;
        notifyDataSetChanged();
    }



    // ------------------------------------------------------------- VIEW HOLDER -------------------------------------
    public static class AlbumAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemAlbumBinding mItemAlbumBinding;

        public AlbumAdapterViewHolder(ItemAlbumBinding itemAlbumBinding) {
            super(itemAlbumBinding.itemAlbum);
            this.mItemAlbumBinding = itemAlbumBinding;
        }

        void bindTrack(Album album) {
            if (mItemAlbumBinding.getItemAlbumViewModel() == null) {
                mItemAlbumBinding.setItemAlbumViewModel( new ItemAlbumViewModel(album, itemView.getContext()));
            } else {
                mItemAlbumBinding.setItemAlbumViewModel(mItemAlbumBinding.getItemAlbumViewModel());
                mItemAlbumBinding.getItemAlbumViewModel().setAlbum(album);
            }

            mItemAlbumBinding.fav.setProgress( mItemAlbumBinding.getItemAlbumViewModel().getProgress() );
            mItemAlbumBinding.fav.setOnClickListener(v ->
                    mItemAlbumBinding.getItemAlbumViewModel().onFavClick(mItemAlbumBinding.fav)
            );
        }
    }
}