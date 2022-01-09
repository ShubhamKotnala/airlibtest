package com.ample.airlib.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ample.airlib.R
import com.ample.airlib.data.LoginDatabase
import com.ample.airlib.data.model.login.LoginResponse
import com.ample.airlib.databinding.ActivityLoginBinding
import com.ample.airlib.network.RetrofitBuilder
import com.ample.airlib.ui.home.HomeActivity
import com.ample.airlib.utils.Status

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** binding ui components */
        val username = binding.editUsername
        val password = binding.editPassword
        val login = binding.login

        /** initialising database */
        val database by lazy { LoginDatabase.getDatabase(this) }

        /** initialising view model */
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(RetrofitBuilder.apiService,  database.loginDao()))[LoginViewModel::class.java]

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null)
                username?.error = getString(loginState.usernameError)

            if (loginState.passwordError != null)
                password?.error = getString(loginState.passwordError)
        })

        username?.afterTextChanged {
            loginViewModel.loginDataChanged(username.text.toString(), password?.text.toString())
        }

        password?.apply {
            this.afterTextChanged {
                loginViewModel.loginDataChanged(username?.text.toString(), password.text.toString())
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        if (login.isEnabled)
                            callLogin()
                    }
                }
                false
            }
        }

        login.setOnClickListener { callLogin() }
    }

    /** fun to call and observe login api */
    private fun callLogin() {
        loginViewModel.callLogin(binding.editUsername?.text.toString(),
            binding.editPassword?.text.toString()).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.loading.visibility = View.GONE
                        if (resource.data != null) {
                            resource.data.username = binding.editUsername?.text.toString()
                            saveDataToLocalStorage(resource.data)
                        } else Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
                    }
                    Status.ERROR -> {
                        binding.loading.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> binding.loading.visibility = View.VISIBLE
                }
            }
        })
    }

    /** fun to save data locally in db
     *  after login */
    private fun saveDataToLocalStorage(loginResponse: LoginResponse) {
        loginViewModel.insert(loginResponse).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    }
                    Status.ERROR -> {
                        binding.loading.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}
/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}