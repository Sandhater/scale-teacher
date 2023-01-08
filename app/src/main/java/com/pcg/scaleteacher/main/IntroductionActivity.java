package com.pcg.scaleteacher.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pcg.scaleteacher.R;
import com.pcg.scaleteacher.base.ConstantBase;

import java.util.zip.Inflater;

public class IntroductionActivity extends ConstantBase {

    private int currentBasicMode;
    private int currentStudyContent;
    private int currentMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        currentBasicMode = intent.getIntExtra(basicModeTag, BasicMode.FORMAL_STYLE);
        currentStudyContent = intent.getIntExtra(studyContentTag, StudyContent.STUDY_SIZE);
        initUI();
    }


    //根据具体场景更新UI
    private void initUI() {
        setContentView(R.layout.activity_introduction);

        TextView tipContent = findViewById(R.id.tip_content);
        TextView activeTab;
        switch (currentBasicMode) {
            case BasicMode.FORMAL_STYLE:
                LinearLayout parentLayout = findViewById(R.id.parent_layout);
                LayoutInflater inflater = this.getLayoutInflater();
                LinearLayout switchFooter = (LinearLayout) inflater.inflate(R.layout.formal_stdudy_switch_footer, null);
                runOnUiThread(() -> parentLayout.addView(switchFooter, new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, 200)));
                if (currentStudyContent == StudyContent.STUDY_SIZE) {
                    activeTab = findViewById(R.id.switch_to_size_formal);
                    activeTab.setTypeface(Typeface.create(activeTab.getTypeface(), Typeface.NORMAL), Typeface.BOLD);
                    tipContent.setText(R.string.size_formal_tip);
                }
                else if (currentStudyContent == StudyContent.STUDY_ANGLE) {
                    activeTab = findViewById(R.id.switch_to_angle_formal);
                    activeTab.setTypeface(Typeface.create(activeTab.getTypeface(), Typeface.NORMAL), Typeface.BOLD);
                    tipContent.setText(R.string.angle_formal_tip);
                }
                break;
            case BasicMode.FREE_STYLE:
                tipContent.setText(R.string.free_mode_tip);
                break;
            case BasicMode.TEST:
                tipContent.setText(R.string.test_mode_tip);
                break;
        }
    }

    /**********未完成************/
    public void startCurrentMode(View view) {
        Intent intent;
        Log.e("Debug", String.valueOf(currentBasicMode));
        switch (currentBasicMode) {
            case BasicMode.FORMAL_STYLE:
                intent = new Intent(this, InputStudyContentActivity.class);
                intent.putExtra(studyContentTag, currentStudyContent);
                startActivity(intent);
                break;
            case BasicMode.FREE_STYLE:
                break;
        }
    }

    public void switchStudyContent(View view) {
        int viewId = view.getId();
        TextView sizeTab = findViewById(R.id.switch_to_size_formal);
        TextView angleTab = findViewById(R.id.switch_to_angle_formal);
        TextView tipContent = findViewById(R.id.tip_content);
        if (currentStudyContent == StudyContent.STUDY_SIZE && viewId == R.id.switch_to_angle_formal) {
            sizeTab.setTypeface(null, Typeface.NORMAL);
            angleTab.setTypeface(Typeface.create(angleTab.getTypeface(), Typeface.NORMAL), Typeface.BOLD);
            tipContent.setText(R.string.angle_formal_tip);
            currentStudyContent = StudyContent.STUDY_ANGLE;
        }
        else if (currentStudyContent == StudyContent.STUDY_ANGLE && viewId == R.id.switch_to_size_formal) {
            sizeTab.setTypeface(Typeface.create(sizeTab.getTypeface(), Typeface.NORMAL), Typeface.BOLD);
            angleTab.setTypeface(null, Typeface.NORMAL);
            tipContent.setText(R.string.size_formal_tip);
            currentStudyContent = StudyContent.STUDY_SIZE;
        }
    }
}