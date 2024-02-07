package com.example.bankexercise.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankexercise.R
import com.example.bankexercise.data.AccountType
import com.example.bankexercise.databinding.FragmentAccountsBinding
import com.example.bankexercise.other.Constants
import org.koin.android.ext.android.inject

class AccountsFragment : Fragment() {

    private var _binding: FragmentAccountsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AccountsViewModel by inject<AccountsViewModel>()

    private lateinit var accountAdapter: AccountAdapter
    private lateinit var cardAdapter: CardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAccountsBinding.inflate(inflater, container, false)

        viewModel.accounts.observe(viewLifecycleOwner) {
            binding.accountsList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                accountAdapter = AccountAdapter(it)
                accountAdapter.setOnItemClickListener {
                    val bundle = Bundle().apply {
                        putSerializable("account", it)
                    }
                    findNavController().navigate(
                        R.id.action_navigation_accounts_to_transactionFragment,
                        bundle
                    )
                }
                adapter = accountAdapter
            }
        }

        viewModel.cards.observe(viewLifecycleOwner) {
            binding.cardsList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                cardAdapter = CardAdapter(it)
                cardAdapter.setOnItemClickListener {
                    val bundle = Bundle().apply {
                        putSerializable("card", it)
                    }
                    findNavController().navigate(
                        R.id.action_navigation_accounts_to_transactionFragment,
                        bundle
                    )
                }
                adapter = cardAdapter
            }
        }

        binding.transferToFamily.setOnClickListener {
            val bundle = Bundle().apply {
                val checkingAccount =
                    viewModel.accounts.value?.first { it.accountType == AccountType.CHECKING }

                val familyAccount =
                    viewModel.accounts.value?.first { it.accountType == AccountType.MY_FAMILY }

                putSerializable("from_account", checkingAccount)
                putSerializable("to_account", familyAccount)
            }
            findNavController().navigate(
                R.id.action_navigation_accounts_to_transferFragment,
                bundle
            )
        }

        binding.transferToSavings.setOnClickListener {
            val bundle = Bundle().apply {
                val checkingAccount =
                    viewModel.accounts.value?.filter { it.accountType == AccountType.CHECKING }
                        ?.first()
                val savingAccount =
                    viewModel.accounts.value?.filter { it.accountType == AccountType.SAVINGS }
                        ?.first()

                putSerializable(Constants.PARAM_KEY_FROM_ACCOUNT, checkingAccount)
                putSerializable(Constants.PARAM_KEY_TO_ACCOUNT, savingAccount)
            }


            findNavController().navigate(
                R.id.action_navigation_accounts_to_transferFragment,
                bundle
            )
        }

        binding.depositCheck.setOnClickListener {
            Toast.makeText(this.context, "Deposit Check not yet implemented", Toast.LENGTH_LONG)
                .show()
        }

        viewModel.getAccounts()
        viewModel.getCards()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}