package com.example.bankexercise.ui.disclosures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bankexercise.databinding.FragmentAccountsBinding
import com.example.bankexercise.databinding.FragmentProfileBinding
import com.example.bankexercise.ui.accounts.AccountsViewModel
import org.koin.android.ext.android.inject

class DisclosuresFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    val viewModel: AccountsViewModel by inject<AccountsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root






        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}