package com.metamorphosis.metapos.Activity.More;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.metamorphosis.metapos.R;
import com.metamorphosis.metapos.Utils.AnimatedExpandableListView;
import com.metamorphosis.metapos.Utils.CustomFont;

import java.util.ArrayList;
import java.util.List;

public class FAQActivity extends AppCompatActivity {

    private AnimatedExpandableListView listViewExpandable;
    private TextView tvToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tvToolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
        tvToolbarTitle.setTypeface(CustomFont.setRegular(getApplicationContext()));



        createExpandableListView();
    }
    private void createExpandableListView() {

        List<GroupItem> items = new ArrayList<GroupItem>();

        // Populate our list with groups and it's children
        for (int i = 0; i <= 16; i++) {
            GroupItem item = new GroupItem();

            //item.title = "Group " + i;
            if (i == 0) {
                item.title = "মেটাপজ কি ডেস্কটপ না ওয়েব ভিত্তিক সফটওয়্যার?";
                ChildItem child0 = new ChildItem();
                child0.title = "আমাদের সফটওয়্যারটি একটি ওয়েব ভিত্তিক সফটওয়্যার যার সাথে রয়েছে একটি এ্যান্ড্রয়েড মোবাইল এ্যাপ। এটি ডেস্কটপ ভিত্তিক সফটওয়্যার নয়। দিন দিন ওয়েব ভিত্তিক সফটওয়্যারগুলি জনপ্রিয় হয়ে উঠছে কারণ সফটওয়্যারটি যে কোনও ডিভাইসের মাধ্যমে যেকোনো স্থান থেকে ব্যবহার করা যায় এবং ডাটা অনলাইনে সুরক্ষিত থাকে।";
                item.items.add(child0);

            } else if (i == 1) {
                item.title = "এটা কি ওপেন সোর্স সফটওয়্যার দিয়ে ডেভেলাপ করা হয়েছে?";
                ChildItem child0 = new ChildItem();
                //child0.title = "The app uses Odoo ERP platform as the architecture and all product information are coming from Odoo ERP (Community Edition). Learn more about Odoo at https://odoo.com";


                child0.title = "সফটওয়্যারটির মূল ইঞ্জিন কোনো ওপেন সোর্স সফটওয়্যার ব্যাবহার করে ডেভেলাপ করা হয়নি। এটি সম্পূর্ণ আমাদের কোম্পানিতে কর্মরত সফটওয়্যার ইঞ্জিনিয়ার দিয়ে বানানো সফটওয়্যার।";
                item.items.add(child0);

            } else if (i == 2) {
                item.title = "আমি কি সফটওয়্যারটি এককালীন কিনতে পারি?";
                ChildItem child0 = new ChildItem();
                child0.title = "দুঃখিত, আমরা সফটওয়্যারটি এককালীন বিক্রি করছি না, আমরা এটি সাবস্ক্রিপশন ভিত্তিক সফটওয়্যার এ্যাজ সার্ভিস / SAAS হিসাবে বিক্রি করছি।";
                item.items.add(child0);

            } else if (i == 3) {
                item.title = "মেটাপজ সফটওয়্যারটির মূল্য কত?";
                ChildItem child0 = new ChildItem();
                child0.title = "সফটওয়্যারের দাম আসলে ফিচারচস এর উপর নির্ভর করে।  ফিচারসগুলোকে সমুন্নয় করে প্যাকেজ তৈরি করি। সেই প্যাকেজ অনুযায়ী মূল্য নির্ধারিত হয়। আমরা চাই সবাই যেন স্বল্প মূল্যে সফটওয়্যার ব্যবহার করতে পারে।";
                item.items.add(child0);

            } else if (i == 4) {
                item.title = "আমাদের কি মাসিক নাকি বাৎসরিক টাকা প্রদান করতে হবে?";
                ChildItem child0 = new ChildItem();
                child0.title = "সফটওয়ারের সার্ভিস চার্জ মাসিক, ত্রৈমাসিক অথবা বাৎসরিক দিতে পারবেন যা আলোচনা সাপেক্ষে নির্ধারণ করা হবে। অগ্রিম বাৎসরিক পেমেন্ট করলে দুই মাসের সার্ভিস চার্জ ডিসকান্ট পাবেন।";
                item.items.add(child0);

            }else if (i == 5) {
                item.title = "কেন আপনাদেরকে সফটওয়্যার সার্ভিস চার্জ দিতে হবে?";
                ChildItem child0 = new ChildItem();
                child0.title = "যে কোনো ধরণের সেবা গ্রহণ করতে হলে আপনাকে অবশ্যই সার্ভিস চার্জ দিতে হবে। ঠিক একইভাবে সফটওয়্যারটি ব্যবহার করতে হলে আপনাকে সার্ভিস চার্জ প্রদান করতে হবে। আমাদের ডেভেলপমেন্ট টিম ও সাপোর্ট টিম কর্তৃক সফটওয়্যার সংক্রান্ত সমস্যার সমাধান, নতুন প্রযুক্তিসমূহ নবায়ন, ডাটা নিরাপত্তা, ডাটা ব্যাকআপ এবং সর্বদা সার্বিক সহায়তা প্রদান করা হয়, যার বিপরীতে নূন্যতম পরিমাণ সার্ভিস চার্জ নেওয়া হয়। তাছাড়া এই সার্ভিস চার্জের একটা বড় অংশই সার্ভার খরচ এ চলে যায়।";
                item.items.add(child0);

            }else if (i == 6) {
                item.title = "সফটওয়্যারটি ব্যবহারের জন্য কি ইন্টারনেট সংযোগ প্রয়োজন?";
                ChildItem child0 = new ChildItem();
                child0.title = "হ্যাঁ, আমাদের সফটওয়্যারটি ব্যবহারের জন্য অবশ্যই ইন্টারনেট সংযোগ প্রয়োজন হবে। আমাদের সফটওয়্যারটি দ্রুত ও হালকা হওয়ায় অল্প ব্যান্ডউইথ ইন্টারনেট সংযোগের মাধ্যমে সহজেই ব্যবহার করতে পারবেন। মোবাইল এ্যাপে লগইন ও ডাটা দেখার জন্য অবশ্যই ইন্টারনেট কানেকশন প্রয়োজন।";
                item.items.add(child0);

            }else if (i == 7) {
                item.title = "ইন্টারনেট সংযোগ চলে গেলে কি আমি সফটওয়্যারটি ব্যাবহার করতে পারবো?";
                ChildItem child0 = new ChildItem();
                child0.title = "ডেস্কটপ বা ল্যাপটপে অবশ্যই পারবেন। আমাদের সফটওয়্যারটি  ইন্টারনেট সংযোগ বিচ্ছিন্ন হলেও অফলাইন ফিচারের মাধ্যমে ব্যবহার করা যায়। সেক্ষেত্রে আপনাকে আমাদের অনলাইন/অফলাইন সিঙ্কিং ফিচারসটি নিতে হবে।  ইন্টারনেট সংযোগ আসলে অফলাইন এর ডাটা অনলাইন এ প্রেরণ করা হয়।";
                item.items.add(child0);

            }else if (i == 8) {
                item.title = "আমার কি এককালীন ইনস্টলেশন চার্জ প্রদান করতে হবে?";
                ChildItem child0 = new ChildItem();
                child0.title = "আপনি যদি আমাদের সফটওয়্যার ব্যবহার করতে আগ্রহী হন তবে আপনাকে এক-বার ইনস্টলেশন চার্জ এবং সফটওয়্যার সার্ভিস চার্জ দিতে হবে যা মাসিক, ত্রৈমাসিক বা বাৎসরিক প্রদান করা যেতে পারে। তবে আপনি চাইলে তিন বছরের সার্ভিস চার্জ একবারে পেমেন্ট করতে পারবেন। মাল্টিইয়ার সাবস্ক্রিপশন ফি পেমেন্টে ডিসকাউন্ট প্রযোজ্য।";
                item.items.add(child0);

            }else if (i == 9) {
                item.title = "আমাদের তথ্য কি আপনাদের কাছে নিরাপদ?";
                ChildItem child0 = new ChildItem();
                child0.title = "আমরা আপনাকে আশ্বস্ত করতে পারি যে আপনার ডাটা আমাদের নিকট সম্পূর্ন নিরাপদ। কারণ আপনার ডাটা সংরক্ষনের জন্য আমরা আর্ন্তজাতিক, সুরক্ষিত এবং কার্যকরী সার্ভার ব্যবহার করছি যা এসএসডি স্টোরেজ সিস্টেমে সংরক্ষিত থাকবে, যার ফলে ডাটা নিরাপত্তা নিশ্চিত করবে। আমরা আপনার তথ্য কারো সাথে শেয়ার করি না।";
                item.items.add(child0);

            }else if (i == 10) {
                item.title = "সফটওয়্যারটি ব্যবহার করতে আমাদের কি ধরণের ডিভাইস প্রয়োজন হবে?";
                ChildItem child0 = new ChildItem();
                child0.title = "প্রাথমিকভাবে, আপনি একটি ল্যাপটপ অথবা একটি ডেস্কটপ কিনলেই আমাদের সফটওয়্যারটি ব্যবহার করতে পারবেন। আমাদের সফটওয়্যারটি স্মার্টফোন ও  ট্যাবলেট ব্যবহার করা যায়। এছাড়া ব্যবসার মালিক হিসেবে আপনি আপনার আয় ব্যয় সেলস তথ্য সহজে দেখার জন্য আমাদের মোবাইল এ্যাপটি ব্যবহার করতে পারে।";
                item.items.add(child0);

            }else if (i == 11) {
                item.title = "যে কেউ কি সফটওয়্যারটি ব্যবহার করতে পারবে?";
                ChildItem child0 = new ChildItem();
                child0.title = "কম্পিউটার চালানো ব্যাপারে সাধারন জ্ঞান আছে এমন যে কোন ব্যক্তি এই সফটওয়্যারটি চালাতে পারবেন।";
                item.items.add(child0);

            }else if (i == 12) {
                item.title = "আমরা কি সফটওয়্যার থেকে কাস্টমারের কাছে এসএমএস পাঠাতে পারবো?";
                ChildItem child0 = new ChildItem();
                child0.title = "জ্বি আমাদের এসএমএস সেবা ব্যবহার করলে সংক্রিয় ভাবে কাস্টমারের কাছে  এসএমএস পাঠাতে পারবেন সেক্ষেত্রে এসএমএস এর বিল আলাদা প্রদন করতে হবে। আপনি মাস্কিং (আপনার ব্যবসার নাম দিয়ে) বা নন-মাস্কিং (কমন মোবাইল নাম্বার থেকে) দুইভাবেই এসএমএস পাঠাতে পারবেন।";
                item.items.add(child0);

            }else if (i == 13) {
                item.title = "সফটওয়্যারটি কি কাস্টোমাইজ করা যায়?";
                ChildItem child0 = new ChildItem();
                child0.title = "হ্যাঁ, আমরা আপনার প্রয়োজন অনুযায়ী সফটওয়্যারটি কাস্টমাইজ করে দিতে পারি সেক্ষেত্রে অতিরিক্ত চার্জ প্রদান করতে হবে আপনাকে যেটা সম্পূর্ণ নির্ভর করবে আপনার কাস্টমাইজেশনের ধরণের উপর।  সফটওয়্যারের মূল মডিউলগুলি কাস্টমাইজ করা যায় না।";
                item.items.add(child0);

            }else if (i == 14) {
                item.title = "আমরা এই সফটওয়্যার দিয়ে কি একাধিক বিজনেস পরিচালনা করতে পারবো?";
                ChildItem child0 = new ChildItem();
                child0.title = "অবশ্যই পারবেন। আমাদের ব্রাঞ্চ ম্যানেজমেন্ট ফিচারসের মাধ্যমে আপনি একধিক বিজনেস পরিচালনা করতে পারবেন।  আপনি এই  ফিচারসের মাধ্যমে ভিন্ন ধরণের একাধিক বিজনেস ও পরিচালনা করতে পারবেন।";
                item.items.add(child0);

            }else if (i == 15) {
                item.title = "সুপারশপ এর জন্য প্রয়োজনীয় হার্ডওয়্যারগুলো কি এই সফটওয়্যারটি সাপোর্ট করবে ?";
                ChildItem child0 = new ChildItem();
                child0.title = "হ্যাঁ অবশ্যই করবে।  সুপারশপের জন্য প্রয়োজন হয় Pos Printer, Barcode Scanner, Cash Drawer, POS Customer Display, POS, Weighing Scale এবং Barcode label Printers এগুলো প্রত্যেকটি ডিভাইস  সফটওয়্যারটির সাথে সংযুক্ত করা যায়।  সংযুক্ত  করতে  কোন সমস্যা হলে আমাদের সাপোর্ট টিমের কাছে সাহায্য নিতে পারবেন।";
                item.items.add(child0);

            }else if (i == 16) {
                item.title = "আপনাদের কাছে  কি প্রয়োজনীয় হার্ডওয়্যারগুলো পাওয়া যাবে ?";
                ChildItem child0 = new ChildItem();
                child0.title = "হ্যাঁ পাবেন। হার্ডওয়্যার বিষয়ে যে কোন প্রশ্ন থাকলে আমাদের কাস্টোমার সাপোর্টে যোগাযোগ করতে পারেন।";
                item.items.add(child0);

            }

            items.add(item);
        }

        ExampleAdapter adapterEx = new ExampleAdapter(getApplicationContext());
        adapterEx.setData(items);

        listViewExpandable = (AnimatedExpandableListView) findViewById(R.id.listViewExpandablee);
        listViewExpandable.setAdapter(adapterEx);
        listViewExpandable.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (listViewExpandable.isGroupExpanded(groupPosition)) {
                    listViewExpandable.collapseGroupWithAnimation(groupPosition);
                } else {
                    listViewExpandable.expandGroupWithAnimation(groupPosition);
                }

                return true;

            }
        });
        listViewExpandable.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                switch (groupPosition) {
                    case 1:
                        if (childPosition == 0) {
                            String url = "https://metaposbd.com";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                        break;
                }

                return true;
            }
        });

    }

    private static class GroupItem {
        String title;
        List<ChildItem> items = new ArrayList<ChildItem>();
    }

    private static class ChildItem {
        String title;
        String hint;
    }

    private static class ChildHolder {
        TextView title;
        TextView hint;
    }

    private static class GroupHolder {
        TextView title;
    }

    private class ExampleAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
        private LayoutInflater inflater;

        private List<GroupItem> items;

        public ExampleAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void setData(List<GroupItem> items) {
            this.items = items;
        }

        @Override
        public ChildItem getChild(int groupPosition, int childPosition) {
            return items.get(groupPosition).items.get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder holder;
            ChildItem item = getChild(groupPosition, childPosition);
            if (convertView == null) {
                holder = new ChildHolder();
                convertView = inflater.inflate(R.layout.list_item, parent, false);
                holder.title = (TextView) convertView.findViewById(R.id.textTitle);
                //holder.hint = (TextView) convertView.findViewById(R.id.textHint);
                holder.title.setTypeface(CustomFont.setRegular(getApplicationContext()));
                convertView.setTag(holder);
            } else {
                holder = (ChildHolder) convertView.getTag();
            }

//            holder.title.setText(item.title);
            holder.title.setText(Html.fromHtml(item.title));
            //holder.hint.setText(item.hint);

            return convertView;
        }

        @Override
        public int getRealChildrenCount(int groupPosition) {
            return items.get(groupPosition).items.size();
        }

        @Override
        public GroupItem getGroup(int groupPosition) {
            return items.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return items.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupHolder holder;
            GroupItem item = getGroup(groupPosition);
            if (convertView == null) {
                holder = new GroupHolder();
                convertView = inflater.inflate(R.layout.group_item, parent, false);
                holder.title = (TextView) convertView.findViewById(R.id.textTitle);
                holder.title.setTypeface(CustomFont.setSemiBold(getApplicationContext()));
                convertView.setTag(holder);
            } else {
                holder = (GroupHolder) convertView.getTag();
            }

            holder.title.setText(item.title);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int arg0, int arg1) {
            return true;
        }

    }
}
