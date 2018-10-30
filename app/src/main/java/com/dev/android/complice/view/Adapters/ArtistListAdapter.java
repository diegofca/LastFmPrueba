package com.dev.android.complice.view.Adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.dev.android.complice.R;
import com.dev.android.complice.databinding.ItemArtistBinding;
import com.dev.android.complice.model.Artist;
import com.dev.android.complice.viewmodel.Items.ItemArtistViewModel;
import java.util.Collections;
import java.util.List;

/**
 * Created by macbookpro on 14/10/18.
 */

public class ArtistListAdapter extends RecyclerView.Adapter<ArtistListAdapter.ArtistAdapterViewHolder> {

    private List<Artist> artistList;

    public ArtistListAdapter() {
        this.artistList = Collections.emptyList();
    }

    @Override
    public ArtistListAdapter.ArtistAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemArtistBinding itemArtistBinding =  DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_artist, parent, false);
        return new ArtistListAdapter.ArtistAdapterViewHolder(itemArtistBinding);
    }

    @Override
    public void onBindViewHolder(ArtistListAdapter.ArtistAdapterViewHolder holder, int position) {
        holder.bindArtist(artistList.get(position));
    }

    @Override public int getItemCount() {
        return artistList.size();
    }

    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
        notifyDataSetChanged();
    }

    // --------------------- VIEW HOLDER -------------------------------------
    public static class ArtistAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemArtistBinding mItemArtistBinding;

        public ArtistAdapterViewHolder(ItemArtistBinding itemArtistBinding) {
            super(itemArtistBinding.itemArtist);
            this.mItemArtistBinding = itemArtistBinding;
        }

        void bindArtist(Artist artist) {

            if (mItemArtistBinding.getItemArtistViewModel() == null) {
                mItemArtistBinding.setItemArtistViewModel( new ItemArtistViewModel(artist, itemView.getContext()));
            } else {
                mItemArtistBinding.setItemArtistViewModel(mItemArtistBinding.getItemArtistViewModel());
                mItemArtistBinding.getItemArtistViewModel().setArtist(artist);
            }

            mItemArtistBinding.fav.setProgress( mItemArtistBinding.getItemArtistViewModel().getProgress() );
            mItemArtistBinding.fav.setOnClickListener(v ->
                    mItemArtistBinding.getItemArtistViewModel().onFavClick(mItemArtistBinding.fav)
            );
        }
    }
}