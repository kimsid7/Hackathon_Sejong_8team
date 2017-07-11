package com.showmethemoney.sejong.showmethemoney.signup.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.showmethemoney.sejong.showmethemoney.R
import com.showmethemoney.sejong.showmethemoney.signup.model.SignupPost
import com.showmethemoney.sejong.showmethemoney.signup.presenter.SignupContract
import com.showmethemoney.sejong.showmethemoney.signup.presenter.SignupPresenter
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity(), SignupContract.View {

    val presenter = SignupPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        sign_up_request_button.setOnClickListener {
            if(sign_up_passward.text.toString()==sign_up_passward_confirm.text.toString()) {
                presenter.signupRequest(SignupPost(sign_up_id.text.toString(), sign_up_passward.text.toString(), sign_up_major.text.toString(), sign_up_name.text.toString()))
            }else{
                makeToast("비밀번호가 서로 다릅니다!")
            }
        }
    }

    override fun makeToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun finishActivity() {
        finish()
    }
}
