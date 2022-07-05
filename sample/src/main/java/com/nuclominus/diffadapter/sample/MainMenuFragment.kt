package com.nuclominus.diffadapter.sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nuclominus.diffadapter.sample.databinding.FragmentMainMenuBinding

class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {

    private val viewBinding by viewBinding(FragmentMainMenuBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnSimpleSample.setOnClickListener {
            findNavController().navigate(R.id.action_MainMenu_to_SimpleSample)
            requireContext().resources
        }

        viewBinding.btnMultiSample.setOnClickListener {
            findNavController().navigate(R.id.action_MainMenu_to_SampleMultiFragment)
            requireContext().resources
        }
    }

}