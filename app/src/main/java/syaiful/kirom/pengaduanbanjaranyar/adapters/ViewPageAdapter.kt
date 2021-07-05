package syaiful.kirom.pengaduanbanjaranyar.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import syaiful.kirom.pengaduanbanjaranyar.fragments.ApproveFragment
import syaiful.kirom.pengaduanbanjaranyar.fragments.DeclineFragment
import syaiful.kirom.pengaduanbanjaranyar.fragments.WaitingFragment

//@Suppress("DEPRECATION")
class ViewPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val pages = listOf(
        WaitingFragment(),
        ApproveFragment(),
        DeclineFragment()
    )

    override fun getCount(): Int = pages.size

    override fun getItem(position: Int): Fragment {
        return pages[position] as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Waiting"
            1 -> "Approve"
            else -> "Decline"
        }
    }
}