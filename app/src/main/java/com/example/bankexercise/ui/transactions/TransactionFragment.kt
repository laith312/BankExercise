package com.example.bankexercise.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankexercise.data.Account
import com.example.bankexercise.data.Card
import com.example.bankexercise.databinding.FragmentTransactionBinding
import org.koin.android.ext.android.inject

class TransactionFragment : Fragment() {

    private var _binding: FragmentTransactionBinding? = null

    private val binding get() = _binding!!

    private val viewModel: TransactionViewModel by inject<TransactionViewModel>()

    private lateinit var transactionAdapter: TransactionAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)

        viewModel.transactions.observe(viewLifecycleOwner) {
            binding.transactionRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                transactionAdapter = TransactionAdapter(it)
                adapter = transactionAdapter
            }
        }

        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        @Suppress("DEPRECATION") // The new method is not backward compatible.
        arguments?.getSerializable("account")?.let {
            if (it is Account) {
                binding.screenTitle.text = it.accountType.toString()
                binding.accountBalance.text = "$" + it.accountBalance.toString()
                binding.accountNumber.text = "XXXX-" + it.accountNumber.drop(5)
                viewModel.getAccountTransactions("123")
            }
        }

        @Suppress("DEPRECATION") // The new method is not backward compatible.
        arguments?.getSerializable("card")?.let {
            if (it is Card) {
                binding.screenTitle.text = it.cardName
                binding.accountBalance.visibility = View.GONE
                binding.accountNumber.text = "XXXX-" + it.cardId.drop(5)
                viewModel.getCardTransactions(it)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}