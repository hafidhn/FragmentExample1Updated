package com.example.fragmentexample1updated;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimpleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleFragment extends Fragment {

    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NONE = 2;

    private int mCurrentChoice = NONE;
    private OnFragmentInteractionListener mListener;

    private static final String CHOICE_PARAM = "choice-param";

    interface OnFragmentInteractionListener {
        void onRadioButtonChoiceChecked(int choice);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
        else {
            throw new ClassCastException(getResources().getString(R.string.exception_message));
        }
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SimpleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SimpleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SimpleFragment newInstance(String param1, String param2) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static SimpleFragment newInstance() {
        SimpleFragment fragment = new SimpleFragment();
        return fragment;
    }

    public static SimpleFragment newInstance(int choice) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putInt(CHOICE_PARAM, choice);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_simple, container, false);
        RadioGroup radioGroup = view.findViewById(R.id.radio_group);
        TextView articleQuestionTextView = view.findViewById(R.id.question_textview);

        if (getArguments().containsKey(CHOICE_PARAM)) {
            mCurrentChoice = getArguments().getInt(CHOICE_PARAM);
            if (mCurrentChoice != 2) {
                radioGroup.check(radioGroup.getChildAt(mCurrentChoice).getId());
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton btn = radioGroup.findViewById(i);
                int selectedIndex = radioGroup.indexOfChild(btn);

                switch (selectedIndex) {
                    case YES :
                        articleQuestionTextView.setText(R.string.yes_message);
                        mCurrentChoice = YES;
                        mListener.onRadioButtonChoiceChecked(YES);
                        break;
                    case NO :
                        articleQuestionTextView.setText(R.string.no_message);
                        mCurrentChoice = NO;
                        mListener.onRadioButtonChoiceChecked(NO);
                        break;
                    default :
                        mCurrentChoice = NONE;
                        mListener.onRadioButtonChoiceChecked(NONE);
                        break;
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}