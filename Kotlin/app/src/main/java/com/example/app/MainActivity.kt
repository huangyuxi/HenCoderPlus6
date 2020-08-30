package com.example.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.app.widget.CodeView
import com.example.core.utils.CacheUtils
import com.example.core.utils.Utils
import com.example.lesson.LessonActivity
import okhttp3.internal.Util

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val userNameKey = "userName"
    private val passwordKey = "password"

    private lateinit var et_username: EditText
    private lateinit var et_password: EditText
    private lateinit var et_code: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_code = findViewById(R.id.et_code)

        et_username.setText(CacheUtils.get(userNameKey))
        et_password.setText(CacheUtils.get(passwordKey))

        val btn_login: Button = findViewById<Button>(R.id.btn_login)
        val img_code: CodeView = findViewById<CodeView>(R.id.code_view)
        btn_login.setOnClickListener(this)
        img_code.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    private fun login() {
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        val code = et_code.text.toString()

        val user: User = User(username, password, code)

        if (verify(user)) {
            CacheUtils.save(userNameKey, username)
            CacheUtils.save(passwordKey, password)
            startActivity(Intent(this, LessonActivity::class.java))
        }

    }

    private fun verify(user: User): Boolean {
        if (user.username == null || user.username!!.length<4){
            Utils.toast("用户名不合法")
            return false
        }
        if (user.password == null || user.password!!.length<4){
            Utils.toast("密码不合法")
            return false
        }

        return true
    }


}