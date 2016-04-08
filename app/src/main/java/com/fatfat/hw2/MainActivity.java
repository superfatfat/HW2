package com.fatfat.hw2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Phone> phoneList;
    private ListView lvPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPhoneList();
        lvPhone = (ListView)findViewById(R.id.lvPhone);
        lvPhone.setAdapter(new PhoneAdapter(this,phoneList));
        lvPhone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Phone phone = (Phone)parent.getItemAtPosition(position);
                String text = phone.getName()+" 價格:" + phone.getPrice();
                Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setPhoneList(){
        phoneList = new ArrayList<>();
        phoneList.add(new Phone(R.drawable.apple_iphone_se,"Apple iPhone SE",15500));
        phoneList.add(new Phone(R.drawable.htc_one_x9_dual_sim,"HTC One X9 dual sim",12600));
        phoneList.add(new Phone(R.drawable.lg_k10,"LG K10",4500));
        phoneList.add(new Phone(R.drawable.samsung_galaxy_j3,"Samsung Galaxy J3",3900));
        phoneList.add(new Phone(R.drawable.samsung_galaxy_s7_edge,"Samsung Galaxy S7 Edge",22900));
        phoneList.add(new Phone(R.drawable.huawei_gr5,"HUAWEI GR5",6700));
        phoneList.add(new Phone(R.drawable.acer_liquid_z630s,"Acer Liquid Z630s",5690));
        phoneList.add(new Phone(R.drawable.asus_zenfone_2_deluxe,"ASUS ZenFone 2 Deluxe",7300));
        phoneList.add(new Phone(R.drawable.infocus_m372,"InFocus M372",3400));
        phoneList.add(new Phone(R.drawable.meitu_m4,"Meitu M4",10390));
        phoneList.add(new Phone(R.drawable.oppo_f1,"OPPO F1",4200));

    }

    private class PhoneAdapter extends BaseAdapter {
        private Context context;
        private List<Phone> phoneList;
        private LayoutInflater layoutInflater;
        public PhoneAdapter(Context context, List<Phone> phoneList) {
            this.context = context;
            this.phoneList = phoneList;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return phoneList.size();
        }

        @Override
        public Object getItem(int position) {
            return phoneList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return phoneList.get(position).getImage();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.phone_layout,parent,false);
            }
            final Phone phone = phoneList.get(position);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.ivPhone);
            imageView.setImageResource(phone.getImage());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView image = new ImageView(MainActivity.this);
                    image.setImageResource(phone.getImage());
                    new AlertDialog.Builder(MainActivity.this).setView(image).show();
                }
            });
            TextView tvPhoneName = (TextView) convertView.findViewById(R.id.tvPhoneName);
            tvPhoneName.setText(phone.getName());
            tvPhoneName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,((TextView)v).getText(),Toast.LENGTH_SHORT).show();
                }
            });
            TextView tvPhonePrice = (TextView) convertView.findViewById(R.id.tvPhonePrice);
            tvPhonePrice.setText(phone.getPrice());
            tvPhonePrice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,((TextView)v).getText(),Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }
    }
}
