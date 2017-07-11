package com.showmethemoney.sejong.showmethemoney.main.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.showmethemoney.sejong.showmethemoney.R
import com.showmethemoney.sejong.showmethemoney.home.view.HomeActivity
import com.showmethemoney.sejong.showmethemoney.main.model.SigninPost
import com.showmethemoney.sejong.showmethemoney.main.presenter.MainContract
import com.showmethemoney.sejong.showmethemoney.main.presenter.MainPresenter
import com.showmethemoney.sejong.showmethemoney.signup.view.SignupActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //presenter.getLoginInfo()
        sign_in_button.setOnClickListener {
            signinRequest()
        }

        sign_up_button.setOnClickListener {
            moveToSignUp()
        }
    }


    private fun moveToSignUp() {
        var intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    override fun moveToHome() {
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun signinRequest(){
        presenter.signRequest(SigninPost(sign_in_id.text.toString(),sign_in_pw.text.toString()),sign_in_id.text.toString())
    }

    override fun makeToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun finishActivity() {
        finish()
    }
}
