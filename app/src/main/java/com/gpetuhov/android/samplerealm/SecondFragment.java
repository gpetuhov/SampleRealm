package com.gpetuhov.android.samplerealm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gpetuhov.android.samplerealm.realm.UserTextItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmResults;

public class SecondFragment extends Fragment {

    @BindView(R.id.data) TextView mDataTextView;

    // Keeps ButterKnife Unbinder object to properly unbind views in onDestroyView of the fragment
    private Unbinder mUnbinder;

    // Keeps Realm instance
    private Realm mRealm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second, container, false);

        // Bind views and save reference to Unbinder object
        mUnbinder = ButterKnife.bind(this, v);

        // Get Realm instance
        mRealm = Realm.getDefaultInstance();

        RealmResults<UserTextItem> results = mRealm.where(UserTextItem.class).findAll();

        String resultsString = "";

        if (results.size() > 0) {
            for (UserTextItem result : results) {
                resultsString = resultsString.concat(result.getText() + "\n");
            }
        } else {
            resultsString = "No data";
        }

        mDataTextView.setText(resultsString);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // This is recommended to do here when using Butterknife in fragments
        mUnbinder.unbind();

        // All Realm instances must be closed.
        // We have 1 instance, which is opened in onCreateView(), and we close it here.
        mRealm.close();
    }
}
