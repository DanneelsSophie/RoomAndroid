package lille.learn.room;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import lille.learn.room.model.Note;


public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private static final int NEW_DELETE = 2;

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteItemView;
        private Button delete;
        private Button update;

        private NoteViewHolder(View itemView) {
            super(itemView);
            noteItemView = itemView.findViewById(R.id.textView);
            delete =  itemView.findViewById(R.id.delete);
            update = itemView.findViewById(R.id.update);
        }
    }

    private final LayoutInflater mInflater;
    private List<Note> mNotes = Collections.emptyList();

    NoteListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, final int  position) {
        Note current = mNotes.get(position);
        holder.noteItemView.setText(current.getNote());

        /**
         * Ici on met les intents qui permettront d'aller sur une autre activité quand on
         * clique sur le bouton delete, ou update
         */
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mInflater.getContext(), MainActivity.class);
                intent.putExtra("note", getNote(position));
                mInflater.getContext().startActivity(intent);
            }

         });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mInflater.getContext(), UpdateNoteActivity.class);
                intent.putExtra("note", getNote(position));
                mInflater.getContext().startActivity(intent);
            }
        });

    }

    /**
     * modifier la liste des notes affichées
     * @param notes une liste de note
     */
    void setNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

    /**
     * Afficher à une position la note qui nous interesse
     * @param position position
     * @return la note
     */
    Note getNote(int position){
        return mNotes.get(position);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}