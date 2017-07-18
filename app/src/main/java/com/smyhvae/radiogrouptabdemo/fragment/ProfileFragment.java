package com.smyhvae.radiogrouptabdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smyhvae.radiogrouptabdemo.R;


/**
 * Created by smyh on 2015/4/28.
 */
public class ProfileFragment extends Fragment {
    private EditText userName;
    private EditText userPsd;
    private Button loginButton;
    private FragmentManager manager;
    private FragmentTransaction ft;

    private TextView userInfo;
    private TextView userSex;
    private TextView userPhone;
    private TextView userCarId;

    private LinearLayout login_layout;
    private LinearLayout user_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        userName = (EditText) view.findViewById(R.id.user_name);
        userPsd = (EditText) view.findViewById(R.id.user_psd);
        loginButton = (Button)view.findViewById(R.id.user_login);

        userInfo = (TextView) view.findViewById(R.id.u_username_info);
        userSex = (TextView)view.findViewById(R.id.u_sex_info);
        userPhone = (TextView) view.findViewById(R.id.u_phone_info);
        userCarId = (TextView) view.findViewById(R.id.u_carId_info);

        login_layout = (LinearLayout) view.findViewById(R.id.login_layout);
        user_layout = (LinearLayout) view.findViewById(R.id.user_layout);

        return view;
    }
    //重写setMenuVisibility方法，不然会出现叠层的现象
    @Override
    public void setMenuVisibility(boolean menuVisibile) {
        super.setMenuVisibility(menuVisibile);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisibile ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (isUserNameAndPwdValid()){
                    Toast.makeText(getActivity(), getString(R.string.login_success), Toast.LENGTH_SHORT).show();

                    login_layout.setVisibility(View.GONE);
                    user_layout.setVisibility(View.VISIBLE);
                    userInfo.setText("邱程");
                    userSex.setText("男");
                    userPhone.setText("18511333856");
                    userCarId.setText("鄂A-W8888");
                }else {
                    Toast.makeText(getActivity(), getString(R.string.namePsd_empty), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isUserNameAndPwdValid() {
        String userName1 = userName.getText().toString().trim();
        String userPsd1 = userPsd.getText().toString().trim();
        //用户名和密码不得为空
        if (userName1.equals("") || userPsd1.equals("")) {
            return false;
        } else if (userName1.equals("qiucheng") && userPsd1.equals("123456")) {
            return true;
        }else {
            return false;
        }
    }


}

