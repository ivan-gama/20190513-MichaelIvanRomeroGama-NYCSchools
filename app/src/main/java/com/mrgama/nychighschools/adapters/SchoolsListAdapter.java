package com.mrgama.nychighschools.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mrgama.nychighschools.R;
import com.mrgama.nychighschools.model.School;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class SchoolsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<School> schoolList;
    private SchoolItemListener listener;

    public SchoolsListAdapter(ArrayList<School> schoolArrayList, SchoolItemListener listener) {
        this.schoolList = schoolArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemMenuView = inflater.inflate(R.layout.item_list_school, viewGroup, false);
        viewHolder = new ViewHolderItemMenu(itemMenuView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderItemMenu holderItem = (ViewHolderItemMenu) holder;
        configureViewHolderItemMenu(holderItem, position);
    }

    @Override
    public int getItemCount() {
        return schoolList.size();
    }

    private void configureViewHolderItemMenu(final ViewHolderItemMenu holder, final int position) {

        School school = schoolList.get(position);
        holder.textSchoolName.setText(school.getSchool_name());
        holder.textSchoolLocation.setText(school.getCity());

        String academicOp = school.getAcademic_opportunities1() + "\n" +
                school.getAcademic_opportunities2() + "\n";
        holder.textAcademicOp.setText(academicOp);
    }

    public interface SchoolItemListener {
        void onSchoolSelected(School school);
    }

    private class ViewHolderItemMenu extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textSchoolName;
        TextView textSchoolLocation;
        TextView textAcademicOp;
        ConstraintLayout clItemContainer;

        private ViewHolderItemMenu(View itemView) {
            super(itemView);

            textSchoolName = itemView.findViewById(R.id.text_schoolName);
            textSchoolLocation = itemView.findViewById(R.id.text_schoolLocation);
            textAcademicOp = itemView.findViewById(R.id.text_academicOp);
            clItemContainer = itemView.findViewById(R.id.cl_itemContainer);

            clItemContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            School school = schoolList.get(getAdapterPosition());
            listener.onSchoolSelected(school);
        }
    }
}