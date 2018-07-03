package com.example.user.cbrinfo.courses;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.cbrinfo.R;
import com.example.user.cbrinfo.model.Valute;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Valute> courses;

    public CoursesAdapter(){
        courses = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        return new ViewHolderCourse(layoutInflater.inflate(R.layout.layout_courses, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderCourse viewHolderCourse = (ViewHolderCourse) holder;
        viewHolderCourse.getTextViewCharCode().setText(courses.get(position).getCharCode());
        viewHolderCourse.getTextViewValuteName().setText(courses.get(position).getName());
        viewHolderCourse.getTextViewNominal().setText("Курс " + courses.get(position).getNominal());
        viewHolderCourse.getTextViewValue().setText("Единиц " + courses.get(position).getValue());
        holder.getLayoutPosition();
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourseList(List<Valute> courseList) {
        courses = courseList;
        notifyDataSetChanged();
    }

    class ViewHolderCourse extends RecyclerView.ViewHolder {
        private TextView textViewCharCode;
        private TextView textViewValuteName;
        private TextView textViewValue;
        private TextView textViewNominal;

        public ViewHolderCourse(View itemView) {
            super(itemView);
            textViewCharCode = itemView.findViewById(R.id.charCode);
            textViewValuteName = itemView.findViewById(R.id.name);
            textViewValue = itemView.findViewById(R.id.value);
            textViewNominal = itemView.findViewById(R.id.nominal);
        }

        public TextView getTextViewCharCode() {
            return textViewCharCode;
        }

        public TextView getTextViewNominal() {
            return textViewNominal;
        }

        public TextView getTextViewValue() {
            return textViewValue;
        }

        public TextView getTextViewValuteName() {
            return textViewValuteName;
        }

    }
}
