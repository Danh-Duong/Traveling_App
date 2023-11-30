package com.example.traveling_app.model.savedtours;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.traveling_app.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
public class SavedTourAdapter extends FirebaseRecyclerAdapter<SavedTour, SavedTourViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private static final Runnable EMPTY_RUNNABLE = () -> {};
    private Runnable onDataChangedCallback = EMPTY_RUNNABLE;
    public SavedTourAdapter(@NonNull FirebaseRecyclerOptions<SavedTour> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SavedTourViewHolder holder, int position, @NonNull SavedTour model) {
        holder.bindDataToViewHolder(model);
    }

    @NonNull
    @Override
    public SavedTourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.love_tour_item, parent, false);
        return new SavedTourViewHolder(rootView);
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        onDataChangedCallback.run();
    }

    public void setOnDataChanged(Runnable onDataChangedCallback) {
        if (onDataChangedCallback == null)
            this.onDataChangedCallback = EMPTY_RUNNABLE;
        else
            this.onDataChangedCallback = onDataChangedCallback;
    }
}
