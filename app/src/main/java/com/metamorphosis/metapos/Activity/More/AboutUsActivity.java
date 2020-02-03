package com.metamorphosis.metapos.Activity.More;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.metamorphosis.metapos.R;
import com.metamorphosis.metapos.Utils.CustomFont;

public class AboutUsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle, tvAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        tvAboutUs = (TextView) findViewById(R.id.tvAboutUs);

        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
        toolbarTitle.setTypeface(CustomFont.setRegular(getApplicationContext()));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        String habitnumber = "<b>" + "MetaPOS" + "</b> " + "is an Business Mobile App.<br/><br/>" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.<br/><br/>" +
                "<b>" + "About MetaKave." + "</b>" + "<br/>" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br/><br/>" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.<br/><br/>" +
                "- Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br/>" +
                "- Tempus imperdiet nulla malesuada pellentesque.<br/>" +
                "- Tempus imperdiet nulla malesuada pellentesque. Lobortis mattis aliquam faucibus purus in massa tempor nec feugiat.<br/>" +
                "<b>" + "Contact" + "</b><br/>" +
                "Email: sadiq@metakave.com<br/>" +
                "Phone: +8801711 056 474<br/>" +
                "Address: MetaKave HQ, Suite 402, House 24, Section 6, Avenue 5<br/>" +
                "Block D, Mirpur, Dhaka 1216, Bangladesh<br/><br/><br/>" +
                "Copyright (c) 2019 MetaKave.<br/>";
        tvAboutUs.setText(Html.fromHtml(habitnumber));
        tvAboutUs.setTypeface(CustomFont.setRegular(getApplicationContext()));
    }
}
