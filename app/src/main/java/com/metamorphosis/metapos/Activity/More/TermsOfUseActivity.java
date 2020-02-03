package com.metamorphosis.metapos.Activity.More;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.metamorphosis.metapos.R;
import com.metamorphosis.metapos.Utils.CustomFont;

public class TermsOfUseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_use);

        Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
        toolbarTitle.setTypeface(CustomFont.setRegular(getApplicationContext()));

        TextView tvFullText = (TextView) findViewById(R.id.tvFullText);
        tvFullText.setTypeface(CustomFont.setRegular(getApplicationContext()));
    }
}
