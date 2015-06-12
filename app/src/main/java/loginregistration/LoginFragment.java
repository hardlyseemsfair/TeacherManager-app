package loginregistration;


import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.classroom.applicationactivity.R;

import util.Config;

public class LoginFragment extends Fragment {

    OnLoginRegisterInterface mCallback;
    String[] server = {"HOME", "TESTHOT", "CUSTOM"};

    public static final LoginFragment newInstance() {
        LoginFragment lf = new LoginFragment();
        Bundle args = new Bundle();
        lf.setArguments(args);
        return lf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Importing all assets like buttons, text fields
        final EditText inputUsername = (EditText) view.findViewById(R.id.loginUsername);
        final EditText inputPassword = (EditText) view.findViewById(R.id.loginPassword);
        Button btnLogin = (Button) view.findViewById(R.id.btnLogin);
        Button btnLinkToRegister = (Button) view.findViewById(R.id.btnLinkToRegisterScreen);
        TextView loginErrorMsg = (TextView) view.findViewById(R.id.login_error);
        // Login button Click Event
//        final EditText first = (EditText) view.findViewById(R.id.first);
//        first.setVisibility(View.GONE);
//        final EditText second = (EditText) view.findViewById(R.id.second);
//        second.setVisibility(View.GONE);
//        final EditText third = (EditText) view.findViewById(R.id.third);
//        third.setVisibility(View.GONE);
//        final EditText fourth = (EditText) view.findViewById(R.id.fourth);
//        fourth.setVisibility(View.GONE);
//        final SharedPreferences settings = getActivity().getSharedPreferences(Config.PREFS_FILE, 0);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                String connect;
//                String connect_dir;
//                String serverString = serverSpinner.getSelectedItem().toString();
//                SharedPreferences.Editor editor = settings.edit();
//                if(serverString.equals("HOME")){
//                    connect = Config.HOME_CONNECT_IP + Config.CONNECT_EXT;
//                    connect_dir = Config. HOME_CONNECT_IP + Config.DIR_EXT;
//                    editor.putString(Config.CONNECT_IP, connect);
//                    editor.putString(Config.CONNECT_DIR, connect_dir);
//                } else if (serverString.equals("TESTHOT")){
//                    connect = Config.TESTHOT_CONNECT_IP + Config.CONNECT_EXT;
//                    connect_dir = Config. TESTHOT_CONNECT_IP + Config.DIR_EXT;
//                    editor.putString(Config.CONNECT_IP, connect);
//                    editor.putString(Config.CONNECT_DIR, connect_dir);
//                } else if (serverString.equals("CUSTOM")){
//                    String ip =
//                }

                LoginTask loginTask = new LoginTask(getActivity());
                loginTask.execute(inputUsername.getText().toString(),inputPassword.getText().toString() );
            }
        });
        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.setRegisterFragment();
            }
        });
        return view;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (OnLoginRegisterInterface) activity;
    }


}
