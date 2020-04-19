package com.hebccc.views.selector;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hebccc.common.base.model.KeyValue;
import com.hebccc.common.util.ClickEventUtil;
import com.hebccc.common.util.MetricsUtil;
import com.hebccc.views.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author John
 * @Copyright 河北省通信建设有限公司网络分公司
 * @Description $desc$
 * @date 2019/3/18 9:21
 */
public class BottomSelector {
    private MaterialDialog dialog;
    private BottomDialogAdapter adapter;
    private OnItemClickListener listener;
    private DisplayMetrics displayMetrics;
    private View view;
    private boolean reverse = false;//keyvalue互换展示

    public BottomSelector(Context context) {
        if (context == null || !(context instanceof Activity)) {
            return;
        }
        adapter = new BottomDialogAdapter();
        displayMetrics = context.getResources().getDisplayMetrics();
        this.dialog = new MaterialDialog.Builder(context).adapter(adapter, new LinearLayoutManager(context)).build();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().getAttributes().width = displayMetrics.widthPixels;
        dialog.getRecyclerView().addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public void setDatas(List<? extends KeyValue> datas) {
        if (adapter != null) {
            adapter.setDatas(datas);
        }
    }

    public List<? extends KeyValue> getDatas() {
        if (adapter != null) {
            return adapter.getDatas();
        } else {
            return new ArrayList<>();
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void show(View view) {
        if (adapter != null && dialog != null) {
            setDialogHeight(adapter.getDatas(), dialog);
            dialog.show();
            this.view = view;
        }
    }

    public void dismiss() {
        if (dialog != null) dialog.dismiss();
    }

    class BottomDialogAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<? extends KeyValue> datas = new ArrayList<>();

        public void setDatas(List<? extends KeyValue> datas) {
            if (datas == null) {
                this.datas = new ArrayList<>();
            } else {
                this.datas = datas;
            }
            notifyDataSetChanged();
        }

        public List<? extends KeyValue> getDatas() {
            if (datas == null) {
                return new ArrayList<>();
            } else {
                return datas;
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.tvContent.setTextColor(ContextCompat.getColor(holder.tvContent.getContext(), R.color.colorBlack3));
            holder.tvContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            int padding = MetricsUtil.dp2px(holder.tvContent.getContext(), 8);
            holder.tvContent.setPadding(padding, padding, padding, padding);
            holder.tvContent.setText(setText(datas.get(position)));
            holder.tvContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ClickEventUtil.isFastClick()) {
                        Log.i(BottomSelector.class.getSimpleName(), "onClick: this is a fast click.");
                        return;
                    }
                    if (listener != null) listener.onItemClick(view, position, datas.get(position));
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

    public CharSequence setText(KeyValue data) {
        if (reverse) {
            return data.getKey();
        } else {
            return data.getValue();
        }
    }

    /**
     * 设置 dialog的高度
     * 可根据list的条数来设置高度
     *
     * @param dialog
     */
    private void setDialogHeight(List list, MaterialDialog dialog) {
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        if (list.size() > 5) p.height = (int) (displayMetrics.heightPixels * 0.6);//设置为当前屏幕高度的一半
        if (list.size() <= 6) p.height = RecyclerView.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(p);     //设置生效
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_dialog_content);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, KeyValue data);
    }
}
