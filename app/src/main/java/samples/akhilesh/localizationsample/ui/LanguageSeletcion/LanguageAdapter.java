package samples.akhilesh.localizationsample.ui.LanguageSeletcion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import samples.akhilesh.localizationsample.model.Language;
import samples.akhilesh.localizationsample.R;



public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {

    private List<Language> languageList;
    private int rowIndex = 0;
    private final OnLanguageItemClickListener listener;

    public LanguageAdapter(int rowIndex, List<Language> languageList, OnLanguageItemClickListener listener) {
        this.rowIndex = rowIndex;
        this.languageList = languageList;
        this.listener = listener;
    }

    @Override
    public LanguageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.language_item, parent, false);
        return new LanguageViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final LanguageViewHolder holder, int position) {
        holder.itemView.setSelected(rowIndex == position);
        Language language = languageList.get(position);
        holder.bind(position, language, listener);
    }

    @Override
    public int getItemCount() {
        return languageList.size();
    }

     class LanguageViewHolder extends RecyclerView.ViewHolder {

        private TextView tvLanguage;

        LanguageViewHolder(View view) {
            super(view);
            tvLanguage = view.findViewById(R.id.tv_language);
        }

        public void bind(final int position, final Language language, final OnLanguageItemClickListener listener) {

            tvLanguage.setText(language.getLang());
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override public void onClick(View v) {
                    // Updating old as well as new positions
                    notifyItemChanged(rowIndex);
                    rowIndex = position;
                    notifyItemChanged(rowIndex);

                    listener.onItemClick(language);

                }
            });
        }
    }
}