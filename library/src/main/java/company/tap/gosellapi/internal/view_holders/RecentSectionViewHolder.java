package company.tap.gosellapi.internal.view_holders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import company.tap.gosellapi.R;
import company.tap.gosellapi.internal.adapters.PaymentOptionsRecyclerViewAdapter;
import company.tap.gosellapi.internal.adapters.RecentPaymentsRecyclerViewAdapter;
import company.tap.gosellapi.internal.api.models.Card;
import company.tap.gosellapi.internal.data_source.payment_options.PaymentOptionsBaseModel;

public class RecentSectionViewHolder extends PaymentOptionsBaseViewHolder<PaymentOptionsBaseModel<ArrayList<Card>>>
        implements RecentPaymentsRecyclerViewAdapter.RecentPaymentsRecyclerViewAdapterListener {
    RecentSectionViewHolder(View itemView, PaymentOptionsViewHolderFocusedStateInterface focusedStateInterface, PaymentOptionsRecyclerViewAdapter.PaymentOptionsViewAdapterListener adapterListener) {
        super(itemView, focusedStateInterface, adapterListener);
    }

    private RecyclerView recentPaymentsRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecentPaymentsRecyclerViewAdapter adapter;
    private PaymentOptionsBaseModel<ArrayList<Card>> data;

    @Override
    public void bind(PaymentOptionsBaseModel<ArrayList<Card>> data) {
        if (recentPaymentsRecyclerView == null) {
            recentPaymentsRecyclerView = itemView.findViewById(R.id.recentPaymentsRecyclerView);
        }

        if (linearLayoutManager == null) {
            linearLayoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            recentPaymentsRecyclerView.setLayoutManager(linearLayoutManager);
        }

        if (adapter == null) {
            adapter = new RecentPaymentsRecyclerViewAdapter(data.getData(), this);
            recentPaymentsRecyclerView.setAdapter(adapter);
        }

//        if (this.data == null || this.data != data) {
//            this.data = data;
//
//        }
    }

    @Override
    public void setFocused(boolean isFocused) {
        if (adapter != null) {
            adapter.setFocused(isFocused);
        }
    }

    @Override
    public void recentPaymentItemClicked(int position, Card card) {
        focusedStateInterface.setFocused(getAdapterPosition());
        adapterListener.recentPaymentItemClicked(position, card);
    }

    @Override
    public void unbind() {
//        recentPaymentsRecyclerView.setAdapter(null);
    }
}
