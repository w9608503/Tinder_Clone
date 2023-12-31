package com.thecode.tinderclone.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.thecode.tinderclone.R

class StartActivity : AppCompatActivity() {
    var btnFacebook: Button? = null
    lateinit var btnPhoneValidation: Button
    var mContext: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        mContext = this
        btnFacebook = findViewById(R.id.btn_facebook_connect)
        btnPhoneValidation = findViewById(R.id.btn_number_connect)
        btnPhoneValidation.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(mContext, PhoneVerificationActivity::class.java)
            startActivity(intent)
            finish()
        })
    } /*public void setSpannableStringText() {

        SpannableString ss = new SpannableString(getResources().getString(R.string.start_text_advice));
        ClickableSpan clickableSpanTerms = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //startActivity(new Intent(StartActivity.this, TermsActivity.class));
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan clickableSpanPolicy = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //startActivity(new Intent(StartActivity.this, PrivacyPolicyActivity.class));
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickableSpanTerms, 51, 63, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpanPolicy, 68, 82, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 51, 63, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 68, 82, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = findViewById(R.id.text_policy_terms);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.TRANSPARENT);

    }*/
}