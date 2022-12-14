package com.example.crud.messages;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    TextView mobileNoTxt;
    TextView messageTxt;
    ImageButton cancelImgBtn;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTxt = itemView.findViewById(R.id.name_txt);
        mobileNoTxt = itemView.findViewById(R.id.mobile_no_txt);
        messageTxt = itemView.findViewById(R.id.message_txt);
        cancelImgBtn = itemView.findViewById(R.id.cancel_img_btn);
    }
}
