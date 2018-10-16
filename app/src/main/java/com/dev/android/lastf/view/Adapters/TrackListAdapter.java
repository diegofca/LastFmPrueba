package com.dev.android.lastf.view.Adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.dev.android.lastf.R;
import com.dev.android.lastf.databinding.ItemTrackBinding;
import com.dev.android.lastf.model.Track;
import com.dev.android.lastf.viewmodel.Items.ItemTrackViewModel;
import java.util.Collections;
import java.util.List;

public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.TrackAdapterViewHolder> {

  private List<Track> trackList;

  public TrackListAdapter() {
    this.trackList = Collections.emptyList();
  }

  @Override public TrackAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     ItemTrackBinding itemTrackBinding =  DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_track,
            parent, false);
      return new TrackAdapterViewHolder(itemTrackBinding);
  }

  @Override public void onBindViewHolder(TrackAdapterViewHolder holder, int position) {
    holder.bindTrack(trackList.get(position));
  }

  @Override public int getItemCount() {
    return trackList.size();
  }

  public void setTrackList(List<Track> trackList) {
    this.trackList = trackList;
    notifyDataSetChanged();
  }

  // --------------------- VIEW HOLDER -------------------------------------
  public static class TrackAdapterViewHolder extends RecyclerView.ViewHolder {
      ItemTrackBinding mItemTrackBinding;

        public TrackAdapterViewHolder(ItemTrackBinding itemTrackBinding) {
          super(itemTrackBinding.itemTrack);
          this.mItemTrackBinding = itemTrackBinding;
        }

        void bindTrack(Track track) {
          if (mItemTrackBinding.getTrackViewModel() == null) {
              Log.d("track", track.name);
               mItemTrackBinding.setTrackViewModel( new ItemTrackViewModel(track, itemView.getContext()));
          } else {
            mItemTrackBinding.setTrackViewModel(mItemTrackBinding.getTrackViewModel());
            mItemTrackBinding.getTrackViewModel().setTrack(track);
          }

          mItemTrackBinding.fav.setProgress( mItemTrackBinding.getTrackViewModel().getProgress() );
          mItemTrackBinding.fav.setOnClickListener(v ->
                  mItemTrackBinding.getTrackViewModel().onFavClick(mItemTrackBinding.fav)
          );
        }
  }
}
