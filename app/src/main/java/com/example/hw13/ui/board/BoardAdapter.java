package com.example.hw13.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.airbnb.lottie.LottieAnimationView;
import com.example.hw13.R;
import com.example.hw13.models.Board;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    private ArrayList<Board> boards;
    private LottieAnimationView lottie;


    public BoardAdapter () {
        boards= new ArrayList<>();
        boards.add(new Board("Title","Description","cat_hero.json"));
        boards.add(new Board("Title","Description","cat_hero.json"));
        boards.add(new Board("Title","Description","cat_hero.json"));

    }
    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_board,parent,false);
        return new BoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return boards.size();
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private TextView textDesc;
        private ImageView imageView;
        private LottieAnimationView lottie;
        private Button btnStart;
        private Button btnSkip;
        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            //imageView=itemView.findViewById(R.id.image);
            //lottie=itemView.findViewById(R.id.lottie);
            textDesc=itemView.findViewById(R.id.description);
            textTitle= itemView.findViewById(R.id.text_title_second);
            btnStart= itemView.findViewById(R.id.btn_start);
            btnSkip= itemView.findViewById(R.id.btn_skip);

        }

        public void bind(int position) {
            Board board=boards.get(position);
            textTitle.setText(board.getTitle());
            textDesc.setText(board.getDesc());
            //imageView.setImageResource(board.getImage());
            //lottie.setAnimationFromJson(board.getLottie(), String.valueOf(lottie.hashCode()));
            if (position == boards.size()-1){
                btnStart.setVisibility(View.VISIBLE);
                btnStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).navigate(R.id.action_boardFragment_to_navigation_home);
                    }
                });
            }else {
                btnStart.setVisibility(View.INVISIBLE);
            }
            if (position!=boards.size()-1){
                btnSkip.setVisibility(View.VISIBLE);
                btnSkip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).navigate(R.id.action_boardFragment_to_navigation_home);
                    }
                });
            }else {
                btnSkip.setVisibility(View.INVISIBLE);
            }

        }
    }
}
