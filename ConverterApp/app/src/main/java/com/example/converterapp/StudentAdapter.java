package com.example.converterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter {

    private List mStudent;
    private Context mContext;

    public StudentAdapter(List mStudent, Context mcontext){
        this.mContext = mcontext;
        this.mContext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent .getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View studentView = inflater.inflate(R.layout.student_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(studentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

//    public class ViewHolder extends RecyclerView.ViewHolder{
//
//    }
}
