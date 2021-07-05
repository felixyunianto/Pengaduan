package syaiful.kirom.pengaduanbanjaranyar.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import syaiful.kirom.pengaduanbanjaranyar.R
import syaiful.kirom.pengaduanbanjaranyar.contracts.LoginActivityContracts
import syaiful.kirom.pengaduanbanjaranyar.databinding.ActivityLoginBinding
import syaiful.kirom.pengaduanbanjaranyar.presenters.LoginActivityPresenter
import syaiful.kirom.pengaduanbanjaranyar.utilities.Constants

class LoginActivity : AppCompatActivity(), LoginActivityContracts.LoginActivityView {

    private var presenter : LoginActivityContracts.LoginActivityPresenter? = null

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = LoginActivityPresenter(this)
        supportActionBar?.hide()

        doLogin()
    }

    private fun doLogin(){
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if(email.isNotEmpty() && password.isNotEmpty()){
                presenter?.login(email, password, this@LoginActivity)
            }else{
                showToast("Tolong isi semua form")
            }
        }
    }

    override fun showToast(message: String) = Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()

    override fun successLogin() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java)).also{
            finish()
        }
    }

    override fun showLoading() {
        binding.btnLogin.isEnabled = false
        binding.loadingLogin.apply {
            isIndeterminate = true
        }
    }

    override fun hideLoading() {
        binding.btnLogin.isEnabled = true
        binding.loadingLogin.apply {
            isIndeterminate = false
            progress = 0
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    override fun onResume() {
        super.onResume()
        val token = Constants.getToken(this@LoginActivity)
        if(!token.equals("UNDEFINED")){
            startActivity(Intent(this@LoginActivity, MainActivity::class.java).also { finish() })
        }
    }
}