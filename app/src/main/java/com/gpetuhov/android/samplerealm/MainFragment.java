package com.gpetuhov.android.samplerealm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gpetuhov.android.samplerealm.realm.UserTextItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;

public class MainFragment extends Fragment {

    @BindView(R.id.new_list_item) EditText mNewItemEditText;

    // Add to list button
    @BindView(R.id.button_add) Button mAddButton;

    // Show all button
    @BindView(R.id.button_show_all) Button mShowAllButton;

    // Keeps ButterKnife Unbinder object to properly unbind views in onDestroyView of the fragment
    private Unbinder mUnbinder;

    // Keeps text, entered by user (new list item)
    private String mNewItemValue;

    // Keeps Realm instance
    private Realm mRealm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        // Bind views and save reference to Unbinder object
        mUnbinder = ButterKnife.bind(this, v);

        mNewItemEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Save entered text
                mNewItemValue = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing
            }
        });

        // Get Realm instance
        mRealm = Realm.getDefaultInstance();

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

    // Called when add button is clicked
    @OnClick(R.id.button_add)
    public void saveData() {

        // Data is written to Realm inside a transaction
        mRealm.beginTransaction();
        // Create new UserTextItem
        UserTextItem userTextItem = mRealm.createObject(UserTextItem.class);
        // Set text of the new UserTextItem
        userTextItem.setText(mNewItemValue);
        mRealm.commitTransaction();

        // Display toast message
        Toast.makeText(getActivity(), "Data added", Toast.LENGTH_SHORT).show();
    }

    // Called when show button is clicked
    @OnClick(R.id.button_show_all)
    public void showAllData() {

        // Create intent to start second activity
        Intent intent = new Intent(getActivity(), SecondActivity.class);

        // Start second activity
        startActivity(intent);
    }
}
