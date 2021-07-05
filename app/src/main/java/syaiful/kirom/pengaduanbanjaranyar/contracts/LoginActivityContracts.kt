package syaiful.kirom.pengaduanbanjaranyar.contracts

import android.content.Context

interface LoginActivityContracts {
    interface LoginActivityView {
        fun showToast(message : String)
        fun successLogin()
        fun showLoading()
        fun hideLoading()
    }

    interface LoginActivityPresenter {
        fun login(email : String, password: String, context : Context)
        fun destroy()
    }
}