package com.example.hw13.ui.news;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw13.App;
import com.example.hw13.OnclickListener;
import com.example.hw13.R;
import com.example.hw13.databinding.ItemNewsBinding;
import com.example.hw13.models.News;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

@SuppressLint("NotifyDataSetChanged")
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<News> list;
    private OnclickListener onclickListener;

    public NewsAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.
                from(parent.getContext()), parent, false));

    }


    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(list.get(position));
        if (position % 2 == 0) {
            holder.binding.textTitle.setBackgroundColor(Color.BLACK);
        } else {
            holder.binding.textTitle.setBackgroundColor(Color.YELLOW);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(OnclickListener onClickListener) {
        this.onclickListener = onClickListener;
    }

    public void addItem(News news) {
        list.add(0, news);
        notifyItemInserted(list.indexOf(news));
    }

    public News getItem(int position) {
        return list.get(position);
    }

    public void updateItem(News news, int position) {
        list.set(position, news);
        notifyItemChanged(position);
    }

    public void addList(List<News> list) {
        Comparator<News> comparator = Comparator.comparing(new Function<News, Long>() {
            @Override
            public Long apply(News news) {
                return news.getCreatedAt();
            }
        });
        this.list=list;
        this.list.sort(comparator);
        Collections.reverse(this.list);
        notifyDataSetChanged();
    }

    public void setList(List<News> list) {
        this.list= (ArrayList<News>) list;
        notifyDataSetChanged();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        ItemNewsBinding binding;

        public NewsViewHolder(@NonNull ItemNewsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onclickListener.onItemClick(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putString("key_text", binding.textTitle.getText().toString());

                }
            });
            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onclickListener.onItemLongClick(getAdapterPosition());
                    new AlertDialog.Builder(view.getContext()).setTitle("Удаление")
                            .setMessage("Вы точно хотите удалить?")
                            .setNegativeButton("нет", null)
                            .setPositiveButton("да", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(view.getContext(), "Delete", Toast.LENGTH_LONG).show();
                                    list.remove(getAdapterPosition());
                                    App.getDataBase().newsDao().deleteTask(list);
                                    notifyDataSetChanged();

                                }
                            }).show();
                    return true;
                }
            });
        }


        public void bind(News news) {
            binding.textTitle.setText(news.getTitle());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss, dd MMM yyyy", Locale.ROOT);
            String date = String.valueOf(simpleDateFormat.format(news.getCreatedAt()));
            binding.time.setText(date);
        }
    }
}
