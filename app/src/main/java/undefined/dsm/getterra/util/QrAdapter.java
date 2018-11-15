package undefined.dsm.getterra.util;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import undefined.dsm.getterra.R;
import undefined.dsm.getterra.model.ListModle;
import undefined.dsm.getterra.ui.QrActivity;

public class QrAdapter extends RecyclerView.Adapter<QrAdapter.ViewHolder> {
    ArrayList<ListModle> listModles;

    public QrAdapter(ArrayList<ListModle> listModles) {
        this.listModles = listModles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_qr, viewGroup, false);
        return new QrAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ListModle listModle = listModles.get(i);
        viewHolder.list.setText(listModle.getList());
        if (listModle.isOur()) {
            switch (listModle.getType()) {
                case 0:
                    viewHolder.list.setTextColor(Color.parseColor("#a4d2e6"));
                    break;
                case 1:
                    viewHolder.list.setTextColor(Color.parseColor("#a4e6ab"));
                    break;
                case 2:
                    viewHolder.list.setTextColor(Color.parseColor("#ffec8f"));
                    break;
                case 3:
                    viewHolder.list.setTextColor(Color.parseColor("#d398ec"));
                    break;
                default:
                    viewHolder.list.setTextColor(Color.parseColor("#a4d2e6"));
                    break;
            }
        } else {
            viewHolder.list.setTextColor(Color.parseColor("#888888"));
        }

    }

    @Override
    public int getItemCount() {
        return listModles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView list;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            list = itemView.findViewById(R.id.item_qr_text);
        }
    }
}
