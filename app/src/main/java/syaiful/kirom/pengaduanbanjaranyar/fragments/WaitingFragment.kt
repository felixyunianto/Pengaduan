package syaiful.kirom.pengaduanbanjaranyar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_waiting.view.*
import syaiful.kirom.pengaduanbanjaranyar.R
import syaiful.kirom.pengaduanbanjaranyar.adapters.ApprovedAdapter
import syaiful.kirom.pengaduanbanjaranyar.adapters.WaitingAdapter
import syaiful.kirom.pengaduanbanjaranyar.contracts.InformasiActivityContracts
import syaiful.kirom.pengaduanbanjaranyar.models.Aduan
import syaiful.kirom.pengaduanbanjaranyar.presenters.InformasiActivityPresenter
import syaiful.kirom.pengaduanbanjaranyar.utilities.Constants

class WaitingFragment : Fragment(), InformasiActivityContracts.InformasiActivityView {

    private var presenter = InformasiActivityPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_waiting, container, false)
    }

    private fun getData(){
        val token = Constants.getToken(requireActivity())
        presenter?.allDataWaiting(token)
    }

    override fun attachAduanToRecycler(aduan: List<Aduan>) {
        requireView().rvWaiting.apply {
            val mlayoutManager = LinearLayoutManager(activity)
            layoutManager = mlayoutManager
            adapter = WaitingAdapter(aduan, requireActivity())
        }
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun showDataEmpty() {
        TODO("Not yet implemented")
    }

    override fun hideDataEmpty() {
        TODO("Not yet implemented")
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

}