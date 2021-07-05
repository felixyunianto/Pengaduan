package syaiful.kirom.pengaduanbanjaranyar.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import syaiful.kirom.pengaduanbanjaranyar.R
import syaiful.kirom.pengaduanbanjaranyar.databinding.ActivityMainBinding
import syaiful.kirom.pengaduanbanjaranyar.fragments.HomeFragment
import syaiful.kirom.pengaduanbanjaranyar.fragments.InformasiFragment
import syaiful.kirom.pengaduanbanjaranyar.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moveFragment()

    }

    private fun moveFragment() {
        setFragment(HomeFragment())
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> {
                    setFragment(HomeFragment())
                }
                R.id.menu_informasi -> {
                    setFragment(InformasiFragment())
                }
                R.id.menu_profil -> {
                    setFragment(ProfileFragment())
                }
            }

            true
        }
    }

    private fun setFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayoutMain, fragment)
            addToBackStack(null)
            commit()
        }
    }
}