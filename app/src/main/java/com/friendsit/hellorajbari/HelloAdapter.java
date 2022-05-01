package com.friendsit.hellorajbari;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HelloAdapter extends RecyclerView.Adapter<HelloAdapter.HelloViewHolder> {
    private Context context;
    private List<HelloModel> list;

    public HelloAdapter(Context context, List<HelloModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HelloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_layout, parent, false);
        return new HelloViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelloViewHolder holder, int position) {
        holder.nameTv.setText(list.get(position).getNam());
        holder.titleTv.setText(list.get(position).getTit());
        holder.phoneTv.setText(list.get(position).getPho());

        holder.dialBtn.setOnClickListener(view -> {
            dialBtnOnClick(list.get(position).getPho());
        });

        holder.phoneTv.setOnClickListener(view -> {
            dialBtnOnClick(list.get(position).getPho());
        });

        holder.expandBtn.setOnClickListener(view -> {
            expandBtnOnClick(holder, position);
        });

        holder.emailTv.setOnClickListener(view -> {
            emailOnClick(list.get(position).getEma());
        });
    }

    private void emailOnClick(String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + email));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    private void expandBtnOnClick(HelloViewHolder holder, int position) {
        if (holder.extraLay.getVisibility() == View.GONE) {
            holder.extraLay.setVisibility(View.VISIBLE);

            if (list.get(position).getEma() != null && !list.get(position).getEma().isEmpty()) {
                holder.emailLay.setVisibility(View.VISIBLE);
                holder.emailTv.setText(list.get(position).getEma());
            }

            if (list.get(position).getAdd() != null && !list.get(position).getAdd().isEmpty()) {
                holder.addressTv.setVisibility(View.VISIBLE);
                holder.addressTv.setText(list.get(position).getAdd());
            }

            if (list.get(position).getDet() != null && !list.get(position).getDet().isEmpty()) {
                holder.detailsTv.setVisibility(View.VISIBLE);
                holder.detailsTv.setText(list.get(position).getDet());
            }
            holder.expandBtn.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
        } else {
            holder.extraLay.setVisibility(View.GONE);
            holder.expandBtn.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
        }
    }

    private void dialBtnOnClick(String Phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "+88" + Phone.trim()));
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HelloViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv, titleTv, phoneTv, emailTv, addressTv, detailsTv;
        private FloatingActionButton dialBtn;
        private ImageButton expandBtn;
        private CardView extraLay;
        private LinearLayout emailLay;

        public HelloViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.nameTv);
            titleTv = itemView.findViewById(R.id.titleTv);
            phoneTv = itemView.findViewById(R.id.phoneTv);
            dialBtn = itemView.findViewById(R.id.dialBtn);
            expandBtn = itemView.findViewById(R.id.expandBtn);
            extraLay = itemView.findViewById(R.id.extraLay);
            emailTv = itemView.findViewById(R.id.emailTv);
            addressTv = itemView.findViewById(R.id.addressTv);
            detailsTv = itemView.findViewById(R.id.detailsTv);
            emailLay = itemView.findViewById(R.id.emailLay);
        }
    }
}
