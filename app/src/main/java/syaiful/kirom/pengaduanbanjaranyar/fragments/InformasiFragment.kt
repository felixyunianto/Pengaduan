package syaiful.kirom.pengaduanbanjaranyar.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.content_aduan.*
import kotlinx.android.synthetic.main.fragment_informasi.*
import syaiful.kirom.pengaduanbanjaranyar.R
import syaiful.kirom.pengaduanbanjaranyar.activities.CreateAduanActivity
import syaiful.kirom.pengaduanbanjaranyar.adapters.AduanAdapter
import syaiful.kirom.pengaduanbanjaranyar.adapters.ViewPageAdapter
import syaiful.kirom.pengaduanbanjaranyar.contracts.InformasiActivityContracts
import syaiful.kirom.pengaduanbanjaranyar.databinding.FragmentHomeBinding
import syaiful.kirom.pengaduanbanjaranyar.databinding.FragmentInformasiBinding
import syaiful.kirom.pengaduanbanjaranyar.models.Aduan
import syaiful.kirom.pengaduanbanjaranyar.models.Category
import syaiful.kirom.pengaduanbanjaranyar.presenters.InformasiActivityPresenter
import syaiful.kirom.pengaduanbanjaranyar.utilities.Constants


class InformasiFragment : Fragment() {
    private var _binding : FragmentInformasiBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInformasiBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarInformasi)
        binding.toolbarLayoutInformasi.title = "Daftar Aduan"

        val view = binding.root
        setupViewPage()
        intent()
        return view
    }

    private fun setupViewPage(){
        binding.viewPager.adapter = ViewPageAdapter(getChildFragmentManager())
        binding.TabLayout.setupWithViewPager(binding.viewPager)
    }

    private fun intent(){
        binding.fab.setOnClickListener {
            val intent = Intent(activity, CreateAduanActivity::class.java)
            activity?.startActivity(intent)
        }
    }

}