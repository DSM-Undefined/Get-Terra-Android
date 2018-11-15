package undefined.dsm.getterra.ui;

import android.content.Context;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.justgo.Connecter.Connecter;

import undefined.dsm.getterra.R;

public class OXFragment extends Fragment {
    TextView o, x;
    boolean b[] = new boolean[2];

    public OXFragment() {

    }

    public interface SendDataOX {
        void sendDataOX(boolean OX[]);
    }

    private SendDataOX sendDataOX;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SendDataOX) {
            sendDataOX = (SendDataOX) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sendDataOX = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        b[0] = false;
        b[1] = false;
        View view = inflater.inflate(R.layout.fragment_ox, container, false);
        o = view.findViewById(R.id.quiz_answeriso_btn);
        x = view.findViewById(R.id.quiz_answerisx_btn);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.quiz_answeriso_btn:
                        b[0] = true;
                        b[1] = false;
                        break;
                    case R.id.quiz_answerisx_btn:
                        b[0] = false;
                        b[1] = true;
                        break;
                }
                sendDataOX.sendDataOX(b);
                selectAnimation(o, b[0]);
                selectAnimation(x, b[1]);
            }
        };
        o.setOnClickListener(listener);
        x.setOnClickListener(listener);
        return view;
    }

    public void selectAnimation(TextView t, boolean b) {
        if (b == true) {
            t.setTextColor(0xffffffff);
            t.setBackgroundColor(0xffffb587);
            t.setBackgroundResource(R.drawable.round_background_reverse);
        } else {
            t.setTextColor(0xffffb587);
            t.setBackgroundColor(0xffffffff);
            t.setBackgroundResource(R.drawable.round_background);
        }
    }
}
