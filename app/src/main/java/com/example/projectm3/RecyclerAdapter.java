package com.example.projectm3;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolderClss> {
    List<Data> list;
   Data calenderData;
    Context context;
    DaoCalender database;
    public RecyclerAdapter(List<Data> list, Context context) {
        this.context=context;
        this.list = list;
        database=RoomDb.instance.getDao();
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolderClss onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);
        return new ViewHolderClss(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolderClss holder, int position) {
        calenderData=list.get(position);
        holder.title.setText(calenderData.getEventName());
        holder.des.setText(calenderData.getDescription());
        holder.date.setText(calenderData.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolderClss extends RecyclerView.ViewHolder {
        TextView title;
        TextView des;
        TextView date;
        public ViewHolderClss(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.itemtitle);
            des=itemView.findViewById(R.id.itemdes);
            date=itemView.findViewById(R.id.itemdate);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(context);
                    alertDialog.setTitle("Delete!");
                    alertDialog.setMessage("Are you sure?");
                    alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                   Data calenderData=list.get(getAdapterPosition());
                                    database.deleteItem(calenderData);
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "Event Deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            })    .start();
                        }
                    });
                    alertDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "canceled!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialog.create();
                    alertDialog.show();

                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Data data = list.get(getAdapterPosition());
                    OpenDialog dialog = new OpenDialog(context, data.eventName, data.description, data.date);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();

                }
            });



        }
    }
}
