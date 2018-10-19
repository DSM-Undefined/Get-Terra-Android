package undefined.dsm.getterra.ui;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import undefined.dsm.getterra.R;

public class SelectFragment extends Fragment {
    TextView s1, s2, s3, s4;
    boolean st[] = new boolean[4];

    public SelectFragment(){
    }

    public interface SendDataSelect{
        void sendDataSelect(boolean select[]);
    }

    private SendDataSelect sendDataSelect;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if(context instanceof SendDataSelect){
            sendDataSelect = (SendDataSelect) context;
        } else{
            throw new RuntimeException(context.toString() + "must implement");
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        sendDataSelect = null;
    }

    Bundle bundle = getArguments();
    String fSel = bundle.getString("f_sel");
    String sSel = bundle.getString("s_sel");
    String tSel = bundle.getString("t_sel");
    String oSel = bundle.getString("o_sel");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState){
        for(int i=0; i<4; i++){
            st[i] = false;
        }
        View view = inflater.inflate(R.layout.fragment_select, container, false);
        s1 = view.findViewById(R.id.quiz_selectfirst_tv);
        s2 = view.findViewById(R.id.quiz_selectsecond_tv);
        s3 = view.findViewById(R.id.quiz_selectthird_tv);
        s4 = view.findViewById(R.id.quiz_selectfourth_tv);

        s1.setText(fSel);
        s2.setText(sSel);
        s3.setText(tSel);
        s4.setText(oSel);

        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch (v.getId())
                {

                    case R.id.quiz_selectfirst_tv:
                        for(int i=0; i<4; i++)
                        {
                            if(i==0) st[i] = true;
                            else st[i] = false;
                        }
                        break;
                    case R.id.quiz_selectsecond_tv:
                        for(int i=0; i<4; i++)
                        {
                            if(i==1) st[i] = true;
                            else st[i] = false;
                        }
                        break;
                    case R.id.quiz_selectthird_tv:
                        for(int i=0; i<4; i++)
                        {
                            if(i==2) st[i] = true;
                            else st[i] = false;
                        }
                        break;
                    case R.id.quiz_selectfourth_tv:
                        for(int i=0; i<4; i++)
                        {
                            if(i==3) st[i] = true;
                            else st[i] = false;
                        }
                        break;
                }
                sendDataSelect.sendDataSelect(st);
                selectAnimation(s1, st[0]);
                selectAnimation(s2, st[1]);
                selectAnimation(s3, st[2]);
                selectAnimation(s4, st[3]);
            }
        };
        s1.setOnClickListener(listener);
        s2.setOnClickListener(listener);
        s3.setOnClickListener(listener);
        s4.setOnClickListener(listener);
        return view;
    }
    public void selectAnimation(TextView t, boolean b){
        if(b==true)
        {
            t.setTextColor(0xffffffff);
            t.setBackgroundColor(0xffffb587);
            t.setBackgroundResource(R.drawable.round_background_reverse);
        }
        else
        {
            t.setTextColor(0xffffb587);
            t.setBackgroundColor(0xffffffff);
            t.setBackgroundResource(R.drawable.round_background);
        }
    }

}
